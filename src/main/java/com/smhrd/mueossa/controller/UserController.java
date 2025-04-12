package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // 회원가입 처리
  @PostMapping("/userInsert")
  public String userInsert(User user, Model model) {
    TbUser tbUser = new TbUser(user);
    tbUser.setType("U");
    // SHA256 암호화
    tbUser.setPw(org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPw()));
    userRepository.save(tbUser);
    return "redirect:/goPreference"; // 회원가입 후 로그인 페이지로 리다이렉트
  }

  // 로그인 처리
  @PostMapping("/userLogin")
  public String userLogin(User user, HttpSession session, Model model) {

    // 비밀번호
    System.out.println("로그인 시도: ID=" + user.getId() + ", PW=" + user.getPw());
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
    // Optional 처리
    return userRepository.findById(id).isEmpty(); // 중복되지 않은 경우 true 반환
  }

  // 이메일 중복 체크
  @PostMapping("/checkEmail")
  @ResponseBody
  public boolean checkEmail(@RequestParam("email") String email) {
    // return !userRepository.existsByEmail(email);
    return userRepository.findByEmail(email).isEmpty(); // 중복되지 않은 경우 true 반환
  }

  // 로그아웃 처리
  @GetMapping("/userLogout")
  public String userLogout(HttpSession session) {
    session.invalidate(); // 세션 무효화
    return "redirect:/goHome"; // 메인 페이지로 리다이렉트
  }

  // 회원 정보 수정 처리
  @PostMapping("/userUpdate")
  public String userUpdate(User user, HttpSession session) {
    TbUser tbUser = (TbUser) session.getAttribute("user"); // 세션에서 사용자 정보 가져오기
    if (tbUser != null) {
      tbUser.setEmail(user.getEmail());
      tbUser.setPw(org.apache.commons.codec.digest.DigestUtils.sha256Hex(user.getPw()));
      tbUser.setNick(user.getNick());
      userRepository.save(tbUser); // 수정된 정보 저장
      session.setAttribute("user", tbUser); // 세션에 업데이트된 사용자 정보 저장
    }
    return "redirect:/goMypage"; // 마이페이지로 리다이렉트
  }

}