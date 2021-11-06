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
@Table(name = "RESOURCES")
@Getter
@Setter
public class ResourcesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RESOURCE_ID")
	private Long id;

	@Column(name = "CODE", unique = true)
	private String code;

}
