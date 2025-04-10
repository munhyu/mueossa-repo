package com.smhrd.mueossa.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	private String id;
	private String email;
	private String pw;
	private String pwConfirm;
	private String gender;
	private Timestamp joinedAt;
	private String role;

	// 기타 등등 추가

}
