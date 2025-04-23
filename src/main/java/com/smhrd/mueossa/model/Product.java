package com.smhrd.mueossa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private String pId;
	private String pBrand;
	private String pName;
	private int pLikes;
	private float pRating;
	private String pDiscount;
	private String pPrice;
	private String pGender;
	private String pLink;
	private String pImage;
	private String pGroup;
	private String pType;
	private Float sentiment;

}
