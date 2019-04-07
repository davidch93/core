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
     * Method used to get current auditor.
     *
     * @return Current active user.
     */
    String getCurrentAuditor();
}
