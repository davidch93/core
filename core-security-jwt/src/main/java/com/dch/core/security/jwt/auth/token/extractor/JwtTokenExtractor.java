package com.dch.core.security.jwt.auth.token.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * An implementation of {@link TokenExtractor} extracts token from
 * Authorization: Bearer scheme and provide your custom implementation that will
 * for example extract token from URL
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.security.jwt.auth.token.extractor.TokenExtractor
 * @since 1.0.0
 */
@Component("jwtTokenExtractor")
public class JwtTokenExtractor implements TokenExtractor {

    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {
        if (StringUtils.isEmpty(header))
            throw new AuthenticationServiceException("Authorization header cannot be blank!");

        if (header.length() < HEADER_PREFIX.length())
            throw new AuthenticationServiceException("Invalid authorization header size.");

        return header.substring(HEADER_PREFIX.length());
    }
}
