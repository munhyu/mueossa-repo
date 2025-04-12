package com.smhrd.mueossa.dto;

import com.smhrd.mueossa.entity.TbProduct;

import lombok.Data;

@Data
public class ProductWithCategoryDTO {
  private TbProduct product;
  private ProdFeelCategoryStatusDTO categoryStatus;

}
