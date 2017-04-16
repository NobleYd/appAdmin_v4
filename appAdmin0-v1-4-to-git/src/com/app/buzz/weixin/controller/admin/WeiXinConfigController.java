/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.Pageable;
import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.message.common.MessageType;
import com.app.controller.admin.BaseController;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/wei_xin_config")
public class WeiXinConfigController extends BaseController {

	private String viewPath = "/weixin/wei_xin_config";

	@Resource
	private WeiXinConfig weiXinConfig;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("weiXinConfig", weiXinConfig);
		return viewPath + "/index";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(MessageType messageType, ModelMap model) {
		model.put("messageType", messageType);
		return viewPath + "/add_" + messageType.toString();
	}

}
