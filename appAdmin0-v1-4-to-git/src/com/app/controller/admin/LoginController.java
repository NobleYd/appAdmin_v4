/*
 * Copyright 2005-2013 app.com. All rights reserved.
 * Support: http://www.app.com
 * License: http://www.app.com/license
 */
package com.app.controller.admin;

import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.Setting;
import com.app.Setting.AccountLockType;
import com.app.service.AdminService;
import com.app.service.RSAService;
import com.app.util.SettingUtils;

/**
 * Controller - 会员登录
 * 
 * @author APP Team
 * @version 1.0
 */
@Controller("adminLoginController")
@RequestMapping("/admin/login")
public class LoginController extends BaseController {

	@Resource(name = "rsaService")
	private RSAService rsaService;
	@Resource(name = "adminService")
	private AdminService adminService;

	/**
	 * 登录页面
	 */
	@RequestMapping()
	public String index(String redirectUrl, HttpServletRequest request, ModelMap model) {
		Setting setting = SettingUtils.get();

		if (adminService.isAuthenticated()) {
			redirectUrl = request.getContextPath() + "/admin/common/main.jhtml";
			return "redirect:" + redirectUrl;
		}

		model.addAttribute("redirectUrl", redirectUrl);
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		RSAPublicKey publicKey = rsaService.generateKey(request);
		model.addAttribute("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
		model.addAttribute("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));

		String message = null;
		String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (loginFailure != null) {
			if (loginFailure.equals("org.apache.shiro.authc.pam.UnsupportedTokenException")) {
				message = "admin.captcha.invalid";
			} else if (loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")) {
				message = "admin.login.unknownAccount";
			} else if (loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")) {
				message = "admin.login.disabledAccount";
			} else if (loginFailure.equals("org.apache.shiro.authc.LockedAccountException")) {
				message = "admin.login.lockedAccount";
			} else if (loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
				if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.admin)) {
					message = "admin.login.accountLockCount";
				} else {
					message = "admin.login.incorrectCredentials";
				}
			} else if (loginFailure.equals("org.apache.shiro.authc.AuthenticationException")) {
				message = "admin.login.authentication";
			}
			message = message(message, SettingUtils.get().getAccountLockCount());
		}
		model.addAttribute("errorMessage", message);
		return "/admin/login/index";
	}

}