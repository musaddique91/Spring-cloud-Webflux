package com.infinily.ums.beans;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBean {
	private Long userId;
	@NotEmpty(message = "first name is required")
	private String firstName;
	private String lastName;
	@NotEmpty(message = "username is required")
	private String username;
	private String password;
	@NotEmpty(message = "email is required")
	private String email;
	private String phone;
	private String language;
	private Long roleId;
	private Long organizationId;
}
