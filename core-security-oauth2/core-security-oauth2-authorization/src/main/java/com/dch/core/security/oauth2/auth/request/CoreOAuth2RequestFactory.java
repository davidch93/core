package com.dch.core.security.oauth2.auth.request;

import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Core implementation of {@link OAuth2RequestFactory} which initializes fields
 * from the parameters map, validates grant types and scopes, and fills in
 * scopes with the default values from the client if they are missing.
 * <p>
 * For more detail look at {@link DefaultOAuth2RequestFactory}.
 * </p>
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 12, 2017
 * @since 1.0.0-SNAPSHOT
 */
@Component
public class CoreOAuth2RequestFactory implements OAuth2RequestFactory {

    @Override
    public AuthorizationRequest createAuthorizationRequest(Map<String, String> authorizationParameters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OAuth2Request createOAuth2Request(AuthorizationRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OAuth2Request createOAuth2Request(ClientDetails client, TokenRequest tokenRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParameters, ClientDetails authenticatedClient) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TokenRequest createTokenRequest(AuthorizationRequest authorizationRequest, String grantType) {
        // TODO Auto-generated method stub
        return null;
    }
}
