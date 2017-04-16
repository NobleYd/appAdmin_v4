/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.job;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.service.WxUserService;
import com.app.util.SettingUtils;

/**
 * Job - 微信
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Component
@Lazy(false)
public class WeiXinJob {

	private static Log log = LogFactory.getLog(WeiXinJob.class);

	@Resource
	private WeiXinConfig weiXinConfig;

	@Resource
	private WxUserService wxUserService;

	/**
	 * 微信 Token 刷新{ 每分钟检测 }
	 */
	@Scheduled(cron = "${job.weixin.token.cron}")
	public void weixinToken() {
		if (SettingUtils.get().getIsWeiXinEnabled() != null && SettingUtils.get().getIsWeiXinEnabled()) {
			log.info("WeiXinJob.weixinToken()");
			weiXinConfig.tryRefreshAccessToken();
		}
	}

	/**
	 * 微信 关注用户 刷新{ 每天检测 }
	 */
	@Scheduled(cron = "${job.weixin.user.cron}")
	public void weixinUser() {
		if (SettingUtils.get().getIsWeiXinEnabled() != null && SettingUtils.get().getIsWeiXinEnabled()) {
			log.info("WeiXinJob.weixinUser()");
			wxUserService.refreshWxUsetInfoFromServer();
		}
	}

}
