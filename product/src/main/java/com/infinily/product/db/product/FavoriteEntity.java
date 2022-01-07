package com.infinily.product.db.product;

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
@Table(name = "FAVORITE_DETAILS")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class FavoriteEntity extends CommonColumns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FAVORITE_ID")
	private Long id;
	
	@Column(name = "PRODUCT_ID")
	private String productId;
	
	@Column(name = "CUSTOMER_ID")
	private Long custmorId;
	
}
