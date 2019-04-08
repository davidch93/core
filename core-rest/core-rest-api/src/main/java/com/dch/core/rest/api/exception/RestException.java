package com.dch.core.rest.api.exception;

import com.dch.core.datastatic.GeneralStatus;

/**
 * Class exception that extends {@link RuntimeException} and used to capture all
 * errors in the rest process.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class RestException extends RuntimeException {

    private GeneralStatus generalStatus;

    /**
     * Construct a {@code RestException} with generic status.
     *
     * @param generalStatus {@link GeneralStatus}
     */
    public RestException(GeneralStatus generalStatus) {
        super();
        this.generalStatus = generalStatus;
    }

    /**
     * Construct a {@code RestException} with generic status and a generic
     * message.
     *
     * @param generalStatus {@link GeneralStatus}
     * @param message       the message
     */
    public RestException(GeneralStatus generalStatus, String message) {
        super(message);
        this.generalStatus = generalStatus;
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
     * @param generalStatus {@link GeneralStatus}
     * @param message       the message
     * @param cause         the cause of the exception
     */
    public RestException(GeneralStatus generalStatus, String message, Throwable cause) {
        super(message, cause);
        this.generalStatus = generalStatus;
    }

    /**
     * Method used to get generic status.
     *
     * @return {@link GeneralStatus}
     */
    public GeneralStatus getGeneralStatus() {
        return generalStatus;
    }
}
