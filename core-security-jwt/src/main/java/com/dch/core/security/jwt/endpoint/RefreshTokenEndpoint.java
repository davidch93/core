package com.dch.core.security.jwt.endpoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.datastatic.response.GenericResponse;
import com.dch.core.security.jwt.auth.token.extractor.TokenExtractor;
import com.dch.core.security.jwt.auth.token.verifier.TokenVerifier;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.exception.InvalidJwtTokenException;
import com.dch.core.security.jwt.model.token.JwtToken;
import com.dch.core.security.jwt.model.token.JwtTokenFactory;
import com.dch.core.security.jwt.model.token.RawAccessJwtToken;
import com.dch.core.security.jwt.model.token.RefreshToken;
import com.dch.core.security.jwt.service.SecurityDetailsService;

/**
 * Controller that manage any API about refresh token endpoint.
 * 
 * @author david.christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 20, 2017
 */
@RestController
public class RefreshTokenEndpoint {

	@Autowired
	private JwtTokenFactory tokenFactory;

	@Autowired
	private JwtSetting jwtSetting;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SecurityDetailsService securityDetailsService;

	@Autowired
	private TokenVerifier tokenVerifier;

	@Autowired
	@Qualifier("jwtTokenExtractor")
	private TokenExtractor tokenExtractor;

	/**
	 * API that used to get new access token by refresh token.
	 * 
	 * @param request
	 *            {@link HttpServletRequest} HTTP Request.
	 * @param response
	 *            {@link HttpServletResponse} HTTP Response.
	 * @return {@link GenericResponse} Response body of token.
	 * @throws IOException
	 * @throws ServletException
	 */
	@GetMapping(value = "/auth/token")
	public GenericResponse refreshToken(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String tokenPayload = tokenExtractor
				.extract(request.getHeader(WebSecuritySupport.JWT_TOKEN_HEADER_PARAM.getValue()));

		RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
		RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSetting.getTokenSigningKey())
				.orElseThrow(() -> new InvalidJwtTokenException("Failed create refresh token!"));

		if (!tokenVerifier.verify(refreshToken.getJti()))
			throw new InvalidJwtTokenException("Refresh token not valid!");

		UserDetails userDetails = userDetailsService.loadUserByUsername(refreshToken.getSubject());
		if (userDetails == null)
			throw new UsernameNotFoundException("User not found: " + refreshToken.getSubject());

		if (userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty())
			throw new InsufficientAuthenticationException("User has no roles assigned");

		JwtToken token = tokenFactory.createAccessJwtToken(userDetails);

		// @formatter:off
		return securityDetailsService.getSecurityResponseBuilder()
				.setData(token)
				.setGenericStatus(GenericStatus.TOKEN_CREATED)
			.build();
		// @formatter:on
	}
}
