package com.dch.core.dataaccess.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Auditing the Author of Changes with Spring Security.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Sep 26, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * {@inheritDoc} <br/>
     * Getting current auditor from {@link SecurityContext#getAuthentication()}.
     */
    @Override
    public String getCurrentAuditor() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext != null && securityContext.getAuthentication() != null
                ? securityContext.getAuthentication().getName() : "Anonymous";
    }
}
