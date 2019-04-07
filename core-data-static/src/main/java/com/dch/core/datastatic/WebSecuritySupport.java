package com.dch.core.datastatic;

/**
 * List of constant for web security support.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public enum WebSecuritySupport {

    BASED_AUTH_ENTRY_POINT("/**"),
    JWT_TOKEN_HEADER_PARAM("X-Authorization"),
    JWT_BASED_LOGIN_ENTRY_POINT("/auth/login"),
    JWT_BASED_REFRESH_ENTRY_POINT("/auth/token"),
    OAUTH2_TOKEN_HEADER_PARAM("Authorization"),
    OAUTH2_BASED_TOKEN_ENTRY_POINT("/oauth/token"),
    OAUTH2_BASED_CHECK_ENTRY_POINT("/oauth/check_token"),
    OAUTH2_BASED_REVOKE_ENTRY_POINT("/oauth/revoke_token");

    private final String value;

    WebSecuritySupport(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
