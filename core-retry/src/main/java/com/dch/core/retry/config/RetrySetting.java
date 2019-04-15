package com.dch.core.retry.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Class property source that contains parameter setting of Retry configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 */
@PropertySource("classpath:config/retry/core-retry-config.properties")
@ConfigurationProperties(prefix = "core.retry")
public class RetrySetting {

    private String identityPrefix = "RETRY";
    private long backOfPeriod = 1000;
    private int maxAttempts = 10;

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

    /**
     * @return the backOfPeriod
     */
    public long getBackOfPeriod() {
        return backOfPeriod;
    }

    /**
     * @param backOfPeriod the backOfPeriod to set
     */
    public void setBackOfPeriod(long backOfPeriod) {
        this.backOfPeriod = backOfPeriod;
    }

    /**
     * @return the maxAttempts
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * @param maxAttempts the maxAttempts to set
     */
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
