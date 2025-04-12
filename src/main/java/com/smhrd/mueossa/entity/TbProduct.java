package com.smhrd.mueossa.entity;

import com.smhrd.mueossa.model.Product;

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
@Table(name = "tb_product")
public class TbProduct {

	@Column(name = "pd_id")
	@NonNull
	@Id
	private String pId;

	@Column(name = "pd_brand")
	private String pBrand;

	@Column(name = "pd_name")
	private String pName;

	@Column(name = "pd_likes")
	private int pLikes;

	@Column(name = "pd_rating")
	private float pRating;

	@Column(name = "pd_discount")
	private String pDiscount;

	@Column(name = "pd_price")
	private int pPrice;

	@Column(name = "pd_gender")
	private String pGender;

	@Column(name = "pd_link")
	private String pLink;

	@Column(name = "pd_image")
	private String pImage;

	@Column(name = "pd_group")
	private String pGroup;

	@Column(name = "pd_type")
	private String pType;

	public TbProduct(Product product) {
		this.pId = product.getPId();
		this.pBrand = product.getPBrand();
		this.pName = product.getPName();
		this.pLikes = product.getPLikes();
		this.pRating = product.getPRating();
		this.pDiscount = product.getPDiscount();
		this.pPrice = product.getPPrice();
		this.pGender = product.getPGender();
		this.pLink = product.getPLink();
		this.pImage = product.getPImage();
		this.pGroup = product.getPGroup();
		this.pType = product.getPType();
	}

}
