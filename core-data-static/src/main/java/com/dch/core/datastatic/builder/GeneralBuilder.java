package com.dch.core.datastatic.builder;

/**
 * General interface for all builder class.
 *
 * @param <T> Object class.
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface GeneralBuilder<T> {

    /**
     * Method used to build an object.
     *
     * @return Object class.
     */
    T build();
}
