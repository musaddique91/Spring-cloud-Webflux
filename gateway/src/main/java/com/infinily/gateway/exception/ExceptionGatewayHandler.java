package com.infinily.gateway.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionGatewayHandler {
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleExceptions(UsernameNotFoundException exception,
			ServerHttpRequest request) {
		ExceptionResponse res=new ExceptionResponse();
		res.setMessage("user does not exist");
		res.setDateTime(LocalDateTime.now());
		res.setCode("NOT_FOUND");
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
