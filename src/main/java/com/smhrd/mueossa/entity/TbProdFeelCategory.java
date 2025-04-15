package com.smhrd.mueossa.entity;

import com.smhrd.mueossa.model.ProdFeelCategory;

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
@Table(name = "tb_prod_feel_category")
public class TbProdFeelCategory {

	@Column(name = "pd_id")
	@NonNull
	@Id
	private String pdId;

	@Column(name = "ct_comf")
	private Float ctComf;

	@Column(name = "ct_fluffy")
	private Float ctFluffy;

	@Column(name = "ct_light")
	private Float ctLight;

	@Column(name = "ct_soft")
	private Float ctSoft;

	@Column(name = "ct_flat")
	private Float ctFlat;

	@Column(name = "ct_pretty")
	private Float ctPretty;

	@Column(name = "ct_cute")
	private Float ctCute;

	@Column(name = "ct_neat")
	private Float ctNeat;

	@Column(name = "ct_modern")
	private Float ctModern;

	@Column(name = "ct_hip")
	private Float ctHip;

	@Column(name = "ct_wide")
	private Float ctWide;

	@Column(name = "ct_narrow")
	private Float ctNarrow;

	@Column(name = "ct_standard")
	private Float ctStandard;

	@Column(name = "ct_cost")
	private Float ctCost;

	@Column(name = "ct_strong")
	private Float ctStrong;

	public TbProdFeelCategory(ProdFeelCategory prodFeelCategory) {
		this.pdId = prodFeelCategory.getPdId();
		this.ctComf = prodFeelCategory.getCtComf();
		this.ctFluffy = prodFeelCategory.getCtFluffy();
		this.ctLight = prodFeelCategory.getCtLight();
		this.ctSoft = prodFeelCategory.getCtSoft();
		this.ctFlat = prodFeelCategory.getCtFlat();
		this.ctPretty = prodFeelCategory.getCtPretty();
		this.ctCute = prodFeelCategory.getCtCute();
		this.ctNeat = prodFeelCategory.getCtNeat();
		this.ctModern = prodFeelCategory.getCtModern();
		this.ctHip = prodFeelCategory.getCtHip();
		this.ctWide = prodFeelCategory.getCtWide();
		this.ctNarrow = prodFeelCategory.getCtNarrow();
		this.ctStandard = prodFeelCategory.getCtStandard();
		this.ctCost = prodFeelCategory.getCtCost();
		this.ctStrong = prodFeelCategory.getCtStrong();
	}
}
