package com.dch.core.security.oauth2.endpoint;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.GeneralResponse;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * Controller that manage any API about custom token endpoint.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@RestController
public class OAuth2TokenEndpoint {

    private final SecurityDetailsService securityDetailsService;
    private final TokenEndpoint tokenEndpoint;

    public OAuth2TokenEndpoint(SecurityDetailsService securityDetailsService, TokenEndpoint tokenEndpoint) {
        this.securityDetailsService = securityDetailsService;
        this.tokenEndpoint = tokenEndpoint;
    }

    /**
     * API that used to get access token by oauth2 authentication parameters.
     * Using RequestMethod.GET
     *
     * @param principal  {@link Principal} Authentication principal.
     * @param parameters {@link Map} Request parameters.
     * @return {@link GeneralResponse} Response body of token.
     * @throws HttpRequestMethodNotSupportedException If error occurred while creating token.
     */
    @GetMapping(value = "/oauth/token")
    public GeneralResponse getAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> responseAccessToken = tokenEndpoint.getAccessToken(principal, parameters);
        return securityDetailsService.getSecurityResponseBuilder()
                .setData(responseAccessToken.getBody())
                .setGeneralStatus(GeneralStatus.TOKEN_CREATED)
                .build();
    }

    /**
     * API that used to get access token by oauth2 authentication parameters.
     * Using RequestMethod.POST
     *
     * @param principal  {@link Principal} Authentication principal.
     * @param parameters {@link Map} Request parameters.
     * @return {@link GeneralResponse} Response body of token.
     * @throws HttpRequestMethodNotSupportedException If error occurred while creating token.
     */
    @PostMapping(value = "/oauth/token")
    public GeneralResponse postCustomAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> responseAccessToken = tokenEndpoint.postAccessToken(principal, parameters);
        return securityDetailsService.getSecurityResponseBuilder()
                .setData(responseAccessToken.getBody())
                .setGeneralStatus(GeneralStatus.TOKEN_CREATED)
                .build();
    }
}
