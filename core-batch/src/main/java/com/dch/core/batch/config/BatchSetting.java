package com.dch.core.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class that contains parameter setting of Batch configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "core.batch")
public class BatchSetting {

    private int chunkSize;
    private String identityPrefix = "BATCH";

    /**
     * Get chunk size.
     *
     * @return the chunk size
     */
    public int getChunkSize() {
        return chunkSize;
    }

    /**
     * Set chunk size.
     *
     * @param chunkSize the chunk size
     */
    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

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
