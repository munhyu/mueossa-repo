package com.smhrd.mueossa.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.smhrd.mueossa.model.Inquiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_inquiry")
public class TbInquiry {

	@Id
	@Column(name = "idx", columnDefinition = "INT UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idx;

	@Column(name = "u_id", length = 50, nullable = false)
	private String uId;

	@Column(name = "title", length = 255, nullable = false)
	private String title;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column(name = "created_at", nullable = true, updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;

	public TbInquiry(Inquiry inquiry) {
		this.uId = inquiry.getUId();
		this.title = inquiry.getTitle();
		this.content = inquiry.getContent();
		if (inquiry.getIdx() != null) {
			this.idx = inquiry.getIdx();
		}
	}

}
