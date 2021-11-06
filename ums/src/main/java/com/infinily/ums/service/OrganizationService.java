package com.infinily.ums.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinily.common.constant.ErrorCodeMessage;
import com.infinily.security.helper.SecurityHelper;
import com.infinily.ums.beans.OrganizationBean;
import com.infinily.ums.db.user.OrganizationEntity;
import com.infinily.ums.db.user.OrganizationRepository;
import com.infinily.ums.exception.UserRuntimeException;
import com.infinily.ums.mapper.OrganizationMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrganizationService {

	@Autowired
	private OrganizationRepository orgRepository;
	@Autowired
	private OrganizationMapper orgMapper;

	public Mono<OrganizationBean> getOrg(String orgName) {
		Mono<OrganizationBean> error = Mono.error(new UserRuntimeException(ErrorCodeMessage.ORG_NOT_FOUND.getCode()));
		return Mono.just(orgName)
				.flatMap(item -> Mono.justOrEmpty(orgRepository.findByName(item)))
				.flatMap(item -> Mono.justOrEmpty(orgMapper.mapOrg(item)))
				.switchIfEmpty(error);

	}

	public Mono<OrganizationBean> saveOrg(OrganizationBean orgBean) {
		return  Mono.zip(Mono.just(orgBean), SecurityHelper.getUsernameFromReact())
		.flatMap(data->{
			OrganizationEntity entity = orgMapper.mapOrg(data.getT1());
			entity.setCreatedDate(LocalDateTime.now());
			entity.setModifiedDate(LocalDateTime.now());
			entity.setCreatedUser(data.getT2());
			entity.setModifiedUser(data.getT2());
			return Mono.justOrEmpty(entity);
		})
		.flatMap(item -> Mono.justOrEmpty(orgRepository.save(item)))
		.flatMap(item -> Mono.justOrEmpty(orgMapper.mapOrg(item)));
	}
	
	public Mono<OrganizationBean> updateOrg(OrganizationBean orgBean) {
		return  Mono.zip(Mono.just(orgBean), SecurityHelper.getUsernameFromReact())
		.flatMap(data->{
			OrganizationEntity entity = orgMapper.mapOrg(data.getT1());
			entity.setModifiedDate(LocalDateTime.now());
			entity.setModifiedUser(data.getT2());
			return Mono.justOrEmpty(entity);
		})
		.flatMap(item -> Mono.justOrEmpty(orgRepository.save(item)))
		.flatMap(item -> Mono.justOrEmpty(orgMapper.mapOrg(item)));
	}
	
	public Flux<OrganizationBean> getAllOrg(){
		return Flux.fromIterable(orgRepository.findAll())
		.flatMap(item-> Flux.just(orgMapper.mapOrg(item)));
		
	}

}
