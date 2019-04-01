package com.dch.core.security.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;

/**
 * Class that used to configure custom token extractor.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 12, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Configuration
public class TokenExtractorConfig {

    /**
     * Bean of Bearer Token Extractor.
     *
     * @return {@link BearerTokenExtractor}
     */
    @Bean
    public BearerTokenExtractor tokenExtractor() {
        return new BearerTokenExtractor();
    }
}
