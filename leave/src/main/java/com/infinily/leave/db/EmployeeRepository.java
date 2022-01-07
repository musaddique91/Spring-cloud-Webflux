package com.infinily.leave.db;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
	EmployeeEntity findOneByUserId(Long userId);
}
