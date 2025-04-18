package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.model.User;
import com.smhrd.mueossa.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userSvc;

  // 로그인 처리
  @PostMapping("/userLogin")
  public String userLogin(User user, HttpSession session, Model model) {
    return userSvc.loginUser(user, session, model) ? "redirect:/goHome" : "login";
  }

  // 로그아웃 처리(세션 유저 정보 삭제)
  @GetMapping("/userLogout")
  public String userLogout(HttpSession session) {
    userSvc.removeUserSessionData(session);
    return "redirect:/goHome";
  }

  // 회원가입 처리
  @PostMapping("/userInsert")
  public String userInsert(User user, Model model) {
    userSvc.joinTbUser(user);
    return "redirect:/goLogin";
  }

  // 회원 정보 수정 처리
  @PostMapping("/userUpdate")
  public String userUpdate(User user, HttpSession session) {
    userSvc.updateUserData(user, session);
    return "redirect:/goHome";
  }

  // 비밀번호 수정
  @PostMapping("/userPwUpdate")
  public String userPwUpdate(@RequestParam("id") String id, @RequestParam("pw") String pw) {
    userSvc.updateUserPw(id, pw);
    return "redirect:/goLogin";
  }

  // 회원 탈퇴 처리
  @GetMapping("/userDelete")
  public String userDelete(HttpSession session) {
    // DB의 유저 정보 삭제
    userSvc.deleteUserRelatedData(session);
    // 세션에 저장된 유저 정보 삭제
    userSvc.removeUserSessionData(session);
    return "redirect:/goHome";
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

}