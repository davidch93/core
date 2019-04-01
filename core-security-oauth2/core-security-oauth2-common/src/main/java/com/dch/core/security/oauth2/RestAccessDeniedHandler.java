package com.dch.core.security.oauth2;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This handler is called once the request access denied. This class extends
 * {@link AbstractRestSecurityExceptionHandler} and implements
 * {@link AccessDeniedHandler}.
 * <p>
 * For more detail look at {@link OAuth2AccessDeniedHandler}.
 * </p>
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 11, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class RestAccessDeniedHandler extends AbstractRestSecurityExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException)
            throws IOException, ServletException {
        doHandle(request, response, authException);
    }
}
