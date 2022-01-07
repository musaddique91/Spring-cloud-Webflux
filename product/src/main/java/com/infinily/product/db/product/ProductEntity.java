package com.infinily.product.db.product;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.infinily.commondb.db.CommonColumns;
import com.infinily.commondb.db.ExtendedAttributeValueEntity;
import com.infinily.commondb.db.AttachmentEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ProductEntity extends CommonColumns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Long id;

	@Column(name = "ORGANIZATION_ID", nullable = false)
	private Long organizationId;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "SALING_PRICE")
	private BigDecimal salingPrice;

	@Column(name = "QUANTITY")
	private int quantity;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "PRODUCT_EXDENDED_VALUES", 
			joinColumns = @JoinColumn(name = "PRODUCT_ID"), 
			inverseJoinColumns = @JoinColumn(name = "EXTENDED_ATTRIBUTE_VALUE_ID")
	)
	private List<ExtendedAttributeValueEntity> extendedAttributesData;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "PRODUCT_IMAGES", 
			joinColumns = @JoinColumn(name = "PRODUCT_ID"), 
			inverseJoinColumns = @JoinColumn(name = "ATTACHMENT_ID")
	)
	private List<AttachmentEntity> images;
}
