package com.dch.core.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.dch.core.cache.error.CachingErrorHandler;

/**
 * Class that implements {@link CachingConfigurer} to provide cache
 * configuration support.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 22, 2017
 */
@ComponentScan("com.dch.core.cache")
public class CachingConfigurerSupport implements CachingConfigurer {

	@Autowired
	private CacheSetting cacheSetting;

	/**
	 * Bean of Cache Manager.<br/>
	 * {@inheritDoc}
	 * 
	 * @return {@link SimpleCacheManager}
	 */
	@Bean
	@Override
	public CacheManager cacheManager() {
		final SimpleCacheManager cacheManager = new SimpleCacheManager();
		return cacheManager;
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return new CachingErrorHandler(cacheSetting.getIdentityPrefix());
	}
}
