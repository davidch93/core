package com.dch.core.security.jwt.model.token;

import com.dch.core.security.jwt.model.Scopes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.List;
import java.util.Optional;

/**
 * Class  used to create and validate refresh token.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.security.jwt.model.token.JwtToken
 * @since 1.0.0
 */
public class RefreshToken implements JwtToken {

    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    /**
     * Creates and validates Refresh token
     *
     * @param token      {@link RawAccessJwtToken}
     * @param signingKey {@link String}
     * @return {@link Optional}&lt;{@link RefreshToken}&gt;
     */
    @SuppressWarnings("unchecked")
    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);

        List<String> scopes = claims.getBody().get("scopes", List.class);
        if (scopes == null || scopes.isEmpty() || scopes.stream().noneMatch(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope)))
            return Optional.empty();

        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }
}
