package com.dch.core.security.oauth2.endpoint;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.response.GenericResponse;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @version 1.0.0
 * @updated Jun 6, 2017
 * @since 1.0.0-SNAPSHOT
 */
@RestController
public class OAuth2TokenEndpoint {

    @Autowired
    private SecurityDetailsService securityDetailsService;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * API that used to get access token by oauth2 authentication parameters.
     * Using RequestMethod.GET
     *
     * @param principal
     *            {@link Principal} Authentication principal.
     * @param parameters
     *            {@link Map}&lt;{@link String}, {@link String}&gt; Request
     *            parameters.
     * @return {@link GenericResponse} Response body of token.
     * @throws HttpRequestMethodNotSupportedException
     */
    @GetMapping(value = "/oauth/token")
    public GenericResponse getAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {

        ResponseEntity<OAuth2AccessToken> responseAccessToken = tokenEndpoint.getAccessToken(principal, parameters);

        // @formatter:off
		return securityDetailsService.getSecurityResponseBuilder()
				.setData(responseAccessToken.getBody())
				.setGenericStatus(GenericStatus.TOKEN_CREATED)
			.build();
		// @formatter:on
    }

    /**
     * API that used to get access token by oauth2 authentication parameters.
     * Using RequestMethod.POST
     *
     * @param principal  {@link Principal} Authentication principal.
     * @param parameters {@link Map}&lt;{@link String}, {@link String}&gt; Request
     *                   parameters.
     * @return {@link GenericResponse} Response body of token.
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping(value = "/oauth/token")
    public GenericResponse postCustomAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {

        ResponseEntity<OAuth2AccessToken> responseAccessToken = tokenEndpoint.postAccessToken(principal, parameters);

        // @formatter:off
		return securityDetailsService.getSecurityResponseBuilder()
				.setData(responseAccessToken.getBody())
				.setGenericStatus(GenericStatus.TOKEN_CREATED)
			.build();
		// @formatter:on
    }
}
