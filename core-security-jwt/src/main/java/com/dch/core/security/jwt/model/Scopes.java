package com.dch.core.security.jwt.model;

/**
 * Scopes of JWT Token.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 20, 2017
 */
public enum Scopes {

	REFRESH_TOKEN;

	public String authority() {
		return "ROLE_" + this.name();
	}
}
