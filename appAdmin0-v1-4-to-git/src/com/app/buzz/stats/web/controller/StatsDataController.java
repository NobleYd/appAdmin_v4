/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.app.buzz.stats.bean.StatsCycle;
import com.app.buzz.stats.bean.StatsMethod;
import com.app.buzz.stats.bean.StatsValueType;
import com.app.buzz.stats.entity.StatsData;
import com.app.buzz.stats.entity.StatsItem;
import com.app.buzz.stats.service.StatsDataService;
import com.app.buzz.stats.service.StatsItemService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;

/***
 *
 * 控制器 - 统计项数据
 *
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/stats_data")
public class StatsDataController extends BaseController {

	private String viewPath = "/stats/stats_data";

	@Resource
	private StatsItemService statsItemService;

	@Resource
	private StatsDataService statsDataService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", statsDataService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.put("statsData", new StatsData());
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(StatsData statsData, Long statsItemId, RedirectAttributes redirectAttributes) {
		if (!isValid(statsData)) {
			return ERROR_VIEW;
		}
		if (statsItemId != null) {
			StatsItem statsItem = statsItemService.find(statsItemId);
			statsData.setStatsItem(statsItem);
		}
		if (statsData.getStatsItem() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计项是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsData.getDataTime() == null) {
			addFlashMessage(redirectAttributes, Message.error("数据时间是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsData.getNumberValue() == null && statsData.getTextValue() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计值是必填项哦！"));
			return "redirect:list.jhtml";
		}

		// statsDataService.save(statsData);
		statsDataService.acceptData(statsData.getStatsItem().getId(), statsData.getDataTime(), statsData.getNumberValue(), statsData.getTextValue());

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		try {
			statsDataService.delete(ids);
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
		StatsData statsData = statsDataService.find(id);
		model.addAttribute("statsData", statsData);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(StatsData statsData, Long statsItemId, RedirectAttributes redirectAttributes) {
		if (!isValid(statsData)) {
			return ERROR_VIEW;
		}
		if (statsItemId != null) {
			StatsItem statsItem = statsItemService.find(statsItemId);
			statsData.setStatsItem(statsItem);
		}
		if (statsData.getStatsItem() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计项是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsData.getDataTime() == null) {
			addFlashMessage(redirectAttributes, Message.error("数据时间是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsData.getNumberValue() == null) {
			addFlashMessage(redirectAttributes, Message.error("数字值是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsData.getTextValue() == null) {
			addFlashMessage(redirectAttributes, Message.error("文本值是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (statsData.getStatsCount() == null) {
			addFlashMessage(redirectAttributes, Message.error("统计数据量是必填项哦！"));
			return "redirect:list.jhtml";
		}
		StatsData persistant = statsDataService.find(statsData.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(statsData, persistant, new ArrayList<String>() {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
				}
			}.toArray(new String[] {}));
			statsDataService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	/***
	 * 根据title这些属性搜索StatsItem实体
	 */
	@RequestMapping(value = "/searchStatsItem", method = RequestMethod.GET)
	public @ResponseBody List<StatsItem> searchStatsItem(String q) {
		return statsItemService.search(q, new String[] { "title", "showTitle" }, 20);
	}

	@RequestMapping(value = "/stats")
	@ResponseBody
	public Object stats(Long statsItemId, StatsCycle statsCycle, StatsMethod statsMethod, Date start, Date end) {

		if (statsMethod == null) {
			statsMethod = StatsMethod.sum;
		}

		Object obj = null;
		switch (statsMethod) {
		case sum:
			obj = statsDataService.findSumData(statsItemId, statsCycle, start, end);
			break;
		case last:
			StatsItem statsItem = statsItemService.find(statsItemId);
			if (statsItem.getStatsValueType() == StatsValueType.number) {
				obj = statsDataService.findLastNumberData(statsItemId, statsCycle, start, end);
			} else {
				obj = statsDataService.findLastTextData(statsItemId, statsCycle, start, end);
			}
			break;
		case max:
			obj = statsDataService.findMaxData(statsItemId, statsCycle, start, end);
			break;
		case min:
			obj = statsDataService.findMinData(statsItemId, statsCycle, start, end);
			break;
		case avg:
			obj = statsDataService.findAverageData(statsItemId, statsCycle, start, end);
			break;
		}

		return obj;
	}

	@RequestMapping(value = "/stats_list")
	public String stats_list(ModelMap model, StatsCycle statsCycle, StatsMethod statsMethod, Date start, Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (statsCycle == null) {
			statsCycle = StatsCycle.second;
		}
		if (start != null) {
			System.out.println("start: " + sdf.format(start));
		}
		if (end != null) {
			System.out.println("end: " + sdf.format(end));
		}
		if (statsMethod == null) {
			statsMethod = StatsMethod.sum;
		}
		switch (statsMethod) {
		case sum:
			model.addAttribute("statsDatas", statsDataService.findSumData(1L, statsCycle, start, end));
			break;
		case last:
			StatsItem statsItem = statsItemService.find(1L);
			if (statsItem.getStatsValueType() == StatsValueType.number) {
				model.addAttribute("statsDatas", statsDataService.findLastNumberData(1L, statsCycle, start, end));
			} else {
				model.addAttribute("statsDatas", statsDataService.findLastTextData(1L, statsCycle, start, end));
			}
			break;
		case max:
			model.addAttribute("statsDatas", statsDataService.findMaxData(1L, statsCycle, start, end));
			break;
		case min:
			model.addAttribute("statsDatas", statsDataService.findMinData(1L, statsCycle, start, end));
			break;
		case avg:
			model.addAttribute("statsDatas", statsDataService.findAverageData(1L, statsCycle, start, end));
			break;
		}

		return "stats/stats_data/stats_list";
	}

}
