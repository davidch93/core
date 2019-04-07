package com.dch.core.dataaccess;

import javax.persistence.*;
import java.util.Set;

/**
 * Base entity for tree model.
 *
 * @param <T> Entity class.
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class TreeBaseEntity<T> extends BaseEntity implements Comparable<T> {

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
     * @param level the level to set
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
     * @param parent the parent to set
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
     * @param childrens the childrens to set
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
