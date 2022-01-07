package com.infinily.leave.controller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinily.leave.beans.LeaveBean;
import com.infinily.leave.enums.LeaveStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("leave/api")
public class LeaveController {
	
	
	@PostMapping()
	public HttpEntity<Flux<LeaveBean>> saveLeaves(@RequestBody List<LeaveBean> bean) {
		return null;
	}

	@GetMapping("{leaveId}")
	public HttpEntity<Mono<LeaveBean>> getLeave(@PathVariable Long leaveId) {
		return null;
	}

	@DeleteMapping("{leaveId}")
	public HttpEntity<Mono<Long>> deleteLeave(@PathVariable Long leaveId) {
		return null;
	}

	@PutMapping("{leaveId}")
	public HttpEntity<Mono<LeaveBean>> updateLeave(@PathVariable Long leaveId, @RequestBody LeaveBean bean) {
		return null;
	}

	@GetMapping("team/{parentUserId}")
	public HttpEntity<Flux<LeaveBean>> getLeaves(@PathVariable Long parentUserId) {
		return null;
	}

	@PatchMapping("{leaveId}/action/{status}")
	public HttpEntity<Mono<LeaveBean>> action(@PathVariable("leaveId") Long leaveId,
			@PathVariable("status") LeaveStatus status) {
		return null;
	}
	
	@PostMapping("planLeave")
	public HttpEntity<Flux<LeaveBean>> savePlanLeaves(@RequestBody List<LeaveBean> bean) {
		return null;
	}
}
