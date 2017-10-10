package com.dch.core.dataaccess;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Ordered entity with an auto generated primary key.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Apr 23, 2017
 */
@MappedSuperclass
public abstract class GeneratedIdOrderedEntity extends OrderedEntity {

	private static final long serialVersionUID = 7315892858786144045L;

	@Id
	@GeneratedValue
	protected UUID id;

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
}
