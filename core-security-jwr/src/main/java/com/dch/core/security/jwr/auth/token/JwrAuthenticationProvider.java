package com.dch.core.security.jwr.auth.token;

import com.dch.core.security.jwr.config.JwrSetting;
import com.dch.core.security.jwr.service.RedisSessionService;
import com.dch.core.security.jwt.auth.JwtAuthenticationToken;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.exception.JwtExpiredTokenException;
import com.dch.core.security.jwt.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author Sayid Sidqi
 */
@Component
public class JwrAuthenticationProvider implements AuthenticationProvider {

	private final JwtSetting jwtSetting;
	private final JwrSetting jwrSetting;
	private final RedisSessionService sessionService;
	
	public JwrAuthenticationProvider(JwtSetting jwtSetting, JwrSetting jwrSetting, RedisSessionService sessionService) {
		this.jwtSetting = jwtSetting;
		this.jwrSetting = jwrSetting;
		this.sessionService = sessionService;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
		Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSetting.getTokenSigningKey());
		String subject = jwsClaims.getBody().getSubject();

		if (!sessionService.sessionExists(subject)) {
			throw new JwtExpiredTokenException("JWT token has deleted from session");
		}

		Date expirationDate = DateUtils.addMinutes(new Date(), jwrSetting.getTokenExpirationTime());
		sessionService.setSession(subject, String.valueOf(expirationDate), expirationDate);

		List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
		List<GrantedAuthority> authorities = scopes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		return new JwtAuthenticationToken(new User(subject, "N/A", authorities), authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
