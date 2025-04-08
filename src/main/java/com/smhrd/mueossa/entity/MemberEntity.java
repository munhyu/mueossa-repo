package com.smhrd.mueossa.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.smhrd.mueossa.model.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "member_entity")
public class MemberEntity {

	@Column(name = "m_id")
	@NonNull
	@Id
	private String id;

	@Column(name = "m_email", unique = true)
	@NonNull
	private String email;

	@Column(name = "m_pw")
	@NonNull
	private String pw;

	@Column(name = "m_gender")
	@NonNull
	private String gender;

	@Column(name = "joined_at")
	@CreationTimestamp
	private Timestamp joinedAt;

	@Column(name = "m_role")
	private String role;

	// 기타 등등 추가
	public MemberEntity(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.pw = member.getPw();
		this.gender = member.getGender();
	}

}
