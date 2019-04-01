package com.dch.core.security.oauth2.service;

import com.dch.core.datastatic.builder.ResponseBuilderHelper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import javax.servlet.http.HttpServletRequest;

/**
 * Core interface which validate servlet request data and other oauth2 filter
 * services.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jul 7, 2017
 * @since 1.0.0-SNAPSHOT
 */
public interface SecurityDetailsService {

    /**
     * Method used to validate request.
     *
     * @param request {@link HttpServletRequest} HTTP Request.
     * @return {@link boolean} true if request is valid and vice versa.
     * @throws AuthenticationException if there are errors during validate request.
     */
    boolean validateRequest(HttpServletRequest request);

    /**
     * Method used to get response builder to build a generic response.
     *
     * @return {@link ResponseBuilderHelper}
     */
    ResponseBuilderHelper getSecurityResponseBuilder();

    /**
     * Method used to get response builder using OAuth2Exception to build a
     * generic response.
     *
     * @param oAuth2Exception {@link OAuth2Exception}
     * @return {@link ResponseBuilderHelper}
     */
    ResponseBuilderHelper getSecurityResponseBuilder(OAuth2Exception oAuth2Exception);
}
