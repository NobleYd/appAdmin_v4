/*
 * Copyright 2005-2013 smsbao.com. All rights reserved.
 * Support: http://www.smsbao.com
 * License: http://www.smsbao.com/license
 */
package com.app.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.service.AdminService;


/**
 * Controller - 管理员注销
 * 
 * @author SMSBAO Team
 * @version 1.0
 */
@Controller("adminLogoutController")
@RequestMapping("/admin/logout")
public class LogoutController extends BaseController {
	
	@Resource(name = "adminService")
	private AdminService adminService;

	/**
	 * 注销
	 */
	@RequestMapping()
	public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String redirectUrl = request.getContextPath() + "/admin/login.jhtml";
		adminService.logout();
		return "redirect:"+redirectUrl;
	}

}