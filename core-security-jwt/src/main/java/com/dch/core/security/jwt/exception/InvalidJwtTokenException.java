package com.dch.core.security.jwt.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Class exception that extends {@link AuthenticationServiceException} and
 * thrown if token not valid.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 20, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class InvalidJwtTokenException extends AuthenticationServiceException {

    private static final long serialVersionUID = 5861918607704102665L;

    /**
     * Construct a {@code InvalidJwtTokenException} with a generic message.
     *
     * @param message the message
     */
    public InvalidJwtTokenException(String message) {
        super(message);
    }

    /**
     * Construct a {@code InvalidJwtTokenException} with a generic message and a
     * cause.
     *
     * @param message the message
     * @param cause   the cause of the exception
     */
    public InvalidJwtTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
