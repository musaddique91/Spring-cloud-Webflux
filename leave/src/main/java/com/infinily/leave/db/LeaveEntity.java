package com.infinily.leave.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infinily.commondb.db.CommonColumns;
import com.infinily.leave.enums.LeaveDayType;
import com.infinily.leave.enums.LeaveStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LEAVE")
@Getter
@Setter
public class LeaveEntity extends CommonColumns{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LEAVE_ID")
	private Long id;
	
	@Column(name = "EMPLOYEE_ID", nullable = false)
	private Long employeeId;
	
	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;
	
	@Column(name = "END_DATE", nullable = false)
	private LocalDateTime endDate;
	
	@Column(name = "LEAVE_TYPE")
	private Long leaveType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "LEAVE_DAY_TYPE")
	private LeaveDayType leaveDayType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "LEAVE_STATUS")
	private LeaveStatus status;
}
