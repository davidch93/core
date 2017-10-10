package com.dch.core.dataaccess.audit.envers;

import java.io.Serializable;

import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Custom audit tracking that implements {@link EntityTrackingRevisionListener}
 * to get additional information about revision entity.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Sep 26, 2017
 */
public class AuditTrackingRevisionListener implements EntityTrackingRevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		final AuditEntity auditEntity = (AuditEntity) revisionEntity;
		auditEntity.setAuditedBy(getCurrentAuditor());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void entityChanged(Class entityClass, String entityName, Serializable entityId, RevisionType revisionType,
			Object revisionEntity) {
		final AuditEntity auditEntity = (AuditEntity) revisionEntity;
		auditEntity.setEntityName(entityName);
	}

	/**
	 * Method used to get current username from {@link SecurityContext}. If
	 * authentication name not exist, then default auditor will be "Anonymous".
	 * 
	 * @return {@link String} Username.
	 */
	public String getCurrentAuditor() {
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext != null && securityContext.getAuthentication() != null
				? securityContext.getAuthentication().getName() : "Anonymous";
	}
}
