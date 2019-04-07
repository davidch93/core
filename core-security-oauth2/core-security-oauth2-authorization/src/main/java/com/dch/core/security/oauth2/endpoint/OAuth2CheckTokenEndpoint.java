package com.dch.core.security.oauth2.endpoint;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.GeneralResponse;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller that manage any API about custom check token endpoint.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@RestController
public class OAuth2CheckTokenEndpoint {

    private final SecurityDetailsService securityDetailsService;
    private final CheckTokenEndpoint checkTokenEndpoint;

    public OAuth2CheckTokenEndpoint(SecurityDetailsService securityDetailsService,
                                    CheckTokenEndpoint checkTokenEndpoint) {
        this.securityDetailsService = securityDetailsService;
        this.checkTokenEndpoint = checkTokenEndpoint;
    }

    /**
     * API that used to check validity token.
     *
     * @param value {@link String} Bearer token.
     * @return {@link GeneralResponse} Response body of checked token.
     */
    @GetMapping(value = "/oauth/check_token")
    public GeneralResponse checkToken(@RequestParam("token") String value) {
        Map<String, ?> map = checkTokenEndpoint.checkToken(value);
        return securityDetailsService.getSecurityResponseBuilder()
                .setData(map)
                .setGeneralStatus(GeneralStatus.SUCCESS)
                .build();
    }
}
