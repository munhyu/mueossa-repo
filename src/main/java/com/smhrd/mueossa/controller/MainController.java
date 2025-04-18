package com.smhrd.mueossa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.smhrd.mueossa.Repository.SurveyRepository;
import com.smhrd.mueossa.entity.TbSurvey;
import com.smhrd.mueossa.entity.TbUser;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

  @Autowired
  private SurveyRepository surveyRepository;

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

  // 카테고리 FilterPdoductController에 있음

  // 찜목록 ProductController에 있음

  // 마이페이지 페이지 이동
  @GetMapping("/goMypage")
  public String goMypage() {
    return "mypage";
  }

  // 취향선택 페이지 이동
  @GetMapping("/goPreference")
  public String goPreference(HttpSession session) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    if (loginUser == null) {
      return "redirect:/goLogin";
    }
    String userId = loginUser.getId();
    Optional<TbSurvey> surveyOptional = surveyRepository.findById(userId);
    if (surveyOptional.isPresent()) {
      session.setAttribute("survey", surveyOptional.get());
    } else {
      session.setAttribute("survey", null);
    }
    return "preference";
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
