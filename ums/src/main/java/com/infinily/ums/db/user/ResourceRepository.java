package com.infinily.ums.db.user;

import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<ResourcesEntity, Long>{
	
	public ResourcesEntity findByCode(String code);
}
