package com.dch.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.dch.core.dataaccess.TreeBaseEntity;

/**
 * {@link GenericService} with some tree service.
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
public interface GenericTreeService<T extends TreeBaseEntity<T>, ID extends Serializable>
		extends GenericService<T, ID> {

	/**
	 * Converts an list of objects into a forest of trees. The returned
	 * {@link Set} will contain only the root nodes, with each root having a
	 * populated.
	 * 
	 * @param nodes
	 *            {@link List}&lt;{@link T}&gt; An array of list of node
	 *            objects, where the Parent property of each node is either null
	 *            for root nodes, or an instantiated object with its Id property
	 *            set.
	 * @return {@link Set}&lt;{@link T}&gt; list of objects into a forest of
	 *         trees.
	 */
	Set<T> buildTree(List<T> nodes);
}
