package com.dch.core.security.oauth2.auth.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.dch.core.security.oauth2.service.SecurityDetailsService;

/**
 * Custom configuration of {@link TokenEndpointAuthenticationFilter}. This
 * filter can be added to {@link AuthorizationServerSecurityConfigurer} provided
 * that the <b>allowFormAuthenticationForClients</b> parameter is true. This
 * filter can also use custom {@link OAuth2AuthenticationEntryPoint} and
 * {@link OAuth2RequestFactory}.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 12, 2017
 */
public class OAuth2TokenEndpointAuthenticationFilter extends TokenEndpointAuthenticationFilter {

	private AuthenticationEntryPoint authenticationEntryPoint;
	private SecurityDetailsService securityDetailsService;
	
	/**
	 * Constructs a {@code OAuth2TokenEndpointAuthenticationFilter} with the
	 * specified authenticationManager, oAuth2RequestFactory,
	 * securityDetailsService, and authenticationEntryPoint.
	 * 
	 * @param authenticationManager
	 *            {@link AuthenticationManager}
	 * @param oAuth2RequestFactory
	 *            {@link OAuth2RequestFactory}
	 * @param authenticationEntryPoint
	 *            {@link AuthenticationEntryPoint}
	 * @param securityDetailsService
	 *            {@link SecurityDetailsService}
	 */
	public OAuth2TokenEndpointAuthenticationFilter(AuthenticationManager authenticationManager,
			OAuth2RequestFactory oAuth2RequestFactory, AuthenticationEntryPoint authenticationEntryPoint,
			SecurityDetailsService securityDetailsService) {
		super(authenticationManager, oAuth2RequestFactory);
		setAuthenticationEntryPoint(authenticationEntryPoint);

		this.authenticationEntryPoint = authenticationEntryPoint;
		this.securityDetailsService = securityDetailsService;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {
			if (securityDetailsService.validateRequest((HttpServletRequest) req))
				super.doFilter(req, res, chain);
		} catch (AuthenticationException ex) {
			authenticationEntryPoint.commence((HttpServletRequest) req, (HttpServletResponse) res, ex);
			onUnsuccessfulAuthentication((HttpServletRequest) req, (HttpServletResponse) res, ex);
		}
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
		// TODO Add your custom unsuccessful authentication or using OAuth2AuthenticationEntryPoint.
	}
}
