package com.dch.core.scheduler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Class property source that contains parameter setting of Scheduler and
 * executor configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@PropertySource("classpath:config/scheduler/core-scheduler-config.properties")
@ConfigurationProperties(prefix = "core.scheduler")
public class SchedulerSetting {

    private String identityPrefix = "SCHEDULER";
    private SchedulerExecutorSetting executor;

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
    public SchedulerExecutorSetting getExecutor() {
        return executor;
    }

    /**
     * Set executor.
     *
     * @param executor the executor
     */
    public void setExecutor(SchedulerExecutorSetting executor) {
        this.executor = executor;
    }

    /**
     * Class that contains parameter setting of Scheduler Executor configuration.
     *
     * @author David.Christianto
     * @version 2.0.0
     * @since 1.0.0
     */
    public static class SchedulerExecutorSetting {

        private int poolSize;
        private String threadNamePrefix;

        /**
         * Get pool size.
         *
         * @return the pool size
         */
        public int getPoolSize() {
            return poolSize;
        }

        /**
         * Set pool size.
         *
         * @param poolSize the pool size
         */
        public void setPoolSize(int poolSize) {
            this.poolSize = poolSize;
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