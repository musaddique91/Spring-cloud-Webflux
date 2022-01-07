package com.infinily.leave.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinily.leave.beans.EmployeeBean;
import com.infinily.leave.beans.LeaveBean;
import com.infinily.leave.db.EmployeeEntity;
import com.infinily.leave.db.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("employee/api")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping()
	public HttpEntity<Flux<LeaveBean>> saveEmployees(@RequestBody List<EmployeeBean> bean) {
		return null;
	}

	@GetMapping("{employeeID}")
	Mono<HttpEntity<EmployeeEntity>> getEmployee(@PathVariable Long employeeID) {
		return employeeRepository.findById(employeeID).map(employee -> {
			return ok(employee);
		});
	}
}
