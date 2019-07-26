package com.dch.core.security.jwr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Class that contains parameter setting of JWT configuration.
 *
 *
 * @author Sayid Sidqi
 */
@ConfigurationProperties(prefix = "core.security.jwr")
public class JwrSetting {

	private Integer tokenExpirationTime;
	
	public Integer getTokenExpirationTime() {
		return tokenExpirationTime;
	}
	
	public void setTokenExpirationTime(Integer tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}
}
