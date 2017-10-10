package com.dch.core.batch.exception;

/**
 * Class exception that extends {@link RuntimeException} and thrown if there are
 * errors during batch process.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 23, 2017
 */
public class BatchException extends RuntimeException {

	private static final long serialVersionUID = 1410455517920997526L;

	/**
	 * Construct a {@code BatchException} with a generic message.
	 * 
	 * @param message
	 *            the message
	 */
	public BatchException(String message) {
		super(message);
	}

	/**
	 * Construct a {@code BatchException} with a generic message and a cause.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause of the exception
	 */
	public BatchException(String message, Throwable cause) {
		super(message, cause);
	}
}
