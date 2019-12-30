package com.dch.core.security.oauth2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class that contains parameter of authorization server.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
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
     * Get check token endpoint url.
     *
     * @return the check token endpoint url
     */
    public String getCheckTokenEndpointUrl() {
        return checkTokenEndpointUrl;
    }

    /**
     * Set check token endpoint url.
     *
     * @param checkTokenEndpointUrl the check token endpoint url
     */
    public void setCheckTokenEndpointUrl(String checkTokenEndpointUrl) {
        this.checkTokenEndpointUrl = checkTokenEndpointUrl;
    }

    /**
     * Get client id.
     *
     * @return the client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Set client id.
     *
     * @param clientId the client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Get client secret.
     *
     * @return the client secret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Set client secret.
     *
     * @param clientSecret the client secret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
     * Get key of data.
     *
     * @return the key of data
     */
    public String getKeyOfData() {
        return keyOfData;
    }

    /**
     * Set key of data.
     *
     * @param keyOfData the key of data
     */
    public void setKeyOfData(String keyOfData) {
        this.keyOfData = keyOfData;
    }

    /**
     * Get resource id.
     *
     * @return the resource id
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Set resource id.
     *
     * @param resourceId the resource id
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Get token name.
     *
     * @return the token name
     */
    public String getTokenName() {
        return tokenName;
    }

    /**
     * Set token name.
     *
     * @param tokenName the token name
     */
    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}