package com.infinily.commondb.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CATAGORY")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BrandEntity extends CommonColumns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATAGORY_ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;
}
