package com.dch.core.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.dch.core.dataaccess.TreeBaseEntity;
import com.dch.core.service.GenericTreeService;

/**
 * Implementation of {@link GenericServiceImpl} with tree service.This class
 * implements {@link GenericTreeService}.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 13, 2017
 * @param <T>
 *            entity.
 * @param <ID>
 *            the primary key for that type.
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
