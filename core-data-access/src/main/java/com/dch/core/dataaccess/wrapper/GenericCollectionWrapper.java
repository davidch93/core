package com.dch.core.dataaccess.wrapper;

import java.util.Collection;

/**
 * Wrapper class that holds which type the {@link Collection} holds.
 *
 * @param <T> Object class.
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 13, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class GenericCollectionWrapper<T> {

    private Class<T> genericType;
    private Collection<T> collections;

    public GenericCollectionWrapper(Class<T> c, Collection<T> collections) {
        this.genericType = c;
        this.collections = collections;
    }

    public Class<T> getGenericType() {
        return genericType;
    }

    public Collection<T> getCollections() {
        return collections;
    }
}
