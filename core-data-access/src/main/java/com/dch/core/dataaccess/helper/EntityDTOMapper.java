package com.dch.core.dataaccess.helper;

import java.util.Collection;
import java.util.List;

/**
 * Entity to DTO mapper and used to copy BaseDTO to BaseEntity properties and
 * vice versa.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 23, 2017
 * 
 * @param <E>
 *            entity type
 * @param <D>
 *            DTO type
 */
public interface EntityDTOMapper<E, D> {

	/**
	 * Method used covert entity to DTO.
	 * 
	 * @param entity
	 *            {@link E} data entity object.
	 * @return {@link D} data transfer object.
	 */
	D toDto(E entity);

	/**
	 * Method used covert DTO to entity.
	 * 
	 * @param dto
	 *            {@link D} data transfer object.
	 * @return {@link E} data entity object.
	 */
	E toEntity(D dto);

	/**
	 * Method used covert list of entity to list of DTO.
	 * 
	 * @param entities
	 *            {@link Collection}&lt;{@link E}&gt; list of data entity
	 *            object.
	 * @return {@link List}&lt;{@link D}&gt; list of data transfer object.
	 */
	List<D> toDtos(Collection<E> entities);

	/**
	 * Method used to convert list of DTO to list of entity.
	 * 
	 * @param dtos
	 *            {@link Collection}&lt;{@link D}&gt; list of data transfer
	 *            object.
	 * @return {@link List}&lt;{@link E}&gt; list of data entity object.
	 */
	List<E> toEntities(Collection<D> dtos);
}
