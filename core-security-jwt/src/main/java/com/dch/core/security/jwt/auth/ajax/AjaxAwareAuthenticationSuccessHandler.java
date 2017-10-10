package com.dch.core.security.jwt.auth.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dch.core.datastatic.GenericStatus;
import com.dch.core.security.jwt.model.token.JwtToken;
import com.dch.core.security.jwt.model.token.JwtTokenFactory;
import com.dch.core.security.jwt.service.SecurityDetailsService;

/**
 * Class that implements {@link AuthenticationSuccessHandler} and called when
 * client has been successfully authenticated. Responsibility of this class is
 * to add JSON payload containing JWT Access and Refresh tokens into the HTTP
 * response body.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 20, 2017
 */
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private SecurityDetailsService securityDetailsService;
	private ObjectMapper mapper;
	private JwtTokenFactory tokenFactory;

	@Autowired
	public AjaxAwareAuthenticationSuccessHandler(SecurityDetailsService securityDetailsService, ObjectMapper mapper,
			JwtTokenFactory tokenFactory) {
		this.securityDetailsService = securityDetailsService;
		this.mapper = mapper;
		this.tokenFactory = tokenFactory;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtToken accessToken = tokenFactory.createAccessJwtToken(userDetails);
		JwtToken refreshToken = tokenFactory.createRefreshToken(userDetails);

		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("token", accessToken.getToken());
		tokenMap.put("refreshToken", refreshToken.getToken());

		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		// @formatter:off
		mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
				.setData(tokenMap)
				.setGenericStatus(GenericStatus.TOKEN_CREATED)
			.build());
		// @formatter:on

		clearAuthenticationAttributes(request);
	}

	/**
	 * Removes temporary authentication-related data which may have been stored
	 * in the session during the authentication process.
	 * 
	 * @param request
	 *            {@link HttpServletRequest}
	 */
	protected final void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
