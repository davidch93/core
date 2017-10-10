package com.dch.core.security.jwt.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Class exception that extends {@link AuthenticationServiceException} and
 * thrown if an authentication request could not be processed due to a system
 * problem.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 20, 2017
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {

	private static final long serialVersionUID = 772605467200346604L;

	/**
	 * Construct a {@code AuthMethodNotSupportedException} with a generic
	 * message.
	 * 
	 * @param message
	 *            the message
	 */
	public AuthMethodNotSupportedException(String message) {
		super(message);
	}

	/**
	 * Construct a {@code AuthMethodNotSupportedException} with a generic
	 * message and a cause.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause of the exception
	 */
	public AuthMethodNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}
}
