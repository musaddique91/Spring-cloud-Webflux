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

import com.infinily.ums.beans.OrganizationBean;
import com.infinily.ums.service.OrganizationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Class OrganizationController.
 */
@RestController
@RequestMapping("ums/api/organization")
public class OrganizationController {
	
	/** The organization service. */
	@Autowired
	private OrganizationService organizationService;

	/**
	 * Gets the organization.
	 *
	 * @param orgName the org name
	 * @return the organization
	 */
	@GetMapping(value = "/{name}")
	public HttpEntity<Mono<OrganizationBean>> getOrganization(@PathVariable String orgName) {
		return new ResponseEntity<Mono<OrganizationBean>>(organizationService.getOrg(orgName), HttpStatus.OK);
	}
	
	/**
	 * Save organization.
	 *
	 * @param orgBean the org bean
	 * @return the http entity
	 */
	@PostMapping
	public HttpEntity<Mono<OrganizationBean>> saveOrganization(@RequestBody OrganizationBean orgBean) {
		return new ResponseEntity<Mono<OrganizationBean>>(organizationService.saveOrg(orgBean), HttpStatus.OK);
	}
	
	/**
	 * Update organization.
	 *
	 * @param orgBean the org bean
	 * @return the http entity
	 */
	@PutMapping
	public HttpEntity<Mono<OrganizationBean>> updateOrganization(@RequestBody OrganizationBean orgBean) {
		return new ResponseEntity<Mono<OrganizationBean>>(organizationService.updateOrg(orgBean), HttpStatus.OK);
	}
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@GetMapping
	public HttpEntity<Flux<OrganizationBean>> getAll() {
		return new ResponseEntity<Flux<OrganizationBean>>(organizationService.getAllOrg(), HttpStatus.OK);
	}
}
