package com.dch.core.dto;

import java.util.UUID;

/**
 * Base DTO with unique ID.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Apr 23, 2017
 * @since 1.0.0-SNAPSHOT
 */
public abstract class BaseIdDTO extends BaseDTO {

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
