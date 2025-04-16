package com.smhrd.mueossa.entity;

import com.smhrd.mueossa.model.ProdImage;

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
@Table(name = "tb_prod_image")
public class TbProdImage {

	@Column(name = "img_idx")
	@NonNull
	@Id
	private String imgIdx;

	@Column(name = "pd_id")
	private String pId;

	@Column(name = "img_name", length = 1000)
	private String imgName;

	public TbProdImage(ProdImage prodImage) {
		this.imgIdx = prodImage.getImgIdx();
		this.pId = prodImage.getPId();
		this.imgName = prodImage.getImgName();
	}
}
