package com.dch.core.security.jwt.endpoint;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.datastatic.WebSecuritySupport;
import com.dch.core.dto.response.GeneralResponse;
import com.dch.core.security.jwt.auth.token.extractor.TokenExtractor;
import com.dch.core.security.jwt.auth.token.factory.TokenFactory;
import com.dch.core.security.jwt.auth.token.verifier.TokenVerifier;
import com.dch.core.security.jwt.config.JwtSetting;
import com.dch.core.security.jwt.exception.InvalidJwtTokenException;
import com.dch.core.security.jwt.model.token.RawAccessJwtToken;
import com.dch.core.security.jwt.model.token.RefreshToken;
import com.dch.core.security.jwt.service.SecurityDetailsService;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller that manage any API about refresh token endpoint.
 *
 * @author david.christianto
 * @version 2.0.0
 * @since 1.0.0
 */
@RestController
public class RefreshTokenEndpoint {

    private final JwtSetting jwtSetting;
    private final UserDetailsService userDetailsService;
    private final SecurityDetailsService securityDetailsService;
    private final TokenExtractor jwtTokenExtractor;
    private final TokenFactory jwtTokenFactory;
    private final TokenVerifier bloomFilterTokenVerifier;

    public RefreshTokenEndpoint(JwtSetting jwtSetting, UserDetailsService userDetailsService,
                                SecurityDetailsService securityDetailsService, TokenExtractor jwtTokenExtractor,
                                TokenFactory jwtTokenFactory, TokenVerifier bloomFilterTokenVerifier) {
        this.jwtSetting = jwtSetting;
        this.userDetailsService = userDetailsService;
        this.securityDetailsService = securityDetailsService;
        this.jwtTokenExtractor = jwtTokenExtractor;
        this.jwtTokenFactory = jwtTokenFactory;
        this.bloomFilterTokenVerifier = bloomFilterTokenVerifier;
    }

    /**
     * API to get new access token by refresh token.
     *
     * @param request the {@link HttpServletRequest} HTTP Request.
     * @return the {@link GeneralResponse response body} of token.
     */
    @GetMapping(value = "/auth/token")
    public GeneralResponse refreshToken(HttpServletRequest request) {
        String tokenPayload = jwtTokenExtractor
                .extract(request.getHeader(WebSecuritySupport.JWT_TOKEN_HEADER_PARAM.getValue()));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSetting.getTokenSigningKey())
                .orElseThrow(() -> new InvalidJwtTokenException("Failed create refresh token!"));

        if (!bloomFilterTokenVerifier.verify(refreshToken.getJti()))
            throw new InvalidJwtTokenException("Refresh token not valid!");

        UserDetails userDetails = userDetailsService.loadUserByUsername(refreshToken.getSubject());
        if (userDetails == null)
            throw new UsernameNotFoundException("User not found: " + refreshToken.getSubject());

        if (userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty())
            throw new InsufficientAuthenticationException("User has no roles assigned");

        return securityDetailsService.getSecurityResponseBuilder()
                .setData(jwtTokenFactory.createAccessJwtToken(userDetails))
                .setGeneralStatus(GeneralStatus.TOKEN_CREATED)
                .build();
    }
}
