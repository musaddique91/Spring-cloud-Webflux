package com.infinily.security.helper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infinily.security.beans.User;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "ums")
public interface IUserDetails {
	@GetMapping("/user/details/{username}")
	Mono<User> loadUserByUsername(@PathVariable("username") String username);
}
