package com.smhrd.mueossa.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.entity.TbInquiry;

@Repository
public interface InquiryRepository extends JpaRepository<TbInquiry, Integer> {

	@Query("SELECT i FROM TbInquiry i WHERE i.uId = :uId")
	List<TbInquiry> qfindByUId(@Param("uId") String uId);

}
