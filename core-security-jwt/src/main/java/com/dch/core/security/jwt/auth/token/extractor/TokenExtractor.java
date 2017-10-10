package com.dch.core.security.jwt.auth.token.extractor;

/**
 * Implementations of this interface should always return raw base-64 encoded
 * representation of JWT Token.
 * 
 * @author david.christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 20, 2017
 */
public interface TokenExtractor {

	/**
	 * Method used to extract payload.
	 * 
	 * @param payload
	 * @return token.
	 */
	String extract(String payload);
}
