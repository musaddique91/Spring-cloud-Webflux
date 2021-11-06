package com.infinily.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.infinily.security.jwt.JwtTokenUtil;

import reactor.core.publisher.Mono;

@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtUtil;

	@Override
	public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Mono<SecurityContext> load(ServerWebExchange swe) {
		Object authToken = swe.getAttribute(JwtTokenUtil.TOKEN);
		if (null != authToken) {
			String username= jwtUtil.getUsernameFromToken((String)authToken);
			Authentication auth = new UsernamePasswordAuthenticationToken(username, authToken);
			return this.authenticationManager.authenticate(auth).map((authentication) -> {
				SecurityContextImpl context = new SecurityContextImpl(authentication);
				SecurityContextHolder.setContext(context);
				ReactiveSecurityContextHolder.withAuthentication(authentication);
				return context;
			});
		} else {
			return Mono.empty();
		}
	}

}