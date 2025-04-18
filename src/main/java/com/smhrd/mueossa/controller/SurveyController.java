package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.mueossa.model.Survey;
import com.smhrd.mueossa.service.SurveyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SurveyController {

  @Autowired
  private SurveyService surveySvc;

  // 취향선택 페이지 이동
  @GetMapping("/goPreference")
  public String goPreference(HttpSession session) {
    boolean isLogin = surveySvc.getLoginUserSurvey(session);

    if (isLogin) {
      return "redirect:/goLogin";
    } else {
      return "preference";
    }
  }

  // 설문조사 결과 저장
  @PostMapping("/surveyInsert")
  @Transactional
  public String surveyInsert(Survey survey, HttpSession session) {
    surveySvc.saveServey(survey, session);
    return "redirect:/goHome";
  }

}