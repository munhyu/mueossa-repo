package com.smhrd.mueossa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.mueossa.Repository.ProdFeelCategoryRepository;
import com.smhrd.mueossa.Repository.ProdImageRepository;
import com.smhrd.mueossa.Repository.ProductRepository;
import com.smhrd.mueossa.entity.TbProdImage;
import com.smhrd.mueossa.entity.TbProduct;

@Controller
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProdImageRepository prodImageRepository;

  @Autowired
  private ProdFeelCategoryRepository prodFeelCategoryRepository;

  // 상품 아이디로 상품 상세 페이지 이동
  @GetMapping("/제품상세테스트")
  public String goProductList(@RequestParam("pId") String pId, Model model) {

    productRepository.findById(pId).ifPresent(product -> {
      model.addAttribute("product", product);
    });

    List<TbProdImage> ProdImageList = prodImageRepository.findAllBypId(pId);
    for (TbProdImage tbProdImage : ProdImageList) {
      System.out.println("ProdImageList: " + tbProdImage.getImgName());
    }

    model.addAttribute("ProdImageList", ProdImageList);

    prodFeelCategoryRepository.findById(pId).ifPresent(prodFeelCategory -> {
      model.addAttribute("prodFeelCategory", prodFeelCategory);
    });

    return "제품상세테스트";
  }

  // 상품 나열 - 기준없이 그냥 전체
  @GetMapping("/제품나열테스트")
  public String goProductList2(Model model) {

    List<TbProduct> productList = productRepository.findAll();
    model.addAttribute("productList", productList);

    return "제품나열테스트";
  }

}
