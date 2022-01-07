package com.infinily.leave.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LeaveRepository extends CrudRepository<LeaveEntity, Long> {
	List<LeaveEntity> findByEmployeeId(Long userId);
}
