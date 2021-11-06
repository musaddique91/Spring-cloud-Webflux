package com.infinily.ums.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.infinily.ums.beans.RoleBean;
import com.infinily.ums.beans.UserBean;
import com.infinily.ums.db.user.RoleEntity;
import com.infinily.ums.db.user.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserEntity mapUser(UserBean userBean);
	
	@Mappings({
		@Mapping(source = "id", target = "userId"),
		@Mapping(target = "password", ignore = true)
	})
	UserBean mapUser(UserEntity userEntity);
	
	@Mappings({
		@Mapping(target = "resources", ignore = true),
	})
	RoleEntity mapRole(RoleBean roleBean);
	
	@Mappings({
		@Mapping(source = "id", target = "roleId"),
		@Mapping(target = "resources", ignore = true),
	})
	RoleBean mapRole(RoleEntity roleEntity);
}
