package com.infinily.ums.db.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.infinily.commondb.db.CommonColumns;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
public class RoleEntity extends CommonColumns {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ORGANIZATION_ID", nullable = false)
	private Long organizationId;
	
	@Transient
	private List<Long> user;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ROLE_RESOURCES",
	 	joinColumns= @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"),
	 	inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID", referencedColumnName = "RESOURCE_ID" )
	)
	private List<ResourcesEntity> resources;

}
