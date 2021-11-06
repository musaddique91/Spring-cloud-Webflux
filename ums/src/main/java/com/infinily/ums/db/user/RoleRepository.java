package com.infinily.ums.db.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

	public RoleEntity findByName(String roleName);
	public boolean existByName(String roleName);

	@Query("select r from RoleEntity r join UserEntity u on r.id=u.roleId where u.username=?1")
	public List<RoleEntity> getRoleByUsername(String username);
	public List<RoleEntity> findByOrganizationId(Long orgId);
	
}
