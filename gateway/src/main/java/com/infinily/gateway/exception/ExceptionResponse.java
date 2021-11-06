package com.infinily.gateway.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResponse {
	private String code;
	private String message;
    private LocalDateTime dateTime;
	private String requestUrl;
}
