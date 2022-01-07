package com.infinily.product.db.product;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.infinily.commondb.db.CommonColumns;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDER_DETAILS")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class OrderDetailsEntity extends CommonColumns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_DETAIL_ID")
	private Long id;
	
	@Column(name = "PRODUCT_ID")
	private String productId;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	@Column(name = "PRICE")
	private BigDecimal price;
	
	@Column(name = "DISCOUNT")
	private BigDecimal discount;
	
	@Column(name = "FINAL_PRICE")
	private BigDecimal finalPrice;
}
