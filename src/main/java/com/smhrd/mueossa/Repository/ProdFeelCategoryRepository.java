package com.smhrd.mueossa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.dto.ProdFeelCategoryStatusDTO; // DTO 임포트
import com.smhrd.mueossa.entity.TbProdFeelCategory;

import java.util.List;
import java.util.Optional; // Optional 임포트

@Repository
public interface ProdFeelCategoryRepository extends JpaRepository<TbProdFeelCategory, String> {
	// 기본적으로 제공하고 있는 CRUD
	// 1. findAll()
	// - select * from 테이블명;
	// 2. findBy(pk값)
	// - select * from 테이블명 where 컬럼명=pk값;
	// 3. save(매개변수 or entity)
	// - insert into 테이블명 values(매개변수 or entity)
	// 4. delete(매개변수)
	// - delete from 테이블명 where 컬럼병=매개변수;

	// 사용자 정의 메소드를 만들어야 할 때 이 클래스에서 정의한다.

	// select * from member where m_id =? and m_pw = ?

	// 반드시 카멜 표기법으로 메소드 명 짓기
	// find + (테이블명) + By + 컬럼명 + And + 컬럼명
	// BoardEntity findByTitleAndContent(String title, String content);
	//
	// @Transactional // insert, delete, update 시 만약 에러가 발생하면 rollback
	// @Modifying // insert, delete, update 시 무조건 써야하는 어노테이션
	// @Query("update BoardEntity b set b.count = b.count+1 where b.idx = :idx")
	// void count(@Param("idx") long idx);

	// 테이블 대신 entity 객체 사용, 컬럼 대신 필드 사용!
	// update member set count = count +1 where idx = 2

	/**
	 * 상품 ID와 기준 점수를 받아 각 카테고리 점수가 기준 이상이면 'Y', 아니면 'N'을 반환합니다.
	 * 
	 * @return 각 카테고리 상태를 담은 DTO (Optional)
	 */
	@Query("SELECT NEW com.smhrd.mueossa.dto.ProdFeelCategoryStatusDTO(" +
			"   pfc.pdId, " +
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
			"FROM TbProdFeelCategory pfc")
	List<ProdFeelCategoryStatusDTO> findCategoryStatusAll();

}
