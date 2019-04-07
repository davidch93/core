package com.dch.core.dataaccess;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Ordered entity with an auto generated primary key.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class GeneratedIdOrderedEntity extends OrderedEntity {

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
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }
}
