package com.dch.core.security.oauth2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Class that contains parameter of authorization server.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jun 12, 2017
 */
@Configuration
@PropertySource("classpath:config/security/core-oauth2-config.properties")
@ConfigurationProperties(prefix = "core.security.oauth2.resource")
public class ResourceSetting {

	private String checkTokenEndpointUrl;
	private String clientId;
	private String clientSecret;
	private String identityPrefix = "SECURITY-RESOURCE";
	private String keyOfData;
	private String resourceId;
	private String tokenName;

	/**
	 * @return the checkTokenEndpointUrl
	 */
	public String getCheckTokenEndpointUrl() {
		return checkTokenEndpointUrl;
	}

	/**
	 * @param checkTokenEndpointUrl
	 *            the checkTokenEndpointUrl to set
	 */
	public void setCheckTokenEndpointUrl(String checkTokenEndpointUrl) {
		this.checkTokenEndpointUrl = checkTokenEndpointUrl;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @param clientSecret
	 *            the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
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
	 * @return the keyOfData
	 */
	public String getKeyOfData() {
		return keyOfData;
	}

	/**
	 * @param keyOfData
	 *            the keyOfData to set
	 */
	public void setKeyOfData(String keyOfData) {
		this.keyOfData = keyOfData;
	}

	/**
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId
	 *            the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the tokenName
	 */
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * @param tokenName
	 *            the tokenName to set
	 */
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
}