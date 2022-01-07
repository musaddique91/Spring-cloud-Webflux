package com.infinily.product.db.product;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.infinily.commondb.db.CommonColumns;
import com.infinily.commondb.enums.STATUS;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDER")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class OrderEntity extends CommonColumns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private Long id;
	
	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;
	
	@Column(name = "DISCOUNT")
	private BigDecimal discount;
	
	@Column(name = "COUPEN_USED")
	private Long coupenUsed;
	
	@Column(name = "FINAL_AMOUNT")
	private BigDecimal finalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ORDER_STATUS")
	private STATUS status;
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "ORDER_DETAILS_MAP", 
			joinColumns = @JoinColumn(name = "ORDER_ID"), 
			inverseJoinColumns = @JoinColumn(name = "ORDER_DETAIL_ID")
	)
	private List<OrderDetailsEntity> orderDetails;
	
}
