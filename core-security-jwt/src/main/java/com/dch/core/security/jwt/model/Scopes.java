package com.dch.core.security.jwt.model;

/**
 * Scopes of JWT Token.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated May 20, 2017
 * @since 1.0.0-SNAPSHOT
 */
public enum Scopes {

    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
