package com.dch.core.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dch.core.datastatic.GenericStatus;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.service.SecurityDetailsService;

/**
 * This handler is called once the request access denied. This class implements
 * {@link AccessDeniedHandler}.
 * 
 * @author David.Christianto
 * @version 1.0.0-SNAPSHOT
 * @since 1.0.0-SNAPSHOT
 * @updated May 22, 2017
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestAccessDeniedHandler.class);

	private SecurityDetailsService securityDetailsService;
	private ObjectMapper mapper;
	private JwtSetting jwtSetting;

	public RestAccessDeniedHandler(SecurityDetailsService securityDetailsService, ObjectMapper mapper,
			JwtSetting jwtSetting) {
		this.securityDetailsService = securityDetailsService;
		this.mapper = mapper;
		this.jwtSetting = jwtSetting;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		LOGGER.error(String.format("[%s] %s", jwtSetting.getIdentityPrefix(), accessDeniedException.getMessage()),
				accessDeniedException);

		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
				.setGenericStatus(GenericStatus.FORBIDDEN).build());
	}
}
