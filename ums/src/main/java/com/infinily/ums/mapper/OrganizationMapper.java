package com.infinily.ums.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.infinily.ums.beans.OrganizationBean;
import com.infinily.ums.beans.UserBean;
import com.infinily.ums.db.user.OrganizationEntity;
import com.infinily.ums.db.user.UserEntity;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
	
	@Mappings({
		@Mapping(source = "organizationId", target = "id"),
		@Mapping(source = "country", target = "address.country"),
		@Mapping(source = "state", target = "address.state"),
		@Mapping(source = "city", target = "address.city"),
		@Mapping(source = "zipCode", target = "address.zipCode"),
		@Mapping(source = "address1", target = "address.address1"),
		@Mapping(source = "address2", target = "address.address2")
	})
	OrganizationEntity mapOrg(OrganizationBean orgBean);
	
	@Mappings({
		@Mapping(source = "id", target = "organizationId"),
		@Mapping(target = "country", source = "address.country"),
		@Mapping(target = "state", source = "address.state"),
		@Mapping(target = "city", source = "address.city"),
		@Mapping(target = "zipCode", source = "address.zipCode"),
		@Mapping(target = "address1", source = "address.address1"),
		@Mapping(target = "address2", source = "address.address2")
	})
	OrganizationBean mapOrg(OrganizationEntity orgEntity);
}
