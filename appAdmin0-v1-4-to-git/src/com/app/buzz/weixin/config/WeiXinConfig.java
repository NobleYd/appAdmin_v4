/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.config;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.app.buzz.weixin.constants.WeiXinURL;
import com.app.buzz.weixin.util.WeiXinHttpUtils;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Component
public class WeiXinConfig {

	private static Log log = LogFactory.getLog(WeiXinConfig.class);

	// ----------------------------------------------配置信息-----------------
	/** 接口配置信息{此信息是通过web配置好然后填写到本系统的信息} */
	private String token;
	/** 公众号信息 */
	private String appId;
	private String appSecret;
	private int expireTime = 7200000;// 单位：毫秒 {默认7200秒}

	/** 配置是否支持 jsapi */
	private boolean enableJsApi;

	// ----------------------------------------------其他信息-----------------

	/** 用于保存 accessToken 和 jsApiTicket */
	private String accessToken;
	private String jsApiTicket;

	/** 用于保证原子操作的2个变量 */
	private final AtomicBoolean accessTokenRefreshingFlag = new AtomicBoolean(false);
	private final AtomicBoolean jsApiTicketRefreshingFlag = new AtomicBoolean(false);

	/** 记录当前 accessToken 获取的时间{用于决定刷新时间} */
	private long weixinAccessTokenStartTime = 0;// 单位：毫秒
	/** 记录当前 jsApiTicket 获取的时间{用于决定刷新时间} */
	private long jsApiTicketStartTime = 0;// 单位：毫秒

	/***
	 * 默认构造函数
	 */
	public WeiXinConfig() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param appid
	 *            应用ID
	 * @param secret
	 *            应用密钥
	 * @param enableJsApi
	 *            是否支持 Js API
	 */
	public WeiXinConfig(String appid, String secret, boolean enableJsApi) {
		super();
		this.appId = appid;
		this.appSecret = secret;
		this.enableJsApi = enableJsApi;
	}

	/***
	 * 如果需要则刷新 Token
	 */
	public void tryRefreshAccessToken() {
		// 预留500秒的时间
		if (this.weixinAccessTokenStartTime + this.expireTime - 500 < System.currentTimeMillis()) {
			this.refreshAccessToken();
		}
	}

	/***
	 * 刷新 AccessToken
	 */
	public void refreshAccessToken() {
		if (accessTokenRefreshingFlag.compareAndSet(false, true)) {
			try {
				// 记录上一次 AccessToken 刷新时间
				long lastWeixinAccessTokenStartTime = weixinAccessTokenStartTime;
				// 设置新的 AccessToken 开始时间
				this.weixinAccessTokenStartTime = System.currentTimeMillis();
				// 刷新
				Map<String, Object> jsonMap = WeiXinHttpUtils.getJson(WeiXinURL.get_accessTokenUrl, this, null, null);
				if (jsonMap.containsKey("access_token")) {
					// example: {"access_token":"ACCESS_TOKEN","expires_in":7200}
					this.accessToken = jsonMap.get("access_token").toString().trim();
					if (jsonMap.containsKey("expires_in")) {
						this.expireTime = (Integer) jsonMap.get("expires_in") * 1000;
					}
					log.info("WeiXinConfig.refreshAccessToken() info. jsonMap = " + jsonMap.toString());
				} else if (jsonMap.containsKey("errcode")) {
					// example: {"errcode":40013,"errmsg":"invalid appid"}
					log.error("WeiXinConfig.refreshAccessToken() error. jsonMap = " + jsonMap.toString());
					this.weixinAccessTokenStartTime = lastWeixinAccessTokenStartTime;
				} else {
					// unknown
					log.error("WeiXinConfig.refreshAccessToken() unknown error. jsonMap = " + jsonMap.toString());
					this.weixinAccessTokenStartTime = lastWeixinAccessTokenStartTime;
				}
			} finally {
				accessTokenRefreshingFlag.set(false);
			}
		}
	}

	public void refreshJsTicket() {

	}

	// ----------------------------------------------getters and setters-----------------

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isEnableJsApi() {
		return enableJsApi;
	}

	public void setEnableJsApi(boolean enableJsApi) {
		this.enableJsApi = enableJsApi;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getJsApiTicket() {
		return jsApiTicket;
	}

	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}

	public long getWeixinAccessTokenStartTime() {
		return weixinAccessTokenStartTime;
	}

	public Date getFormatedWeixinAccessTokenStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(weixinAccessTokenStartTime);
		return calendar.getTime();
	}

	public void setWeixinAccessTokenStartTime(long weixinAccessTokenStartTime) {
		this.weixinAccessTokenStartTime = weixinAccessTokenStartTime;
	}

	public long getJsApiTicketStartTime() {
		return jsApiTicketStartTime;
	}

	public Date getFormatedJsApiTicketStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(jsApiTicketStartTime);
		return calendar.getTime();
	}

	public void setJsApiTicketStartTime(long jsApiTicketStartTime) {
		this.jsApiTicketStartTime = jsApiTicketStartTime;
	}

	public AtomicBoolean getAccessTokenRefreshingFlag() {
		return accessTokenRefreshingFlag;
	}

	public AtomicBoolean getJsApiTicketRefreshingFlag() {
		return jsApiTicketRefreshingFlag;
	}
}
