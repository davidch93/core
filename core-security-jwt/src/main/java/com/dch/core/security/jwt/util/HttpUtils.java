package com.dch.core.security.jwt.util;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.builder.ResponseBuilder;
import com.dch.core.security.jwt.auth.token.factory.TokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 *
 * @author Sayid Sidqi
 */
public final class HttpUtils {
	
	private HttpUtils() { }

	public static Map<String, String> createToken(
		Authentication authentication,
		TokenFactory tokenFactory,
		boolean includeRefreshToken
	) {
		Map<String, String> tokens = new HashMap<>();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		tokens.put("token", tokenFactory.createAccessJwtToken(userDetails).getToken());
		tokens.put("refreshToken", tokenFactory.createRefreshToken(userDetails).getToken());
		if (includeRefreshToken) {
			tokens.put("refreshToken", tokenFactory.createRefreshToken(userDetails).getToken());
		}
		return tokens;
	}

	/**
	 * Write token to json.
	 *
	 * @param response - http response
	 * @param mapper - jackson object mapper
	 * @param responseBuilder - builder for building response in json
	 * @param tokens - token map to be written in json
	 * @throws IOException - throws IO exception if fail to write
	 */
	public static void writeToken(
		HttpServletResponse response,
		ObjectMapper mapper,
		ResponseBuilder responseBuilder,
		Map<String, String> tokens
	) throws IOException {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		mapper.writeValue(
			response.getWriter(),
			responseBuilder.setData(tokens).setGeneralStatus(GeneralStatus.TOKEN_CREATED).build()
		);
	}

	/**
	 * Removes temporary authentication-related data
	 * which may have been store in the session during the authentication process.
	 *
	 * @param request {@link HttpServletRequest} - http request
	 */
	public static void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (isNull(session)) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
