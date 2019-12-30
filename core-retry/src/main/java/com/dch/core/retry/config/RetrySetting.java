package com.dch.core.retry.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class property source that contains parameter setting of Retry configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 */
@ConfigurationProperties(prefix = "core.retry")
public class RetrySetting {

    private String identityPrefix = "RETRY";
    private long backOfPeriod = 1000;
    private int maxAttempts = 10;

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

    /**
     * Get back of period.
     *
     * @return the back of period
     */
    public long getBackOfPeriod() {
        return backOfPeriod;
    }

    /**
     * Set back of period.
     *
     * @param backOfPeriod the back of period
     */
    public void setBackOfPeriod(long backOfPeriod) {
        this.backOfPeriod = backOfPeriod;
    }

    /**
     * Get max attempts.
     *
     * @return the max attempts
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Set max attempts.
     *
     * @param maxAttempts the max attempts
     */
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
