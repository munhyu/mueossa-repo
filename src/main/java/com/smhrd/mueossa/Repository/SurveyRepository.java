package com.smhrd.mueossa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.entity.TbSurvey;

@Repository
public interface SurveyRepository extends JpaRepository<TbSurvey, String> {
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

	// 회원가입 시 비밀번호 SHA2로 암호화해서 저장하기

	// TbUser findById(String id);

}
