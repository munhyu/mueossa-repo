package com.smhrd.mueossa.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.smhrd.mueossa.model.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

	@Column(unique = true)
	@NonNull
	@Id
	private String m_id;

	@Column(unique = true)
	@NonNull
	private String m_email;

	@Column
	@NonNull
	private String m_pw;

	@Column
	@NonNull
	private String m_gender;

	@Column
	@CreationTimestamp
	private Timestamp joined_at;

	@Column
	private String m_role;

	// 기타 등등 추가
	public MemberEntity(Member member) {
		this.m_id = member.getM_id();
		this.m_email = member.getM_email();
		this.m_pw = member.getM_pw();
		this.m_gender = member.getM_gender();
	}

}
