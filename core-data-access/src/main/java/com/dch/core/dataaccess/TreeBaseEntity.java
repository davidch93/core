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
    protected Set<T> children;

    /**
     * Get level.
     *
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Set level.
     *
     * @param level the level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Get parent.
     *
     * @return the parent
     */
    public T getParent() {
        return parent;
    }

    /**
     * Set parent.
     *
     * @param parent the parent
     */
    public void setParent(T parent) {
        this.parent = parent;
    }

    /**
     * Get children.
     *
     * @return the children
     */
    public Set<T> getChildren() {
        return children;
    }

    /**
     * Set children.
     *
     * @param children the children
     */
    public void setChildren(Set<T> children) {
        this.children = children;
    }

    /**
     * Get id.
     *
     * @return the id
     */
    public abstract Long getId();
}
