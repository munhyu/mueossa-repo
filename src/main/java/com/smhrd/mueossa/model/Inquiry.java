package com.smhrd.mueossa.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {

	private Integer idx;
	private String uId;
	private String title;
	private String content;
	private Timestamp createdAt;

}
