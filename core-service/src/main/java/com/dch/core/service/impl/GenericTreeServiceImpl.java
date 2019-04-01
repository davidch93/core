package com.dch.core.service.impl;

import com.dch.core.dataaccess.TreeBaseEntity;
import com.dch.core.service.GenericTreeService;

import java.io.Serializable;
import java.util.*;

/**
 * Implementation of {@link GenericServiceImpl} with tree service.This class
 * implements {@link GenericTreeService}.
 *
 * @param <T>  entity.
 * @param <ID> the primary key for that type.
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 13, 2017
 * @since 1.0.0-SNAPSHOT
 */
public abstract class GenericTreeServiceImpl<T extends TreeBaseEntity<T>, ID extends Serializable>
        extends GenericServiceImpl<T, ID> implements GenericTreeService<T, ID> {

    @Override
    public Set<T> buildTree(List<T> nodes) {
        Map<Long, T> dictionary = new HashMap<>();
        nodes.forEach(node -> {
            dictionary.put(node.getId(), node);
            node.setChildrens(new TreeSet<>());
        });

        Set<T> rootNodes = new TreeSet<>();
        nodes.forEach(node -> {
            if (node.getParent() == null)
                rootNodes.add(node);
            else {
                if (!dictionary.containsKey(node.getParent().getId()))
                    return;

                node.setParent(dictionary.get(node.getParent().getId()));
                node.getParent().getChildrens().add(node);
            }
        });

        return rootNodes;
    }
}
