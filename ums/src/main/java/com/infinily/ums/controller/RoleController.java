
package com.infinily.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinily.ums.beans.RoleBean;
import com.infinily.ums.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("ums/api")
public class RoleController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "roles")
	public HttpEntity<Flux<RoleBean>> getAllRoles() {
		return new ResponseEntity<>(userService.getAllRoles(), HttpStatus.OK);
	}

	@PostMapping("role")
	public HttpEntity<Mono<RoleBean>> saveRole(@RequestBody RoleBean roleBean) {
		return new ResponseEntity<>(userService.saveRole(roleBean), HttpStatus.CREATED);
	}

	@PutMapping("role")
	public HttpEntity<Mono<RoleBean>> updateRole(@PathVariable Long roleId, @RequestBody RoleBean roleBean) {
		return new ResponseEntity<>(userService.updateRole(roleId, roleBean), HttpStatus.CREATED);
	}

	@GetMapping(value = "role/{roleId}")
	public HttpEntity<Mono<RoleBean>> getRole(@PathVariable Long roleId) {
		return new ResponseEntity<>(userService.getRole(roleId), HttpStatus.OK);
	}

	@GetMapping(value = "resources")
	public HttpEntity<Flux<String>> getResources() {
		return new ResponseEntity<>(userService.getre, HttpStatus.OK);
	}

}
