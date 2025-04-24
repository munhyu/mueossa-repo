package com.smhrd.mueossa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.smhrd.mueossa.entity.TbUser;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

  // 문의 페이지 이동
  @GetMapping("/goInquiry")
  public String goInquiry() {
    return "inquiry"; // inquiry.html로 이동
  }

  // 문의 게시판 페이지 이동
  @GetMapping("/goInquiryList")
  public String goInquiryList() {
    return "inquiryList";
  }

  // 계정 찾는 페이지 이동
  @GetMapping("/goFindAccount")
  public String goFindAccount() {
    return "findAccount";
  }

  // modaltest로 이동
  @GetMapping("/goModalTest")
  public String goModalTest() {
    return "modaltest"; // modaltest.html로 이동
  }

  // 회원가입 페이지 이동
  @GetMapping("/goJoin")
  public String goJoin() {
    return "join"; // main.html로 이동
  }

  // 로그인 페이지 이동
  @GetMapping("/goLogin")
  public String goLogin() {
    return "login";
  }

  // 마이페이지 페이지 이동
  @GetMapping("/goMypage")
  public String goMypage() {
    return "mypage";
  }

  // 회원 정보 수정 페이지 이동
  @GetMapping("/goUpdate")
  public String goUpdate(HttpSession session) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    if (loginUser == null) {
      return "redirect:/goLogin";
    } else {
      return "update";
    }
  }

}
