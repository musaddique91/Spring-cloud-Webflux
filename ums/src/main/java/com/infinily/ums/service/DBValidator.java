package com.infinily.ums.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinily.common.constant.ErrorCodeMessage;
import com.infinily.ums.beans.RoleBean;
import com.infinily.ums.beans.UserBean;
import com.infinily.ums.db.user.RoleEntity;
import com.infinily.ums.db.user.RoleRepository;
import com.infinily.ums.db.user.UserEntity;
import com.infinily.ums.db.user.UserRepository;
import com.infinily.ums.exception.UserRuntimeException;

@Service
public class DBValidator {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public UserBean validateSaveUser(UserBean bean) {
		if (userRepository.existsByUsername(bean.getUsername())) {
			throw new UserRuntimeException(ErrorCodeMessage.USERNAME_EXIST.getCode());
		}
		if (userRepository.existsByEmail(bean.getEmail())) {
			throw new UserRuntimeException(ErrorCodeMessage.EMAIL_EXIST.getCode());
		}
		return bean;
	}

	public RoleBean validateSaveRole(RoleBean bean) {
		if (roleRepository.existByName(bean.getRoleName())) {
			throw new UserRuntimeException(ErrorCodeMessage.ROLE_EXIST.getCode());
		}
		return bean;
	}

	public RoleBean validateUpdateRole(Long roleId, RoleBean bean) {
		RoleEntity role = roleRepository.findByName(bean.getRoleName());
		if (null != role && role.getId() != roleId) {
			throw new UserRuntimeException(ErrorCodeMessage.ROLE_EXIST.getCode());
		}
		return bean;
	}

	public UserBean validateUpdateUser(Long userId, UserBean bean) {
		UserEntity user = userRepository.findByUsername(bean.getUsername());
		if (null != user && user.getId() != userId) {
			throw new UserRuntimeException(ErrorCodeMessage.USERNAME_EXIST.getCode());
		}
		user = userRepository.findByEmail(bean.getEmail());
		if (null != user && user.getId() != userId) {
			throw new UserRuntimeException(ErrorCodeMessage.EMAIL_EXIST.getCode());
		}
		return bean;
	}

}
