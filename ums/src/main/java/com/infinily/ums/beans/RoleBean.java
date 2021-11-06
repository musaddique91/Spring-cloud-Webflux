package com.infinily.ums.beans;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleBean {
	private Long roleId;
	private Long organizationId;
	@NotEmpty(message = "role name is required")
	private String roleName;
	private List<String> resources;
	private List<Long> users;
}
