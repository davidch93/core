package com.dch.core.security.jwt.auth.token.verifier;

import org.springframework.stereotype.Component;

/**
 * Class that implement {@link TokenVerifier} to check for revoked tokens.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 21, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Component
public class BloomFilterTokenVerifier implements TokenVerifier {

    @Override
    public boolean verify(String jti) {
        return true;
    }
}
