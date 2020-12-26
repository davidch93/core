package com.dch.core.cache.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;
import static net.logstash.logback.argument.StructuredArguments.v;

/**
 * An implementation of {@link CachingConfigurer} with empty methods allowing
 * sub-classes to override only the methods they're interested in.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.cache.annotation.CachingConfigurer
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.cache")
@EnableConfigurationProperties(CacheSetting.class)
public class CachingConfigurerSupport implements CachingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(CachingConfigurerSupport.class);
    private final CacheSetting cacheSetting;

    public CachingConfigurerSupport(CacheSetting cacheSetting) {
        this.cacheSetting = cacheSetting;
    }

    /**
     * Bean of {@link CacheManager}. We used {@link SimpleCacheManager} as default.
     *
     * @return {@link SimpleCacheManager}
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        return new SimpleCacheManager();
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
        return new CacheErrorHandler() {

            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                Map<String, Object> entries = new HashMap<>(2);
                entries.put("cache", cache);
                entries.put("key", key);
                logger.error("[{}] Error occurred while getting cache! The details are {}",
                        cacheSetting.getIdentityPrefix(), e(entries), exception);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                Map<String, Object> entries = new HashMap<>(3);
                entries.put("cache", cache);
                entries.put("key", key);
                entries.put("value", value);
                logger.error("[{}] Error occurred while putting cache! The details are {}",
                        cacheSetting.getIdentityPrefix(), e(entries), exception);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                Map<String, Object> entries = new HashMap<>(2);
                entries.put("cache", cache);
                entries.put("key", key);
                logger.error("[{}] Error occurred while evicting cache! The details are {}",
                        cacheSetting.getIdentityPrefix(), e(entries), exception);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                logger.error("[{}] Error occurred while clearing cache! The details are {}",
                        cacheSetting.getIdentityPrefix(), v("cache", cache), exception);
            }
        };
    }
}
