/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.web.config.controller;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Filter;
import com.app.Message;
import com.app.Order;
import com.app.Pageable;
import com.app.buzz.dev.entity.DevConfig;
import com.app.buzz.dev.service.DevConfigService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/dev/config")
public class DevConfigController extends BaseController {

	@Resource
	private DevConfigService devConfigService;

	private String viewPath = "/com/app/buzz/dev/web/config/view";

	/**
	 * 按照默认值新增一个基本配置对象。
	 */
	@RequestMapping(value = "/save_new_default", method = RequestMethod.GET)
	public String saveNewDefault(RedirectAttributes redirectAttributes) {
		devConfigService.save(new DevConfig());
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		pageable.setUnlimitPageSize();
		pageable.getOrders().add(Order.desc("isCurrent"));
		model.addAttribute("page", devConfigService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(DevConfig devConfig, RedirectAttributes redirectAttributes) {
		if (!isValid(devConfig)) {
			return ERROR_VIEW;
		}
		if (devConfig.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("配置名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getIsCurrent() == null) {
			addFlashMessage(redirectAttributes, Message.error("是否当前启用是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getCommonTemplatePath() == null) {
			addFlashMessage(redirectAttributes, Message.error("公共 模板文件路径是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getCustomerTemplatePath() == null) {
			addFlashMessage(redirectAttributes, Message.error("自定义 模板文件路径是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getJavaOutputPath() == null) {
			addFlashMessage(redirectAttributes, Message.error("Java 输出文件路径是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getPageOutputPath() == null) {
			addFlashMessage(redirectAttributes, Message.error("页面 输出文件路径是必填项哦！"));
			return "redirect:list.jhtml";
		}
		devConfigService.save(devConfig);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		devConfigService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("devConfig", devConfigService.find(id));
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(DevConfig devConfig, RedirectAttributes redirectAttributes) {
		if (!isValid(devConfig)) {
			return ERROR_VIEW;
		}
		if (devConfig.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("配置名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getIsCurrent() == null) {
			addFlashMessage(redirectAttributes, Message.error("是否当前启用是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getCommonTemplatePath() == null) {
			addFlashMessage(redirectAttributes, Message.error("公共 模板文件路径是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getCustomerTemplatePath() == null) {
			addFlashMessage(redirectAttributes, Message.error("自定义 模板文件路径是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getJavaOutputPath() == null) {
			addFlashMessage(redirectAttributes, Message.error("Java 输出文件路径是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devConfig.getPageOutputPath() == null) {
			addFlashMessage(redirectAttributes, Message.error("页面 输出文件路径是必填项哦！"));
			return "redirect:list.jhtml";
		}

		DevConfig persistant = devConfigService.find(devConfig.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(devConfig, persistant, new String[] { BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME });
			devConfigService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	@RequestMapping("/check_name_unique")
	@ResponseBody
	public Boolean checkNameUnique(final String previousName, final String name) {
		if (name == null) {
			return false;
		}
		if (name.equals(previousName)) {
			return true;
		}
		return devConfigService.find(Filter.eq("name", name, false)) == null;
	}
}
