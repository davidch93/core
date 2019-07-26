package com.dch.core.security.jwr.auth.ajax;

import com.dch.core.security.jwr.config.JwrSetting;
import com.dch.core.security.jwr.service.RedisSessionService;
import com.dch.core.security.jwt.auth.token.factory.TokenFactory;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import com.dch.core.security.jwt.util.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author Sayid Sidqi
 */
public class RedisAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final ObjectMapper mapper;
	private final JwrSetting jwrSetting;
	private final TokenFactory tokenFactory;
	private final RedisSessionService sessionService;
	private final SecurityDetailsService securityDetailsService;
	
	public RedisAuthenticationSuccessHandler(
		ObjectMapper mapper,
		JwrSetting jwrSetting,
		TokenFactory tokenFactory,
		RedisSessionService sessionService,
		SecurityDetailsService securityDetailsService) {
		this.mapper = mapper;
		this.jwrSetting = jwrSetting;
		this.tokenFactory = tokenFactory;
		this.sessionService = sessionService;
		this.securityDetailsService = securityDetailsService;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Map tokens = HttpUtils.createToken(authentication, tokenFactory, false);
		Date expirationDate = DateUtils.addMinutes(new Date(), jwrSetting.getTokenExpirationTime());
		sessionService.setSession(userDetails.getUsername(), String.valueOf(expirationDate), expirationDate);
		HttpUtils.writeToken(response, mapper, securityDetailsService.getSecurityResponseBuilder(), tokens);
		HttpUtils.clearAuthenticationAttributes(request);
	}
}
