package com.smhrd.mueossa.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.smhrd.mueossa.Repository.ProdFeelCategoryRepository;
import com.smhrd.mueossa.Repository.ProdImageRepository;
import com.smhrd.mueossa.Repository.ProductRepository;
import com.smhrd.mueossa.Repository.WishlistRepository;
import com.smhrd.mueossa.dto.ProdFeelCategoryPercentileDTO;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;
import com.smhrd.mueossa.entity.TbProdImage;
import com.smhrd.mueossa.entity.TbProduct;
import com.smhrd.mueossa.entity.TbUser;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

  @Autowired
  private ProductRecommendationService productRecommendationService; // 서비스 주입

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProdImageRepository prodImageRepository;

  @Autowired
  private ProdFeelCategoryRepository prodFeelCategoryRepository;

  @Autowired
  private WishlistRepository wishlistRepository;

  /** 찜목록 불러와서 모델에 추가하는 메서드 */
  public void loadWishlistProducts(HttpSession session, Model model) {
    // 세션에서 찜 목록(Set<Integer>) 가져오기
    @SuppressWarnings("unchecked")
    Set<String> wishlistIds = (Set<String>) session.getAttribute("wishlist");

    List<ProductAndCategoryDTO> wishlistItems = new ArrayList<>();
    if (wishlistIds != null && !wishlistIds.isEmpty()) {
      // 찜 목록 ID로 상품 정보 조회
      wishlistItems = prodFeelCategoryRepository.findByPIdIn(wishlistIds);

      // 포맷팅 적용
      for (ProductAndCategoryDTO product : wishlistItems) {
        getFormattedPrice(product); // 가격, 이름 등 포맷팅 메서드 호출
      }
    }

    // 모델에 찜 목록 상품 추가
    model.addAttribute("prodCateList", wishlistItems);
    // 세션의 찜 목록 ID Set도 전달 (HTML에서 초기 아이콘 상태 확인용)
    model.addAttribute("wishlist", wishlistIds != null ? wishlistIds : Collections.emptySet());
  }

  /** 토글 버튼 누르면 찜목록 변경 */
  public ResponseEntity<Map<String, Object>> toggleWishlistResponse(Map<String, String> payload, HttpSession session) {
    Map<String, Object> response = new HashMap<>();
    // 로그인 체크

    try {
      // payload에서 pId 값(String) 가져오기
      String pId = payload.get("pId");

      // pId가 null이거나 비어있는지 확인 (추가적인 방어 코드)
      if (pId == null || pId.isEmpty()) {
        response.put("success", false);
        response.put("message", "상품 ID가 전송되지 않았습니다.");
        return ResponseEntity.badRequest().body(response); // 400 Bad Request
      }

      // 세션에서 찜 목록 가져오기 (없으면 새로 생성)
      @SuppressWarnings("unchecked") // 타입 캐스팅 경고 무시
      Set<String> wishlist = (Set<String>) session.getAttribute("wishlist");
      if (wishlist == null) {
        wishlist = new HashSet<>();
      }

      boolean added;

      // 로그인 체크
      if (session.getAttribute("user") == null) {
        // 찜 목록에 상품 ID(String) 추가 또는 제거
        if (wishlist.contains(pId)) {
          wishlist.remove(pId);
          added = false; // 제거됨
        } else {
          wishlist.add(pId);
          added = true; // 추가됨
        }
      } else {
        // 회원 아이디 가져오기
        String userId = ((TbUser) session.getAttribute("user")).getId();
        // 찜 목록에 상품 ID(String) 추가 또는 제거
        if (wishlist.contains(pId)) {
          wishlist.remove(pId);
          added = false; // 제거됨
          // DB에서 삭제
          wishlistRepository.qdeleteByIdAndPId(userId, pId);
        } else {
          wishlist.add(pId);
          added = true; // 추가됨
          // DB에 추가
          wishlistRepository.qinsertByIdAndPId(userId, pId);
        }

      }

      // 변경된 찜 목록을 세션에 다시 저장
      session.setAttribute("wishlist", wishlist);

      response.put("success", true);
      response.put("added", added); // 추가되었는지 여부 반환
      return ResponseEntity.ok(response);

    } catch (Exception e) {
      response.put("success", false);
      response.put("message", "서버 오류가 발생했습니다.");
      return ResponseEntity.internalServerError().body(response);
    }
  }

  /** goHome 기본적인 제품 정보 불러오기 */
  public void loadProductAndCategoryData(Model model) {
    List<ProductAndCategoryDTO> prodCateList = prodFeelCategoryRepository.findProductAndCategory();

    // 상품 리스트에서 가격, 이름 등 포맷팅 메서드
    for (ProductAndCategoryDTO product : prodCateList) {
      getFormattedPrice(product);
    }
    model.addAttribute("prodCateList", prodCateList);
  }

  /** productInfo 상품 정보 불러오기 + 유사 상품 추천 */
  public void loadProductInsights(String pId, Model model) {
    productRepository.findById(pId).ifPresent(product -> {
      getFormattedPrice(product);
      model.addAttribute("product", product);
    });

    List<TbProdImage> ProdImageList = prodImageRepository.findAllBypId(pId);
    model.addAttribute("ProdImageList", ProdImageList);

    prodFeelCategoryRepository.findById(pId).ifPresent(prodFeelCategory -> {
      model.addAttribute("prodFeelCategory", prodFeelCategory);
    });

    // 각 카테고리 점수가 하위 몇 %인지
    Optional<ProdFeelCategoryPercentileDTO> prodPercentOpt = prodFeelCategoryRepository.findCategoryPercentiles(pId);
    prodPercentOpt.ifPresent(prodPercent -> {
      model.addAttribute("prodPercent", prodPercent);
    });

    // 유사 상품 추천 로직 호출
    List<ProductAndCategoryDTO> similarProducts = productRecommendationService.findSimilarProducts(pId, 3); // 상위 5개
    model.addAttribute("similarProducts", similarProducts);
  }

  /** 제품 출력 전 포맷팅 ProductAndCategoryDTO */
  public void getFormattedPrice(ProductAndCategoryDTO product) {
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

  /** 제품 출력 전 포맷팅 TbProduct */
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