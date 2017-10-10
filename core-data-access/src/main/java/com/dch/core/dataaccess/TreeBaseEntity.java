package com.dch.core.dataaccess;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Base entity for tree model.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 13, 2017
 * @param <T>
 *            Entity class.
 */
@MappedSuperclass
public abstract class TreeBaseEntity<T> extends BaseEntity implements Comparable<T> {

	private static final long serialVersionUID = 7689262902084055454L;

	@Column(name = "level", nullable = false)
	protected Integer level;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	protected T parent;

	@Transient
	protected Set<T> childrens;

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the parent
	 */
	public T getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(T parent) {
		this.parent = parent;
	}

	/**
	 * @return the childrens
	 */
	public Set<T> getChildrens() {
		return childrens;
	}

	/**
	 * @param childrens
	 *            the childrens to set
	 */
	public void setChildrens(Set<T> childrens) {
		this.childrens = childrens;
	}

	/**
	 * Method used to get unique id.
	 * 
	 * @return {@link Long} Unique ID.
	 */
	public abstract Long getId();
}
