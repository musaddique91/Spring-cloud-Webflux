package com.infinily.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.infinily.security.beans.User;
import com.infinily.security.beans.UserDetail;
import com.infinily.security.helper.IUserDetails;

import reactor.core.publisher.Mono;

@Service
@PropertySource("classpath:security.properties")
public class UserDetailService {
	
	@Value("${admin.user:admin}")
	private String adminUser;
	@Value("${admin.password}")
	private String adminPassword;
	
	private WebClient client = null;
	
	@PostConstruct
	public void initializeWebClient() {
		client = WebClient.create("http://localhost:8081/");
	}
	
	public Mono<UserDetail> loadUserByUsername(String username) { 
		if(adminUser.equalsIgnoreCase(username)) {
			return adminUser(username);
		}
		return client.get()
		.uri("user/details/{username}", username)
		.retrieve()
		.onStatus(httpStatus->HttpStatus.INTERNAL_SERVER_ERROR.equals(httpStatus), clientResponse-> Mono.error(new UsernameNotFoundException("user does not exist")))
		.bodyToMono(User.class)
		.flatMap(user->{
			UserDetail userDetail = new UserDetail();
			userDetail.setUsername(user.getUsername());
			userDetail.setPassword(user.getPassword());
			userDetail.setEnabled(user.getEnabled());
			userDetail.setRoles(user.getRoles());
			userDetail.setResources(user.getResources());
			userDetail.setOrganizationId(user.getOrganizationId());
			return Mono.justOrEmpty(userDetail);
		});
	}
	
	private Mono<UserDetail> adminUser(String username){
		UserDetail userDetail = new UserDetail();
		userDetail.setUsername(username); //admin
		userDetail.setPassword(adminPassword); //admin
		userDetail.setEnabled(true);
		List<String> l=new ArrayList<>();
		l.add("ROLE_ADMIN");
		userDetail.setRoles(l);
		return Mono.justOrEmpty(userDetail);
	}

}
