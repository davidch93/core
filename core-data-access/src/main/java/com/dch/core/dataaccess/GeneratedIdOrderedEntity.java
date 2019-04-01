package com.dch.core.dataaccess;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Ordered entity with an auto generated primary key.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 23, 2017
 * @since 1.0.0-SNAPSHOT
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
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }
}
