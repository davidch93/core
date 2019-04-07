package com.dch.core.security.jwt.model.token;

import com.dch.core.security.jwt.exception.InvalidJwtTokenException;
import com.dch.core.security.jwt.exception.JwtExpiredTokenException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that implements {@link JwtToken} and contains raw access of token.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class RawAccessJwtToken implements JwtToken {

    private static final Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    /**
     * Parses and validates JWT Token signature.
     */
    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            logger.error("Invalid JWT Token", ex);
            throw new InvalidJwtTokenException("Invalid JWT token!", ex);
        } catch (ExpiredJwtException expiredEx) {
            logger.info("JWT Token is expired", expiredEx);
            throw new JwtExpiredTokenException(this, "JWT Token expired!", expiredEx);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
