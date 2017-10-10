package com.dch.core.dataaccess.helper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class that implement {@link EntityDTOMapper} and allows behavior to
 * be added to an individual object, either statically or dynamically, without
 * affecting the behavior of other objects
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 23, 2017
 * 
 * @param <E>
 *            Entity type
 * @param <D>
 *            DTO type
 */
public abstract class AbstractEntityDTOMapper<E, D> implements EntityDTOMapper<E, D> {

	@Override
	public List<D> toDtos(Collection<E> entities) {
		return entities.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
	}

	@Override
	public List<E> toEntities(Collection<D> dtos) {
		return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
	}
}
