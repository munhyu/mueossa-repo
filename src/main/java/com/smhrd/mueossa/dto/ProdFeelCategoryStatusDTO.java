package com.smhrd.mueossa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdFeelCategoryStatusDTO {
  private String pdId;
  private String comfStatus; // ctComf >= threshold ? 'Y' : 'N'
  private String fluffyStatus; // ctFluffy >= threshold ? 'Y' : 'N'
  private String lightStatus; // ctLight >= threshold ? 'Y' : 'N'
  private String softStatus; // ctSoft >= threshold ? 'Y' : 'N'
  private String flatStatus; // ctFlat >= threshold ? 'Y' : 'N'
  private String prettyStatus; // ctPretty >= threshold ? 'Y' : 'N'
  private String cuteStatus; // ctCute >= threshold ? 'Y' : 'N'
  private String neatStatus; // ctNeat >= threshold ? 'Y' : 'N'
  private String modernStatus; // ctModern >= threshold ? 'Y' : 'N'
  private String hipStatus; // ctHip >= threshold ? 'Y' : 'N'
  private String wideStatus; // ctWide >= threshold ? 'Y' : 'N'
  private String narrowStatus; // ctNarrow >= threshold ? 'Y' : 'N'
  private String standardStatus;// ctStandard >= threshold ? 'Y' : 'N'
  private String costStatus; // ctCost >= threshold ? 'Y' : 'N'
  private String strongStatus; // ctStrong >= threshold ? 'Y' : 'N'
}