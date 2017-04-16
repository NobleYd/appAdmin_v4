/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.controller.admin;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.CommonAttributes;
import com.app.FileInfo.FileType;
import com.app.Message;
import com.app.Setting;
import com.app.Setting.AccountLockType;
import com.app.Setting.CaptchaType;
import com.app.Setting.RoundType;
import com.app.Setting.WatermarkPosition;
import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.service.CacheService;
import com.app.service.FileService;
import com.app.service.MailService;
import com.app.util.SettingUtils;
import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.smtp.SMTPSenderFailedException;

/**
 * Controller - 系统设置
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Controller("adminSettingController")
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {

	@Resource(name = "fileService")
	private FileService fileService;
	@Resource(name = "cacheService")
	private CacheService cacheService;
	@Resource
	private MailService mailService;
	@Resource
	private WeiXinConfig weiXinConfig;

	/**
	 * 邮件测试
	 */
	@RequestMapping(value = "/mail_test", method = RequestMethod.POST)
	public @ResponseBody Message mailTest(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail) {
		if (StringUtils.isEmpty(toMail)) {
			return ERROR_MESSAGE;
		}
		Setting setting = SettingUtils.get();
		if (StringUtils.isEmpty(smtpPassword)) {
			smtpPassword = setting.getSmtpPassword();
		}
		try {
			if (!isValid(Setting.class, "smtpFromMail", smtpFromMail) || !isValid(Setting.class, "smtpHost", smtpHost) || !isValid(Setting.class, "smtpPort", smtpPort) || !isValid(Setting.class, "smtpUsername", smtpUsername)) {
				return ERROR_MESSAGE;
			}
			mailService.sendTestMail(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail);
		} catch (MailSendException e) {
			Exception[] messageExceptions = e.getMessageExceptions();
			if (messageExceptions != null) {
				for (Exception exception : messageExceptions) {
					if (exception instanceof SMTPSendFailedException) {
						SMTPSendFailedException smtpSendFailedException = (SMTPSendFailedException) exception;
						Exception nextException = smtpSendFailedException.getNextException();
						if (nextException instanceof SMTPSenderFailedException) {
							return Message.error("admin.setting.mailTestSenderFailed");
						}
					} else if (exception instanceof MessagingException) {
						MessagingException messagingException = (MessagingException) exception;
						Exception nextException = messagingException.getNextException();
						if (nextException instanceof UnknownHostException) {
							return Message.error("admin.setting.mailTestUnknownHost");
						} else if (nextException instanceof ConnectException) {
							return Message.error("admin.setting.mailTestConnect");
						}
					}
				}
			}
			return Message.error("admin.setting.mailTestError");
		} catch (MailAuthenticationException e) {
			return Message.error("admin.setting.mailTestAuthentication");
		} catch (Exception e) {
			return Message.error("admin.setting.mailTestError");
		}
		return Message.success("admin.setting.mailTestSuccess");
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(ModelMap model) {
		model.addAttribute("watermarkPositions", WatermarkPosition.values());
		model.addAttribute("roundTypes", RoundType.values());
		model.addAttribute("captchaTypes", CaptchaType.values());
		model.addAttribute("accountLockTypes", AccountLockType.values());
		return "/admin/setting/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Setting setting, MultipartFile watermarkImageFile, RedirectAttributes redirectAttributes) {
		if (!isValid(setting)) {
			return ERROR_VIEW;
		}
		// 获取原先设置
		Setting srcSetting = SettingUtils.get();
		if (watermarkImageFile != null && !watermarkImageFile.isEmpty()) {
			if (!fileService.isValid(FileType.image, watermarkImageFile)) {
				addFlashMessage(redirectAttributes, Message.error("admin.upload.invalid"));
				return "redirect:edit.jhtml";
			}
			String watermarkImage = fileService.uploadLocal(FileType.image, watermarkImageFile);
			setting.setWatermarkImage(watermarkImage);
		} else {
			setting.setWatermarkImage(srcSetting.getWatermarkImage());
		}
		setting.setCnzzSiteId(srcSetting.getCnzzSiteId());
		setting.setCnzzPassword(srcSetting.getCnzzPassword());
		SettingUtils.set(setting);
		cacheService.clear();

		OutputStream outputStream = null;
		try {
			org.springframework.core.io.Resource resource = new ClassPathResource(CommonAttributes.APP_PROPERTIES_PATH);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			String templateUpdateDelay = properties.getProperty("template.update_delay");
			String messageCacheSeconds = properties.getProperty("message.cache_seconds");
			if (setting.getIsDevelopmentEnabled()) {
				if (!templateUpdateDelay.equals("0") || !messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "0");
					properties.setProperty("message.cache_seconds", "0");
					properties.store(outputStream, "APP PROPERTIES");
				}
			} else {
				if (templateUpdateDelay.equals("0") || messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "3600");
					properties.setProperty("message.cache_seconds", "3600");
					properties.store(outputStream, "APP PROPERTIES");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}

		// 根据未更改之前的 weiXinConfig 得知是否需要马上刷新。
		setting = SettingUtils.get();
		boolean needRefreshAccessToken = false;

		if (setting.getIsWeiXinEnabled() //
				&& StringUtils.isNotEmpty(setting.getWeixinAppId()) //
				&& StringUtils.isNotEmpty(setting.getWeixinAppSecret()) //
				&& weiXinConfig != null//
				&& (srcSetting.getIsWeiXinEnabled() == null // 之前未开启
						|| !srcSetting.getIsWeiXinEnabled()// 之前未开启
						|| !setting.getWeixinAppId().equals(weiXinConfig.getAppId()) //
						|| !setting.getWeixinAppSecret().equals(weiXinConfig.getAppSecret()))//
		) {
			// 配置有变化，需要更新 AccessToken 。
			needRefreshAccessToken = true;
		}

		// 更新 weiXinConfig 信息。
		if (weiXinConfig != null) {
			weiXinConfig.setToken(setting.getWeixinToken());
			weiXinConfig.setAppId(setting.getWeixinAppId());
			weiXinConfig.setAppSecret(setting.getWeixinAppSecret());
		}

		// AccessToken相关{ 每次更新微信配置立即更新一次 }
		if (needRefreshAccessToken) {
			weiXinConfig.refreshAccessToken();
		}

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:edit.jhtml";
	}

}