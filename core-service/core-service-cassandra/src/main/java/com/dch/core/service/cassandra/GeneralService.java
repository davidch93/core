package com.dch.core.service.cassandra;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * General Manager that talks to GeneralDao to CRUD POJOs. Extend this interface
 * if you want typesafe (no casting necessary) managers for your domain objects.
 *
 * @param <T>  entity.
 * @param <ID> the primary key for that type.
 * @author david.christianto
 * @version 2.0.0
 */
public interface GeneralService<T, ID extends Serializable> {

    /**
     * Generic method to save an object.
     *
     * @param entity {@link T} the object to save.
     * @return {@link Mono} the updated object
     */
    Mono<T> save(T entity);

    /**
     * Generic method to update an object.
     *
     * @param entity {@link T} the object to save.
     * @return {@link Mono} the updated object
     */
    Mono<T> update(T entity);

    /**
     * Checks for existence of an object of type T using the id arg.
     *
     * @param id the identifier (primary key) of the object to get
     * @return {@code true} if it exists, false if it doesn't
     */
    Mono<Boolean> exists(ID id);

    /**
     * General method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return {@link Mono} a populated object
     */
    Mono<T> get(ID id);

    /**
     * General method used to get all objects of a particular type. This is the
     * same as lookup up all rows in a table.
     *
     * @return {@link Flux} List of populated objects
     */
    Flux<T> getAll();

    /**
     * General method to delete an object based on class and id
     *
     * @param id the identifier (primary key) of the object to delete
     * @return {@link Mono} void.
     */
    Mono<Void> delete(ID id);

    /**
     * General method to delete an object
     *
     * @param entity the object to delete
     * @return {@link Mono} void.
     */
    Mono<Void> delete(T entity);
}
