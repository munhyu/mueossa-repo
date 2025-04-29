package com.smhrd.mueossa.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.entity.TbUser;

@Repository
public interface UserRepository extends JpaRepository<TbUser, String> {

	TbUser findByIdAndPw(String id, String pw);

	Optional<TbUser> findByEmail(String email); // 이메일 중복 체크
	// 회원가입 시 비밀번호 SHA2로 암호화해서 저장하기

}
