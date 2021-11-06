package com.infinily.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinily.security.beans.User;
import com.infinily.ums.service.UserService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/details")
public class UserDetailsController {

	@Autowired
	private UserService userService;

	@GetMapping("{username}")
	public HttpEntity<Mono<User>> loadUserByUsername(@PathVariable String username) {
		return new ResponseEntity<Mono<User>>(userService.loadUserByUsername(username), HttpStatus.OK);
	}
}
