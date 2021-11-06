package com.infinily.security.helper;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.infinily.security.beans.User;
import com.infinily.security.beans.UserDetail;
import com.infinily.security.jwt.JwtTokenUtil;

import reactor.core.publisher.Mono;

public class SecurityHelper {
	public static String getUsername() {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication && !(authentication instanceof AnonymousAuthenticationToken)) {
			username = (String) authentication.getPrincipal();
		}
		if (null == username) {
			username = "ADMIN";
		}
		return username;
	}
	
	public static UserDetail getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication && !(authentication instanceof AnonymousAuthenticationToken)) {
			Mono<UserDetail> user = (Mono) authentication.getDetails();
			return user.block();
		}
		return null;
	}

	public static Mono<Object> getCurrentUserFromReact() {
		return ReactiveSecurityContextHolder.getContext()
			.map(sc -> sc.getAuthentication())
			.map(auth -> {
					return auth.getDetails();
			});
	}

	public static String getToken() {
		String token = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			token = (String) authentication.getCredentials();
			token = JwtTokenUtil.TOKEN_TYPE + token;
		}
		return token;
	}

	public static Mono<String> getUsernameFromReact() {
		return ReactiveSecurityContextHolder.getContext()
				.map(sc -> sc.getAuthentication())
				.map(auth -> {
					return (String) auth.getPrincipal();
		});
	}
	
	public static Mono<Long> getCurrentOrgId() {
		return ReactiveSecurityContextHolder.getContext()
			.map(sc -> sc.getAuthentication())
			.map(auth -> auth.getDetails())
			.map(userDetails->{
				UserDetail userDetail=(UserDetail)userDetails;
				return userDetail.getOrganizationId();
			});
	}

}
