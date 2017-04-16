/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.controller.admin;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Filter;
import com.app.Message;
import com.app.Pageable;
import com.app.entity.BaseEntity;
import com.app.entity.Template;
import com.app.entity.TemplateEntity;
import com.app.service.TemplateService;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/template")
public class TemplateController extends BaseController {

	private String viewPath = "/admin/template";

	@Resource
	private TemplateService templateService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", templateService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.put("Types", Template.Type.values());
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Template template, RedirectAttributes redirectAttributes) {
		if (!isValid(template)) {
			return ERROR_VIEW;
		}
		if (template.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("模板名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (template.getType() == null) {
			addFlashMessage(redirectAttributes, Message.error("模板类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (template.getTemplatePath() == null) {
			template.setTemplatePath(viewPath + "/contents/" + template.getNameOfTimeStamp());
		}

		templateService.save(template);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		templateService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		Template template = templateService.find(id);
		model.put("Types", Template.Type.values());
		model.addAttribute("template", template);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Template template, RedirectAttributes redirectAttributes) {
		if (!isValid(template)) {
			return ERROR_VIEW;
		}
		if (template.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("模板名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (template.getType() == null) {
			addFlashMessage(redirectAttributes, Message.error("模板类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		Template persistant = templateService.find(template.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(template, persistant, new ArrayList<String>() {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
					add(TemplateEntity.TEMPLATE_PATH_PROPERTY_NAME);
				}
			}.toArray(new String[] {}));
			templateService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	@RequestMapping("/check_name_unique")
	@ResponseBody
	public Boolean checkNameUnique(final String previousName, final String name) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}
		if (StringUtils.equalsIgnoreCase(previousName, name)) {
			return true;
		}
		return templateService.find(Filter.eq("name", name, false)) == null;
	}

}
