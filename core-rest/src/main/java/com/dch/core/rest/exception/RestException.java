package com.dch.core.rest.exception;

import com.dch.core.datastatic.GenericStatus;

/**
 * Class exception that extends {@link RuntimeException} and used to capture all
 * errors in the rest process.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 25, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class RestException extends RuntimeException {

    private static final long serialVersionUID = -6072014895614093858L;

    private GenericStatus genericStatus;

    /**
     * Construct a {@code RestException} with generic status.
     *
     * @param genericStatus {@link GenericStatus}
     */
    public RestException(GenericStatus genericStatus) {
        super();
        this.genericStatus = genericStatus;
    }

    /**
     * Construct a {@code RestException} with generic status and a generic
     * message.
     *
     * @param genericStatus {@link GenericStatus}
     * @param message       the message
     */
    public RestException(GenericStatus genericStatus, String message) {
        super(message);
        this.genericStatus = genericStatus;
    }

    /**
     * Construct a {@code RestException} with a generic message.
     *
     * @param message the message
     * @param cause   the cause of the exception
     */
    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construct a {@code RestException} with generic status, a generic message
     * and a cause.
     *
     * @param responseStatus {@link GenericStatus}
     * @param message        the message
     * @param cause          the cause of the exception
     */
    public RestException(GenericStatus genericStatus, String message, Throwable cause) {
        super(message, cause);
        this.genericStatus = genericStatus;
    }

    /**
     * Method used to get generic status.
     *
     * @return {@link GenericStatus}
     */
    public GenericStatus getGenericStatus() {
        return genericStatus;
    }
}
