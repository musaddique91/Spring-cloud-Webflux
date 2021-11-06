package com.infinily.security.service;

import com.infinily.security.beans.User;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import reactor.core.publisher.Mono;

@Headers({"Accept: application/json"})
public interface UserClient {
	
	@RequestLine("GET /user/details/{username}")
	Mono<User> loadUserByUsername(@Param("username") String  username);
	
}
