package com.dch.core.security.oauth2.service;

import com.dch.core.dto.response.builder.ResponseBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import javax.servlet.http.HttpServletRequest;

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
     * Validate the given HTTP request.
     *
     * @param request the {@link HttpServletRequest} HTTP Request.
     * @return {@code true} if request is valid and vice versa.
     * @throws AuthenticationException if there are errors during validate request.
     */
    boolean validateRequest(HttpServletRequest request);

    /**
     * Get response builder to build a generic response.
     *
     * @return the {@link ResponseBuilder}
     */
    ResponseBuilder getSecurityResponseBuilder();

    /**
     * Get response builder using OAuth2Exception to build a generic response.
     *
     * @param oAuth2Exception the {@link OAuth2Exception}
     * @return the {@link ResponseBuilder}
     */
    ResponseBuilder getSecurityResponseBuilder(OAuth2Exception oAuth2Exception);
}
