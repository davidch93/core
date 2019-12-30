package com.dch.core.security.jwt.service;

import com.dch.core.dto.response.builder.ResponseBuilder;

/**
 * Core interface which validate servlet request data and other oauth2 filter
 * services.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface SecurityDetailsService {

    /**
     * Get response builder to build a generic response.
     *
     * @return the {@link ResponseBuilder}
     */
    ResponseBuilder getSecurityResponseBuilder();
}
