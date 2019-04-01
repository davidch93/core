package com.dch.core.security.oauth2.endpoint;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.response.GenericResponse;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller that manage any API about custom check token endpoint.
 *
 * @author David.Christianto
 * @version 1.0.0
 * @updated Jun 6, 2017
 * @since 1.0.0-SNAPSHOT
 */
@RestController
public class OAuth2CheckTokenEndpoint {

    @Autowired
    private SecurityDetailsService securityDetailsService;

    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;

    /**
     * API that used to check validity token.
     *
     * @param value
     *            {@link String} Bearer token.
     * @return {@link GenericResponse} Response body of checked token.
     */
    @GetMapping(value = "/oauth/check_token")
    public GenericResponse checkToken(@RequestParam("token") String value) {
        Map<String, ?> map = checkTokenEndpoint.checkToken(value);

        // @formatter:off
		return securityDetailsService.getSecurityResponseBuilder()
				.setData(map)
				.setGenericStatus(GenericStatus.SUCCESS)
			.build();
		// @formatter:on
    }
}
