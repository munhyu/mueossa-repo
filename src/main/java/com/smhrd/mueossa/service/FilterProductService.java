// filepath: c:\Users\smhrd\Desktop\mueossa-repo\src\main\java\com\smhrd\mueossa\service\ProductRecommendationService.java
package com.smhrd.mueossa.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.smhrd.mueossa.Repository.ProdFeelCategoryRepository;
import com.smhrd.mueossa.dto.FilterForm;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class FilterProductService {

  @Autowired
  private ProdFeelCategoryRepository prodFeelCategoryRepository;

  @Autowired
  private ProductService productService;

  /** 세션의 카테고리 필터링 불러오기 */
  public void loadProductAndCategoryData(Model model, HttpSession session) {
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
      productService.getFormattedPrice(product);
    }
    model.addAttribute("prodCateList", prodCateList);
  }

  /**
   * 상품 필터링 과정
   * 선택한 카테고리와 제품 카테고리가 일치하는 것만 조회
   * 카테고리 선택용 dto 만들기
   */
  public void loadAndSaveFilteredProductCategories(FilterForm filterForm, Model model, HttpSession session) {
    List<ProductAndCategoryDTO> productAndCategoryDTO = prodFeelCategoryRepository
        .findProductAndCategoryByFilter(filterForm);

    // 포맷팅
    for (ProductAndCategoryDTO product : productAndCategoryDTO) {
      productService.getFormattedPrice(product);
    }

    model.addAttribute("prodCateList", productAndCategoryDTO);
    // 세션에 필터링된 상품 리스트 저장
    session.setAttribute("filteredProducts", filterForm);
  }

  /** 상품 검색해서 모델에 추가하는 메서드 */
  public void searchProductsModelAdd(String keyword, Model model) {
    List<ProductAndCategoryDTO> finalResults = new ArrayList<>();
    Set<String> processedProductIds = new HashSet<>(); // 중복 제거용 Set

    // 입력된 키워드를 공백 기준으로 분리 (연속된 공백도 처리)
    String[] individualKeywords = keyword.trim().split("\\s+");

    for (String individualKeyword : individualKeywords) {
      if (!individualKeyword.isEmpty()) { // 빈 키워드는 무시
        // 각 개별 키워드로 검색 수행
        List<ProductAndCategoryDTO> currentResults = prodFeelCategoryRepository
            .findProductAndCategoryByKeyword(individualKeyword);

        // 결과 병합 (중복 제거)
        for (ProductAndCategoryDTO product : currentResults) {
          if (processedProductIds.add(product.getPId())) { // Set에 pId 추가 성공 시 (중복 아님) True
            finalResults.add(product);
          }
        }
      }
    }

    // 최종 결과 리스트를 sentiment 기준으로 내림차순 정렬 (필요한 경우)
    finalResults.sort(
        Comparator.comparing(ProductAndCategoryDTO::getSentiment, Comparator.nullsLast(Comparator.reverseOrder())));

    // 결과 포맷팅
    for (ProductAndCategoryDTO product : finalResults) {
      productService.getFormattedPrice(product);
    }

    // 최종 결과 리스트를 모델에 추가
    model.addAttribute("prodCateList", finalResults);
  }
}