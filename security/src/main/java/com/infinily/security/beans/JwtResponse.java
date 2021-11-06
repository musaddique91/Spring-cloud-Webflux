package com.infinily.security.beans;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class JwtResponse {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private String jwttoken;
	private Date expireOn;
	private String tokenType="Bearer";
	public JwtResponse(String jwttoken, Date expireOn) {
		super();
		this.jwttoken = jwttoken;
		this.expireOn = expireOn;
	}
	
	
}
