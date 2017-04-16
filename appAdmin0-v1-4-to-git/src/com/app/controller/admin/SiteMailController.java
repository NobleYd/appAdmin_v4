/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.controller.admin;

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
import com.app.Order;
import com.app.Pageable;
import com.app.entity.Admin;
import com.app.entity.BaseEntity;
import com.app.entity.Contact;
import com.app.entity.SiteMail;
import com.app.entity.SiteMail.Location;
import com.app.service.AdminService;
import com.app.service.ContactService;
import com.app.service.SiteMailService;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/site_mail")
public class SiteMailController extends BaseController {

	private String viewPath = "/admin/site_mail";

	@Resource
	private AdminService adminService;
	@Resource
	private ContactService contactService;

	@Resource
	private SiteMailService siteMailService;

	/**
	 * 邮件列表（调试使用）
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", siteMailService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 收件箱
	 */
	@RequestMapping(value = "/received_box", method = RequestMethod.GET)
	public String receivedBox(Pageable pageable, ModelMap model) {
		final Admin currentAdmin = adminService.getCurrent();
		pageable.getFilters().add(Filter.eq("toAdmin", currentAdmin, false));
		pageable.getFilters().add(Filter.eq("toLocation", Location.receivedBox, false));
		pageable.getOrders().add(Order.asc("isRead"));
		pageable.getOrders().add(Order.desc(BaseEntity.CREATE_DATE_PROPERTY_NAME));
		model.addAttribute("page", siteMailService.findPage(pageable));
		return viewPath + "/received_box";
	}

	/**
	 * 草稿箱
	 */
	@RequestMapping(value = "/draft_box", method = RequestMethod.GET)
	public String draftBox(Pageable pageable, ModelMap model) {
		final Admin currentAdmin = adminService.getCurrent();
		pageable.getFilters().add(Filter.eq("authorAdmin", currentAdmin, false));
		pageable.getFilters().add(Filter.eq("authorLocation", Location.draftBox, false));
		pageable.getOrders().add(Order.desc(BaseEntity.CREATE_DATE_PROPERTY_NAME));
		model.addAttribute("page", siteMailService.findPage(pageable));
		return viewPath + "/draft_box";
	}

	/**
	 * 发件箱
	 */
	@RequestMapping(value = "/sent_box", method = RequestMethod.GET)
	public String sentBox(Pageable pageable, ModelMap model) {
		final Admin currentAdmin = adminService.getCurrent();
		pageable.getFilters().add(Filter.eq("authorAdmin", currentAdmin, false));
		pageable.getFilters().add(Filter.eq("authorLocation", Location.sentBox, false));
		model.addAttribute("page", siteMailService.findPage(pageable));
		return viewPath + "/sent_box";
	}

	/**
	 * 回收箱
	 */
	@RequestMapping(value = "/recycled_box", method = RequestMethod.GET)
	public String recycledBox(Pageable pageable, ModelMap model) {
		final Admin currentAdmin = adminService.getCurrent();

		// 下面俩个情况的条件注释掉，通过自定义findPage实现（Filter目前不支持or）。
		// 情况1
		// pageable.getFilters().add(Filter.eq("authorAdmin", currentAdmin, false));
		// pageable.getFilters().add(Filter.eq("authorLocation", Location.recycledBox, false));
		// or
		// 情况2
		// pageable.getFilters().add(Filter.eq("toAdmin", currentAdmin, false));
		// pageable.getFilters().add(Filter.eq("toLocation", Location.recycledBox, false));

		model.addAttribute("page", siteMailService.findPageOfRecycledBox(pageable, currentAdmin));

		return viewPath + "/recycled_box";
	}

