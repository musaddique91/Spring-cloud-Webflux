package com.infinily.commondb.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.infinily.commondb.enums.ExtendedAttributeCatagory;
import com.infinily.commondb.enums.ExtendedAttributeDataType;

@Entity
@Table(name = "EXTENDED_ATTRIBUTE")
@EntityListeners(AuditingEntityListener.class)
public class ExtendedAttributeEntity extends CommonColumns {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXTENDED_ATTRIBUTE_ID")
	private Long id;

	@Column(name = "EXTENDED_ATTRIBUTE_NAME")
	private String attributeName;

	@Column(name = "EXTENDED_ATTRIBUTE_DATATYPE")
	@Enumerated(EnumType.STRING)
	private ExtendedAttributeDataType attributeDataType;

	@Column(name = "EXTENDED_ATTRIBUTE_CATAGORY")
	@Enumerated(EnumType.STRING)
	private ExtendedAttributeCatagory attributeCatagory;
}
