/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.web.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Message;
import com.app.Pageable;
import com.app.buzz.stats.bean.StatsCycle;
import com.app.buzz.stats.bean.StatsMethod;
import com.app.buzz.stats.bean.StatsValueType;
import com.app.buzz.stats.entity.StatsItem;
import com.app.buzz.stats.service.StatsItemService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;
import com.app.service.AdminService;

/***
 *
 * 控制器 - 统计项目
 *
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/stats_item")
public class StatsItemController extends BaseController {

	private String viewPath = "/stats/stats_item";

	@Resource
	private StatsItemService statsItemService;

	@Resource
	private AdminService adminService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", statsItemService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("statsValueTypes", StatsValueType.values());
		model.addAttribute("statsMethods", StatsMethod.values());
		model.addAttribute("statsCycles", StatsCycle.values());
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(StatsItem statsItem, RedirectAttributes redirectAttributes) {
		if (!isValid(statsItem)) {
			return ERROR_VIEW;
		}
		if (statsItem.getTitle() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计项标题(英文)是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsItem.getShowTitle() == null) {
			addFlashMessage(redirectAttributes, Message.error("显示标题是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsItem.getStatsValueType() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计值类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsItem.getStatsCycle() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计周期是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsItem.getStatsMethod() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计方法是必填项哦！"));
			return "redirect:list.jhtml";
		}

		//设置创建者ID为当前登录管理员的ID
		statsItem.setPartnerId(adminService.getCurrent().getId());
		
		statsItemService.save(statsItem);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		try {
			statsItemService.delete(ids);
		} catch (Exception e) {
			return ERROR_MESSAGE;
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		StatsItem statsItem = statsItemService.find(id);
		model.addAttribute("statsItem", statsItem);
		
		model.addAttribute("statsValueTypes", StatsValueType.values());
		model.addAttribute("statsMethods", StatsMethod.values());
		model.addAttribute("statsCycles", StatsCycle.values());
		
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(StatsItem statsItem, RedirectAttributes redirectAttributes) {
		if (!isValid(statsItem)) {
			return ERROR_VIEW;
		}
		if (statsItem.getTitle() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计项标题(英文)是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsItem.getShowTitle() == null) {
			addFlashMessage(redirectAttributes, Message.error("显示标题是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsItem.getStatsValueType() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计值类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsItem.getStatsCycle() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计周期是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsItem.getStatsMethod() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计方法是必填项哦！"));
			return "redirect:list.jhtml";
		}
		StatsItem persistant = statsItemService.find(statsItem.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(statsItem, persistant, new ArrayList<String>() {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
					add("partnerId");//创建统计项的人不可变更
				};
			}.toArray(new String[] {}));
			statsItemService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}
	
	@RequestMapping("/chart/{statsItemId}")
	public String chart(@PathVariable("statsItemId") Long statsItemId, ModelMap model) {
		if (statsItemId == null) {
			return ERROR_VIEW;
		}
		StatsItem statsItem = statsItemService.find(statsItemId);
		model.addAttribute("statsItem", statsItem);
		return "stats/stats_item/chart";
	}

}
