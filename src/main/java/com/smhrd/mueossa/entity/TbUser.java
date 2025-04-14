package com.smhrd.mueossa.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.smhrd.mueossa.model.User;

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
@Table(name = "tb_user")
public class TbUser {

	@Column(name = "u_id")
	@NonNull
	@Id
	private String id;

	@Column(name = "u_email", unique = true)
	@NonNull
	private String email;

	@Column(name = "u_pw")
	@NonNull
	private String pw;

	@Column(name = "u_nick", length = 50)
	@NonNull
	private String nick;

	@Column(name = "u_gender", length = 1)
	@NonNull
	private String gender;

	@Column(name = "u_type", length = 1)
	private String type;

	@Column(name = "joined_at", nullable = true)
	@CreationTimestamp
	private Timestamp joinedAt;

	// 기타 등등 추가
	public TbUser(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.pw = user.getPw();
		this.nick = user.getNick();
		this.gender = user.getGender();
	}

}
