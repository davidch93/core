package com.dch.core.security.jwr.config;

import com.dch.core.security.jwr.auth.ajax.RedisAuthenticationSuccessHandler;
import com.dch.core.security.jwr.auth.token.JwrAuthenticationProvider;
import com.dch.core.security.jwr.service.RedisSessionService;
import com.dch.core.security.jwt.auth.ajax.AjaxAuthenticationProvider;
import com.dch.core.security.jwt.auth.token.JwtAuthenticationProvider;
import com.dch.core.security.jwt.auth.token.extractor.TokenExtractor;
import com.dch.core.security.jwt.auth.token.factory.TokenFactory;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.config.WebSecurityConfigurerSupport;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public abstract class RedisBasedSecurityConfigurerSupport extends WebSecurityConfigurerSupport {
	
	protected final JwrSetting jwrSetting;
	protected final ObjectMapper objectMapper;
	protected final TokenFactory jwtTokenFactory;
	protected final RedisSessionService sessionService;
	protected final SecurityDetailsService securityDetailsService;
	protected final JwrAuthenticationProvider jwrAuthenticationProvider;
	protected final AjaxAuthenticationProvider ajaxAuthenticationProvider;
	
	public RedisBasedSecurityConfigurerSupport(AuthenticationManager authenticationManager, AjaxAuthenticationProvider ajaxAuthenticationProvider, JwtAuthenticationProvider jwtAuthenticationProvider, JwtSetting jwtSetting, SecurityDetailsService securityDetailsService, TokenExtractor jwtTokenExtractor, TokenFactory jwtTokenFactory, ObjectMapper objectMapper, JwrSetting jwrSetting, RedisSessionService sessionService, JwrAuthenticationProvider jwrAuthenticationProvider) {
		super(
			authenticationManager,
			ajaxAuthenticationProvider,
			jwtAuthenticationProvider,
			jwtSetting,
			securityDetailsService,
			jwtTokenExtractor,
			jwtTokenFactory,
			objectMapper
		);
		this.jwrSetting = jwrSetting;
		this.objectMapper = objectMapper;
		this.sessionService = sessionService;
		this.jwtTokenFactory = jwtTokenFactory;
		this.securityDetailsService = securityDetailsService;
		this.jwrAuthenticationProvider = jwrAuthenticationProvider;
		this.ajaxAuthenticationProvider = ajaxAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(ajaxAuthenticationProvider);
		auth.authenticationProvider(jwrAuthenticationProvider);
	}

	@Override
	protected AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new RedisAuthenticationSuccessHandler(
			objectMapper, jwrSetting, jwtTokenFactory, sessionService, securityDetailsService
		);
	}

	protected final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureAuthorization(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
		authorizeRequests = configureRedisAuthorization(authorizeRequests);
		return authorizeRequests.antMatchers("/auth/revoke").authenticated();
	}

	@SuppressWarnings("WeakerAccess")
	protected abstract ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureRedisAuthorization(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests);
	

}
