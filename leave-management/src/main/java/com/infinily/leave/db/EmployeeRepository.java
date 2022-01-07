package com.infinily.leave.db;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<EmployeeEntity, Long> {
	Mono<EmployeeEntity> findOneByUserId(Long userId);
}
