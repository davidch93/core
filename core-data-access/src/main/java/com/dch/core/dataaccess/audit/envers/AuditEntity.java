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
 * @version 1.0.0
 * @updated Sep 26, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Entity
@RevisionEntity(AuditTrackingRevisionListener.class)
@Table(name = "revinfo", schema = "audit")
public class AuditEntity extends DefaultRevisionEntity {

    private static final long serialVersionUID = 3116427087867358723L;

    private String auditedBy;
    private String entityName;

    /**
     * @return the auditedBy
     */
    public String getAuditedBy() {
        return auditedBy;
    }

    /**
     * @param auditedBy the auditedBy to set
     */
    public void setAuditedBy(String auditedBy) {
        this.auditedBy = auditedBy;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
