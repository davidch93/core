package com.dch.core.dataaccess.audit.service;

/**
 * Class that provide various service to support audit process. Implement this interface
 * if you want to create custom audit services.
 *
 * @author David.Christianto
 * @version 2.0.0
 */
public interface AuditService {

    /**
     * Get current auditor.
     *
     * @return the current active user.
     */
    String getCurrentAuditor();
}
