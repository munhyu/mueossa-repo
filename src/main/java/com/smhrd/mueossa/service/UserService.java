package com.smhrd.mueossa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.smhrd.mueossa.Repository.SurveyRepository;
import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.Repository.WishlistRepository;
import com.smhrd.mueossa.dto.FilterForm;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.User;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SurveyRepository surveyRepository;

  @Autowired
  private WishlistRepository wishlistRepository;

  /** DB의 유저 정보 삭제 */
  public void deleteUserRelatedData(HttpSession session) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    String userId = loginUser.getId();
    surveyRepository.deleteById(userId);
    wishlistRepository.qdeleteById(userId);
    userRepository.deleteById(userId);
  }

  /** 유저 정보 수정 */
  public void updateUserData(User user, HttpSession session) {
    TbUser tbUser = new TbUser(user);
    TbUser loginUser = (TbUser) session.getAttribute("user");
    String userId = loginUser.getId();
    String pw = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPw());
    tbUser.setId(userId);
    tbUser.setEmail(user.getEmail());
    tbUser.setNick(user.getNick());
    tbUser.setPw(pw);
    tbUser.setGender(loginUser.getGender());
    tbUser.setJoinedAt(loginUser.getJoinedAt());
    tbUser.setType(loginUser.getType());
    userRepository.save(tbUser);
  }

  /** 세션에 유저 정보 삭제 */
  public void removeUserSessionData(HttpSession session) {
    session.removeAttribute("user");
    session.removeAttribute("filteredProducts"); // 설문조사 정보 삭제
    session.removeAttribute("wishlist"); // 찜 목록 삭제
  }

  /** 로그인 세션에 유저 정보 set */
  public boolean loginUser(User user, HttpSession session, Model model) {
    // SHA256 암호화
    String pw = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPw());

    TbUser tbUser = userRepository.findByIdAndPw(user.getId(), pw);
    if (tbUser != null) {
      System.out.println("로그인 성공");
      session.setAttribute("user", tbUser); // 세션에 사용자 정보 저장

      // 로그인한 사용자의 설문조사 정보 가져오기
      surveyRepository.findById(tbUser.getId()).ifPresent(survey -> {
        FilterForm filterForm = new FilterForm(survey);
        session.setAttribute("filteredProducts", filterForm); // 세션에 설문조사 정보 저장
      });

      // 로그인한 사용자의 찜 목록 가져오기
      wishlistRepository.qfindProductIdsByUserId(tbUser.getId()).ifPresent(wishlist -> {
        session.setAttribute("wishlist", wishlist); // 세션에 찜 목록 저장
      });
      return true; // 메인 페이지로 리다이렉트
    } else {
      System.out.println("로그인 실패");
      model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
      return false; // 에러 메시지와 함께 main.html로 이동
    }
  }

  /** 회원가입 db에 유저추가 */
  public void joinTbUser(User user) {
    TbUser tbUser = new TbUser(user);
    tbUser.setType("U");
    // SHA256 암호화
    tbUser.setPw(org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPw()));
    userRepository.save(tbUser);
  }

  /** id와 pw를 입력받아 새로운 pw로 수정 */
  public void updateUserPw(String id, String pw) {
    TbUser tbUser = userRepository.findById(id).orElse(null);
    if (tbUser != null) {
      tbUser.setPw(org.apache.commons.codec.digest.DigestUtils.sha256Hex(pw));
      userRepository.save(tbUser);
    } else {
      System.out.println("사용자를 찾을 수 없습니다.");
    }
  }
}