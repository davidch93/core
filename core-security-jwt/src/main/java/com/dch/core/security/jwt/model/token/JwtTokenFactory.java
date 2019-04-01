package com.dch.core.security.jwt.model.token;

import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.model.Scopes;
import com.dch.core.util.TextUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Factory class that should be always used to create {@link JwtToken}.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 20, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Component
public class JwtTokenFactory {

    private final JwtSetting settings;

    @Autowired
    public JwtTokenFactory(JwtSetting settings) {
        this.settings = settings;
    }

    /**
     * Factory method for issuing new JWT Tokens.
     *
     * @param userDetails
     *            {@link UserDetails}
     * @return {@link AccessJwtToken}
     */
    public AccessJwtToken createAccessJwtToken(UserDetails userDetails) {
        if (TextUtil.isEmpty(userDetails.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty())
            throw new IllegalArgumentException("User doesn't have any privileges");

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("scopes", userDetails.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        DateTime currentTime = new DateTime();

        // @formatter:off
		String token = Jwts
			.builder()
				.setClaims(claims)
				.setIssuer(settings.getTokenIssuer())
				.setIssuedAt(currentTime.toDate())
				.setExpiration(currentTime.plusMinutes(settings.getTokenExpirationTime()).toDate())
				.signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
			.compact();
		// @formatter:on

        return new AccessJwtToken(token, claims);
    }

    /**
     * Method used to create refresh token.
     *
     * @param userDetails {@link UserDetails}
     * @return {@link JwtToken}
     */
    public JwtToken createRefreshToken(UserDetails userDetails) {
        if (TextUtil.isEmpty(userDetails.getUsername()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        DateTime currentTime = new DateTime();

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));

        // @formatter:off
		String token = Jwts
			.builder()
				.setClaims(claims).setIssuer(settings.getTokenIssuer())
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(currentTime.toDate())
				.setExpiration(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).toDate())
				.signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
			.compact();
		// @formatter:on

        return new AccessJwtToken(token, claims);
    }
}
