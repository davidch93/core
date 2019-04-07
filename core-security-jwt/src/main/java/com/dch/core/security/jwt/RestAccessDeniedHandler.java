package com.dch.core.security.jwt;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This handler is called once the request access denied.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.web.access.AccessDeniedHandler
 * @since 1.0.0
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestAccessDeniedHandler.class);

    private SecurityDetailsService securityDetailsService;
    private ObjectMapper mapper;
    private JwtSetting jwtSetting;

    public RestAccessDeniedHandler(SecurityDetailsService securityDetailsService, ObjectMapper mapper,
                                   JwtSetting jwtSetting) {
        this.securityDetailsService = securityDetailsService;
        this.mapper = mapper;
        this.jwtSetting = jwtSetting;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        LOGGER.error(String.format("[%s] Access denied!", jwtSetting.getIdentityPrefix()), accessDeniedException);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
                .setGeneralStatus(GeneralStatus.FORBIDDEN).build());
    }
}
