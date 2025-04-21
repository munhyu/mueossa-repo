package com.smhrd.mueossa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.mueossa.Repository.SurveyRepository;
import com.smhrd.mueossa.dto.FilterForm;
import com.smhrd.mueossa.entity.TbSurvey;
import com.smhrd.mueossa.entity.TbUser;
import com.smhrd.mueossa.model.Survey;

import jakarta.servlet.http.HttpSession;

@Service
public class SurveyService {

  @Autowired
  private SurveyRepository surveyRepository;

  /** 유저의 설문 정보 세션에 추가 */
  public boolean getLoginUserSurvey(HttpSession session) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    if (loginUser == null) {
      return true;
    } else {
      String userId = loginUser.getId();
      Optional<TbSurvey> surveyOptional = surveyRepository.findById(userId);
      if (surveyOptional.isPresent()) {
        session.setAttribute("survey", surveyOptional.get());
      } else {
        session.setAttribute("survey", null);
      }
      return false;
    }
  }

  /** 설문(survey) 결과 저장(기본 값 : N) */
  public void saveServey(Survey survey, HttpSession session) {
    TbUser loginUser = (TbUser) session.getAttribute("user");
    String loginId = loginUser.getId();
    TbSurvey tbSurvey = new TbSurvey(survey);
    tbSurvey.setId(loginId);
    if (tbSurvey.getComf() == null)
      tbSurvey.setComf("N");
    if (tbSurvey.getCost() == null)
      tbSurvey.setCost("N");
    if (tbSurvey.getCute() == null)
      tbSurvey.setCute("N");
    if (tbSurvey.getFlat() == null)
      tbSurvey.setFlat("N");
    if (tbSurvey.getFluffy() == null)
      tbSurvey.setFluffy("N");
    if (tbSurvey.getHip() == null)
      tbSurvey.setHip("N");
    if (tbSurvey.getLight() == null)
      tbSurvey.setLight("N");
    if (tbSurvey.getModern() == null)
      tbSurvey.setModern("N");
    if (tbSurvey.getNarrow() == null)
      tbSurvey.setNarrow("N");
    if (tbSurvey.getNeat() == null)
      tbSurvey.setNeat("N");
    if (tbSurvey.getPretty() == null)
      tbSurvey.setPretty("N");
    if (tbSurvey.getSoft() == null)
      tbSurvey.setSoft("N");
    if (tbSurvey.getStandard() == null)
      tbSurvey.setStandard("N");
    if (tbSurvey.getStrong() == null)
      tbSurvey.setStrong("N");
    if (tbSurvey.getWide() == null)
      tbSurvey.setWide("N");
    surveyRepository.save(tbSurvey);

    FilterForm filterForm = new FilterForm(tbSurvey);
    session.setAttribute("filteredProducts", filterForm);
  }

}