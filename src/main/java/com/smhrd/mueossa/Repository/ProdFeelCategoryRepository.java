package com.smhrd.mueossa.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.dto.FilterForm;
import com.smhrd.mueossa.dto.ProdCategoryAndGroupDTO;
import com.smhrd.mueossa.dto.ProdFeelCategoryPercentileDTO;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;
import com.smhrd.mueossa.entity.TbProdFeelCategory;

@Repository
public interface ProdFeelCategoryRepository extends JpaRepository<TbProdFeelCategory, String> {

	// id로 조회 - ProdFeelCategory와 Product를 join하여 pGroup정보를 같이 얻는 쿼리
	@Query("SELECT NEW com.smhrd.mueossa.dto.ProdCategoryAndGroupDTO(" +
			"   pfc.pdId, " +
			"   p.pGroup, " +
			"   pfc.ctComf, " +
			"   pfc.ctFluffy, " +
			"   pfc.ctLight, " +
			"   pfc.ctSoft, " +
			"   pfc.ctFlat, " +
			"   pfc.ctPretty, " +
			"   pfc.ctCute, " +
			"   pfc.ctNeat, " +
			"   pfc.ctModern, " +
			"   pfc.ctHip, " +
			"   pfc.ctWide, " +
			"   pfc.ctNarrow, " +
			"   pfc.ctStandard, " +
			"   pfc.ctCost, " +
			"   pfc.ctStrong) " + // sentiment는 Product에서 가져옴
			"FROM TbProduct p JOIN TbProdFeelCategory pfc ON p.pId = pfc.pdId where pfc.pdId = :pdId")
	Optional<ProdCategoryAndGroupDTO> qfindProductAndCategoryBypId(String pdId); // DTO로 반환

	// pGroup으로 조회 - ProdFeelCategory와 Product를 join하여 pGroup정보를 같이 얻는 쿼리
	@Query("SELECT NEW com.smhrd.mueossa.dto.ProdCategoryAndGroupDTO(" +
			"   pfc.pdId, " +
			"   p.pGroup, " +
			"   pfc.ctComf, " +
			"   pfc.ctFluffy, " +
			"   pfc.ctLight, " +
			"   pfc.ctSoft, " +
			"   pfc.ctFlat, " +
			"   pfc.ctPretty, " +
			"   pfc.ctCute, " +
			"   pfc.ctNeat, " +
			"   pfc.ctModern, " +
			"   pfc.ctHip, " +
			"   pfc.ctWide, " +
			"   pfc.ctNarrow, " +
			"   pfc.ctStandard, " +
			"   pfc.ctCost, " +
			"   pfc.ctStrong) " + // sentiment는 Product에서 가져옴
			"FROM TbProduct p JOIN TbProdFeelCategory pfc ON p.pId = pfc.pdId where p.pGroup = :pGroup")
	List<ProdCategoryAndGroupDTO> qfindProductAndCategoryBypGroup(String pGroup); // DTO로 반환

	// 상품 ID와 기준 점수를 받아 각 카테고리 점수가 기준 이상이면 'Y', 아니면 'N'을 반환
	// 아이디로 하나 반환
	@Query("SELECT NEW com.smhrd.mueossa.dto.ProductAndCategoryDTO(" +
			"   p.pId, " +
			"   p.pBrand, " +
			"   p.pName, " +
			"   p.pLikes, " +
			"   p.pRating, " +
			"   p.pDiscount, " +
			"   p.pPrice, " +
			"   p.pGender, " +
			"   p.pLink, " +
			"   p.pImage, " +
			"   p.pGroup, " +
			"   p.pType, " +
			"   p.sentiment, " +
			"   CASE WHEN pfc.ctComf >= 26.47 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFluffy >= 1.28 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctLight >= 2.4 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctSoft >= 0.48 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFlat >= 1.74 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctPretty >= 34.96 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCute >= 1.14 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNeat >= 2.19 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctModern >= 0.52 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctHip >= 17.95 THEN 'Y' ELSE 'N' END, " + // 휘뚜루마뚜루
			"   CASE WHEN pfc.ctWide >= 20.7 THEN 'Y' ELSE 'N' END, " + // 발볼이 넓은
			"   CASE WHEN pfc.ctNarrow >= 15.47 THEN 'Y' ELSE 'N' END, " + // 발볼이 좁은
			"   CASE WHEN pfc.ctStandard >= 23.66 THEN 'Y' ELSE 'N' END, " + // 발볼이 보통
			"   CASE WHEN pfc.ctCost >= 10.1 THEN 'Y' ELSE 'N' END, " + // 가격이 착한
			"   CASE WHEN pfc.ctStrong >= 3.12 THEN 'Y' ELSE 'N' END) " + // 튼튼한
			"FROM TbProduct p JOIN TbProdFeelCategory pfc ON p.pId = pfc.pdId where p.pId = :pId ORDER BY p.sentiment DESC")
	ProductAndCategoryDTO findProductAndCategoryByPId(String pId); // DTO로 반환

