package com.dch.core.security.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.security.oauth2.RestAuthenticationEntryPoint;
import com.dch.core.security.oauth2.service.SecurityDetailsService;

/**
 * Web security configurer that extends {@link WebSecurityConfigurerAdapter} to
 * provide security configuration support.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 9, 2017
 */
@ComponentScan("com.dch.core.security.oauth2")
public class WebSecurityConfigurerSupport extends WebSecurityConfigurerAdapter {

	@Autowired
	protected SecurityDetailsService securityDetailsService;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
			.csrf().disable()
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint())
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
					.antMatchers(WebSecuritySupport.OAUTH2_BASED_REVOKE_ENTRY_POINT.getValue()).permitAll();

		authorizeRequests = configureAuthorization(authorizeRequests)
			.anyRequest().authenticated();
		// @formatter:on
	}

	/**
	 * Method used to get default authentication entry point.
	 * 
	 * @return {@link RestAuthenticationEntryPoint}
	 */
	protected AuthenticationEntryPoint authenticationEntryPoint() {
		return new RestAuthenticationEntryPoint(securityDetailsService);
	}

	/**
	 * Method used to add custom authorization configurations. By default do
	 * nothing.
	 * 
	 * @param authorizeRequests
	 *            {@link ExpressionUrlAuthorizationConfigurer}&lt;{@link HttpSecurity}&gt;.{@link ExpressionInterceptUrlRegistry}
	 * @return {@link ExpressionUrlAuthorizationConfigurer}&lt;{@link HttpSecurity}&gt;.{@link ExpressionInterceptUrlRegistry}
	 * @throws Exception
	 *             if there are errors during configure authorization.
	 */
	protected ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureAuthorization(
			ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests)
			throws Exception {
		return authorizeRequests;
	}
}