	/**
	 * 永久删除（调试功能）
	 */
	@RequestMapping(value = "/removed_box", method = RequestMethod.GET)
	public String removedBox(Pageable pageable, ModelMap model) {
		final Admin currentAdmin = adminService.getCurrent();
		// 自定义findPage实现
		model.addAttribute("page", siteMailService.findPageOfRemovedBox(pageable, currentAdmin));
		return viewPath + "/removed_box";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.put("AuthorAdmins", adminService.findAll());
		model.put("ToAdmins", adminService.findAll());
		model.put("ToContacts", contactService.findAll());
		model.put("Locations", SiteMail.Location.values());
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SiteMail siteMail, Long authorAdminId, Long toAdminId, Long toContactId, RedirectAttributes redirectAttributes) {
		if (!isValid(siteMail)) {
			return ERROR_VIEW;
		}
		if (authorAdminId != null) {
			Admin authorAdmin = adminService.find(authorAdminId);
			siteMail.setAuthorAdmin(authorAdmin);
		}
		if (toAdminId != null) {
			Admin toAdmin = adminService.find(toAdminId);
			siteMail.setToAdmin(toAdmin);
		}
		if (toContactId != null) {
			Contact toContact = contactService.find(toContactId);
			siteMail.setToContact(toContact);
		}
		if (siteMail.getTitle() == null) {
			addFlashMessage(redirectAttributes, Message.error("标题是必填项哦！"));
			return "redirect:list.jhtml";
		}

		siteMailService.save(siteMail);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		final Admin currentAdmin = adminService.getCurrent();
		List<SiteMail> siteMails = siteMailService.findList(ids);
		for (SiteMail siteMail : siteMails) {
			if (currentAdmin.getId().equals(siteMail.getAuthorAdmin().getId())) {
				// currentAdmin为发送人
				if (Location.removed.equals(siteMail.getAuthorLocation())) {
					// 从垃圾箱删除->彻底删除(通过设置null实现，记录保留)
					siteMail.setAuthorLocation(null);
					siteMailService.update(siteMail);
				} else if (Location.recycledBox.equals(siteMail.getAuthorLocation())) {
					// 从回收箱删除->垃圾箱
					siteMail.setAuthorLocation(Location.removed);
					siteMailService.update(siteMail);
				} else {
					siteMail.setAuthorLocation(Location.recycledBox);
					siteMailService.update(siteMail);
				}
			} else if (currentAdmin.getId().equals(siteMail.getToAdmin().getId())) {
				// currentAdmin为收件人
				if (Location.removed.equals(siteMail.getToLocation())) {
					// 从垃圾箱删除->彻底删除(通过设置null实现，记录保留)
					siteMail.setToLocation(null);
					siteMailService.update(siteMail);
				} else if (Location.recycledBox.equals(siteMail.getToLocation())) {
					// 从回收箱删除->垃圾箱
					siteMail.setToLocation(Location.removed);
					siteMailService.update(siteMail);
				} else {
					siteMail.setToLocation(Location.recycledBox);
					siteMailService.update(siteMail);
				}
			} else {
				// 异常
				if (siteMails.size() == 1) {
					// 非批量删除
					return ERROR_MESSAGE;
				} else {
					// 批量删除忽略错误，继续下一条数据。
					continue;
				}
			}
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		SiteMail siteMail = siteMailService.find(id);
		model.put("AuthorAdmins", adminService.findAll());
		model.put("ToAdmins", adminService.findAll());
		model.put("ToContacts", contactService.findAll());
		model.put("Locations", SiteMail.Location.values());
		model.addAttribute("siteMail", siteMail);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SiteMail siteMail, Long authorAdminId, Long toAdminId, Long toContactId, RedirectAttributes redirectAttributes) {
		if (!isValid(siteMail)) {
			return ERROR_VIEW;
		}
		if (authorAdminId != null) {
			Admin authorAdmin = adminService.find(authorAdminId);
			siteMail.setAuthorAdmin(authorAdmin);
		}
		if (toAdminId != null) {
			Admin toAdmin = adminService.find(toAdminId);
			siteMail.setToAdmin(toAdmin);
		}
		if (toContactId != null) {
			Contact toContact = contactService.find(toContactId);
			siteMail.setToContact(toContact);
		}
		if (siteMail.getTitle() == null) {
			addFlashMessage(redirectAttributes, Message.error("标题是必填项哦！"));
			return "redirect:list.jhtml";
		}
		SiteMail persistant = siteMailService.find(siteMail.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(siteMail, persistant, new ArrayList<String>() {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
				}
			}.toArray(new String[] {}));
			siteMailService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	/**
	 * 查看邮件
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		SiteMail siteMail = siteMailService.find(id);

		// 如果是收件人查看，设置为已读。
		final Admin currentAdmin = adminService.getCurrent();
		// 注意toAdmin为null的情况不考虑是否已读（因为是email发送，是否读取未知，已经不属于站内信逻辑）。
		if (siteMail.getToAdmin() != null && currentAdmin.getId().equals(siteMail.getToAdmin().getId())) {
			// 设置为已读
			siteMail.setIsRead(true);
			siteMailService.update(siteMail);
		}

		model.addAttribute("siteMail", siteMail);
		return viewPath + "/view";
	}

	/***
	 * 根据-这些属性搜索Admin实体
	 */
	@RequestMapping(value = "/searchToAdmin", method = RequestMethod.GET)
	public @ResponseBody List<Admin> searchToAdmin(String q) {
		return adminService.search(q, new String[] { "username", "name" }, 20);
	}

	/***
	 * 根据-这些属性搜索Contact实体
	 */
	@RequestMapping(value = "/searchToContact", method = RequestMethod.GET)
	public @ResponseBody List<Contact> searchToContact(String q) {
		return contactService.search(q, new String[] {}, 20);
	}

	/**
	 * 发送邮件
	 */
	@RequestMapping(value = "/to_mail_send", method = RequestMethod.GET)
	public String sendMail(Long id, Long draftId, ModelMap model) {
		// 草稿箱发送判断
		SiteMail draftSiteMail = siteMailService.find(draftId);
		model.addAttribute("draftSiteMail", draftSiteMail);
		// 如果不是草稿箱发送，才考虑id（管理员列表发送）
		if (draftSiteMail == null) {
			Admin admin = adminService.find(id);
			model.addAttribute("admin", admin);
		}
		return viewPath + "/mail_send";
	}

	/**
	 * 发送邮件
	 */
	@RequestMapping(value = "/mail_send", method = RequestMethod.POST)
	public String sendMail(SiteMail siteMail, Long draftId, Long toAdminId, RedirectAttributes redirectAttributes, ModelMap model) {
		Admin toAdmin = adminService.find(toAdminId);
		if (toAdmin == null) {
			addFlashMessage(redirectAttributes, Message.error("收件人是必填项哦！"));
			return "redirect:sent_box.jhtml";
		}

		siteMail.setToAdmin(toAdmin);
		siteMail.setAuthorAdmin(adminService.getCurrent());

		if (siteMail.getTemplatePath() == null) {
			siteMail.setTemplatePath(viewPath + "/contents/" + siteMail.getNameOfTimeStamp());
		}

		// 投入发件人发件箱
		siteMail.setAuthorLocation(Location.sentBox);
		// 投入收件人收件箱
		siteMail.setToLocation(Location.receivedBox);

		// 如果draftId==null则为直接发送、否则为草稿发送。
		SiteMail persistSiteMail = siteMailService.find(draftId);
		if (persistSiteMail == null) {
			siteMailService.save(siteMail);
		} else {
			// 只需要更新可能被修改的{ 收件人、标题、内容 }
			persistSiteMail.setToAdmin(siteMail.getToAdmin());
			persistSiteMail.setTitle(siteMail.getTitle());
			persistSiteMail.setContent(siteMail.getContent());
			// 其次从草稿-->正常发送
			persistSiteMail.setAuthorLocation(Location.sentBox);
			persistSiteMail.setToLocation(Location.receivedBox);
			siteMailService.update(persistSiteMail);
		}

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		return "redirect:sent_box.jhtml";
	}

	/**
	 * 保存草稿
	 */
	@RequestMapping(value = "/mail_send", params = "draft", method = RequestMethod.POST)
	public String saveDraftMail(SiteMail siteMail, Long draftId, Long toAdminId, RedirectAttributes redirectAttributes, ModelMap model) {
		Admin toAdmin = adminService.find(toAdminId);
		if (toAdmin == null) {
			addFlashMessage(redirectAttributes, Message.error("收件人是必填项哦！"));
			return "redirect:draft_box.jhtml";
		}

		siteMail.setToAdmin(toAdmin);
		siteMail.setAuthorAdmin(adminService.getCurrent());

		if (siteMail.getTemplatePath() == null) {
			siteMail.setTemplatePath(viewPath + "/contents/" + siteMail.getNameOfTimeStamp());
		}

		// 投入发件人草稿箱
		siteMail.setAuthorLocation(Location.draftBox);
		// 收件人不可见
		siteMail.setToLocation(null);

		// 如果draftId==null则为保存草稿、否则为更新草稿。
		SiteMail persistSiteMail = siteMailService.find(draftId);
		if (persistSiteMail == null) {
			siteMailService.save(siteMail);
		} else {
			// 只需要更新可能被修改的{ 收件人、标题、内容 }
			persistSiteMail.setToAdmin(siteMail.getToAdmin());
			persistSiteMail.setTitle(siteMail.getTitle());
			persistSiteMail.setContent(siteMail.getContent());
			siteMailService.update(persistSiteMail);
		}

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		return "redirect:draft_box.jhtml";
	}

}
