package com.infinily.ums.db.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_PASSWORD")
@Getter
@Setter
public class PasswordEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PASSWORD_ID")
	private Long id;

	@Column(name = "USER_ID")
	private boolean userId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FLG_TEMP_PASSWORD")
	private boolean tempPassword;

}
