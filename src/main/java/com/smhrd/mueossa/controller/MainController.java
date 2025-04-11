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

  @GetMapping("/goIndex") 
  public String goIndex() {
    return "index";
  }

  // 메인 페이지 이동
  @GetMapping("goHome")
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
  
  
}
