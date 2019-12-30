package com.dch.core.service.jpa;

import com.dch.core.dataaccess.TreeBaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Generic Manager that talks to GeneralDao to CRUD POJOs with some tree service.
 *
 * @param <T>  the type of entity.
 * @param <ID> the type of primary key.
 * @author David.Christianto
 * @version 2.0.0
 * @see GenericService
 * @since 1.0.0
 */
public interface GenericTreeService<T extends TreeBaseEntity<T>, ID extends Serializable>
        extends GenericService<T, ID> {

    /**
     * Converts an list of objects into a forest of trees. The returned {@link Set} will contain only the root nodes,
     * with each root having a populated.
     *
     * @param nodes An array of list of node objects, where the Parent property of each node is either null for root
     *              nodes, or an instantiated object with its Id property set.
     * @return the set of objects into a forest of trees.
     */
    Set<T> buildTree(List<T> nodes);
}
