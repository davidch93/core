package com.dch.core.dto;

import com.dch.core.datastatic.DataStatus;

import java.util.Date;

/**
 * Class that define common field that exist in every data transfer object.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public abstract class BaseDTO {

    protected DataStatus status;
    protected String createdBy;
    protected Date createdDate;
    protected String modifiedBy;
    protected Date modifiedDate;

    /**
     * Get status.
     *
     * @return the status
     */
    public DataStatus getStatus() {
        return status;
    }

    /**
     * Set status.
     *
     * @param status the status
     */
    public void setStatus(DataStatus status) {
        this.status = status;
    }

    /**
     * Get created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set created by.
     *
     * @param createdBy the created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Set created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Get modified by.
     *
     * @return the modified by
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Set modified by.
     *
     * @param modifiedBy the modified by
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Get modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Set modified date.
     *
     * @param modifiedDate the modified date
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
