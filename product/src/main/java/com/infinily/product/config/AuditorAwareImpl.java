package com.infinily.product.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.infinily.security.helper.SecurityHelper;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		/*
		 * Optional<String> blockOptional = SecurityHelper.getUsernameFromReact()
		 * .blockOptional(Duration.ofSeconds(1)); return blockOptional;
		 */
		return Optional.of(SecurityHelper.getUsername());
	}

}
