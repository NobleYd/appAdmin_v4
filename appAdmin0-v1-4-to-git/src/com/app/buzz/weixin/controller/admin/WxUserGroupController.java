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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Filter;
import com.app.Message;
import com.app.Pageable;
import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.entity.WxUserGroup;
import com.app.buzz.weixin.service.WxUserGroupService;
import com.app.buzz.weixin.util.WeiXinUtils;
import com.app.controller.admin.BaseController;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/wx_user_group")
public class WxUserGroupController extends BaseController {

	private String viewPath = "/weixin/wx_user_group";

	@Resource
	private WxUserGroupService wxUserGroupService;

	@Resource
	private WeiXinConfig weiXinConfig;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", wxUserGroupService.findPage(pageable));
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
	public String save(WxUserGroup wxUserGroup, RedirectAttributes redirectAttributes) {
		if (!isValid(wxUserGroup)) {
			return ERROR_VIEW;
		}
		if (wxUserGroup.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("分组名是必填项哦！"));
			return "redirect:list.jhtml";
		}

		// 上传到微信服务器
		if (WeiXinUtils.addWxUserGroup(weiXinConfig, wxUserGroup)) {
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			wxUserGroupService.save(wxUserGroup);
			return "redirect:list.jhtml";
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
			return "redirect:add.jhtml";
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		// 仅仅支持单一删除
		if (ids != null && ids.length == 1) {
			WxUserGroup wxUserGroup = wxUserGroupService.find(ids[0]);
			if (WeiXinUtils.deleteWxUserGroup(weiXinConfig, wxUserGroup.getGroupId())) {
				wxUserGroupService.delete(ids);
				return SUCCESS_MESSAGE;
			} else {
				return ERROR_MESSAGE;
			}
		} else {
			return ERROR_MESSAGE;
		}
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		WxUserGroup wxUserGroup = wxUserGroupService.find(id);
		model.addAttribute("wxUserGroup", wxUserGroup);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(WxUserGroup wxUserGroup, RedirectAttributes redirectAttributes) {
		if (!isValid(wxUserGroup)) {
			return ERROR_VIEW;
		}
		if (wxUserGroup.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("分组名是必填项哦！"));
			return "redirect:edit.jhtml?id=" + wxUserGroup.getId();
		}
		WxUserGroup persistant = wxUserGroupService.find(wxUserGroup.getId());
		if (persistant != null) {
			persistant.setName(wxUserGroup.getName());
			if (WeiXinUtils.updateWxUserGroup(weiXinConfig, persistant)) {
				wxUserGroupService.update(persistant);
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
				return "redirect:list.jhtml";
			} else {
				addFlashMessage(redirectAttributes, ERROR_MESSAGE);
				return "redirect:edit.jhtml?id=" + wxUserGroup.getId();
			}
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:edit.jhtml?id=" + wxUserGroup.getId();
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
		return wxUserGroupService.find(Filter.eq("name", name, false)) == null;
	}

}
