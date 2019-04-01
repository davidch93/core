package com.dch.core.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class that contains parameter setting of Cache configuration.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 16, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Configuration
@PropertySource("classpath:config/cache/core-cache-config.properties")
@ConfigurationProperties(prefix = "core.cache")
public class CacheSetting {

    private String identityPrefix = "CACHE";

    /**
     * @return the identityPrefix
     */
    public String getIdentityPrefix() {
        return identityPrefix;
    }

    /**
     * @param identityPrefix the identityPrefix to set
     */
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }
}