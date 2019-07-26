package com.dch.core.security.jwr.config;

import com.dch.core.security.jwt.config.JwtSetting;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 *
 *
 * @author Sayid Sidqi
 */
@Configuration
@EnableConfigurationProperties({JwtSetting.class, JwrSetting.class})
public class JwrPropertiesConfig {

	private final JwtSetting jwtSetting;
	
	public JwrPropertiesConfig(JwtSetting jwtSetting) {
		this.jwtSetting = jwtSetting;
	}
	
	@PostConstruct
	public void initialize() {
		// Ignore jwt expiration time, so that expired time only checked in redis
		jwtSetting.setTokenExpirationTime(null);
	}

}
