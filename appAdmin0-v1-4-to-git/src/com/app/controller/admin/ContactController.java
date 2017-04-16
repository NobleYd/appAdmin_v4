/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.controller.admin;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Filter;
import com.app.Message;
import com.app.Pageable;
import com.app.Setting;
import com.app.entity.BaseEntity;
import com.app.entity.Contact;
import com.app.entity.SiteMail;
import com.app.service.AdminService;
import com.app.service.ContactService;
import com.app.service.MailService;
import com.app.service.SiteMailService;
import com.app.util.SettingUtils;
import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.smtp.SMTPSenderFailedException;

@Controller
@RequestMapping("/admin/contact")
public class ContactController extends BaseController {

	private String viewPath = "/admin/contact";

	@Resource
	private ContactService contactService;

	@Resource
	private AdminService adminService;

	@Resource
	private SiteMailService siteMailService;

	@Resource
	private MailService mailService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", contactService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.put("Sexs", Contact.Sex.values());
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Contact contact, RedirectAttributes redirectAttributes) {
		if (!isValid(contact)) {
			return ERROR_VIEW;
		}
		if (contact.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("姓名/昵称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (contact.getMobile() == null) {
			addFlashMessage(redirectAttributes, Message.error("联系电话是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (contact.getEmail() == null) {
			addFlashMessage(redirectAttributes, Message.error("电子邮箱是必填项哦！"));
			return "redirect:list.jhtml";
		}

		contactService.save(contact);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		contactService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		Contact contact = contactService.find(id);
		model.put("Sexs", Contact.Sex.values());
		model.addAttribute("contact", contact);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Contact contact, RedirectAttributes redirectAttributes) {
		if (!isValid(contact)) {
			return ERROR_VIEW;
		}
		if (contact.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("姓名/昵称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (contact.getMobile() == null) {
			addFlashMessage(redirectAttributes, Message.error("联系电话是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (contact.getEmail() == null) {
			addFlashMessage(redirectAttributes, Message.error("电子邮箱是必填项哦！"));
			return "redirect:list.jhtml";
		}
		Contact persistant = contactService.find(contact.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(contact, persistant, new ArrayList<String>() {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
				}
			}.toArray(new String[] {}));
			contactService.update(persistant);
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
		return contactService.find(Filter.eq("name", name, false)) == null;
	}

	/**
	 * 发送邮件
	 */
	@RequestMapping(value = "/to_mail_send", method = RequestMethod.GET)
	public String sendMail(Long id, ModelMap model) {
		Contact contact = contactService.find(id);
		model.addAttribute("contact", contact);
		return viewPath + "/mail_send";
	}

	/**
	 * 发送邮件
	 */
	@RequestMapping(value = "/mail_send", method = RequestMethod.POST)
	public String sendMail(SiteMail siteMail, Long toContactId, RedirectAttributes redirectAttributes, ModelMap model) {
		Contact toContact = contactService.find(toContactId);
		if (toContact == null) {
			addFlashMessage(redirectAttributes, Message.error("收件人是必填项哦！"));
			return "redirect:list.jhtml";
		}

		siteMail.setToContact(toContact);
		siteMail.setAuthorAdmin(adminService.getCurrent());

		if (siteMail.getTemplatePath() == null) {
			siteMail.setTemplatePath(viewPath + "/contents/" + siteMail.getNameOfTimeStamp());
		}

		siteMailService.save(siteMail);

		Setting setting = SettingUtils.get();
		try {
			mailService.send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toContact.getEmail(), siteMail.getTitle(), siteMail.getContent(), false);
		} catch (MailSendException e) {
			Exception[] messageExceptions = e.getMessageExceptions();
			if (messageExceptions != null) {
				for (Exception exception : messageExceptions) {
					if (exception instanceof SMTPSendFailedException) {
						SMTPSendFailedException smtpSendFailedException = (SMTPSendFailedException) exception;
						Exception nextException = smtpSendFailedException.getNextException();
						if (nextException instanceof SMTPSenderFailedException) {
							addFlashMessage(redirectAttributes, Message.error("admin.setting.mailTestSenderFailed"));
							return "redirect:to_mail_send.jhtml?id=" + toContactId;
						}
					} else if (exception instanceof MessagingException) {
						MessagingException messagingException = (MessagingException) exception;
						Exception nextException = messagingException.getNextException();
						if (nextException instanceof UnknownHostException) {
							addFlashMessage(redirectAttributes, Message.error("admin.setting.mailTestUnknownHost"));
							return "redirect:to_mail_send.jhtml?id=" + toContactId;
						} else if (nextException instanceof ConnectException) {
							addFlashMessage(redirectAttributes, Message.error("admin.setting.mailTestConnect"));
							return "redirect:to_mail_send.jhtml?id=" + toContactId;
						}
					}
				}
			}
			addFlashMessage(redirectAttributes, Message.error("admin.setting.mailTestError"));
			return "redirect:to_mail_send.jhtml?id=" + toContactId;
		} catch (MailAuthenticationException e) {
			addFlashMessage(redirectAttributes, Message.error("admin.setting.mailTestAuthentication"));
			return "redirect:to_mail_send.jhtml?id=" + toContactId;
		} catch (Exception e) {
			addFlashMessage(redirectAttributes, Message.error("admin.setting.mailTestError"));
			return "redirect:to_mail_send.jhtml?id=" + toContactId;
		}
		addFlashMessage(redirectAttributes, Message.success("发送成功"));
		return "redirect:list.jhtml";
	}

}
