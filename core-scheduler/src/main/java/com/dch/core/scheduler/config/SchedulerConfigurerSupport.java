package com.dch.core.scheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.ErrorHandler;

import com.dch.core.scheduler.error.SchedulerErrorHandler;

/**
 * Class that provide scheduler configuration support. This configuration using
 * {@link ThreadPoolTaskScheduler}.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 16, 2017
 */
@ComponentScan("com.dch.core.scheduler")
public class SchedulerConfigurerSupport {

	@Autowired
	private SchedulerSetting schedulerSetting;

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
	 * Method used to get scheduler error handler.
	 * 
	 * @return {@link SchedulerErrorHandler}
	 */
	protected ErrorHandler schedulerErrorHandler() {
		return new SchedulerErrorHandler(schedulerSetting.getIdentityPrefix());
	}
}
