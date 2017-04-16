/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller - 角色
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Controller("adminQrCodeController")
@RequestMapping("/admin/qrcode")
public class QrCodeController extends BaseController {

	/**
	 * 生成二维码页面
	 */
	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public String generate() {
		return "/admin/qrcode/generate";
	}

}