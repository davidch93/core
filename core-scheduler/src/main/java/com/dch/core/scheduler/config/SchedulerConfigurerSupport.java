package com.dch.core.scheduler.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.ErrorHandler;

/**
 * Class that provide scheduler configuration support. This configuration using
 * {@link ThreadPoolTaskScheduler}.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.scheduler")
@EnableConfigurationProperties(SchedulerSetting.class)
public class SchedulerConfigurerSupport {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfigurerSupport.class);
    private final SchedulerSetting schedulerSetting;

    public SchedulerConfigurerSupport(SchedulerSetting schedulerSetting) {
        this.schedulerSetting = schedulerSetting;
    }

    /**
     * Bean of Task Scheduler.
     *
     * @return {@link ThreadPoolTaskScheduler}
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(schedulerSetting.getExecutor().getPoolSize());
        threadPoolTaskScheduler.setThreadNamePrefix(schedulerSetting.getExecutor().getThreadNamePrefix());
        threadPoolTaskScheduler.setErrorHandler(schedulerErrorHandler());
        return threadPoolTaskScheduler;
    }

    /**
     * Get scheduler error handler.
     *
     * @return the {@link ErrorHandler}
     */
    private ErrorHandler schedulerErrorHandler() {
        return throwable -> logger.error(String.format("[%s] Error occurred while executing scheduler job!",
                schedulerSetting.getIdentityPrefix()), throwable);
    }
}
