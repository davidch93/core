package com.dch.core.security.jwt.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Class exception that thrown if an authentication request could not be processed due to a system problem.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.authentication.AuthenticationServiceException
 * @since 1.0.0
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {

    /**
     * Construct a {@code AuthMethodNotSupportedException} with a generic message.
     *
     * @param message the message
     */
    public AuthMethodNotSupportedException(String message) {
        super(message);
    }

    /**
     * Construct a {@code AuthMethodNotSupportedException} with a generic message and a cause.
     *
     * @param message the message
     * @param cause   the cause of the exception
     */
    public AuthMethodNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
