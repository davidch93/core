package com.dch.core.security.jwt.auth.token;

import com.dch.core.security.jwt.auth.JwtAuthenticationToken;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.model.token.JwtToken;
import com.dch.core.security.jwt.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An {@link AuthenticationProvider} implementation that will use provided
 * instance of {@link JwtToken} to perform authentication. This class has the
 * following responsibilities:<br>
 * 1. Verify the access token's signature.<br>
 * 2. Extract identity and authorization claims from Access token and use them
 * to create UserContext.<br>
 * 3. If Access token is malformed, expired or simply if token is not signed
 * with the appropriate signing key Authentication exception will be thrown.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.authentication.AuthenticationProvider
 * @since 1.0.0
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtSetting jwtSettings;

    public JwtAuthenticationProvider(JwtSetting jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        List<GrantedAuthority> authorities = Stream.of(jwsClaims.getBody().get("scopes", String.class).split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new JwtAuthenticationToken(new User(jwsClaims.getBody().getSubject(), "N/A", authorities), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
