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
     * Build an object.
     *
     * @return the specific object.
     */
    T build();
}
