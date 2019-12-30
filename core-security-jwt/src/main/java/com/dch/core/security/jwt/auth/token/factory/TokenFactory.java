package com.dch.core.security.jwt.auth.token.factory;

import com.dch.core.security.jwt.model.token.JwtToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface of Token Factory. Default token use JWT.
 *
 * @author david.christianto
 * @version 2.0.0
 */
public interface TokenFactory {

    /**
     * Factory method for issuing new JWT Tokens.
     *
     * @param userDetails the {@link UserDetails authenticated user details}.
     * @return the {@link JwtToken}
     */
    JwtToken createAccessJwtToken(UserDetails userDetails);

    /**
     * Create refresh token.
     *
     * @param userDetails the {@link UserDetails authenticated user details}.
     * @return the {@link JwtToken}
     */
    JwtToken createRefreshToken(UserDetails userDetails);
}
