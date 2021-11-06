package com.infinily.ums.db.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.infinily.commondb.db.CommonColumns;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORGANIZATION")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class OrganizationEntity extends CommonColumns {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORGANIZATION_ID")
	private Long id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "ORGANIZATION_ADDRESS",
			joinColumns = @JoinColumn(name="ORGANIZATION_ID"),
			inverseJoinColumns = @JoinColumn(name="ADDRESS_ID")
	)
	private AddressEntity address;
}
