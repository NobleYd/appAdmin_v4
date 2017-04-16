/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.listener;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.app.Setting;
import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.constants.WeixinAutoReplyMessages;
import com.app.buzz.weixin.entity.AutoReplyMessage;
import com.app.buzz.weixin.service.AutoReplyMessageService;
import com.app.util.SettingUtils;

/**
 * Listener - 初始化
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent> {

	/** logger */
	private static final Logger logger = Logger.getLogger(InitListener.class.getName());

	/** servletContext */
	private ServletContext servletContext;

	@Value("${system.version}")
	private String systemVersion;

	@Resource
	private WeiXinConfig weiXinConfig;

	@Resource
	private WeixinAutoReplyMessages weixinAutoReplyMessages;

	@Resource
	private AutoReplyMessageService autoReplyMessageService;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (servletContext != null && contextRefreshedEvent.getApplicationContext().getParent() == null) {
			String info = "I|n|i|t|i|a|l|i|z|i|n|g| |A|P|P|" + systemVersion;
			logger.info(info.replace("|", ""));

			// 加载 weiXinConfig 信息。
			if (weiXinConfig != null) {
				Setting setting = SettingUtils.get();
				if (setting != null) {
					weiXinConfig.setToken(setting.getWeixinToken());
					weiXinConfig.setAppId(setting.getWeixinAppId());
					weiXinConfig.setAppSecret(setting.getWeixinAppSecret());
					if (setting.getIsWeiXinEnabled() && StringUtils.isNotEmpty(weiXinConfig.getAppId()) && StringUtils.isNotEmpty(weiXinConfig.getAppSecret())) {
						// 立即刷新 AccessToken 。
						weiXinConfig.refreshAccessToken();
					}
				}
			}

			// 加载 weixinAutoReplyMessages 。
			if (weixinAutoReplyMessages != null) {
				List<AutoReplyMessage> autoReplyMessages = autoReplyMessageService.findAll();
				for (AutoReplyMessage msg : autoReplyMessages) {
					weixinAutoReplyMessages.put(msg.getKeyword(), msg.getReply());
				}
			}

		}
	}

}