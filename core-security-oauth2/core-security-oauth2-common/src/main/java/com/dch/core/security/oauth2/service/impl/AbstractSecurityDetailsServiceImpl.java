package com.dch.core.security.oauth2.service.impl;

import com.dch.core.datastatic.GeneralStatus;
import com.dch.core.dto.response.builder.ResponseBuilder;
import com.dch.core.security.oauth2.service.SecurityDetailsService;
import org.springframework.context.MessageSource;
import org.springframework.security.oauth2.common.exceptions.*;

/**
 * This class serves as the Base class for all security details service - namely
 * to hold common service methods that they might all use.
 *
 * @author David.Christianto
 * @version 2.0.0
 * @see com.dch.core.security.oauth2.service.SecurityDetailsService
 * @since 1.0.0
 */
public abstract class AbstractSecurityDetailsServiceImpl implements SecurityDetailsService {

    protected final MessageSource messageSource;

    protected AbstractSecurityDetailsServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ResponseBuilder getSecurityResponseBuilder() {
        return new ResponseBuilder(messageSource);
    }

    @Override
    public ResponseBuilder getSecurityResponseBuilder(OAuth2Exception oAuth2Exception) {
        if (oAuth2Exception instanceof InvalidClientException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.INVALID_CLIENT);
        else if (oAuth2Exception instanceof InvalidGrantException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.INVALID_GRANT);
        else if (oAuth2Exception instanceof InvalidRequestException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.INVALID_REQUEST);
        else if (oAuth2Exception instanceof InvalidScopeException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.INVALID_SCOPE);
        else if (oAuth2Exception instanceof InvalidTokenException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.INVALID_TOKEN);
        else if (oAuth2Exception instanceof RedirectMismatchException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.REDIRECT_URI_MISMATCH);
        else if (oAuth2Exception instanceof UnauthorizedClientException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.UNAUTHORIZED_CLIENT);
        else if (oAuth2Exception instanceof UnsupportedGrantTypeException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.UNSUPPORTED_GRANT_TYPE);
        else if (oAuth2Exception instanceof UnsupportedResponseTypeException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.UNSUPPORTED_RESPONSE_TYPE);
        else if (oAuth2Exception instanceof UserDeniedAuthorizationException)
            return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.FORBIDDEN);

        return getSecurityResponseBuilder().setGeneralStatus(GeneralStatus.INVALID_AUTHENTICATION);
    }
}
