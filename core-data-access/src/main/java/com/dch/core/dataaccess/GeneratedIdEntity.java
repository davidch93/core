package com.dch.core.dataaccess;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Base entity with an auto generated primary key.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 23, 2017
 * @since 1.0.0-SNAPSHOT
 */
@MappedSuperclass
public abstract class GeneratedIdEntity extends BaseEntity {

    private static final long serialVersionUID = -2841374112138370596L;

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
