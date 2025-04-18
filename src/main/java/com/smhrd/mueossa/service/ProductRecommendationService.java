// filepath: c:\Users\smhrd\Desktop\mueossa-repo\src\main\java\com\smhrd\mueossa\service\ProductRecommendationService.java
package com.smhrd.mueossa.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.mueossa.Repository.ProdFeelCategoryRepository;
import com.smhrd.mueossa.dto.ProdCategoryAndGroupDTO;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;

@Service
public class ProductRecommendationService {

  @Autowired
  private ProdFeelCategoryRepository prodFeelCategoryRepository; // TbProdFeelCategory Repository

  public List<ProductAndCategoryDTO> findSimilarProducts(String targetProductId, int limit) {
    // 1. 기준 상품 정보 조회 (TbProdFeelCategory 엔티티 조회로 수정)

    Optional<ProdCategoryAndGroupDTO> targetProductOpt = prodFeelCategoryRepository
        .qfindProductAndCategoryBypId(targetProductId);
    if (targetProductOpt.isEmpty()) {
      return Collections.emptyList(); // 기준 상품 없으면 빈 리스트 반환
    }
    // ProdCategoryAndGroupDTO 타입으로 기준 상품 정보 저장
    ProdCategoryAndGroupDTO targetProduct = targetProductOpt.get();

    // 2. 전체 상품 정보 조회
    // pGroup이 일치하는 상품 조회
    String pGroup = targetProductOpt.isPresent() ? targetProductOpt.get().getPGroup() : "";
    List<ProdCategoryAndGroupDTO> allProducts = prodFeelCategoryRepository.qfindProductAndCategoryBypGroup(pGroup);

    // 3. 거리 계산 (상품 ID(String)와 거리 매핑)
    Map<String, Double> distances = new HashMap<>();
    for (ProdCategoryAndGroupDTO product : allProducts) {
      if (product.getPdId().equals(targetProductId)) {
        continue; // 기준 상품 제외
      }
      double distance = calculateEuclideanDistance(targetProduct, product);
      distances.put(product.getPdId(), distance);
    }

    // 4. 거리 기준 정렬 및 상위 N개 ID(String) 선택
    List<String> similarProductIds = distances.entrySet().stream()
        .sorted(Map.Entry.comparingByValue()) // 거리 오름차순 정렬
        .limit(limit) // 상위 N개
        .map(Map.Entry::getKey) // 상품 ID(String) 추출
        .collect(Collectors.toList());

    // 5. 선택된 ID(String)로 상품 상세 정보 조회 (ProductAndCategoryDTO 사용)
    if (similarProductIds.isEmpty()) {
      return Collections.emptyList();
    }
    // ProdFeelCategoryRepository에 findByPIdIn 메서드가 ProductAndCategoryDTO를 반환하고
    // String Set을 받는다고 가정
    List<ProductAndCategoryDTO> similarProducts = prodFeelCategoryRepository
        .findByPIdIn(new HashSet<>(similarProductIds)); // Set<String>으로 전달

    // 거리 순서대로 다시 정렬 (p.getPId()는 String)
    similarProducts.sort(Comparator.comparingDouble(p -> distances.get(p.getPId())));

    // 포맷팅 메서드 호출 (가격, 이름 등)
    for (ProductAndCategoryDTO product : similarProducts) {
      getFormattedPrice(product); // 가격, 이름 등 포맷팅 메서드 호출
    }

    return similarProducts;
  }

  // 유클리드 거리 계산
  private double calculateEuclideanDistance(ProdCategoryAndGroupDTO p1, ProdCategoryAndGroupDTO p2) {
    double sumOfSquares = 0;
    sumOfSquares += Math.pow(p1.getCtComf() - p2.getCtComf(), 2);
    sumOfSquares += Math.pow(p1.getCtFluffy() - p2.getCtFluffy(), 2);
    sumOfSquares += Math.pow(p1.getCtLight() - p2.getCtLight(), 2);
    sumOfSquares += Math.pow(p1.getCtSoft() - p2.getCtSoft(), 2);
    sumOfSquares += Math.pow(p1.getCtFlat() - p2.getCtFlat(), 2);
    sumOfSquares += Math.pow(p1.getCtPretty() - p2.getCtPretty(), 2);
    sumOfSquares += Math.pow(p1.getCtCute() - p2.getCtCute(), 2);
    sumOfSquares += Math.pow(p1.getCtNeat() - p2.getCtNeat(), 2);
    sumOfSquares += Math.pow(p1.getCtModern() - p2.getCtModern(), 2);
    sumOfSquares += Math.pow(p1.getCtHip() - p2.getCtHip(), 2);
    sumOfSquares += Math.pow(p1.getCtWide() - p2.getCtWide(), 2);
    sumOfSquares += Math.pow(p1.getCtNarrow() - p2.getCtNarrow(), 2);
    sumOfSquares += Math.pow(p1.getCtStandard() - p2.getCtStandard(), 2);
    sumOfSquares += Math.pow(p1.getCtCost() - p2.getCtCost(), 2);
    sumOfSquares += Math.pow(p1.getCtStrong() - p2.getCtStrong(), 2);
    return Math.sqrt(sumOfSquares);
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