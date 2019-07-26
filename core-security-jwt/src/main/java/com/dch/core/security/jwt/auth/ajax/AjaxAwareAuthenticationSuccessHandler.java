package com.dch.core.security.jwt.auth.ajax;

import com.dch.core.security.jwt.auth.token.factory.TokenFactory;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import com.dch.core.security.jwt.util.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Class that implements {@link AuthenticationSuccessHandler} and called when
 * client has been successfully authenticated. Responsibility of this class is
 * to add JSON payload containing JWT Access and Refresh tokens into the HTTP
 * response body.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler
 * @since 1.0.0
 */
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper mapper;
    private TokenFactory tokenFactory;
    private SecurityDetailsService securityDetailsService;
    
    public AjaxAwareAuthenticationSuccessHandler(
        SecurityDetailsService securityDetailsService,
        ObjectMapper mapper,
        TokenFactory tokenFactory
    ) {
        this.securityDetailsService = securityDetailsService;
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException {
        Map<String, String> tokens = HttpUtils.createToken(authentication, tokenFactory, true);
        HttpUtils.writeToken(response, mapper, securityDetailsService.getSecurityResponseBuilder(), tokens);
        HttpUtils.clearAuthenticationAttributes(request);
    }
    
}
