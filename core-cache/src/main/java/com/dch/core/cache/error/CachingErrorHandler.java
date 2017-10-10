package com.dch.core.cache.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * Custom scheduler error handler by implementing {@link CacheErrorHandler}.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 16, 2017
 */
public class CachingErrorHandler implements CacheErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CachingErrorHandler.class);

	public final String identityPrefix;

	public CachingErrorHandler(final String identityPrefix) {
		this.identityPrefix = identityPrefix;
	}

	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
		LOGGER.error(String.format("[%s] %s", identityPrefix, cache.getName()), exception);
	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
		LOGGER.error(String.format("[%s] %s", identityPrefix, cache.getName()), exception);
	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
		LOGGER.error(String.format("[%s] %s", identityPrefix, cache.getName()), exception);
	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		LOGGER.error(String.format("[%s] %s", identityPrefix, cache.getName()), exception);
	}
}
