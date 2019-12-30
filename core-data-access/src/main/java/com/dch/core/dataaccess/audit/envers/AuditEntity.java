package com.dch.core.dataaccess.audit.envers;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Stores the basic revision information (revision number, timestamp) with a
 * custom value of the user who was executing the change and entity name.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Entity
@RevisionEntity(AuditTrackingRevisionListener.class)
@Table(name = "revinfo", schema = "audit")
public class AuditEntity extends DefaultRevisionEntity {

    private String auditedBy;
    private String entityName;

    /**
     * Get audited by.
     *
     * @return the audited by
     */
    public String getAuditedBy() {
        return auditedBy;
    }

    /**
     * Set audited by.
     *
     * @param auditedBy the audited by
     */
    public void setAuditedBy(String auditedBy) {
        this.auditedBy = auditedBy;
    }

    /**
     * Get entity name.
     *
     * @return the entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Set entity name.
     *
     * @param entityName the entity name
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
