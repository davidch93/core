package com.dch.core.scheduler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class property source that contains parameter setting of Scheduler and
 * executor configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Configuration
@PropertySource("classpath:config/scheduler/core-scheduler-config.properties")
@ConfigurationProperties(prefix = "core.scheduler")
public class SchedulerSetting {

    private String identityPrefix = "SCHEDULER";
    private SchedulerExecutorSetting executor;

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
     * @return the executor
     */
    public SchedulerExecutorSetting getExecutor() {
        return executor;
    }

    /**
     * @param executor the executor to set
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
         * @return the poolSize
         */
        public int getPoolSize() {
            return poolSize;
        }

        /**
         * @param poolSize the poolSize to set
         */
        public void setPoolSize(int poolSize) {
            this.poolSize = poolSize;
        }

        /**
         * @return the threadNamePrefix
         */
        public String getThreadNamePrefix() {
            return threadNamePrefix;
        }

        /**
         * @param threadNamePrefix the threadNamePrefix to set
         */
        public void setThreadNamePrefix(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }
    }
}