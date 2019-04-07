package com.dch.core.service.impl;

import com.dch.core.dataaccess.TreeBaseEntity;
import com.dch.core.service.GeneralTreeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.*;

/**
 * Implementation of {@link GeneralServiceImpl} with tree service.
 *
 * @param <T>  entity.
 * @param <ID> the primary key for that type.
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.service.GeneralService
 * @see com.dch.core.service.GeneralTreeService
 * @since 1.0.0
 */
public abstract class GeneralTreeServiceImpl<T extends TreeBaseEntity<T>, ID extends Serializable>
        extends GeneralServiceImpl<T, ID> implements GeneralTreeService<T, ID> {

    public GeneralTreeServiceImpl(JpaRepository<T, ID> repository) {
        super(repository);
    }

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
