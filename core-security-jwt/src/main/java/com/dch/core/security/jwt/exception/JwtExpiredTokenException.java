package com.dch.core.security.jwt.exception;

import com.dch.core.security.jwt.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

/**
 * Class exception that extends {@link AuthenticationException} and thrown if
 * security token had been expired.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 20, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class JwtExpiredTokenException extends AuthenticationException {

    private static final long serialVersionUID = 594131875553964846L;

    private JwtToken token;

    /**
     * Construct a {@code JwtExpiredTokenException} with a generic message.
     *
     * @param message the message
     */
    public JwtExpiredTokenException(String message) {
        super(message);
    }

    /**
     * Construct a {@code JwtExpiredTokenException} with token, a generic
     * message and a cause.
     *
     * @param token   {@link JwtToken} token
     * @param message the message
     * @param cause   the cause of the exception
     */
    public JwtExpiredTokenException(JwtToken token, String message, Throwable cause) {
        super(message, cause);
        this.token = token;
    }

    /**
     * Method used to get JWT token.
     *
     * @return {@link String} Token.
     */
    public String token() {
        return token.getToken();
    }
}
