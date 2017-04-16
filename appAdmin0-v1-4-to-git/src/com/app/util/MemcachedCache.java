/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.OperationTimeoutException;

/**
 * Utils - MemcachedCache
 * 
 * @author APP TEAM
 * @version 1.0
 *
 */
public class MemcachedCache {

	protected static Log log = LogFactory.getLog(MemcachedCache.class);

	private MemcachedClient memcachedClient;

	private ObjectMapper mapper = new ObjectMapper();

	public void set(String key, String value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return;
		} else {
			memcachedClient.set(key, 0, value);
			return;
		}
	}

	public void set(String key, String value, int expiry) {
		if (StringUtils.isEmpty(key) || value == null) {
			return;
		} else {
			memcachedClient.set(key, expiry, value);
			return;
		}
	}

	public void set(String key, Object value, int expiry) {
		String sv = null;
		try {
			sv = mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			log.warn("", e);
		}
		set(key, sv, expiry);
	}

	public <T> T get(String key, Class<T> valueType) {
		String value = get(key);
		if (value == null)
			return null;
		try {
			return mapper.readValue(value, valueType);
		} catch (Exception e) {
			log.warn("", e);
			return null;
		}
	}

	public String get(String key) {
		if (StringUtils.isEmpty(key))
			return null;
		String o;
		try {
			o = (String) memcachedClient.get(key);
		} catch (OperationTimeoutException e) {
			o = (String) memcachedClient.get(key);
		}
		return o;
	}

	public void delete(String key) {
		if (StringUtils.isEmpty(key)) {
			return;
		} else {
			memcachedClient.delete(key);
			return;
		}
	}

	public boolean exists(String key) {
		if (StringUtils.isEmpty(key))
			return false;
		else
			return memcachedClient.get(key) != null;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

}
