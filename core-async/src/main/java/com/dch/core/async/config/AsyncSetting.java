package com.dch.core.async.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Property source class that contain parameters setting of Asynchronous and Executor configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "core.async")
public class AsyncSetting {

    private String identityPrefix = "ASYNC";
    private AsyncExecutorSetting executor;

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
     * Get executor.
     *
     * @return the executor
     */
    public AsyncExecutorSetting getExecutor() {
        return executor;
    }

    /**
     * Set executor.
     *
     * @param executor the executor
     */
    public void setExecutor(AsyncExecutorSetting executor) {
        this.executor = executor;
    }

    /**
     * Contains parameter setting of Asynchronous Executor configuration.
     *
     * @author David.Christianto
     * @version 2.0.0
     * @since 1.0.0
     */
    public static class AsyncExecutorSetting {

        private int corePoolSize;
        private int keepAliveSeconds;
        private int maxPoolSize;
        private int queueCapacity;
        private String threadNamePrefix;

        /**
         * Get core pool size.
         *
         * @return the core pool size
         */
        public int getCorePoolSize() {
            return corePoolSize;
        }

        /**
         * Set core pool size.
         *
         * @param corePoolSize the core pool size
         */
        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        /**
         * Get keep alive seconds.
         *
         * @return the keep alive seconds
         */
        public int getKeepAliveSeconds() {
            return keepAliveSeconds;
        }

        /**
         * Set keep alive seconds.
         *
         * @param keepAliveSeconds the keep alive seconds
         */
        public void setKeepAliveSeconds(int keepAliveSeconds) {
            this.keepAliveSeconds = keepAliveSeconds;
        }

        /**
         * Get max pool size.
         *
         * @return the max pool size
         */
        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        /**
         * Set max pool size.
         *
         * @param maxPoolSize the max pool size
         */
        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        /**
         * Get queue capacity.
         *
         * @return the queue capacity
         */
        public int getQueueCapacity() {
            return queueCapacity;
        }

        /**
         * Set queue capacity.
         *
         * @param queueCapacity the queue capacity
         */
        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }

        /**
         * Get thread name prefix.
         *
         * @return the thread name prefix
         */
        public String getThreadNamePrefix() {
            return threadNamePrefix;
        }

        /**
         * Set thread name prefix.
         *
         * @param threadNamePrefix the thread name prefix
         */
        public void setThreadNamePrefix(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }
    }
}
