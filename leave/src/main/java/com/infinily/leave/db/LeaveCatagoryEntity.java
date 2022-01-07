package com.infinily.leave.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infinily.commondb.db.CommonColumns;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LEAVE_CATAGORY")
@Getter
@Setter
public class LeaveCatagoryEntity extends CommonColumns{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LEAVE_CATAGORY_ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ORGANIZATION_ID")
	private Long organizationId;
	
}
