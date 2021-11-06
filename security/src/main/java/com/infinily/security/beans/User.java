package com.infinily.security.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString @NoArgsConstructor
public class User {
	
	private static final long serialVersionUID = 1L;
	@Getter @Setter
	private String username;
	@Getter @Setter
	private String password;	
	@Getter @Setter
	private Boolean enabled;	
	@Getter @Setter
	private List<String> roles;
	@Getter @Setter
	private List<String> resources;
	@Getter @Setter
	private Long organizationId;
	public User(String username, String password, Boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
	
	
}