package com.smhrd.mueossa.entity;

import com.smhrd.mueossa.model.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class MemberEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idx; // pk 값
	
	@Column(unique = true)
	@NonNull
	private String id;
	
	@Column(unique = true)
	@NonNull
	private String email;
	
	@Column(nullable = false)
	@NonNull
	private String pw;
	
// 기타 등등 추가
	
	public MemberEntity(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.pw = member.getPw();
	}

}
