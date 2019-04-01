package com.dch.core.security.jwt.auth.ajax;

import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.security.jwt.exception.AuthMethodNotSupportedException;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Custom processing of Ajax authentication requests. This class is the entry
 * point of our JWT authentication process; the filter extracts the JWT token
 * from the request headers and delegates authentication to the injected
 * AuthenticationManager. This class extends
 * {@link AbstractAuthenticationProcessingFilter}.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 20, 2017
 * @since 1.0.0-SNAPSHOT
 */
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final String credentialsCharset = "UTF-8";

    public AjaxLoginProcessingFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler,
                                     AuthenticationFailureHandler failureHandler) {
        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        if (!HttpMethod.POST.name().equals(request.getMethod()))
            throw new AuthMethodNotSupportedException(
                    "Authentication method not supported. Request method: " + request.getMethod());

        String header = request.getHeader(WebSecuritySupport.JWT_TOKEN_HEADER_PARAM.getValue());
        if (header == null || !header.startsWith("Basic "))
            throw new AuthenticationServiceException("Username or Password not provided");

        String[] tokens = extractAndDecodeHeader(header, request);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(tokens[0], tokens[1]);

        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    /**
     * Decodes the header into a username and password.
     *
     * @param header  Header name.
     * @param request {@link HttpServletRequest} HTTP Request.
     * @throws UnsupportedEncodingException If the named charset is not supported.
     * @throws BadCredentialsException      If the Basic header is not present or is not valid Base64
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
            throws UnsupportedEncodingException {

        byte[] base64Token = header.substring(6).getBytes(credentialsCharset);
        byte[] decoded;

        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, credentialsCharset);
        int delim = token.indexOf(":");
        if (delim == -1)
            throw new BadCredentialsException("Invalid basic authentication token");

        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
}
