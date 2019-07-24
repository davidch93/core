package com.dch.core.security.jwt.auth.token.factory;

import com.dch.core.common.util.DateUtil;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.model.Scopes;
import com.dch.core.security.jwt.model.token.AccessJwtToken;
import com.dch.core.security.jwt.model.token.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Factory class that should be always used to create {@link JwtToken}.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Component("jwtTokenFactory")
public class JwtTokenFactory implements TokenFactory {

    private final JwtSetting settings;

    public JwtTokenFactory(JwtSetting settings) {
        this.settings = settings;
    }

    @Override
    public JwtToken createAccessJwtToken(UserDetails userDetails) {
        Assert.hasLength(userDetails.getUsername(), "Cannot create JWT Token without username!");
        Assert.notEmpty(userDetails.getAuthorities(), "User doesn't have any privileges!");

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("scopes", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")));

        Date currentDate = new Date();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(currentDate)
                .setExpiration(DateUtil.addMinute(currentDate, settings.getTokenExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    @Override
    public JwtToken createRefreshToken(UserDetails userDetails) {
        Assert.hasLength(userDetails.getUsername(), "Cannot create JWT Token without username!");

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("scopes", Collections.singletonList(Scopes.REFRESH_TOKEN.authority()));

        Date currentDate = new Date();
        String token = Jwts.builder()
                .setClaims(claims).setIssuer(settings.getTokenIssuer())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentDate)
                .setExpiration(DateUtil.addMinute(currentDate, settings.getRefreshTokenExpTime()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}
