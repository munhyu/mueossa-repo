package com.smhrd.mueossa.entity;

import com.smhrd.mueossa.model.Survey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "tb_survey")
public class TbSurvey {

	@Column(name = "u_id")
	@NonNull
	@Id
	private String id;

	@Column(name = "s_comf", length = 1)
	@NonNull
	private String comf;

	@Column(name = "s_cost", length = 1)
	@NonNull
	private String cost;

	@Column(name = "s_cute", length = 1)
	@NonNull
	private String cute;

	@Column(name = "s_flat", length = 1)
	@NonNull
	private String flat;

	@Column(name = "s_fluffy", length = 1)
	@NonNull
	private String fluffy;

	@Column(name = "s_hip", length = 1)
	@NonNull
	private String hip;

	@Column(name = "s_light", length = 1)
	@NonNull
	private String light;

	@Column(name = "s_modern", length = 1)
	@NonNull
	private String modern;

	@Column(name = "s_narrow", length = 1)
	@NonNull
	private String narrow;

	@Column(name = "s_neat", length = 1)
	@NonNull
	private String neat;

	@Column(name = "s_pretty", length = 1)
	@NonNull
	private String pretty;

	@Column(name = "s_soft", length = 1)
	@NonNull
	private String soft;

	@Column(name = "s_standard", length = 1)
	@NonNull
	private String standard;

	@Column(name = "s_strong", length = 1)
	@NonNull
	private String strong;

	@Column(name = "s_wide", length = 1)
	@NonNull
	private String wide;

	// 기타 등등 추가
	public TbSurvey(Survey survey) {
		this.id = survey.getId();
		this.comf = survey.getComf();
		this.cost = survey.getCost();
		this.cute = survey.getCute();
		this.flat = survey.getFlat();
		this.fluffy = survey.getFluffy();
		this.hip = survey.getHip();
		this.light = survey.getLight();
		this.modern = survey.getModern();
		this.narrow = survey.getNarrow();
		this.neat = survey.getNeat();
		this.pretty = survey.getPretty();
		this.soft = survey.getSoft();
		this.standard = survey.getStandard();
		this.strong = survey.getStrong();
		this.wide = survey.getWide();
	}
}