package com.smhrd.mueossa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "tb_wishlist")
public class TbWishlist {

	@Id
	@Column(name = "wish_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wishIdx;

	@Column(name = "pd_id")
	@NonNull
	private String pId;

	@Column(name = "u_id")
	@NonNull
	private String id;

}
