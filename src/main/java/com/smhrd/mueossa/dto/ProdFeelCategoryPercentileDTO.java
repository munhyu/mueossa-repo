package com.smhrd.mueossa.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdFeelCategoryPercentileDTO {
  private int pdId;
  private int ctComfPercentileRank;
  private int ctFluffyPercentileRank;
  private int ctLightPercentileRank;
  private int ctSoftPercentileRank;
  private int ctFlatPercentileRank;
  private int ctPrettyPercentileRank;
  private int ctCutePercentileRank;
  private int ctNeatPercentileRank;
  private int ctModernPercentileRank;
  private int ctHipPercentileRank;
  private int ctWidePercentileRank;
  private int ctNarrowPercentileRank;
  private int ctStandardPercentileRank;
  private int ctCostPercentileRank;
  private int ctStrongPercentileRank;

  public ProdFeelCategoryPercentileDTO(int pdId, Double ctComfPercentileRank, Double ctFluffyPercentileRank,
      Double ctLightPercentileRank, Double ctSoftPercentileRank, Double ctFlatPercentileRank,
      Double ctPrettyPercentileRank, Double ctCutePercentileRank, Double ctNeatPercentileRank,
      Double ctModernPercentileRank, Double ctHipPercentileRank, Double ctWidePercentileRank,
      Double ctNarrowPercentileRank, Double ctStandardPercentileRank, Double ctCostPercentileRank,
      Double ctStrongPercentileRank) {
    this.pdId = pdId;
    this.ctComfPercentileRank = ctComfPercentileRank.intValue();
    this.ctFluffyPercentileRank = ctFluffyPercentileRank.intValue();
    this.ctLightPercentileRank = ctLightPercentileRank.intValue();
    this.ctSoftPercentileRank = ctSoftPercentileRank.intValue();
    this.ctFlatPercentileRank = ctFlatPercentileRank.intValue();
    this.ctPrettyPercentileRank = ctPrettyPercentileRank.intValue();
    this.ctCutePercentileRank = ctCutePercentileRank.intValue();
    this.ctNeatPercentileRank = ctNeatPercentileRank.intValue();
    this.ctModernPercentileRank = ctModernPercentileRank.intValue();
    this.ctHipPercentileRank = ctHipPercentileRank.intValue();
    this.ctWidePercentileRank = ctWidePercentileRank.intValue();
    this.ctNarrowPercentileRank = ctNarrowPercentileRank.intValue();
    this.ctStandardPercentileRank = ctStandardPercentileRank.intValue();
    this.ctCostPercentileRank = ctCostPercentileRank.intValue();
    this.ctStrongPercentileRank = ctStrongPercentileRank.intValue();
  }
}