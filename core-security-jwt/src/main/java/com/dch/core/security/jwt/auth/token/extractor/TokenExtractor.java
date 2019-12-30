package com.dch.core.security.jwt.auth.token.extractor;

/**
 * Implementations of this interface should always return raw base-64 encoded
 * representation of JWT Token.
 *
 * @author david.christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface TokenExtractor {

    /**
     * Extract the given payload.
     *
     * @param payload the header.
     * @return the token.
     */
    String extract(String payload);
}
