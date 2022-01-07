package com.infinily.leave.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LeaveCatagoryRepository extends CrudRepository<LeaveCatagoryEntity, Long> {
	List<PlanLeaveEntity> findByOrganizationId(Long organizationId);
}
