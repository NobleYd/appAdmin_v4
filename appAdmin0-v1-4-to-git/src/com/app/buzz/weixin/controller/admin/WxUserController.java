/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Message;
import com.app.Pageable;
import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.entity.WxUser;
import com.app.buzz.weixin.entity.WxUserGroup;
import com.app.buzz.weixin.service.WxUserGroupService;
import com.app.buzz.weixin.service.WxUserService;
import com.app.buzz.weixin.util.WeiXinUtils;
import com.app.controller.admin.BaseController;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/wx_user")
public class WxUserController extends BaseController {

	private static Log log = LogFactory.getLog(WxUserController.class);

	private String viewPath = "/weixin/wx_user";

	@Resource
	private WxUserService wxUserService;

	@Resource
	private WxUserGroupService wxUserGroupService;

	@Resource
	private WeiXinConfig weiXinConfig;

	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;

	/**
	 * 同步服务器用户到本地
	 */
	@RequestMapping(value = "/get_server_list", method = RequestMethod.GET)
	public String get_server_list(RedirectAttributes redirectAttributes, ModelMap model) {
		// 异步执行
		taskExecutor.execute(new Runnable() {
			public void run() {
				wxUserService.refreshWxUsetInfoFromServer();
			}
		});
		// 返回提示
		addFlashMessage(redirectAttributes, Message.success("开始同步数据，时间取决于关注用户数量，时间未知！"));
		return "redirect:list.jhtml";
	}

	/**
	 * 从服务器刷新信息
	 */
	@RequestMapping(value = "/refresh_info", method = RequestMethod.GET)
	public String refreshWxUserInfo(Long id, RedirectAttributes redirectAttributes, ModelMap model) {
		WxUser persistant = wxUserService.find(id);
		if (persistant != null) {
			if (WeiXinUtils.refreshWxUserInfo(weiXinConfig, persistant)) {
				wxUserService.update(persistant);
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			}
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	/**
	 * 批量移动用户的分组
	 */
	@RequestMapping(value = "/batch_move2group")
	public @ResponseBody Message batchMove2group(Long[] ids, Long wxGroupId) {
		// wxUserService.delete(ids);
		WxUserGroup wxUserGroup = wxUserGroupService.find(wxGroupId);
		if (wxUserGroup == null)
			return ERROR_MESSAGE;
		if (ids != null && ids.length > 0) {
			List<WxUser> wxUsers = wxUserService.findList(ids);
			if (WeiXinUtils.batchMoveWxUser2Group(weiXinConfig, wxUsers, wxUserGroup)) {
				for (WxUser wxUser : wxUsers) {
					wxUser.setGroupid(wxUserGroup.getGroupId());
					wxUserService.update(wxUser);
				}
			}
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", wxUserService.findPage(pageable));
		model.addAttribute("wxUserGroups", wxUserGroupService.findAll());
		return viewPath + "/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		wxUserService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		WxUser wxUser = wxUserService.find(id);
		model.addAttribute("wxUser", wxUser);

		model.addAttribute("wxUserGroups", wxUserGroupService.findAll());

		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(WxUser wxUser, Long wxUserGroupId, RedirectAttributes redirectAttributes) {
		if (!isValid(wxUser)) {
			return ERROR_VIEW;
		}
		if (wxUserGroupId != null) {
			WxUserGroup wxUserGroup = wxUserGroupService.find(wxUserGroupId);
			if (wxUserGroup != null) {
				wxUser.setGroupid(wxUserGroup.getGroupId());
			}
		}
		WxUser persistant = wxUserService.find(wxUser.getId());
		if (persistant != null) {
			persistant.setRemark(wxUser.getRemark());
			persistant.setGroupid(wxUser.getGroupid());
			// 更新到服务器
			if (WeiXinUtils.moveWxUser2Group(weiXinConfig, persistant) && WeiXinUtils.remarkWxUser(weiXinConfig, persistant)) {
				wxUserService.update(persistant);
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			} else {
				addFlashMessage(redirectAttributes, ERROR_MESSAGE);
				return "redirect:edit.jhtml?id=" + persistant.getId();
			}
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

}
