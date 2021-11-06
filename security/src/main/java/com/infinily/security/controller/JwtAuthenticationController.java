package com.infinily.security.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infinily.security.beans.JwtRequest;
import com.infinily.security.beans.JwtResponse;
import com.infinily.security.jwt.JwtTokenUtil;
import com.infinily.security.service.UserDetailService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("auth")
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody JwtRequest request) {
		return userService.loadUserByUsername(request.getUsername())
		.map(userDetails->{
			if(passwordEncoder.matches(request.getPassword(), userDetails.getPassword())){
				String token = jwtUtil.generateToken(userDetails);
				Date expireOn = jwtUtil.getExpirationDateFromToken(token);
				return ResponseEntity.ok(new JwtResponse(token, expireOn));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}
