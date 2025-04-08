package com.smhrd.mueossa.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	private String m_id;
	private String m_email;
	private String m_pw;
	private String m_gender;
	private Timestamp joined_at;
	private String m_role;

	// 기타 등등 추가

}
