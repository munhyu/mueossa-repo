package com.smhrd.mueossa.Repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // Param 임포트 추가
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smhrd.mueossa.entity.TbWishlist;

@Repository
public interface WishlistRepository extends JpaRepository<TbWishlist, Integer> {
	// 로그인 로그아웃할 때 세션 초기화
	// 추가할 때 db에도 저장
	// 로그인 하면 db에서 가져와서 세션에 저장

	// 유저 id(String)와 pId(Integer)로 찾기
	@Query(value = "select * from tb_wishlist where u_id = :userId and pd_id = :pId", nativeQuery = true)
	Optional<TbWishlist> qfindByIdAndPId(String userId, String pId);

	@Transactional
	@Modifying
	@Query(value = "delete from tb_wishlist where u_id = :userId and pd_id = :pId", nativeQuery = true)
	void qdeleteByIdAndPId(String userId, String pId);

	// insert
	@Transactional
	@Modifying
	@Query(value = "insert into tb_wishlist (u_id, pd_id) values (:userId, :pId)", nativeQuery = true)
	void qinsertByIdAndPId(String userId, String pId);

	// 유저 id로 찜한 상품 ID(String) 리스트 가져오기
	@Query(value = "select pd_id from tb_wishlist where u_id = :userId", nativeQuery = true)
	Optional<Set<String>> qfindProductIdsByUserId(@Param("userId") String userId);
}
