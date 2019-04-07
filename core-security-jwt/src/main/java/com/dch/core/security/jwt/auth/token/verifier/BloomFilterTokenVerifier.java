package com.dch.core.security.jwt.auth.token.verifier;

import org.springframework.stereotype.Component;

/**
 * Class to check for revoked tokens.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.security.jwt.auth.token.verifier.TokenVerifier
 * @since 1.0.0
 */
@Component("bloomFilterTokenVerifier")
public class BloomFilterTokenVerifier implements TokenVerifier {

    @Override
    public boolean verify(String jti) {
        return true;
    }
}
