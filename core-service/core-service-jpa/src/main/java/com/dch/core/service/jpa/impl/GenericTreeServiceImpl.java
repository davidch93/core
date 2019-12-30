package com.dch.core.service.jpa.impl;

import com.dch.core.dataaccess.TreeBaseEntity;
import com.dch.core.service.jpa.GenericTreeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.*;

/**
 * Implementation of {@link GenericServiceImpl} with tree service.
 *
 * @param <T>  entity.
 * @param <ID> the primary key for that type.
 * @author David.Christianto
 * @version 2.0.0
 * @see GenericTreeService
 * @since 1.0.0
 */
public abstract class GenericTreeServiceImpl<T extends TreeBaseEntity<T>, ID extends Serializable>
        extends GenericServiceImpl<T, ID> implements GenericTreeService<T, ID> {

    public GenericTreeServiceImpl(JpaRepository<T, ID> repository) {
        super(repository);
    }

    @Override
    public Set<T> buildTree(List<T> nodes) {
        Map<Long, T> dictionary = new HashMap<>();
        nodes.forEach(node -> {
            dictionary.put(node.getId(), node);
            node.setChildren(new TreeSet<>());
        });

        Set<T> rootNodes = new TreeSet<>();
        nodes.forEach(node -> {
            if (node.getParent() == null)
                rootNodes.add(node);
            else {
                if (!dictionary.containsKey(node.getParent().getId()))
                    return;

                node.setParent(dictionary.get(node.getParent().getId()));
                node.getParent().getChildren().add(node);
            }
        });

        return rootNodes;
    }
}
