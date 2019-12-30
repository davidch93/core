package com.dch.core.security.jwt.config;

import com.dch.core.security.jwt.model.token.JwtToken;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Class that contains parameter setting of JWT configuration.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@PropertySource("classpath:config/security/core-jwt-config.properties")
@ConfigurationProperties(prefix = "core.security.jwt")
public class JwtSetting {

    /**
     * {@link JwtToken} can be refreshed during this timeframe.
     */
    private Integer refreshTokenExpTime;

    /**
     * {@link JwtToken} will expire after this time.
     */
    private Integer tokenExpirationTime;

    /**
     * Identity prefix.
     */
    private String identityPrefix = "SECURITY-JWT";

    /**
     * Token issuer.
     */
    private String tokenIssuer;

    /**
     * Key is used to sign {@link JwtToken}.
     */
    private String tokenSigningKey;

    /**
     * Get refresh token exp time.
     *
     * @return the refresh token exp time
     */
    public Integer getRefreshTokenExpTime() {
        return refreshTokenExpTime;
    }

    /**
     * Set refresh token exp time.
     *
     * @param refreshTokenExpTime the refresh token exp time
     */
    public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    /**
     * Get token expiration time.
     *
     * @return the token expiration time
     */
    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    /**
     * Set token expiration time.
     *
     * @param tokenExpirationTime the token expiration time
     */
    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    /**
     * Get identity prefix.
     *
     * @return the identity prefix
     */
    public String getIdentityPrefix() {
        return identityPrefix;
    }

    /**
     * Set identity prefix.
     *
     * @param identityPrefix the identity prefix
     */
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    /**
     * Get token issuer.
     *
     * @return the token issuer
     */
    public String getTokenIssuer() {
        return tokenIssuer;
    }

    /**
     * Set token issuer.
     *
     * @param tokenIssuer the token issuer
     */
    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    /**
     * Get token signing key.
     *
     * @return the token signing key
     */
    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    /**
     * Set token signing key.
     *
     * @param tokenSigningKey the token signing key
     */
    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }
}
