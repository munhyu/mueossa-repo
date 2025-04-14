package com.smhrd.mueossa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.mueossa.Repository.ProdFeelCategoryRepository;
import com.smhrd.mueossa.Repository.ProdImageRepository;
import com.smhrd.mueossa.Repository.ProductRepository;
import com.smhrd.mueossa.dto.ProdFeelCategoryPercentileDTO;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;
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
      getFormattedPrice(product);
      model.addAttribute("product", product);
    });

    List<TbProdImage> ProdImageList = prodImageRepository.findAllBypId(pId);
    model.addAttribute("ProdImageList", ProdImageList);

    prodFeelCategoryRepository.findById(pId).ifPresent(prodFeelCategory -> {
      model.addAttribute("prodFeelCategory", prodFeelCategory);
    });

    // 각 카테고리 점수가 상위 %인지
    Optional<ProdFeelCategoryPercentileDTO> prodPercentOpt = prodFeelCategoryRepository.findCategoryPercentiles(pId);
    prodPercentOpt.ifPresent(prodPercent -> {
      model.addAttribute("prodPercent", prodPercent);
    });

    return "제품상세테스트";
  }

  // home 페이지 이동
  @GetMapping({ "/", "goHome" })
  public String goJoin(Model model) {

    List<ProductAndCategoryDTO> prodCateList = prodFeelCategoryRepository.findProductAndCategory();

    // pPrice를 세자리 수 마다 , 를 넣기
    // pName의 최대 글자 수를 제한
    for (ProductAndCategoryDTO product : prodCateList) {
      getFormattedPrice(product);
    }
    model.addAttribute("prodCateList", prodCateList);
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

  private void getFormattedPrice(TbProduct product) {
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
