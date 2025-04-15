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
import com.smhrd.mueossa.dto.FilterForm;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

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

  @GetMapping("/searchProduct")
  public String getMethodName(@RequestParam("keyword") String keyword, Model model) {
    List<ProductAndCategoryDTO> productAndCategoryDTO = prodFeelCategoryRepository
        .findProductAndCategoryByKeyword(keyword);
    for (ProductAndCategoryDTO product : productAndCategoryDTO) {
      getFormattedPrice(product);
    }
    model.addAttribute("prodCateList", productAndCategoryDTO);
    return "home";
  }

  /*
   * 상품 필터링 과정
   * 선택한 카테고리와 제품 카테고리가 일치하는 것만 조회
   * 카테고리 선택용 dto 만들기
   */
  @PostMapping({ "/filterProduct" })
  public String goFilterProduct(FilterForm filterForm, Model model, HttpSession session) {
    List<ProductAndCategoryDTO> productAndCategoryDTO = prodFeelCategoryRepository
        .findProductAndCategoryByFilter(filterForm);

    // 포맷팅
    for (ProductAndCategoryDTO product : productAndCategoryDTO) {
      getFormattedPrice(product);
    }

    model.addAttribute("prodCateList", productAndCategoryDTO);
    // 세션에 필터링된 상품 리스트 저장
    session.setAttribute("filteredProducts", filterForm);
    return "filterCategory";
  }

  @GetMapping({ "/goCategory" })
  public String goFilterProduct(Model model, HttpSession session) {

    List<ProductAndCategoryDTO> prodCateList = null;
    // 세션에서 필터링된 상품 리스트가 있으면 그걸로 조회
    if (session.getAttribute("filteredProducts") != null) {
      FilterForm filterForm = (FilterForm) session.getAttribute("filteredProducts");
      prodCateList = prodFeelCategoryRepository.findProductAndCategoryByFilter(filterForm);
    } else {
      // 필터링된 상품 리스트가 없으면 전체 상품 조회
      prodCateList = prodFeelCategoryRepository.findProductAndCategory();
    }

    // 포맷팅
    for (ProductAndCategoryDTO product : prodCateList) {
      getFormattedPrice(product);
    }
    model.addAttribute("prodCateList", prodCateList);
    return "filterCategory";
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
    if (name.length() > 10) {
      name = name.substring(0, 10) + "...";
    }
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
