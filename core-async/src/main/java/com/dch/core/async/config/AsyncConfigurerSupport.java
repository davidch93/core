package com.dch.core.async.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.dch.core.async.error.AsyncExceptionHandler;

/**
 * A convenience {@link AsyncConfigurer} that implements all methods so that the
 * defaults are used. Provides a backward compatible alternative of implementing
 * {@link AsyncConfigurer} directly.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 13, 2017
 */
@ComponentScan("com.dch.core.async")
public class AsyncConfigurerSupport implements AsyncConfigurer {

	@Autowired
	private AsyncSetting asyncSetting;

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(asyncSetting.getExecutor().getCorePoolSize());
		executor.setKeepAliveSeconds(asyncSetting.getExecutor().getKeepAliveSeconds());
		executor.setMaxPoolSize(asyncSetting.getExecutor().getMaxPoolSize());
		executor.setQueueCapacity(asyncSetting.getExecutor().getQueueCapacity());
		executor.setThreadNamePrefix(asyncSetting.getExecutor().getThreadNamePrefix());
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncExceptionHandler(asyncSetting.getIdentityPrefix());
	}
}
