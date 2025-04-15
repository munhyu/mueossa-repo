package com.smhrd.mueossa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.mueossa.Repository.ProdFeelCategoryRepository;
import com.smhrd.mueossa.Repository.ProdImageRepository;
import com.smhrd.mueossa.Repository.ProductRepository;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;
import com.smhrd.mueossa.model.FilterForm;

@Controller
public class FilterProductController {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProdImageRepository prodImageRepository;

  @Autowired
  private ProdFeelCategoryRepository prodFeelCategoryRepository;

  @GetMapping("/filterCategory")
  public String goFilterTest() {
    return "filterCategory";
  }

  /*
   * 상품 필터링 과정
   * 선택한 카테고리와 제품 카테고리가 일치하는 것만 조회
   * 카테고리 선택용 dto 만들기
   */
  @PostMapping("/filterProduct")
  public String goFilterProduct(FilterForm filterForm, Model model) {
    List<ProductAndCategoryDTO> productAndCategoryDTO = prodFeelCategoryRepository
        .findProductAndCategoryByFilter(filterForm);

    // 포맷팅
    for (ProductAndCategoryDTO product : productAndCategoryDTO) {
      getFormattedPrice(product);
    }
    model.addAttribute("prodCateList", productAndCategoryDTO);
    return "home";
  }

  // formatting method
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

    String brand = product.getPBrand();

    // pBrand의 최대 글자 수를 제한
    if (brand.length() > 8) {
      brand = brand.substring(0, 8) + "...";
    }
    //  브랜드 다시 set
    product.setPBrand(brand);
    // 글자 다시 설정
    product.setPName(name);

    // 문자열을 숫자로 변환
    int priceValue = Integer.parseInt(price);

    // 숫자를 세자리마다 콤마로 포맷팅
    String formattedPrice = String.format("%,d원", priceValue);

    // 포맷팅된 값을 다시 설정
    product.setPPrice(formattedPrice);
  }

}
