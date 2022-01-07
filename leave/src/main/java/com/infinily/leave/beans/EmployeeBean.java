package com.infinily.leave.beans;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeBean {
	private Long employeeId;
	@NotEmpty(message = "first name is required")
	private String firstName;
	private String lastName;
	private String password;
	@NotEmpty(message = "email is required")
	private String email;
	private String phone;
	private String language;
	private Long roleId;
	private Long organizationId;
}
