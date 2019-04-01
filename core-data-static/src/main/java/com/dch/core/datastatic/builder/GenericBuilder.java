package com.dch.core.datastatic.builder;

/**
 * Generic interface for all builder class.
 *
 * @param <T> Object class.
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 23, 2017
 * @since 1.0.0-SNAPSHOT
 */
public interface GenericBuilder<T> {

    /**
     * Method used to build an object.
     *
     * @return Object class.
     */
    T build();
}
