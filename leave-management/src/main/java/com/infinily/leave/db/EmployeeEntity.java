package com.infinily.leave.db;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

@Table("employee")
@Getter
@Setter
public class EmployeeEntity {
	@Id
	@Column("employee_id")
	private Long id;

	@Column("user_id")
	private Long userId;

	@Column("join_date")
	private LocalDateTime joinDate;

	@Column("birth_date")
	private LocalDate birthDate;

	@Column("leave_type")
	private String leaveType;

}
