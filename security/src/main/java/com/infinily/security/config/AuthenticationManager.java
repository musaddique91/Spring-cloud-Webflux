package com.infinily.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.infinily.security.beans.User;
import com.infinily.security.beans.UserDetail;
import com.infinily.security.jwt.JwtTokenUtil;
import com.infinily.security.service.UserDetailService;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

	@Autowired
	private JwtTokenUtil jwtUtil;
	@Autowired
	private UserDetailService userDetailsService;
	
	@Override
	@SuppressWarnings("unchecked")
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();
		try {
			if (!jwtUtil.validateToken(authToken)) {
				return Mono.empty();
			}
			Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
			List<String> rolesMap = claims.get("role", List.class);
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (String rolemap : rolesMap) {
				authorities.add(new SimpleGrantedAuthority(rolemap));
			}
			UsernamePasswordAuthenticationToken data = new UsernamePasswordAuthenticationToken(claims.getSubject(), authToken, authorities);
			Mono<UserDetail> user = userDetailsService.loadUserByUsername(jwtUtil.getUsernameFromToken(authToken));
			data.setDetails(user);
			return Mono.just(data);
		} catch (Exception e) {
			return Mono.empty();
		}
	}
}
