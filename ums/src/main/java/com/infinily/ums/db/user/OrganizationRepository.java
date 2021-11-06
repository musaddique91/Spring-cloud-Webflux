package com.infinily.ums.db.user;

import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Long> {

	public OrganizationEntity findByName(String name);
}
