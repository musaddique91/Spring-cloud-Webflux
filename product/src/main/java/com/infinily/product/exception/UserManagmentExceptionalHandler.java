package com.infinily.product.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.infinily.common.helper.UserHelper;
import com.infinily.security.helper.SecurityHelper;

@ControllerAdvice
public class UserManagmentExceptionalHandler {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserHelper userHelper;

	@ExceptionHandler(UserRuntimeException.class)
	public ResponseEntity<ExceptionResponse> handleExceptions(UserRuntimeException exception,
			ServerHttpRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(getMessage(exception.getCode()));
		response.setCode(exception.getCode());
		response.setRequestUrl(request.getURI().getPath());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<ExceptionResponse> handleExceptions(WebExchangeBindException exception,
			ServerHttpRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		List<String> errors = exception.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
		response.setCode("VALIDATION_ERROR");
		response.setMessage("validation error");
		response.setErrors(errors);
		response.setRequestUrl(request.getURI().getPath());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}


	private String getMessage(String code) {
		String username = SecurityHelper.getUsername();
		Locale locale =Locale.ENGLISH;
		if (null != username) {
			locale = Locale.forLanguageTag(userHelper.getUserLocal(username));
		}
		return messageSource.getMessage(code, null, locale);
	}

}
