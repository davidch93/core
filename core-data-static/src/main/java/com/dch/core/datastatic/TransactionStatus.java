package com.dch.core.datastatic;

/**
 * List of Transaction Status in application.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 23, 2017
 */
public enum TransactionStatus {

	/**
	 * Status for approved transaction.
	 */
	APPROVED,

	/**
	 * Status for canceled transaction.
	 */
	CANCELED,

	/**
	 * Status for new transaction.
	 */
	CREATED,

	/**
	 * Status for deleted transaction.
	 */
	DELETED,

	/**
	 * Status for expired transaction.
	 */
	EXPIRED,

	/**
	 * Status for failed transaction.
	 */
	FAILED,

	/**
	 * Status for transaction not processed.
	 */
	NO_RESPONSE,

	/**
	 * Status for pending transaction.
	 */
	PENDING,

	/**
	 * Status for rejected transaction.
	 */
	REJECTED,

	/**
	 * Status for submitted transaction.
	 */
	SUBMITTED,

	/**
	 * Status for updated transaction.
	 */
	UPDATED,

	/**
	 * Status for waiting transaction.
	 */
	WAITING;
}
