package com.smhrd.mueossa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

	private Integer wishIdx;
	private String pId;
	private String id;

	public Wishlist(String pId, String id) {
		this.pId = pId;
		this.id = id;
	}
}
