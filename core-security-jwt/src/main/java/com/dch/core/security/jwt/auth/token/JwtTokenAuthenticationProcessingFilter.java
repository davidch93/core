package com.dch.core.security.jwt.auth.token;

import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.security.jwt.auth.JwtAuthenticationToken;
import com.dch.core.security.jwt.auth.token.extractor.TokenExtractor;
import com.dch.core.security.jwt.model.token.RawAccessJwtToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that used to performs validation of provided JWT Token. This filter has
 * the following responsibilities:<br/>
 * 1. Check for access token in X-Authorization header. If Access token is found
 * in the header, delegate authentication to JwtAuthenticationProvider otherwise
 * throw authentication exception.<br/>
 * 2. Invokes success or failure strategies based on the outcome of
 * authentication process performed by JwtAuthenticationProvider.<br/>
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see AbstractAuthenticationProcessingFilter
 * @since 1.0.0
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;

    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
                                                  TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String tokenPayload = request.getHeader(WebSecuritySupport.JWT_TOKEN_HEADER_PARAM.getValue());
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
