package com.dch.core.scheduler.exception;

/**
 * Class exception that extends {@link RuntimeException} and thrown if there are
 * errors during the schedule process.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 24, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class SchedulerException extends RuntimeException {

    private static final long serialVersionUID = 8134567604679084904L;

    /**
     * Construct a {@code SchedulerException} with a generic message.
     *
     * @param message the message
     */
    public SchedulerException(String message) {
        super(message);
    }

    /**
     * Construct a {@code SchedulerException} with a generic message and a
     * cause.
     *
     * @param message the message
     * @param cause   the cause of the exception
     */
    public SchedulerException(String message, Throwable cause) {
        super(message, cause);
    }
}
