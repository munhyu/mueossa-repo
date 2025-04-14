package com.smhrd.mueossa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smhrd.mueossa.Repository.SurveyRepository;
import com.smhrd.mueossa.entity.TbSurvey;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.Survey;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

  @Autowired
  private SurveyRepository surveyRepository;

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

  // 카테고리 페이지 이동
  // 일단 임시로 바꿔둠 filterCategory로
  @GetMapping("/goCategory")
  public String goCategory() {
    return "filterCategory";
  }

  // 찜목록 페이지 이동
  @GetMapping("/goWishlist")
  public String goWishlist(HttpSession session) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    if (loginUser == null) {
      return "redirect:/goLogin";
    } else {
      return "wishlist";
    }
  }

  // 마이페이지 페이지 이동
  @GetMapping("/goMypage")
  public String goMypage() {
    return "mypage";
  }

  // 취향선택 페이지 이동
  @GetMapping("/goPreference")
  public String goPreference(HttpSession session, Model model, Survey survey) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    if (loginUser == null) {
      return "redirect:/goLogin";
    } else {
      String userId = loginUser.getId();
      surveyRepository.findById(userId);
      Optional<TbSurvey> surveyOptional = surveyRepository.findById(userId);
      System.out.println("설문???" + surveyOptional);
      model.addAttribute("surveyOptional", surveyOptional);
      return "preference";
    }
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
