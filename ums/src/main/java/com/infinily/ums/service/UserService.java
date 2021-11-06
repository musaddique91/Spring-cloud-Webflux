package com.infinily.ums.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.infinily.common.constant.ErrorCodeMessage;
import com.infinily.security.beans.User;
import com.infinily.security.beans.UserDetail;
import com.infinily.security.helper.SecurityHelper;
import com.infinily.ums.beans.RoleBean;
import com.infinily.ums.beans.UserBean;
import com.infinily.ums.db.user.ResourceRepository;
import com.infinily.ums.db.user.ResourcesEntity;
import com.infinily.ums.db.user.RoleEntity;
import com.infinily.ums.db.user.RoleRepository;
import com.infinily.ums.db.user.UserRepository;
import com.infinily.ums.exception.UserRuntimeException;
import com.infinily.ums.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DBValidator dbValidator;
	@Autowired
	@Qualifier("passEncoder")
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ResourceRepository resourceRepository;

	public Mono<UserBean> getUser(String username) {
		Mono<UserBean> error = Mono.error(new UserRuntimeException(ErrorCodeMessage.USER_NOT_FOUND.getCode()));
		return Mono.just(username).flatMap(item -> Mono.justOrEmpty(userRepository.findByUsername(item)))
				.flatMap(item -> Mono.justOrEmpty(userMapper.mapUser(item))).switchIfEmpty(error);

	}

	@Transactional
	public Optional<UserBean> saveUser(UserBean userBean) {
		return Optional.of(userBean).map(dbValidator::validateSaveUser).map(userMapper::mapUser).map(userEntity -> {
			userEntity.createPassword(passwordEncoder);
			if (null == userEntity.getOrganizationId()) {
				userEntity.setOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId());
			}
			return userEntity;
		}).map(userRepository::save).map(userMapper::mapUser);
	}

	public Flux<UserBean> getAllUsers() {
		return SecurityHelper.getCurrentUserFromReact().flatMap(item -> {
			UserDetail userDetail = (UserDetail) item;
			return Mono.justOrEmpty(userDetail.getOrganizationId());
		}).flatMapMany(item -> {
			return Flux.fromIterable(userRepository.findByOrganizationId(item));
		}).flatMap(item -> Flux.just(userMapper.mapUser(item)));

	}

	public Mono<User> loadUserByUsername(String username) {
		Mono<User> error = Mono.error(new UserRuntimeException(ErrorCodeMessage.USER_NOT_FOUND.getCode()));
		return Mono.just(username).flatMap(item -> Mono.justOrEmpty(userRepository.findByUsername(item)))
				.flatMap(userEntity -> {
					User u = new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isActive());
					u.setOrganizationId(userEntity.getOrganizationId());
					return Mono.justOrEmpty(u);
				}).flatMap(user -> {
					List<RoleEntity> roleEntity = roleRepository.getRoleByUsername(user.getUsername());
					Set<String> resources = new HashSet<>();
					if (!CollectionUtils.isEmpty(roleEntity)) {
						for (RoleEntity e : roleEntity) {
							Set<String> roleResource = e.getResources().stream().map(ResourcesEntity::getCode)
									.collect(Collectors.toSet());
							resources.addAll(roleResource);
						}
						user.setResources(resources.stream().map(resource -> {
							resource = "ROLE_" + resource;
							return resource;
						}).collect(Collectors.toList()));
					}
					return Mono.justOrEmpty(user);
				}).switchIfEmpty(error);
	}

	public Mono<RoleBean> getRole(Long roleId) {
		Mono<RoleBean> error = Mono.error(new UserRuntimeException(ErrorCodeMessage.ROLE_NOT_FOUND.getCode()));
		return Mono.just(roleId).flatMap(item -> Mono.justOrEmpty(roleRepository.findById(roleId))).flatMap(item -> {
			RoleBean roleBean = userMapper.mapRole(item);
			if (null != item.getResources()) {
				roleBean.setResources(
						item.getResources().stream().map(ResourcesEntity::getCode).collect(Collectors.toList()));
			}
			List<Long> userIdOfRole = userRepository.getUserIdOfRole(item.getId());
			roleBean.setUsers(userIdOfRole);
			return Mono.justOrEmpty(roleBean);
		}).switchIfEmpty(error);

	}

	@Transactional
	public Mono<RoleBean> saveRole(final RoleBean role) {
		RoleBean roleBean = dbValidator.validateSaveRole(role);
		return Mono.zip(Mono.just(roleBean), SecurityHelper.getCurrentUserFromReact()).flatMap(data -> {
			UserDetail currentUser = (UserDetail) data.getT2();
			RoleEntity entity = userMapper.mapRole(data.getT1());
			entity.setCreatedDate(LocalDateTime.now());
			entity.setModifiedDate(LocalDateTime.now());
			entity.setCreatedUser(currentUser.getUsername());
			entity.setModifiedUser(currentUser.getUsername());
			if (null == data.getT1().getOrganizationId()) {
				entity.setOrganizationId(currentUser.getOrganizationId());
			} else {
				entity.setOrganizationId(data.getT1().getOrganizationId());
			}
			if (null != roleBean.getResources()) {
				entity.setResources(roleBean.getResources().stream().map(resourceRepository::findByCode)
						.collect(Collectors.toList()));
			}
			entity.setUser(data.getT1().getUsers());
			return Mono.justOrEmpty(entity);
		}).flatMap(item -> {
			RoleEntity save = roleRepository.save(item);
			if (!CollectionUtils.isEmpty(item.getUser())) {
				userRepository.updateRoleForUsers(save.getId(), item.getUser());
			}
			return Mono.justOrEmpty(save);
		}).flatMap(item -> Mono.justOrEmpty(userMapper.mapRole(item)));
	}

	@Transactional
	public Mono<RoleBean> updateRole(final Long roleId, final RoleBean role) {
		RoleBean roleBean = dbValidator.validateUpdateRole(roleId, role);
		return Mono.zip(Mono.just(roleBean), SecurityHelper.getCurrentUserFromReact()).flatMap(data -> {
			UserDetail currentUser = (UserDetail) data.getT2();
			Optional<RoleEntity> dbRole = roleRepository.findById(roleId);
			RoleEntity entity = dbRole.get();
			entity.setModifiedDate(LocalDateTime.now());
			entity.setModifiedUser(currentUser.getUsername());
			if (null != roleBean.getResources()) {
				entity.setResources(roleBean.getResources().stream().map(resourceRepository::findByCode)
						.collect(Collectors.toList()));
			}
			return Mono.justOrEmpty(entity);
		}).flatMap(item -> {
			RoleEntity save = roleRepository.save(item);
			if (!CollectionUtils.isEmpty(item.getUser())) {
				userRepository.updateRoleForUsers(save.getId(), item.getUser());
			}
			return Mono.justOrEmpty(save);
		}).flatMap(item -> Mono.justOrEmpty(userMapper.mapRole(item)));
	}

	public Flux<RoleBean> getAllRoles() {
		return SecurityHelper.getCurrentOrgId().flatMapMany(item -> {
			return Flux.fromIterable(roleRepository.findByOrganizationId(item));
		}).flatMap(item -> Flux.just(userMapper.mapRole(item)));
	}
}
