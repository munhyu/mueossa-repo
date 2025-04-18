package com.smhrd.mueossa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.mueossa.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping({ "/", "goHome" })
  public String goHome(Model model) {

    productService.loadProductAndCategoryData(model);
    return "home";
  }

  // 상품 아이디로 상품 상세 페이지 이동
  @GetMapping("/productInfo")
  public String goProductList(@RequestParam("pId") String pId, Model model) {

    productService.loadProductInsights(pId, model);

    return "productInfo";
  }

  // 찜 상태 토글 API
  @PostMapping("/toggleWishlist")
  @ResponseBody
  public ResponseEntity<Map<String, Object>> toggleWishlist(@RequestBody Map<String, String> payload,
      HttpSession session) {

    return productService.toggleWishlistResponse(payload, session);
  }

  // 찜목록 페이지 이동 (기존 로직 수정)
  @GetMapping("/goWishlist")
  public String goWishlist(HttpSession session, Model model) { // Model 파라미터 추가
    productService.loadWishlistProducts(session, model);

    return "wishlist"; // wishlist.html 뷰 반환
  }

}
