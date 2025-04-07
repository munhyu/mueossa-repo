package com.smhrd.mueossa.model;

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
	
	// 기타 등등 추가
	
}
