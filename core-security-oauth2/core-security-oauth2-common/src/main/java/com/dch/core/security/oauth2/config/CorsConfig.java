package com.dch.core.security.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Class that used to configure custom global CORS configuration.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 14, 2017
 */
@Configuration
public class CorsConfig {

	/**
	 * Bean of CORS Filter.
	 * <p>
	 * The following defaults are applied if not already set:
	 * <ul>
	 * <li>Allow all origins, i.e. {@code "*"}.</li>
	 * <li>Allow all methods.</li>
	 * <li>Allow all headers.</li>
	 * <li>Allow credentials.</li>
	 * <li>Set max age to 1800 seconds (30 minutes).</li>
	 * </ul>
	 * </p>
	 * 
	 * @return {@link CorsConfigurationSource}
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.applyPermitDefaultValues();
		corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		return source;
	}
}
