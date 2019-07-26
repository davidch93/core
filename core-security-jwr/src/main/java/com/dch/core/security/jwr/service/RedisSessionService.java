package com.dch.core.security.jwr.service;

import java.io.Serializable;
import java.util.Date;

/**
 * Interface that use redis as session management.
 *
 * @author Sayid Sidqi
 */
public interface RedisSessionService<K extends Serializable> {

	void setSession(K key, String session, Date expired);

	boolean sessionExists(K key);

	String getSession(K key);

	void clearSession(K key);
	
}
