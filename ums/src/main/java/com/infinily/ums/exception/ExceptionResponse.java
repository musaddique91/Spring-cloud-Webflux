package com.infinily.ums.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.infinily.common.constant.Constants;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionResponse {
	private String code;
	private String message;
	private List<String> errors;
	@JsonFormat(shape = Shape.STRING, pattern = Constants.DATE_PATTERN)
    private LocalDateTime dateTime;
	private String requestUrl;
}
