package com.dch.core.datastatic.builder;

/**
 * Generic interface for all builder class.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 23, 2017
 * @param <T>
 *            Object class.
 */
public interface GenericBuilder<T> {

	/**
	 * Method used to build an object.
	 * 
	 * @return Object class.
	 */
	T build();
}
