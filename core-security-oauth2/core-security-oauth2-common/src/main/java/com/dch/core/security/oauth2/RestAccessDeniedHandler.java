package com.dch.core.security.oauth2;

import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This handler is called once the request access denied.
 * <p>
 * For more detail look at {@link OAuth2AccessDeniedHandler}.
 * </p>
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler
 * @see org.springframework.security.web.access.AccessDeniedHandler
 * @since 1.0.0
 */
public class RestAccessDeniedHandler extends AbstractOAuth2SecurityExceptionHandler implements AccessDeniedHandler {

    private final SecurityDetailsService securityDetailsService;

    public RestAccessDeniedHandler(SecurityDetailsService securityDetailsService) {
        this.securityDetailsService = securityDetailsService;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException)
            throws IOException, ServletException {
        doHandle(request, response, authException);
    }

    @Override
    protected ResponseEntity<?> enhanceResponse(ResponseEntity<?> result, Exception authException) {
        return new ResponseEntity<>(
                securityDetailsService.getSecurityResponseBuilder((OAuth2Exception) result.getBody()).build(),
                result.getHeaders(), result.getStatusCode());
    }
}
