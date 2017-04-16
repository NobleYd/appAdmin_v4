/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.app.Pageable;
import com.app.buzz.demo.entity.TreeEntityTest;
import com.app.buzz.demo.service.TreeEntityTestService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/tree_entity_test")
public class TreeEntityTestController extends BaseController {

	private String viewPath = "/demo/tree_entity_test";

	@Resource
	private TreeEntityTestService treeEntityTestService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("treeEntityTestTree", treeEntityTestService.findTree());
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("treeEntityTestTree", treeEntityTestService.findTree());
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TreeEntityTest treeEntityTest, Long parentId, RedirectAttributes redirectAttributes) {
		if (!isValid(treeEntityTest)) {
			return ERROR_VIEW;
		}
		if (treeEntityTest.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("标题是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (treeEntityTest.getDescription() == null) {
			addFlashMessage(redirectAttributes, Message.error("描述是必填项哦！"));
			return "redirect:list.jhtml";
		}
		treeEntityTest.setParent(treeEntityTestService.find(parentId));

		treeEntityTestService.save(treeEntityTest);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		List<TreeEntityTest> treeEntityTests = treeEntityTestService.findList(ids);
		for (TreeEntityTest treeEntityTest : treeEntityTests) {
			if (treeEntityTest.getChildren() != null && treeEntityTest.getChildren().size() > 0) {
				return ERROR_MESSAGE;
			} else {
				treeEntityTestService.delete(treeEntityTest);
			}
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		TreeEntityTest treeEntityTest = treeEntityTestService.find(id);
		model.addAttribute("treeEntityTestTree", treeEntityTestService.findTree());
		model.addAttribute("children", treeEntityTestService.findChildren(treeEntityTest));
		model.addAttribute("treeEntityTest", treeEntityTest);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TreeEntityTest treeEntityTest, Long parentId, RedirectAttributes redirectAttributes) {
		if (!isValid(treeEntityTest)) {
			return ERROR_VIEW;
		}
		treeEntityTest.setParent(treeEntityTestService.find(parentId));
		if (treeEntityTest.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("标题是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (treeEntityTest.getDescription() == null) {
			addFlashMessage(redirectAttributes, Message.error("描述是必填项哦！"));
			return "redirect:list.jhtml";
		}
		TreeEntityTest persistant = treeEntityTestService.find(treeEntityTest.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(treeEntityTest, persistant, new ArrayList<String>() {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
				}
			}.toArray(new String[] {}));
			treeEntityTestService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	@RequestMapping("/check_name_unique")
	@ResponseBody
	public Boolean checkNameUnique(final String previousName, final String name) {
		// string类型请使用注释部分的if判断替换下面对应两个if判断
		// if (StringUtils.isEmpty(name)) {
		// return false;
		// }
		// if (StringUtils.equalsIgnoreCase(previousName, name)) {
		// return true;
		// }
		if (name == null) {
			return false;
		}
		if (name.equals(previousName)) {
			return true;
		}
		return treeEntityTestService.find(Filter.eq("name", name, false)) == null;
	}

}
