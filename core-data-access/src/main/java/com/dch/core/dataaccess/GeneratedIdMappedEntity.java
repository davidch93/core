package com.dch.core.dataaccess;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Base mapped entity with an auto generated primary key.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.dataaccess.BaseMappedEntity
 */
@MappedSuperclass
public abstract class GeneratedIdMappedEntity extends BaseMappedEntity {

    @Id
    @GeneratedValue
    protected UUID id;

    /**
     * Get id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(UUID id) {
        this.id = id;
    }
}
