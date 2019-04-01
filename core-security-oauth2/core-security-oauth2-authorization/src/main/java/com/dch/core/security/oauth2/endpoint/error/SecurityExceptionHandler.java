package com.dch.core.security.oauth2.endpoint.error;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.response.GenericResponse;
import com.dch.core.security.oauth2.config.AuthorizationSetting;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class that defined security exception handler for all endpoint.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 6, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class SecurityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityExceptionHandler.class);

    @Autowired
    private SecurityDetailsService securityDetailsService;

    @Autowired
    private AuthorizationSetting authorizationSetting;

    /**
     * Method to handle HTTP request method not supported exception.
     *
     * @param ex
     *            {@link HttpRequestMethodNotSupportedException}
     * @return {@link ResponseEntity}&lt;{@link GenericResponse}&gt; Response
     *         body of method not supported.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GenericResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        LOGGER.error(String.format("[%s] %s", authorizationSetting.getIdentityPrefix(), ex.getMessage()), ex);
        // @formatter:off
		return new ResponseEntity<GenericResponse>(securityDetailsService
			.getSecurityResponseBuilder()
				.setGenericStatus(GenericStatus.METHOD_NOT_ALLOWED)
				.setArgs(new String[] {ex.getMethod()})
			.build(), HttpStatus.METHOD_NOT_ALLOWED);
		// @formatter:on
    }

    /**
     * Method to handle Oauth2 exception.
     *
     * @param ex {@link OAuth2Exception}
     * @return {@link ResponseEntity}&lt;{@link GenericResponse}&gt; Response
     * body of oauth2 exception.
     */
    @ExceptionHandler(OAuth2Exception.class)
    public ResponseEntity<GenericResponse> handleOauth2Exception(OAuth2Exception ex) {
        LOGGER.error(String.format("[%s] %s", authorizationSetting.getIdentityPrefix(), ex.getMessage()), ex);
        // @formatter:off
		return new ResponseEntity<GenericResponse>(securityDetailsService
			.getSecurityResponseBuilder(ex).build(), HttpStatus.valueOf(ex.getHttpErrorCode()));
		// @formatter:on
    }
}
