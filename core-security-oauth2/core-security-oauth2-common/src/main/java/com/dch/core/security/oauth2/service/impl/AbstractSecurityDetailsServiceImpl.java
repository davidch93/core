package com.dch.core.security.oauth2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;

import com.dch.core.datastatic.GenericStatus;
import com.dch.core.datastatic.builder.ResponseBuilderHelper;
import com.dch.core.security.oauth2.builder.SecurityResponseBuilder;
import com.dch.core.security.oauth2.service.SecurityDetailsService;

/**
 * This class serves as the Base class for all security details service - namely
 * to hold common service methods that they might all use. This class implements
 * {@link SecurityDetailsService}.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 24, 2017
 */
public abstract class AbstractSecurityDetailsServiceImpl implements SecurityDetailsService {

	@Autowired
	protected MessageSource messageSource;

	@Override
	public ResponseBuilderHelper getSecurityResponseBuilder() {
		return new SecurityResponseBuilder(messageSource);
	}

	@Override
	public ResponseBuilderHelper getSecurityResponseBuilder(OAuth2Exception oAuth2Exception) {
		GenericStatus genericStatus;
		if (oAuth2Exception instanceof InvalidClientException)
			genericStatus = GenericStatus.INVALID_CLIENT;
		else if (oAuth2Exception instanceof InvalidGrantException)
			genericStatus = GenericStatus.INVALID_GRANT;
		else if (oAuth2Exception instanceof InvalidRequestException)
			genericStatus = GenericStatus.INVALID_REQUEST;
		else if (oAuth2Exception instanceof InvalidScopeException)
			genericStatus = GenericStatus.INVALID_SCOPE;
		else if (oAuth2Exception instanceof InvalidTokenException)
			genericStatus = GenericStatus.INVALID_TOKEN;
		else if (oAuth2Exception instanceof RedirectMismatchException)
			genericStatus = GenericStatus.REDIRECT_URI_MISMATCH;
		else if (oAuth2Exception instanceof UnauthorizedClientException)
			genericStatus = GenericStatus.UNAUTHORIZED_CLIENT;
		else if (oAuth2Exception instanceof UnsupportedGrantTypeException)
			genericStatus = GenericStatus.UNSUPPORTED_GRANT_TYPE;
		else if (oAuth2Exception instanceof UnsupportedResponseTypeException)
			genericStatus = GenericStatus.UNSUPPORTED_RESPONSE_TYPE;
		else if (oAuth2Exception instanceof UserDeniedAuthorizationException)
			genericStatus = GenericStatus.FORBIDDEN;
		else
			genericStatus = GenericStatus.INVALID_AUTHENTICATION;

		return getSecurityResponseBuilder().setGenericStatus(genericStatus);
	}
}