	// product와 productCategory를 join하여 제품 정보와 카테고리 점수를 가져오는 쿼리
	@Query("SELECT NEW com.smhrd.mueossa.dto.ProductAndCategoryDTO(" +
			"   p.pId, " +
			"   p.pBrand, " +
			"   p.pName, " +
			"   p.pLikes, " +
			"   p.pRating, " +
			"   p.pDiscount, " +
			"   p.pPrice, " +
			"   p.pGender, " +
			"   p.pLink, " +
			"   p.pImage, " +
			"   p.pGroup, " +
			"   p.pType, " +
			"   p.sentiment, " +
			"   CASE WHEN pfc.ctComf >= 26.47 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFluffy >= 1.28 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctLight >= 2.4 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctSoft >= 0.48 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFlat >= 1.74 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctPretty >= 34.96 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCute >= 1.14 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNeat >= 2.19 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctModern >= 0.52 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctHip >= 17.95 THEN 'Y' ELSE 'N' END, " + // 휘뚜루마뚜루
			"   CASE WHEN pfc.ctWide >= 20.7 THEN 'Y' ELSE 'N' END, " + // 발볼이 넓은
			"   CASE WHEN pfc.ctNarrow >= 15.47 THEN 'Y' ELSE 'N' END, " + // 발볼이 좁은
			"   CASE WHEN pfc.ctStandard >= 23.66 THEN 'Y' ELSE 'N' END, " + // 발볼이 보통
			"   CASE WHEN pfc.ctCost >= 10.1 THEN 'Y' ELSE 'N' END, " + // 가격이 착한
			"   CASE WHEN pfc.ctStrong >= 3.12 THEN 'Y' ELSE 'N' END) " + // 튼튼한
			"FROM TbProduct p JOIN TbProdFeelCategory pfc ON p.pId = pfc.pdId ORDER BY p.sentiment DESC")
	List<ProductAndCategoryDTO> findProductAndCategory(); // DTO로 반환

	// 제품 감성 카테고리 점수의 백분위수를 계산하는 쿼리
	@Query(value = "SELECT * FROM (" + // 서브쿼리 시작
			"    SELECT " +
			"        pd_id, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_comf DESC)) * 100 AS ctComfPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_fluffy DESC)) * 100 AS ctFluffyPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_light DESC)) * 100 AS ctLightPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_soft DESC)) * 100 AS ctSoftPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_flat DESC)) * 100 AS ctFlatPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_pretty DESC)) * 100 AS ctPrettyPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_cute DESC)) * 100 AS ctCutePercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_neat DESC)) * 100 AS ctNeatPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_modern DESC)) * 100 AS ctModernPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_hip DESC)) * 100 AS ctHipPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_wide DESC)) * 100 AS ctWidePercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_narrow DESC)) * 100 AS ctNarrowPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_standard DESC)) * 100 AS ctStandardPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_cost DESC)) * 100 AS ctCostPercentileRank, " +
			"        (1 - PERCENT_RANK() OVER (ORDER BY ct_strong DESC)) * 100 AS ctStrongPercentileRank " +
			"    FROM tb_prod_feel_category" + // 전체 테이블에서 백분위수 계산
			") AS ranked_data " + // 서브쿼리 결과
			"WHERE ranked_data.pd_id = :pd_id", // 서브쿼리 결과에서 필터링
			nativeQuery = true)
	Optional<ProdFeelCategoryPercentileDTO> findCategoryPercentiles(@Param("pd_id") String pd_id);

