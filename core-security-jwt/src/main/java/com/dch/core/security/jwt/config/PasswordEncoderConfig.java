package com.dch.core.security.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class that used to configure custom password encoder.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * Bean of BCrypt password encoder.
     *
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
