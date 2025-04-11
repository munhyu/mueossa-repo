package com.smhrd.mueossa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  // 기본 메인페이지
  @GetMapping("/")
  public String home() {
    return "home";
  }

  // 메인 페이지 이동
  @GetMapping("/goHome")
  public String goHome() {
    return "home";
  }

  // 회원가입 페이지 이동
  @GetMapping("/goJoin")
  public String goJoin() {
    return "join"; // main.html로 이동
  }

  // 로그인 페이지 이동
  @GetMapping("/goLogin")
  public String goLogin() {
    return "login"; // main.html로 이동
  }

  // 카테고리 페이지 이동
  @GetMapping("/goCategory")
  public String goCategory() {
    return "category";
  }

  // 찜목록 페이지 이동
  @GetMapping("/goWishlist")
  public String goWishlist() {
    return "wishlist";
  }

  // 마이페이지 페이지 이동
  @GetMapping("/goMypage")
  public String goMypage() {
    return "mypage";
  }

  // 취향선택 페이지 이동
  @GetMapping("/goPreference")
  public String goPreference() {
    return "preference";
  }

  // 회원 정보 수정 페이지 이동
  @GetMapping("/goUpdate")
  public String goUpdate() {
    return "update";
  }

}
