package com.smhrd.mueossa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.smhrd.mueossa.Repository.ProdFeelCategoryRepository;
import com.smhrd.mueossa.Repository.ProdImageRepository;
import com.smhrd.mueossa.Repository.ProductRepository;
import com.smhrd.mueossa.Repository.WishlistRepository;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;

@Service
public class ProductService {

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

  public void loadProductAndCategoryData(Model model) {
    List<ProductAndCategoryDTO> prodCateList = prodFeelCategoryRepository.findProductAndCategory();

    // 상품 리스트에서 가격, 이름 등 포맷팅 메서드
    for (ProductAndCategoryDTO product : prodCateList) {
      getFormattedPrice(product);
    }
    model.addAttribute("prodCateList", prodCateList);
  }

  private void getFormattedPrice(ProductAndCategoryDTO product) {
    String price = product.getPPrice();
    String name = product.getPName();

    // pName의 _가 포함되어 있으면 _를 공백으로 변경
    // pName의 시작이 [이면 ]까지 제거
    if (name.startsWith("[")) {
      int endIndex = name.indexOf("]");
      if (endIndex != -1) {
        name = name.substring(endIndex + 1).trim();
      }
    }
    // pName의 최대 글자 수를 제한
    if (name.length() > 9) {
      name = name.substring(0, 9) + "...";
    }
    // 글자 다시 설정
    product.setPName(name);

    String brand = product.getPBrand();

    // pBrand의 최대 글자 수를 제한
    if (brand.length() > 8) {
      brand = brand.substring(0, 8) + "...";
    }
    // 브랜드 다시 set
    product.setPBrand(brand);

    // 문자열을 숫자로 변환
    int priceValue = Integer.parseInt(price);

    // 숫자를 세자리마다 콤마로 포맷팅
    String formattedPrice = String.format("%,d원", priceValue);

    // 포맷팅된 값을 다시 설정
    product.setPPrice(formattedPrice);
  }
}