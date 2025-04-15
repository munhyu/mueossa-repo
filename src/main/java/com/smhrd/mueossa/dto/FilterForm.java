package com.smhrd.mueossa.dto;

import com.smhrd.mueossa.entity.TbSurvey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterForm {
  // 구두, 부츠/워커, 스니커즈, 샌들/슬리퍼
  private String pGroup;

  // 편한
  private String ctComf;

  // 푹신한
  private String ctFluffy;

  // 가벼운
  private String ctLight;

  // 부드러운
  private String ctSoft;

  // 플랫한
  private String ctFlat;

  // 예쁜
  private String ctPretty;

  // 귀여운
  private String ctCute;

  // 깔끔한
  private String ctNeat;

  // 모던한
  private String ctModern;

  // 휘뚜루마뚜루
  private String ctHip;

  // 발볼이 넓은
  private String ctWide;

  // 발볼이 좁은
  private String ctNarrow;

  // 발볼이 보통
  private String ctStandard;

  // 가격이 착한
  private String ctCost;

  // 튼튼한
  private String ctStrong;

  // survey로 만드는 생성자
  public FilterForm(TbSurvey survey) {
    this.ctComf = survey.getComf();
    this.ctFluffy = survey.getFluffy();
    this.ctLight = survey.getLight();
    this.ctSoft = survey.getSoft();
    this.ctFlat = survey.getFlat();
    this.ctPretty = survey.getCute();
    this.ctNeat = survey.getNeat();
    this.ctModern = survey.getModern();
    this.ctHip = survey.getHip();
    this.ctWide = survey.getWide();
    this.ctNarrow = survey.getNarrow();
    this.ctStandard = survey.getStandard();
    this.ctCost = survey.getCost();
    this.ctStrong = survey.getStrong();
  }
}
