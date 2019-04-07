package com.dch.core.security.jwt.model;

/**
 * Scopes of JWT Token.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public enum Scopes {

    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
