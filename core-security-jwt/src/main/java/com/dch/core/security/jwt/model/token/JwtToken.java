package com.dch.core.security.jwt.model.token;

/**
 * Interface of JWT.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface JwtToken {

    /**
     * Method used to get token.
     *
     * @return Token.
     */
    String getToken();
}
