package com.infinily.leave.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRuntimeException extends RuntimeException {
	private String code;
	private String message;

	public UserRuntimeException(String code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}

	public UserRuntimeException(String code) {
		this.code = code;
	}

}
