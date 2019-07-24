package com.dch.core.security.jwt.auth.ajax;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.security.jwt.auth.token.factory.TokenFactory;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
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

    private SecurityDetailsService securityDetailsService;
    private ObjectMapper mapper;
    private TokenFactory tokenFactory;

    public AjaxAwareAuthenticationSuccessHandler(SecurityDetailsService securityDetailsService, ObjectMapper mapper,
                                                 TokenFactory tokenFactory) {
        this.securityDetailsService = securityDetailsService;
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", tokenFactory.createAccessJwtToken(userDetails).getToken());
        tokenMap.put("refreshToken", tokenFactory.createRefreshToken(userDetails).getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        mapper.writeValue(response.getWriter(), securityDetailsService.getSecurityResponseBuilder()
                .setData(tokenMap)
                .setGeneralStatus(GeneralStatus.TOKEN_CREATED)
                .build());

        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process.
     *
     * @param request {@link HttpServletRequest}
     */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
