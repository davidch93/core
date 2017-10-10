package com.dch.core.async.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class property source that contains parameter setting of Asynchronous and
 * Executor configuration.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 20, 2017
 */
@Configuration
@PropertySource("classpath:config/async/core-async-config.properties")
@ConfigurationProperties(prefix = "core.async")
public class AsyncSetting {

	private String identityPrefix = "ASYNC";
	private AsyncExecutorSetting executor;

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

	/**
	 * @return the executor
	 */
	public AsyncExecutorSetting getExecutor() {
		return executor;
	}

	/**
	 * @param executor
	 *            the executor to set
	 */
	public void setExecutor(AsyncExecutorSetting executor) {
		this.executor = executor;
	}

	/**
	 * Class that contains parameter setting of Asynchronous Executor configuration.
	 * 
	 * @author David.Christianto
	 * @version 1.0.0-SNAPSHOT
	 * @since 1.0.0-SNAPSHOT
	 * @updated Jun 13, 2017
	 */
	public static class AsyncExecutorSetting {

		private int corePoolSize;
		private int keepAliveSeconds;
		private int maxPoolSize;
		private int queueCapacity;
		private String threadNamePrefix;

		/**
		 * @return the corePoolSize
		 */
		public int getCorePoolSize() {
			return corePoolSize;
		}

		/**
		 * @param corePoolSize
		 *            the corePoolSize to set
		 */
		public void setCorePoolSize(int corePoolSize) {
			this.corePoolSize = corePoolSize;
		}

		/**
		 * @return the keepAliveSeconds
		 */
		public int getKeepAliveSeconds() {
			return keepAliveSeconds;
		}

		/**
		 * @param keepAliveSeconds
		 *            the keepAliveSeconds to set
		 */
		public void setKeepAliveSeconds(int keepAliveSeconds) {
			this.keepAliveSeconds = keepAliveSeconds;
		}

		/**
		 * @return the maxPoolSize
		 */
		public int getMaxPoolSize() {
			return maxPoolSize;
		}

		/**
		 * @param maxPoolSize
		 *            the maxPoolSize to set
		 */
		public void setMaxPoolSize(int maxPoolSize) {
			this.maxPoolSize = maxPoolSize;
		}

		/**
		 * @return the queueCapacity
		 */
		public int getQueueCapacity() {
			return queueCapacity;
		}

		/**
		 * @param queueCapacity
		 *            the queueCapacity to set
		 */
		public void setQueueCapacity(int queueCapacity) {
			this.queueCapacity = queueCapacity;
		}

		/**
		 * @return the threadNamePrefix
		 */
		public String getThreadNamePrefix() {
			return threadNamePrefix;
		}

		/**
		 * @param threadNamePrefix
		 *            the threadNamePrefix to set
		 */
		public void setThreadNamePrefix(String threadNamePrefix) {
			this.threadNamePrefix = threadNamePrefix;
		}
	}
}
