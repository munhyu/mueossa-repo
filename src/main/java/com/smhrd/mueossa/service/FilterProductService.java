// filepath: c:\Users\smhrd\Desktop\mueossa-repo\src\main\java\com\smhrd\mueossa\service\ProductRecommendationService.java
package com.smhrd.mueossa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.mueossa.Repository.ProdFeelCategoryRepository;
import com.smhrd.mueossa.Repository.ProdImageRepository;
import com.smhrd.mueossa.Repository.ProductRepository;
import com.smhrd.mueossa.Repository.WishlistRepository;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;

@Service
public class FilterProductService {

  @Autowired
  private ProductRecommendationService productRecommendationService; // 서비스 주입

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProdImageRepository prodImageRepository;

  @Autowired
  private ProdFeelCategoryRepository prodFeelCategoryRepository;

  @Autowired
  private WishlistRepository wishlistRepository;

}