package com.smhrd.mueossa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.entity.TbUser;

@Repository
public interface EmailRepository extends JpaRepository<TbUser, String> {

	// 이메일 정보를 기반으로 사용자 정보를 조회하는 메소드
	TbUser findByEmail(String email); // 이메일 중복 체크
	// 이메일을 기반으로 사용자 정보를 조회하는 메소드

}
