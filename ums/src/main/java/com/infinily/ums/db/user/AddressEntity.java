package com.infinily.ums.db.user;

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
@Table(name = "ADDRESS")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AddressEntity extends EntityAuditAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID")
	private Long id;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "STATE")
	private String state;

	@Column(name = "CITY")
	private String city;

	@Column(name = "ZIP_CODE")
	private String zipCode;

	@Column(name = "ADDRESS_1")
	private String address1;

	@Column(name = "ADDRESS_2")
	private String address2;
}
