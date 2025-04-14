package com.smhrd.mueossa.Repository;

import java.util.List;
import java.util.Optional; // Optional 임포트

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.dto.ProdFeelCategoryPercentileDTO;
import com.smhrd.mueossa.dto.ProductAndCategoryDTO;
import com.smhrd.mueossa.entity.TbProdFeelCategory;

@Repository
public interface ProdFeelCategoryRepository extends JpaRepository<TbProdFeelCategory, String> {
	/**
	 * 상품 ID와 기준 점수를 받아 각 카테고리 점수가 기준 이상이면 'Y', 아니면 'N'을 반환합니다.
	 * 
	 * @return 각 카테고리 상태를 담은 DTO (Optional)
	 */
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
			"   CASE WHEN pfc.ctComf >= 37.57 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFluffy >= 3.43 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctLight >= 5.5 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctSoft >= 1.07 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFlat >= 3.2 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctPretty >= 45.62 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCute >= 3.41 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNeat >= 3.96 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctModern >= 1.09 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctHip >= 24.65 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctWide >= 20.7 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNarrow >= 15.47 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctStandard >= 23.66 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCost >= 17.41 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctStrong >= 5.35 THEN 'Y' ELSE 'N' END) " +
			"FROM TbProduct p JOIN TbProdFeelCategory pfc ON p.pId = pfc.pdId where p.pId = :pId")
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
			"   CASE WHEN pfc.ctComf >= 37.57 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFluffy >= 3.43 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctLight >= 5.5 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctSoft >= 1.07 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctFlat >= 3.2 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctPretty >= 45.62 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCute >= 3.41 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNeat >= 3.96 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctModern >= 1.09 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctHip >= 24.65 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctWide >= 20.7 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctNarrow >= 15.47 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctStandard >= 23.66 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctCost >= 17.41 THEN 'Y' ELSE 'N' END, " +
			"   CASE WHEN pfc.ctStrong >= 5.35 THEN 'Y' ELSE 'N' END) " +
			"FROM TbProduct p JOIN TbProdFeelCategory pfc ON p.pId = pfc.pdId")
	List<ProductAndCategoryDTO> findProductAndCategory(); // DTO로 반환

	@Query(value = "SELECT * FROM (" + // 서브쿼리 시작
			"    SELECT " +
			"        pd_id, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_comf DESC) * 100) AS ctComfPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_fluffy DESC) * 100) AS ctFluffyPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_light DESC) * 100) AS ctLightPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_soft DESC) * 100) AS ctSoftPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_flat DESC) * 100) AS ctFlatPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_pretty DESC) * 100) AS ctPrettyPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_cute DESC) * 100) AS ctCutePercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_neat DESC) * 100) AS ctNeatPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_modern DESC) * 100) AS ctModernPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_hip DESC) * 100) AS ctHipPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_wide DESC) * 100) AS ctWidePercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_narrow DESC) * 100) AS ctNarrowPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_standard DESC) * 100) AS ctStandardPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_cost DESC) * 100) AS ctCostPercentileRank, " +
			"        (PERCENT_RANK() OVER (ORDER BY ct_strong DESC) * 100) AS ctStrongPercentileRank " +
			"    FROM tb_prod_feel_category" + // 전체 테이블에서 백분위수 계산
			") AS ranked_data " + // 서브쿼리 결과
			"WHERE ranked_data.pd_id = :pd_id", // 서브쿼리 결과에서 필터링
			nativeQuery = true)
	Optional<ProdFeelCategoryPercentileDTO> findCategoryPercentiles(@Param("pd_id") String pd_id);

}
