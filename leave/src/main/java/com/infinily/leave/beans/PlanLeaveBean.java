package com.infinily.leave.beans;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlanLeaveBean {
	private Long planLeaveId;
	private Long employeeId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
