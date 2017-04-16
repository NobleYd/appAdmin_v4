/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.web.attribute.controller;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Message;
import com.app.Pageable;
import com.app.buzz.dev.entity.DevAttribute;
import com.app.buzz.dev.service.DevAttributeService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/dev/attribute")
public class DevAttributeController extends BaseController {

	private String viewPath = "/com/app/buzz/dev/web/attribute/view";

	@Resource
	private DevAttributeService devAttributeService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", devAttributeService.findPage(pageable));
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
	public String save(DevAttribute devAttribute, RedirectAttributes redirectAttributes) {
		if (!isValid(devAttribute)) {
			return ERROR_VIEW;
		}
		if (devAttribute.getAttributeName() == null) {
			addFlashMessage(redirectAttributes, Message.error("属性名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devAttribute.getAttributeDesc() == null) {
			addFlashMessage(redirectAttributes, Message.error("属性注释是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devAttribute.getAttributeType() == null) {
			addFlashMessage(redirectAttributes, Message.error("属性类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		devAttributeService.save(devAttribute);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		devAttributeService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("devAttribute", devAttributeService.find(id));
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(DevAttribute devAttribute, RedirectAttributes redirectAttributes) {
		if (!isValid(devAttribute)) {
			return ERROR_VIEW;
		}
		if (devAttribute.getAttributeName() == null) {
			addFlashMessage(redirectAttributes, Message.error("属性名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devAttribute.getAttributeDesc() == null) {
			addFlashMessage(redirectAttributes, Message.error("属性注释是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devAttribute.getAttributeType() == null) {
			addFlashMessage(redirectAttributes, Message.error("属性类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		DevAttribute persistant = devAttributeService.find(devAttribute.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(devAttribute, persistant, new String[] { BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME });
			devAttributeService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

}
