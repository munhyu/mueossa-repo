package com.smhrd.mueossa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smhrd.mueossa.Repository.SurveyRepository;
import com.smhrd.mueossa.Repository.UserRepository;
import com.smhrd.mueossa.entity.TbSurvey;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.Survey;
import com.smhrd.mueossa.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

  @Autowired
  private SurveyRepository surveyRepository;

  // 기본 메인페이지
  @GetMapping("/")
  public String home() {
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
    return "login";
  }

  // 카테고리 페이지 이동
  @GetMapping("/goCategory")
  public String goCategory() {
    return "category";
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
  // @GetMapping("/goPreference")
  // public String goPreference(HttpSession session, Model model, Survey survey) {
  // TbUser loginUser = (TbUser) session.getAttribute("user");
  // if (loginUser == null) {
  // String userId = loginUser.getId();
  // surveyRepository.findById(userId);
  // return "redirect:/goLogin";
  // } else {
  // return "preference";
  // }
  // }
  @GetMapping("/goPreference")
  public String goPreference(HttpSession session, Model model) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
    if (loginUser == null) {
      return "redirect:/goLogin";
    }
    String userId = loginUser.getId();
    // 사용자 ID로 Survey 데이터 조회
    Optional<TbSurvey> surveyOptional = surveyRepository.findById(userId);
    // Survey 데이터가 존재하면 모델에 추가
    if (surveyOptional.isPresent()) {
      model.addAttribute("survey", surveyOptional.get());
    } else {
      model.addAttribute("survey", new TbSurvey()); // 빈 Survey 객체 추가
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
