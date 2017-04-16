/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.app.buzz.weixin.message.common.Reply;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Component
public class WeixinAutoReplyMessages {

	private static Map<String, Reply> replys = new HashMap<String, Reply>();

	public static Map<String, Reply> getReplys() {
		return replys;
	}

	public static void setReplys(Map<String, Reply> replys) {
		WeixinAutoReplyMessages.replys = replys;
	}

	public void put(String key, Reply reply) {
		replys.put(key, reply);
	}

	public Reply get(String key) {
		return replys.get(key);
	}

	public void remove(String key) {
		replys.remove(key);
	}

}
