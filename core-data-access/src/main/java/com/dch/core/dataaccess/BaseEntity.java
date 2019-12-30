package com.dch.core.dataaccess;

import com.dch.core.datastatic.DataStatus;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Class that defined common field that exist in every model and mapped as
 * superclass.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Audited
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", length = 15)
    protected DataStatus status;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    protected String createdBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    protected Date createdDate;

    @Column(name = "modified_by")
    @LastModifiedBy
    protected String modifiedBy;

    @Column(name = "modified_date")
    @LastModifiedDate
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
