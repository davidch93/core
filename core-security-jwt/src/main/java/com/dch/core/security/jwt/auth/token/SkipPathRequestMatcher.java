package com.dch.core.security.jwt.auth.token;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class filter is configured to skip following endpoints.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.web.util.matcher.RequestMatcher
 * @since 1.0.0
 */
public class SkipPathRequestMatcher implements RequestMatcher {

    private OrRequestMatcher matchers;
    private RequestMatcher processingMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
        List<RequestMatcher> requestMatchers = pathsToSkip.stream().map(AntPathRequestMatcher::new)
                .collect(Collectors.toList());
        matchers = new OrRequestMatcher(requestMatchers);
        processingMatcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (matchers.matches(request)) {
            return false;
        }
        return processingMatcher.matches(request);
    }
}
