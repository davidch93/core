package com.dch.core.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Contains parameter setting of Cache configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "core.cache")
public class CacheSetting {

    private String identityPrefix = "CACHE";

    /**
     * Get identity prefix.
     *
     * @return the identity prefix
     */
    public String getIdentityPrefix() {
        return identityPrefix;
    }

    /**
     * Set identity prefix.
     *
     * @param identityPrefix the identity prefix
     */
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }
}