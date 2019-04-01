package com.dch.core.dataaccess.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

/**
 * Default configuration for auditing data. You can create custom auditor aware
 * and date time provider to add in this configuration.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Sep 25, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class AuditConfigurerSupport {

    /**
     * Bean of Auditor Provider.
     *
     * @return {@link AuditorAware}
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
