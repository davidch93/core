package com.dch.core.security.jwt.auth.ajax;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.exception.AuthMethodNotSupportedException;
import com.dch.core.security.jwt.exception.InvalidJwtTokenException;
import com.dch.core.security.jwt.exception.JwtExpiredTokenException;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that implements {@link AuthenticationFailureHandler} and used in case
 * of authentication failures. This class can used to design specific error
 * messages based on exception type that have occurred during the authentication
 * process.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 20, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxAwareAuthenticationFailureHandler.class);

    private SecurityDetailsService securityDetailsService;
    private ObjectMapper mapper;
    private JwtSetting jwtSetting;

    @Autowired
    public AjaxAwareAuthenticationFailureHandler(SecurityDetailsService securityDetailsService, ObjectMapper mapper,
                                                 JwtSetting jwtSetting) {
        this.securityDetailsService = securityDetailsService;
        this.mapper = mapper;
        this.jwtSetting = jwtSetting;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        LOGGER.error(String.format("[%s] %s", jwtSetting.getIdentityPrefix(), e.getMessage()), e);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (e instanceof AuthMethodNotSupportedException)
            mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
                    .setGenericStatus(GenericStatus.AUTHENTICATION_METHOD_NOT_SUPPORTED).build());
        else if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException)
            mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
                    .setGenericStatus(GenericStatus.INVALID_USERNAME_PASSWORD).build());
        else if (e instanceof InvalidJwtTokenException)
            mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
                    .setGenericStatus(GenericStatus.INVALID_TOKEN).build());
        else if (e instanceof JwtExpiredTokenException)
            mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
                    .setGenericStatus(GenericStatus.TOKEN_EXPIRED).build());

        mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
                .setGenericStatus(GenericStatus.AUTHENTICATION_FAILED).build());
    }
}
