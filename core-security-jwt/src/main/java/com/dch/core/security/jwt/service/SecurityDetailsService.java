package com.dch.core.security.jwt.service;

import com.dch.core.datastatic.builder.ResponseBuilderHelper;

/**
 * Core interface which validate servlet request data and other oauth2 filter
 * services.
 * 
 * @author David.Christianto
 * @version 1.0.0
 * @since 1.0.0-SNAPSHOT
 * @updated Jul 7, 2017
 */
public interface SecurityDetailsService {

	/**
	 * Method used to get response builder to build a generic response.
	 * 
	 * @return {@link ResponseBuilderHelper}
	 */
	ResponseBuilderHelper getSecurityResponseBuilder();
}