	// 상품명, 브랜드, 그룹, 타입 검색
	@Query("SELECT NEW com.smhrd.mueossa.dto.ProductAndCategoryDTO(" +
			"   p.pId, " +
			"   p.pBrand, " +
			"   p.pName, " +
			"   p.pLikes, " +
			"   p.pRating, " +
			"   p.pDiscount, " +
			"   p.pPrice, " +
			"   p.pGender, " +
			"   p.pLink, " +
			"   p.pImage, " +
			"   p.pGroup, " +
			"   p.pType, " +
			"   p.sentiment, " +
			"   CASE WHEN pfc.ctComf >= 26.47 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFluffy >= 1.28 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctLight >= 2.4 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctSoft >= 0.48 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFlat >= 1.74 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctPretty >= 34.96 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCute >= 1.14 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNeat >= 2.19 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctModern >= 0.52 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctHip >= 17.95 THEN 'Y' ELSE 'N' END, " + // 휘뚜루마뚜루
			"   CASE WHEN pfc.ctWide >= 20.7 THEN 'Y' ELSE 'N' END, " + // 발볼이 넓은
			"   CASE WHEN pfc.ctNarrow >= 15.47 THEN 'Y' ELSE 'N' END, " + // 발볼이 좁은
			"   CASE WHEN pfc.ctStandard >= 23.66 THEN 'Y' ELSE 'N' END, " + // 발볼이 보통
			"   CASE WHEN pfc.ctCost >= 10.1 THEN 'Y' ELSE 'N' END, " + // 가격이 착한
			"   CASE WHEN pfc.ctStrong >= 3.12 THEN 'Y' ELSE 'N' END) " + // 튼튼한
			"FROM TbProduct p JOIN TbProdFeelCategory pfc ON p.pId = pfc.pdId " +
			"WHERE p.pName LIKE CONCAT('%', :Keyword, '%') OR p.pBrand LIKE CONCAT('%', :Keyword, '%') OR p.pGroup LIKE CONCAT('%', :Keyword, '%') OR p.pType LIKE CONCAT('%', :Keyword, '%') ORDER BY p.sentiment DESC")
	List<ProductAndCategoryDTO> findProductAndCategoryByKeyword(@Param("Keyword") String Keyword); // DTO로 반환
	// ...existing code...
	// 상품명, 브랜드, 그룹, 타입 검색 (공백으로 구분된 키워드 중 하나라도 포함 시 검색)

	// 여러 pId로 ProductAndCategoryDTO 리스트 조회 (JPQL 사용)
	@Query("SELECT NEW com.smhrd.mueossa.dto.ProductAndCategoryDTO(" +
			"   p.pId, p.pBrand, p.pName, p.pLikes, p.pRating, p.pDiscount, p.pPrice, p.pGender, p.pLink, p.pImage, p.pGroup, p.pType, p.sentiment, "
			+
			"   CASE WHEN pfc.ctComf >= 26.47 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFluffy >= 1.28 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctLight >= 2.4 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctSoft >= 0.48 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFlat >= 1.74 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctPretty >= 34.96 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCute >= 1.14 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNeat >= 2.19 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctModern >= 0.52 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctHip >= 17.95 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctWide >= 14.14 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNarrow >= 10.36 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctStandard >= 19.8 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCost >= 10.1 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctStrong >= 3.12 THEN 'Y' ELSE 'N' END) " +
			"FROM TbProduct p JOIN TbProdFeelCategory pfc ON p.pId = pfc.pdId " +
			"WHERE p.pId IN :pIds ORDER BY p.sentiment DESC") // WHERE 절 추가
	List<ProductAndCategoryDTO> findByPIdIn(@Param("pIds") Set<String> pIds);

}
