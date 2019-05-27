package com.dch.core.retry.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * A convenience retry configurer that used to configure default
 * {@link RetryTemplate} configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@ComponentScan("com.dch.core.retry")
@EnableConfigurationProperties(RetrySetting.class)
public class RetryConfigurerSupport {

    private static final Logger logger = LoggerFactory.getLogger(RetryConfigurerSupport.class);
    private final RetrySetting retrySetting;

    public RetryConfigurerSupport(RetrySetting retrySetting) {
        this.retrySetting = retrySetting;
    }

    /**
     * Bean of {@code RetryTemplate}.
     *
     * @return {@link RetryTemplate}
     */
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.registerListener(retryListener());

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(retrySetting.getBackOfPeriod());
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(retrySetting.getMaxAttempts());
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

    /**
     * Method used to get default retry listener.
     *
     * @return {@link RetryListener}
     */
    protected RetryListener retryListener() {
        return new RetryListener() {

            @Override
            public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback,
                                                       Throwable throwable) {
                logger.info(String.format("[%s] Retry on close", retrySetting.getIdentityPrefix()), throwable);
            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
                                                         Throwable throwable) {
                logger.info(String.format("[%s] Retry on error", retrySetting.getIdentityPrefix()), throwable);
            }
        };
    }
}
