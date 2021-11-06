package com.infinily.ums.db.user;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>{
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user entity
	 */
	public UserEntity findByUsername(String username);
	
	/**
	 * Exists by email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	public boolean existsByEmail(String email);
	
	/**
	 * Exists by username.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean existsByUsername(String username);
	
	/**
	 * Find by organization id.
	 *
	 * @param organizationId the organization id
	 * @return the list
	 */
	public List<UserEntity> findByOrganizationId(Long organizationId);
	
	/**
	 * Gets the user idof role.
	 *
	 * @param roleId the role id
	 * @return the user idof role
	 */
	@Query("select u.id from UserEntity u where u.roleId=?1")
	public List<Long> getUserIdOfRole(Long roleId);
	
	public UserEntity findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("update UserEntity set roleId=?1 where id in (?2)")
	public int updateRoleForUsers(Long roleId, List<Long> userIds);
	
}
