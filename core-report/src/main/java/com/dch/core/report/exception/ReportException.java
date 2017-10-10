package com.dch.core.report.exception;

/**
 * Class exception that extends {@link RuntimeException} and thrown if there are
 * errors during the report generation process.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 20, 2017
 */
public class ReportException extends RuntimeException {

	private static final long serialVersionUID = -895885280233649504L;

	/**
	 * Construct a {@code ReportException} with a generic message.
	 * 
	 * @param message
	 *            the message
	 */
	public ReportException(String message) {
		super(message);
	}

	/**
	 * Construct a {@code ReportException} with a generic message and a cause.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause of the exception
	 */
	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}
}
