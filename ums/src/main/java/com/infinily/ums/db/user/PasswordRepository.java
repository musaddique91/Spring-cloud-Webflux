package com.infinily.ums.db.user;

import org.springframework.data.repository.CrudRepository;

public interface PasswordRepository extends CrudRepository<PasswordEntity, Long>{
	
	public PasswordEntity findByUserId(String userId);
}
