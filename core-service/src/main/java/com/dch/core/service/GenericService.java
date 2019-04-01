package com.dch.core.service;

import com.dch.core.dataaccess.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Generic Manager that talks to GenericDao to CRUD POJOs. Extend this interface
 * if you want typesafe (no casting necessary) managers for your domain objects.
 *
 * @param <T>  entity.
 * @param <ID> the primary key for that type.
 * @author david.christianto
 * @version 0.0.1-SNAPSHOT
 * @updated Aug 29, 2016
 * @since 0.0.1
 */
public interface GenericService<T extends BaseEntity, ID extends Serializable> {

    /**
     * Generic method to save an object.
     *
     * @param entity {@link T} the object to save.
     * @return the updated object
     */
    Optional<T> save(T entity);

    /**
     * Generic method to update an object.
     *
     * @param entity {@link T} the object to save.
     * @return the updated object
     */
    Optional<T> update(T entity);

    /**
     * Checks for existence of an object of type T using the id arg.
     *
     * @param id the identifier (primary key) of the object to get
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(ID id);

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     */
    Optional<T> get(ID id);

    /**
     * Generic method used to get all objects of a particular type. This is the
     * same as lookup up all rows in a table.
     *
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Generic method to delete an object based on class and id
     *
     * @param id the identifier (primary key) of the object to delete
     */
    void delete(ID id);

    /**
     * Generic method to delete an object
     *
     * @param entity the object to delete
     */
    void delete(T entity);

    /**
     * Generic method to remove an object based on class and id
     *
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(ID id);

    /**
     * Generic method to remove an object
     *
     * @param entity the object to remove
     */
    void remove(T entity);
}
