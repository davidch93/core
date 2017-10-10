package com.dch.core.security.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class that used to configure custom password encoder.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 20, 2017
 */
@Configuration
public class PasswordEncoderConfig {

	/**
	 * Bean of BCrypt password encoder.
	 * 
	 * @return {@link BCryptPasswordEncoder}
	 */
	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
