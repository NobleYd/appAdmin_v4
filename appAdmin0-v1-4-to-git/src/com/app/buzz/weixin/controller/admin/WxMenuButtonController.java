/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.constants.WeiXinURL;
import com.app.buzz.weixin.entity.WxMenuButton;
import com.app.buzz.weixin.entity.WxMenuButton.WxMenuButtonType;
import com.app.buzz.weixin.service.WxMenuButtonService;
import com.app.buzz.weixin.util.WeiXinHttpUtils;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;
import com.app.util.JacksonUtils;

/***
 *
 * 控制器 - 微信按钮
 *
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/wx_menu_button")
public class WxMenuButtonController extends BaseController {

	private static Log log = LogFactory.getLog(WxMenuButtonController.class);

	private String viewPath = "/weixin/wx_menu_button";

	@Resource
	private WxMenuButtonService wxMenuButtonService;

	@Resource
	private WeiXinConfig weiXinConfig;

	/**
	 * 查询当前菜单方案/删除方案
	 */
	@RequestMapping(value = "/show_menus", method = RequestMethod.GET)
	public String showMenus(String clear, ModelMap model) {
		if (!StringUtils.isEmpty(clear)) {
			Map<String, Object> jsonMap = WeiXinHttpUtils.getJson(WeiXinURL.get_menu_clear, weiXinConfig, null, null);
			if (jsonMap.containsKey("errcode")) {
				if ("0".equals(jsonMap.get("errcode").toString().trim())) {
					// 成功
					log.info("WxMenuButtonController.showMenus->clearMenus() info. jsonMap = " + jsonMap.toString());
				} else {
					// 失败
					log.error("WxMenuButtonController.showMenus->clearMenus() error. jsonMap = " + jsonMap.toString());
				}
			} else {
				// unknown
				log.error("WxMenuButtonController.showMenus->clearMenus() unknown error. jsonMap = " + jsonMap.toString());
			}
			return "redirect:show_menus.jhtml";
		}
		model.addAttribute("menus", WeiXinHttpUtils.getJson(WeiXinURL.get_menu_all, weiXinConfig, null, null));
		return viewPath + "/menu_show";
	}

	/**
	 * 设置菜单方案
	 */
	@RequestMapping(value = "/set_menus", method = RequestMethod.GET)
	public String setMenus(ModelMap model) {
		model.put("wxMenuButtons", wxMenuButtonService.findAll());
		return viewPath + "/set_menus";
	}

	/**
	 * 设置菜单方案
	 */
	@RequestMapping(value = "/set_menus", method = RequestMethod.POST)
	public String setMenus(Long ids[], RedirectAttributes redirectAttributes, ModelMap model) {
		System.out.println(Arrays.asList(ids));
		//
		HashMap<String, Object> menus = new HashMap<String, Object>();
		List<Object> buttons = new ArrayList<Object>();
		for (int i = 0; i < ids.length; i++) {
			WxMenuButton wxMenuButton = wxMenuButtonService.find(ids[i]);
			if (wxMenuButton == null) {
				addFlashMessage(redirectAttributes, ERROR_MESSAGE);
				return "redirect:show_menus.jhtml";
			}
			buttons.add(wxMenuButton.getWxMenuButton());
		}
		menus.put("button", buttons);
		Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_menu_create, weiXinConfig, null, JacksonUtils.toJson(menus));
		if (jsonMap.containsKey("errcode")) {
			if ("0".equals(jsonMap.get("errcode").toString().trim())) {
				// 成功
				log.info("WxMenuButtonController.setMenus() info. jsonMap = " + jsonMap.toString());
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			} else {
				addFlashMessage(redirectAttributes, ERROR_MESSAGE);
				// 失败
				log.error("WxMenuButtonController.setMenus() error. jsonMap = " + jsonMap.toString());
			}
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
			// unknown
			log.error("WxMenuButtonController.setMenus() unknown error. jsonMap = " + jsonMap.toString());
		}
		return "redirect:show_menus.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", wxMenuButtonService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.put("Types", WxMenuButton.WxMenuButtonType.values());
		model.put("Parents", wxMenuButtonService.findList(null, new ArrayList<Filter>() {
			{
				// 不允许出现三级
				add(Filter.isNull("parent"));
			}
		}, null));
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(WxMenuButton wxMenuButton, Long parentId, RedirectAttributes redirectAttributes) {
		if (!isValid(wxMenuButton)) {
			return ERROR_VIEW;
		}
		if (parentId != null) {
			WxMenuButton parent = wxMenuButtonService.find(parentId);
			if (parent.getParent() != null) {
				addFlashMessage(redirectAttributes, Message.error("非法父按钮！"));
				return "redirect:add.jhtml";
			}
			wxMenuButton.setParent(parent);
		}
		if (wxMenuButton.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("显示名称是必填项哦！"));
			return "redirect:add.jhtml";
		}
		if (wxMenuButton.getType() == null) {
			addFlashMessage(redirectAttributes, Message.error("按钮类型是必填项哦！"));
			return "redirect:add.jhtml";
		}

		if (WxMenuButtonType.parent.equals(wxMenuButton.getType())) {
		} else if (WxMenuButtonType.click.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.view.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getUrl() == null) {
				addFlashMessage(redirectAttributes, Message.error("URL是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.scancode_push.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.scancode_waitmsg.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.pic_sysphoto.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.pic_photo_or_album.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.pic_weixin.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.location_select.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.media_id.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getMedia_id() == null) {
				addFlashMessage(redirectAttributes, Message.error("MediaId是必填项哦！"));
				return "redirect:add.jhtml";
			}
		} else if (WxMenuButtonType.view_limited.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getMedia_id() == null) {
				addFlashMessage(redirectAttributes, Message.error("MediaId是必填项哦！"));
				return "redirect:add.jhtml";
			}
		}

		wxMenuButtonService.save(wxMenuButton);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		try {
			wxMenuButtonService.delete(ids);
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
		WxMenuButton wxMenuButton = wxMenuButtonService.find(id);
		model.put("Types", WxMenuButton.WxMenuButtonType.values());
		model.put("Parents", wxMenuButtonService.findList(null, new ArrayList<Filter>() {
			{
				// 不允许出现三级
				add(Filter.isNull("parent"));
			}
		}, null));
		model.addAttribute("wxMenuButton", wxMenuButton);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(WxMenuButton wxMenuButton, Long parentId, RedirectAttributes redirectAttributes) {
		if (!isValid(wxMenuButton)) {
			return ERROR_VIEW;
		}
		if (parentId != null) {
			WxMenuButton parent = wxMenuButtonService.find(parentId);
			if (parent.getParent() != null) {
				addFlashMessage(redirectAttributes, Message.error("非法父按钮！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
			wxMenuButton.setParent(parent);
		}
		if (wxMenuButton.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("显示名称是必填项哦！"));
			return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
		}
		if (wxMenuButton.getType() == null) {
			addFlashMessage(redirectAttributes, Message.error("按钮类型是必填项哦！"));
			return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
		}

		if (WxMenuButtonType.parent.equals(wxMenuButton.getType())) {
		} else if (WxMenuButtonType.click.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.view.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getUrl() == null) {
				addFlashMessage(redirectAttributes, Message.error("URL是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.scancode_push.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.scancode_waitmsg.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.pic_sysphoto.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.pic_photo_or_album.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.pic_weixin.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.location_select.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getKey() == null) {
				addFlashMessage(redirectAttributes, Message.error("KEY是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.media_id.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getMedia_id() == null) {
				addFlashMessage(redirectAttributes, Message.error("MediaId是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		} else if (WxMenuButtonType.view_limited.equals(wxMenuButton.getType())) {
			if (wxMenuButton.getMedia_id() == null) {
				addFlashMessage(redirectAttributes, Message.error("MediaId是必填项哦！"));
				return "redirect:edit.jhtml?id=" + wxMenuButton.getId();
			}
		}

		WxMenuButton persistant = wxMenuButtonService.find(wxMenuButton.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(wxMenuButton, persistant, new ArrayList<String>() {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
				}
			}.toArray(new String[] {}));
			wxMenuButtonService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	/***
	 * 根据name这些属性搜索WxMenuButton实体
	 */
	@RequestMapping(value = "/searchParent", method = RequestMethod.GET)
	public @ResponseBody List<WxMenuButton> searchParent(String q) {
		return wxMenuButtonService.search(q, new String[] { "name" }, 20);
	}

}
