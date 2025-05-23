package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.mueossa.service.FilterProductService;
import com.smhrd.mueossa.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FilterProductController {

  @Autowired
  private FilterProductService filterProdSvc;

  @Autowired
  private ProductService prodSvc;

  // 상품 검색 기능
  @GetMapping("/searchProduct")
  public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
    // 키워드가 null이거나 비어있으면 기본 페이지로 이동
    if (keyword == null || keyword.trim().isEmpty()) {
      return "redirect:/goHome";
    }
    filterProdSvc.searchProductsModelAdd(keyword, model);
    return "searchProduct";
  }

  // footer 카테고리 눌렀을 때 이동
  @GetMapping({ "/goCategory" })
  public String goFilterProduct(Model model, HttpSession session) {
    prodSvc.loadProductAndCategoryData(model);

    // filterProdSvc.loadProductAndCategoryData(model, session);
    return "filterCategory";
  }

}
