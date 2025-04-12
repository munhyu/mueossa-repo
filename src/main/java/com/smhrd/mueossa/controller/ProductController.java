package com.smhrd.mueossa.controller;

import java.util.ArrayList;
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
import com.smhrd.mueossa.dto.ProdFeelCategoryStatusDTO;
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
    model.addAttribute("ProdImageList", ProdImageList);

    // 카테고리 점수 처리 로직 추가
    List<String> highScoringCategories = new ArrayList<>();

    prodFeelCategoryRepository.findById(pId).ifPresent(prodFeelCategory -> {
      model.addAttribute("prodFeelCategory", prodFeelCategory);

      // 각 카테고리 점수 확인 및 기준 점수 이상인 경우 이름 추가
      // (TbProdFeelCategory 엔티티에 해당 필드와 getter가 있다고 가정)
      if (prodFeelCategory.getCtComf() >= 37.57) {
        highScoringCategories.add("편하다");
      }
      if (prodFeelCategory.getCtFluffy() >= 3.43) {
        highScoringCategories.add("푹신한");
      }
      if (prodFeelCategory.getCtSoft() >= 1.07) {
        highScoringCategories.add("부드러운");
      }
      if (prodFeelCategory.getCtPretty() >= 45.62) {
        highScoringCategories.add("이쁜");
      }
      if (prodFeelCategory.getCtCute() >= 3.41) {
        highScoringCategories.add("귀여운");
      }
      if (prodFeelCategory.getCtNeat() >= 3.96) {
        highScoringCategories.add("깔끔한");
      }
      if (prodFeelCategory.getCtModern() >= 1.09) {
        highScoringCategories.add("모던한");
      }
      if (prodFeelCategory.getCtHip() >= 24.65) {
        highScoringCategories.add("휘뚜루마뚜루");
      }
      if (prodFeelCategory.getCtWide() >= 20.7) {
        highScoringCategories.add("발볼이 넓은");
      }
      if (prodFeelCategory.getCtNarrow() >= 15.47) {
        highScoringCategories.add("발볼이 좁은");
      }
      if (prodFeelCategory.getCtStandard() >= 23.66) {
        highScoringCategories.add("발볼이 보통");
      }
      if (prodFeelCategory.getCtCost() >= 17.41) {
        highScoringCategories.add("가격이 착한");
      }
      if (prodFeelCategory.getCtStrong() >= 5.35) {
        highScoringCategories.add("튼튼한");
      }

    });

    // 기준 점수 이상인 카테고리 이름 리스트를 모델에 추가
    model.addAttribute("highScoringCategories", highScoringCategories);

    return "제품상세테스트";
  }

  // 상품 나열 - 기준없이 그냥 전체
  @GetMapping("/제품나열테스트")
  public String goProductList2(Model model) {

    List<TbProduct> productList = productRepository.findAll();

    // pPrice를 세자리 수 마다 , 를 넣기
    // pName의 최대 글자 수를 제한
    for (TbProduct product : productList) {
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

    model.addAttribute("productList", productList);

    // dto로 카테고리 점수 가져오기
    List<ProdFeelCategoryStatusDTO> categoryList = prodFeelCategoryRepository.findCategoryStatusAll();

    model.addAttribute("categoryList", categoryList);

    return "제품나열테스트";
  }

}
