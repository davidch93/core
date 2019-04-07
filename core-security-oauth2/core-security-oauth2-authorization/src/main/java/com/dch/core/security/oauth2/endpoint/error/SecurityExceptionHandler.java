package com.dch.core.security.oauth2.endpoint.error;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.GeneralResponse;
import com.dch.core.security.oauth2.config.AuthorizationSetting;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @version 2.0.0
 * @since 1.0.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class SecurityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(SecurityExceptionHandler.class);

    private final SecurityDetailsService securityDetailsService;
    private final AuthorizationSetting authorizationSetting;

    public SecurityExceptionHandler(SecurityDetailsService securityDetailsService,
                                    AuthorizationSetting authorizationSetting) {
        this.securityDetailsService = securityDetailsService;
        this.authorizationSetting = authorizationSetting;
    }

    /**
     * Method to handle HTTP request method not supported exception.
     *
     * @param ex {@link HttpRequestMethodNotSupportedException}
     * @return {@link GeneralResponse} Response body of method not supported.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GeneralResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error(String.format("[%s] %s", authorizationSetting.getIdentityPrefix(), ex.getMessage()), ex);
        return new ResponseEntity<>(securityDetailsService.getSecurityResponseBuilder()
                .setGeneralStatus(GeneralStatus.METHOD_NOT_ALLOWED)
                .setArgs(new String[]{ex.getMethod()})
                .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Method to handle Oauth2 exception.
     *
     * @param ex {@link OAuth2Exception}
     * @return {@link GeneralResponse} Response body of oauth2 exception.
     */
    @ExceptionHandler(OAuth2Exception.class)
    public ResponseEntity<GeneralResponse> handleOauth2Exception(OAuth2Exception ex) {
        logger.error(String.format("[%s] %s", authorizationSetting.getIdentityPrefix(), ex.getMessage()), ex);
        return new ResponseEntity<>(securityDetailsService.getSecurityResponseBuilder(ex).build(),
                HttpStatus.valueOf(ex.getHttpErrorCode()));
    }
}
