package com.dch.core.security.jwt.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Class exception that thrown if token not valid.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.authentication.AuthenticationServiceException
 * @since 1.0.0
 */
public class InvalidJwtTokenException extends AuthenticationServiceException {

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
