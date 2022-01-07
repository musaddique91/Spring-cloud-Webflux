package com.infinily.leave.db;

import java.time.LocalDateTime;

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
@Table(name = "EMPLOYEE")
@Getter
@Setter
public class EmployeeEntity extends CommonColumns{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID")
	private Long id;
	
	@Column(name = "USER_ID", nullable = false)
	private Long userId;
	
	@Column(name = "JOIN_DATE", nullable = false)
	private LocalDateTime joinDate;
	
	@Column(name = "BIRTH_DATE", nullable = false)
	private LocalDateTime birthDate;
	
	@Column(name = "LEAVE_TYPE")
	private String projectName;
	
}
