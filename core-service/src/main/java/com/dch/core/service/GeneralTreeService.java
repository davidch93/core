package com.dch.core.service;

import com.dch.core.dataaccess.TreeBaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * General Manager that talks to GeneralDao to CRUD POJOs with some tree service.
 *
 * @param <T>  entity.
 * @param <ID> the primary key for that type.
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.service.GeneralService
 * @since 1.0.0
 */
public interface GeneralTreeService<T extends TreeBaseEntity<T>, ID extends Serializable>
        extends GeneralService<T, ID> {

    /**
     * Converts an list of objects into a forest of trees. The returned
     * {@link Set} will contain only the root nodes, with each root having a
     * populated.
     *
     * @param nodes {@link List}&lt;{@link T}&gt; An array of list of node
     *              objects, where the Parent property of each node is either null
     *              for root nodes, or an instantiated object with its Id property
     *              set.
     * @return {@link Set}&lt;{@link T}&gt; list of objects into a forest of
     * trees.
     */
    Set<T> buildTree(List<T> nodes);
}
