package com.smhrd.mueossa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAndCategoryDTO {
  private String pId;
  private String pBrand;
  private String pName;
  private int pLikes;
  private Float pRating;
  private String pDiscount;
  private String pPrice;
  private String pGender;
  private String pLink;
  private String pImage;
  private String pGroup;
  private String pType;

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
}
