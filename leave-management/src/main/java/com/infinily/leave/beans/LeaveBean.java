package com.infinily.leave.beans;

import java.time.LocalDateTime;

import com.infinily.leave.enums.LeaveDayType;
import com.infinily.leave.enums.LeaveStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LeaveBean {
	private Long leaveId;
	private Long employeeId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Long leaveType;
	private LeaveDayType leaveDayType;
	private LeaveStatus status;
}
