package com.dch.core.security.jwt.auth.token.verifier;

/**
 * Interface of Token Verifier.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 21, 2017
 */
public interface TokenVerifier {

	/**
	 * Method used to check for revoked tokens.
	 * 
	 * @param jti
	 * @return true if token revoked and vice versa.
	 */
	public boolean verify(String jti);
}
