package com.dch.core.security.oauth2.endpoint;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.GeneralResponse;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
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
 * @version 2.0.0
 * @since 1.0.0
 */
@RestController
public class OAuth2RevokeTokenEndpoint {

    private final SecurityDetailsService securityDetailsService;
    private final ConsumerTokenServices consumerTokenServices;
    private final TokenExtractor tokenExtractor;

    public OAuth2RevokeTokenEndpoint(SecurityDetailsService securityDetailsService,
                                     ConsumerTokenServices consumerTokenServices, TokenExtractor tokenExtractor) {
        this.securityDetailsService = securityDetailsService;
        this.consumerTokenServices = consumerTokenServices;
        this.tokenExtractor = tokenExtractor;
    }

    /**
     * API to revoke active token.
     *
     * @param request the {@link HttpServletRequest} HTTP Request.
     * @return the {@link GeneralResponse response body} of revoked token.
     */
    @DeleteMapping(value = "/oauth/revoke_token")
    public GeneralResponse revokeToken(HttpServletRequest request) {
        Authentication authentication = tokenExtractor.extract(request);
        consumerTokenServices.revokeToken(authentication.getPrincipal().toString());
        return securityDetailsService.getSecurityResponseBuilder()
                .setGeneralStatus(GeneralStatus.TOKEN_REVOKED)
                .build();
    }
}
