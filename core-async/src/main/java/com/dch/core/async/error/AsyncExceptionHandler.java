package com.dch.core.async.error;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 * Custom asynchronous exception handler by implementing
 * {@link AsyncUncaughtExceptionHandler}.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 13, 2017
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncExceptionHandler.class);

	private final String identityPrefix;

	public AsyncExceptionHandler(String identityPrefix) {
		this.identityPrefix = identityPrefix;
	}

	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... object) {
		LOGGER.error(String.format("[%s] Method name - %s", identityPrefix, method.getName()), throwable);
		for (Object param : object) {
			LOGGER.info(String.format("[%s] Parameter value - %s", identityPrefix, param));
		}
	}
}
