package com.smhrd.mueossa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdCategoryAndGroupDTO {
  private String pdId;
  private String pGroup;

  private float ctComf;

  // 푹신한
  private float ctFluffy;

  // 가벼운
  private float ctLight;

  // 부드러운
  private float ctSoft;

  // 플랫한
  private float ctFlat;

  // 예쁜
  private float ctPretty;

  // 귀여운
  private float ctCute;

  // 깔끔한
  private float ctNeat;

  // 모던한
  private float ctModern;

  // 휘뚜루마뚜루
  private float ctHip;

  // 발볼이 넓은
  private float ctWide;

  // 발볼이 좁은
  private float ctNarrow;

  // 발볼이 보통
  private float ctStandard;

  // 가격이 착한
  private float ctCost;

  // 튼튼한
  private float ctStrong;
}
