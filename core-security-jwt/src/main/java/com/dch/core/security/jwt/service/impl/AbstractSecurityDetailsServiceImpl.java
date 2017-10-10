package com.dch.core.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.dch.core.datastatic.builder.ResponseBuilderHelper;
import com.dch.core.security.jwt.builder.SecurityResponseBuilder;
import com.dch.core.security.jwt.service.SecurityDetailsService;

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
}
