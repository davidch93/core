package com.dch.core.service.exception;

/**
 * Class exception that extends {@link RuntimeException} and used to capture all
 * errors in the service process. This class is root of exception hierarchy for
 * checked exceptions in service.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class ServiceException extends RuntimeException {

    /**
     * Construct a {@code ServiceException} with a generic message.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Construct a {@code ServiceException} with a generic message and a cause.
     *
     * @param message the message
     * @param cause   the cause of the exception
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
