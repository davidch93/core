package com.dch.core.async.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A convenience {@link AsyncConfigurer} that implements all methods so that the
 * defaults are used. Provides a backward compatible alternative of implementing
 * {@link AsyncConfigurer} directly.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.scheduling.annotation.AsyncConfigurer
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.async")
@EnableConfigurationProperties(AsyncSetting.class)
public class AsyncConfigurerSupport implements AsyncConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(AsyncConfigurerSupport.class);
    private final AsyncSetting asyncSetting;

    /**
     * Construct a {@code AsyncConfigurerSupport} with a specified setting.
     *
     * @param asyncSetting the async setting
     */
    public AsyncConfigurerSupport(AsyncSetting asyncSetting) {
        this.asyncSetting = asyncSetting;
    }

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
        return (throwable, method, objects) -> {
            logger.error(String.format("[%s] Error method: %s", asyncSetting.getIdentityPrefix(), method), throwable);
            logger.info(String.format("[%s] Parameter value: [%s]", asyncSetting.getIdentityPrefix(),
                    Stream.of(objects).map(Object::toString).collect(Collectors.joining(", "))));
        };
    }
}
