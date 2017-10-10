package com.dch.core.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class that contains parameter setting of Batch configuration.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 18, 2017
 */
@Configuration
@PropertySource("classpath:config/batch/core-batch-config.properties")
@ConfigurationProperties(prefix = "core.batch")
public class BatchSetting {

	private int chunkSize;
	private String identityPrefix = "BATCH";

	/**
	 * @return the chunkSize
	 */
	public int getChunkSize() {
		return chunkSize;
	}

	/**
	 * @param chunkSize
	 *            the chunkSize to set
	 */
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	/**
	 * @return the identityPrefix
	 */
	public String getIdentityPrefix() {
		return identityPrefix;
	}

	/**
	 * @param identityPrefix
	 *            the identityPrefix to set
	 */
	public void setIdentityPrefix(String identityPrefix) {
		this.identityPrefix = identityPrefix;
	}
}
