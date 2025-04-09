package com.smhrd.mueossa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/")
  public String index() {
    return "main";
  }

  @GetMapping("/goIndex")
  public String goIndex() {
    return "index";
  }

}
