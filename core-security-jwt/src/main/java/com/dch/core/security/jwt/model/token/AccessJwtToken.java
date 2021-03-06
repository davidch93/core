package com.dch.core.security.jwt.model.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

/**
 * Raw representation of JWT Token.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.security.jwt.model.token.JwtToken
 * @since 1.0.0
 */
public final class AccessJwtToken implements JwtToken {

    private final String rawToken;

    @JsonIgnore
    private Claims claims;

    public AccessJwtToken(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    /**
     * Get token.
     *
     * @return the token
     */
    public String getToken() {
        return this.rawToken;
    }

    /**
     * Get claims.
     *
     * @return the claims
     */
    public Claims getClaims() {
        return claims;
    }
}
