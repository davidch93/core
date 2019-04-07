package com.dch.core.security.oauth2.resource.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Custom implementation of {@link ResourceServerTokenServices} that used to
 * obtain the contents of an access token from /check_token endpoint. If the
 * endpoint returns a 400 response, this indicates that the token is invalid.
 * For more detail look at {@link RemoteTokenServices}.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
 * @since 1.0.0
 */
public class GeneralRemoteTokenServices implements ResourceServerTokenServices {

    private static final Logger logger = LoggerFactory.getLogger(GeneralRemoteTokenServices.class);

    private String checkTokenEndpointUrl;
    private String clientId;
    private String clientSecret;
    private String tokenName = "token";
    private String identityPrefix = "OAUTH2-RESOURCE";
    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
    private RestOperations restTemplate = new RestTemplate();

    public GeneralRemoteTokenServices() {
        ((RestTemplate) restTemplate).setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
    }

    /**
     * @param checkTokenEndpointUrl the checkTokenEndpointUrl to set
     */
    public void setCheckTokenEndpointUrl(String checkTokenEndpointUrl) {
        this.checkTokenEndpointUrl = checkTokenEndpointUrl;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @param clientSecret the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @param tokenName the tokenName to set
     */
    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    /**
     * @param identityPrefix the identityPrefix to set
     */
    public void setIdentityPrefix(String identityPrefix) {
        this.identityPrefix = identityPrefix;
    }

    /**
     * @param tokenConverter the tokenConverter to set
     */
    public void setTokenConverter(AccessTokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
    }

    /**
     * @param restTemplate the restTemplate to set
     */
    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException,
            InvalidTokenException {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap();
        formData.add(this.tokenName, accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.getAuthorizationHeader(this.clientId, this.clientSecret));
        Map<String, Object> map = this.postForMap(this.checkTokenEndpointUrl, formData, headers);
        if (map.containsKey("error")) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("[%s] check_token returned error: %s", identityPrefix, map.get("error")));
            }

            throw new InvalidTokenException(accessToken);
        } else if (!Boolean.TRUE.equals(map.get("active"))) {
            logger.debug(String.format("[%s] check_token returned active attribute: %s", identityPrefix,
                    map.get("active")));
            throw new InvalidTokenException(accessToken);
        }
        return this.tokenConverter.extractAuthentication(map);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    /**
     * Method used to get authorization header using client ID and secret.
     * Encoded by Base64 encoder.
     *
     * @param clientId     {@code String} Client ID
     * @param clientSecret {@code String} Client Secret
     * @return {@link String} Authorization: Basic (Base64 clientId:secret)
     */
    protected String getAuthorizationHeader(String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null) {
            logger.warn(String.format("[%s] Null Client ID or Client Secret detected. Endpoint that requires " +
                    "authentication will reject request with 401 error.", identityPrefix));
        }

        String creds = String.format("%s:%s", clientId, clientSecret);
        return "Basic " + new String(Base64Utils.encode(creds.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Method used to check token endpoint to Authorization Server.
     *
     * @param path     {@link String} Check token endpoint URL.
     * @param formData {@link MultiValueMap} Parameters.
     * @param headers  {@link HttpHeaders} HTTP Header.
     * @return {@link Map} Response body.
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> postForMap(String path, MultiValueMap<String, String> formData, HttpHeaders headers) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

        return restTemplate.exchange(path, HttpMethod.POST, new HttpEntity(formData, headers), Map.class,
                new Object[0]).getBody();
    }
}
