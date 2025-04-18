package com.smhrd.mueossa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.mueossa.dto.FilterForm;
import com.smhrd.mueossa.service.FilterProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FilterProductController {

  @Autowired
  private FilterProductService filterProdSvc;

  // 상품 검색 기능
  @GetMapping("/searchProduct")
  public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
    filterProdSvc.searchProductsModelAdd(keyword, model);
    return "home";
  }

  // 카테고리 페이지
  @PostMapping({ "/filterProduct" })
  public String goFilterProduct(FilterForm filterForm, Model model, HttpSession session) {
    filterProdSvc.loadAndSaveFilteredProductCategories(filterForm, model, session);
    return "filterCategory";
  }

  // footer 카테고리 눌렀을 때 이동
  @GetMapping({ "/goCategory" })
  public String goFilterProduct(Model model, HttpSession session) {

    filterProdSvc.loadProductAndCategoryData(model, session);
    return "filterCategory";
  }

}
