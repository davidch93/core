package com.dch.core.security.jwt.model.token;

/**
 * Interface of JWT.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 20, 2017
 * @since 1.0.0-SNAPSHOT
 */
public interface JwtToken {

    /**
     * Method used to get token.
     *
     * @return Token.
     */
    String getToken();
}
