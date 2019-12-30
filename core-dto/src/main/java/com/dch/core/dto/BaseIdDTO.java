package com.dch.core.dto;

import java.util.UUID;

/**
 * Base DTO with unique ID.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public abstract class BaseIdDTO extends BaseDTO {

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
