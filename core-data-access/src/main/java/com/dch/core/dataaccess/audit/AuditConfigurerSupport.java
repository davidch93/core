package com.dch.core.dataaccess.audit;

import com.dch.core.dataaccess.audit.service.AuditService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Default configuration for auditing data. You can create custom auditor aware
 * and date time provider to add in this configuration. Auditing the Author of Changes with Spring Security.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.dataaccess.audit")
public abstract class AuditConfigurerSupport {

    private final AuditService auditService;

    protected AuditConfigurerSupport(AuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * Bean of Auditor Provider.
     *
     * @return {@link AuditorAware}
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(auditService.getCurrentAuditor());
    }
}
