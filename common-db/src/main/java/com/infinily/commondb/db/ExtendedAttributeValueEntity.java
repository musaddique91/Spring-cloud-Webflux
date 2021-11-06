package com.infinily.commondb.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "EXTENDED_ATTRIBUTE_VALUE")
@EntityListeners(AuditingEntityListener.class)
public class ExtendedAttributeValueEntity extends CommonColumns {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXTENDED_ATTRIBUTE_VALUE_ID")
	private Long id;

	@Column(name = "EXTENDED_ATTRIBUTE_VALUE")
	private String attributeValue;

	@Column(name = "EXTENDED_ATTRIBUTE_ID", nullable = false)
	private Long extendedAttributeId;
}
