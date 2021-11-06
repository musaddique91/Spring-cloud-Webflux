package com.infinily.common.constant;

import lombok.Getter;

public enum ErrorCodeMessage {
	USER_NOT_FOUND("user.notfound"),
	USER_EMAIL_BLANK("user.emailEmpty"),
	USER_USERNAME_BLANK("user.usernameEmpty"),
	USER_FIRSTNAME_BLANK("user.firstNameEmpty"),
	USER_LASTNAME_BLANK("user.lastNameEmpty"),
	ORG_NOT_FOUND("org.notfound"),
	USERNAME_EXIST("user.usernameExist"),
	EMAIL_EXIST("user.emailExist"),
	ROLE_NOT_FOUND("role.notfound"),
	ROLE_EXIST("role.roleExist");
	
	@Getter
	private String code;

	private ErrorCodeMessage(String code) {
		this.code = code;
	}
	
	
}
