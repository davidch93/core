package com.dch.core.security.jwt.auth.token.verifier;

/**
 * Interface of Token Verifier.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public interface TokenVerifier {

    /**
     * Method used to check for revoked tokens.
     *
     * @param jti JWT ID.
     * @return true if token revoked and vice versa.
     */
    boolean verify(String jti);
}
