package com.dch.core.security.oauth2.endpoint;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.response.GenericResponse;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller that manage any API about revoke token endpoint.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 6, 2017
 * @since 1.0.0-SNAPSHOT
 */
@RestController
public class OAuth2RevokeTokenEndpoint {

    @Autowired
    private SecurityDetailsService securityDetailsService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private TokenExtractor tokenExtractor;

    /**
     * API that used to revoke active token.
     *
     * @param request
     *            {@link HttpServletRequest} HTTP Request.
     * @return {@link GenericResponse} Response body of revoked token.
     */
    @DeleteMapping(value = "/oauth/revoke_token")
    public GenericResponse revokeToken(HttpServletRequest request) {
        Authentication authentication = tokenExtractor.extract(request);
        consumerTokenServices.revokeToken(authentication.getPrincipal().toString());

        // @formatter:off
		return securityDetailsService
			.getSecurityResponseBuilder()
				.setGenericStatus(GenericStatus.TOKEN_REVOKED)
			.build();
		// @formatter:on
    }
}
