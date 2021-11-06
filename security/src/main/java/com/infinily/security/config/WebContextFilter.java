package com.infinily.security.config;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class WebContextFilter implements WebFilter {

	public static final String X_CUSTOM_HEADER = "X-Custom-Header";

	@Override
	public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

		serverWebExchange.getResponse().getHeaders().add("web-filter", "web-filter-test");
		return webFilterChain.filter(serverWebExchange);
	}

	/*
	 * @Autowired private SecurityContextRepository repository;
	 * 
	 * public WebContextFilter(SecurityContextRepository repository) {
	 * Assert.notNull(repository, "repository cannot be null"); this.repository =
	 * repository; }
	 * 
	 * @Override public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain
	 * chain) { Mono<Void> subscriberContext = chain.filter(exchange)
	 * .subscriberContext(c -> c.hasKey(SecurityContext.class) ? c :
	 * withSecurityContext(c, exchange) ); return subscriberContext; }
	 * 
	 * private reactor.util.context.Context
	 * withSecurityContext(reactor.util.context.Context mainContext,
	 * ServerWebExchange exchange) { return
	 * mainContext.putAll(this.repository.load(exchange)
	 * .as(ReactiveSecurityContextHolder::withSecurityContext)); }
	 */

}
