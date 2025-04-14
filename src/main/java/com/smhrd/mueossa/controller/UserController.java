package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.mueossa.Repository.SurveyRepository;
import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SurveyRepository surveyRepository;

  // 회원가입 처리
  @PostMapping("/userInsert")
  public String userInsert(User user, Model model) {
    TbUser tbUser = new TbUser(user);
    tbUser.setType("U");
    // SHA256 암호화
    tbUser.setPw(org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPw()));
    userRepository.save(tbUser);
    return "redirect:/goLogin";
  }

  // 로그인 처리
  @PostMapping("/userLogin")
  public String userLogin(User user, HttpSession session, Model model) {
    // SHA256 암호화
    String pw = org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPw());

    TbUser tbUser = userRepository.findByIdAndPw(user.getId(), pw);
    if (tbUser != null) {
      System.out.println("로그인 성공");
      session.setAttribute("user", tbUser); // 세션에 사용자 정보 저장
      return "redirect:/goHome"; // 메인 페이지로 리다이렉트
    } else {
      System.out.println("로그인 실패");
      model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
      return "home"; // 에러 메시지와 함께 main.html로 이동
    }
  }

  // 아이디 중복 체크
  @PostMapping("/checkId")
  @ResponseBody
  public boolean checkId(@RequestParam("id") String id) {
    return userRepository.findById(id).isEmpty();
  }

  // 이메일 중복 체크
  @PostMapping("/checkEmail")
  @ResponseBody
  public boolean checkEmail(@RequestParam("email") String email) {
    return userRepository.findByEmail(email).isEmpty();
  }

  // 로그아웃 처리
  @GetMapping("/userLogout")
  public String userLogout(HttpSession session) {
    session.invalidate();
    return "redirect:/goHome";
  }

  // 회원 정보 수정 처리
  @PostMapping("/userUpdate")
  public String userUpdate(User user, HttpSession session) {
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
    return "redirect:/goHome";
  }

  // 회원 탈퇴 처리
  @GetMapping("/userDelete")
  public String userDelete(HttpSession session) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    String userId = loginUser.getId();
    surveyRepository.deleteById(userId);
    userRepository.deleteById(userId);
    session.invalidate();
    return "redirect:/goHome";
  }

}