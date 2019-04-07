package com.dch.core.dataaccess.audit.envers;

import com.dch.core.dataaccess.audit.service.AuditService;
import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Custom audit tracking that implements {@link EntityTrackingRevisionListener}
 * to get additional information about revision entity.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.hibernate.envers.EntityTrackingRevisionListener
 * @since 1.0.0
 */
@Component
public class AuditTrackingRevisionListener implements EntityTrackingRevisionListener {

    private final AuditService auditService;

    public AuditTrackingRevisionListener(AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public void newRevision(Object revisionEntity) {
        final AuditEntity auditEntity = (AuditEntity) revisionEntity;
        auditEntity.setAuditedBy(auditService.getCurrentAuditor());
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void entityChanged(Class entityClass, String entityName, Serializable entityId, RevisionType revisionType,
                              Object revisionEntity) {
        final AuditEntity auditEntity = (AuditEntity) revisionEntity;
        auditEntity.setEntityName(entityName);
    }
}
