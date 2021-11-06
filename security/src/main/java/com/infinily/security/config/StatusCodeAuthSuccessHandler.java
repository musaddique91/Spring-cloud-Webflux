package com.infinily.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

public class StatusCodeAuthSuccessHandler implements ServerAuthenticationSuccessHandler {

    private HttpStatus statusCode = HttpStatus.OK;

    public StatusCodeAuthSuccessHandler statusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        webFilterExchange.getExchange().getResponse().setStatusCode(statusCode);
        SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
        return Mono.empty();
    }
}
