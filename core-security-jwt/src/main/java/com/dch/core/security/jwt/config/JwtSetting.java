package com.dch.core.security.jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.dch.core.security.jwt.model.token.JwtToken;

/**
 * Class that contains parameter setting of JWT configuration.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated May 20, 2017
 */
@Configuration
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
	 * @return the refreshTokenExpTime
	 */
	public Integer getRefreshTokenExpTime() {
		return refreshTokenExpTime;
	}

	/**
	 * @param refreshTokenExpTime
	 *            the refreshTokenExpTime to set
	 */
	public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
		this.refreshTokenExpTime = refreshTokenExpTime;
	}

	/**
	 * @return the tokenExpirationTime
	 */
	public Integer getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	/**
	 * @param tokenExpirationTime
	 *            the tokenExpirationTime to set
	 */
	public void setTokenExpirationTime(Integer tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}

	/**
	 * @return the identityPrefix
	 */
	public String getIdentityPrefix() {
		return identityPrefix;
	}

	/**
	 * @param identityPrefix
	 *            the identityPrefix to set
	 */
	public void setIdentityPrefix(String identityPrefix) {
		this.identityPrefix = identityPrefix;
	}

	/**
	 * @return the tokenIssuer
	 */
	public String getTokenIssuer() {
		return tokenIssuer;
	}

	/**
	 * @param tokenIssuer
	 *            the tokenIssuer to set
	 */
	public void setTokenIssuer(String tokenIssuer) {
		this.tokenIssuer = tokenIssuer;
	}

	/**
	 * @return the tokenSigningKey
	 */
	public String getTokenSigningKey() {
		return tokenSigningKey;
	}

	/**
	 * @param tokenSigningKey
	 *            the tokenSigningKey to set
	 */
	public void setTokenSigningKey(String tokenSigningKey) {
		this.tokenSigningKey = tokenSigningKey;
	}
}
