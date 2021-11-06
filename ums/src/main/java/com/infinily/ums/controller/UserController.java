
package com.infinily.ums.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinily.security.helper.SecurityHelper;
import com.infinily.ums.beans.RoleBean;
import com.infinily.ums.beans.UserBean;
import com.infinily.ums.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("ums/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "hello")
	public HttpEntity<String> hello(Authentication authentication, @RequestBody UserBean userBean) {
		authentication.getPrincipal();
		SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
		Mono<Object> currentUser = SecurityHelper.getCurrentUserFromReact();
		System.out.println(SecurityHelper.getUsername());
		return new ResponseEntity<>("Hello ".concat("user"), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}")
	public HttpEntity<Mono<UserBean>> user(@PathVariable String username) {
		return new ResponseEntity<Mono<UserBean>>(userService.getUser(username), HttpStatus.OK);
	}

	@GetMapping
	public HttpEntity<Flux<UserBean>> users() {
		return new ResponseEntity<Flux<UserBean>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping
	public HttpEntity<UserBean> user(Authentication authentication, @Valid @RequestBody UserBean userBean) {
		SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
		Optional<UserBean> saveUser = userService.saveUser(userBean);
		return new ResponseEntity<UserBean>(saveUser.get(), HttpStatus.CREATED);
	}

}
