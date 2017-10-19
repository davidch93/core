package com.dch.core.security.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.dch.core.security.oauth2.RestAccessDeniedHandler;
import com.dch.core.security.oauth2.RestAuthenticationEntryPoint;
import com.dch.core.security.oauth2.service.SecurityDetailsService;

/**
 * The server configuration that used to provide implementations of the client
 * details service and token services and to enable or disable certain aspects
 * of the mechanism globally. This default configuration using
 * {@link JdbcTokenStore} and extends
 * {@link AuthorizationServerConfigurerAdapter} to provide authorization server
 * configuration support.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 31, 2017
 */
@ComponentScan("com.cnx.core.security.oauth2")
public class AuthorizationServerConfigurerSupport extends AuthorizationServerConfigurerAdapter {

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	protected DataSource dataSource;

	@Autowired
	protected UserDetailsService userDetailsService;

	@Autowired
	protected SecurityDetailsService securityDetailsService;

	@Autowired
	protected AuthorizationSetting authorizationSetting;

	/**
	 * Bean of JDBC Client Details Service.
	 * 
	 * @return {@link JdbcClientDetailsService}
	 */
	@Bean
	public JdbcClientDetailsService clientDetailsService() {
		return new JdbcClientDetailsService(dataSource);
	}

	/**
	 * Bean of JDBC Token Store.
	 * 
	 * @return {@link TokenStore}
	 */
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		// @formatter:off
		oauthServer
			.authenticationEntryPoint(authenticationEntryPoint())
			.accessDeniedHandler(accessDeniedHandler())
			.checkTokenAccess("isAuthenticated()")
			.tokenKeyAccess("permitAll()");
		// @formatter:on
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// @formatter:off
		clients.withClientDetails(clientDetailsService());
		// @formatter:on
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// @formatter:off
		endpoints
			.tokenStore(tokenStore())
			.tokenEnhancer(tokenEnhancerChain())
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService);
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
	 * Method used to get default access denied handler.
	 * 
	 * @return {@link RestAccessDeniedHandler}
	 */
	protected AccessDeniedHandler accessDeniedHandler() {
		return new RestAccessDeniedHandler();
	}

	/**
	 * Method used to get custom token using token enhancer.
	 * 
	 * @return {@link TokenEnhancerChain}
	 */
	protected TokenEnhancerChain tokenEnhancerChain() {
		return new TokenEnhancerChain();
	}
}
