/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.controller.admin;

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
import com.app.buzz.weixin.constants.WeixinAutoReplyMessages;
import com.app.buzz.weixin.entity.AutoReplyMessage;
import com.app.buzz.weixin.message.common.MessageType;
import com.app.buzz.weixin.message.reply.NewsItem;
import com.app.buzz.weixin.service.AutoReplyMessageService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/auto_reply_message")
public class AutoReplyMessageController extends BaseController {

	private String viewPath = "/weixin/auto_reply_message";

	@Resource
	private AutoReplyMessageService autoReplyMessageService;

	@Resource
	private WeixinAutoReplyMessages weixinAutoReplyMessages;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", autoReplyMessageService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(MessageType messageType, ModelMap model) {
		model.put("messageType", messageType);
		return viewPath + "/add_" + messageType.toString();
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(AutoReplyMessage autoReplyMessage, RedirectAttributes redirectAttributes) {
		if (!isValid(autoReplyMessage)) {
			return ERROR_VIEW;
		}
		if (autoReplyMessage.getMessageType() == null) {
			addFlashMessage(redirectAttributes, Message.error("消息类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (autoReplyMessage.getKeyword() == null) {
			addFlashMessage(redirectAttributes, Message.error("关键词是必填项哦！"));
			return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
		}

		// 根据消息类型验证必填性
		if (MessageType.text.equals(autoReplyMessage.getMessageType())) {
			// 文本消息
			if (autoReplyMessage.getContent() == null) {
				addFlashMessage(redirectAttributes, Message.error("文本消息内容是必填项哦！"));
				return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
			}
		} else if (MessageType.image.equals(autoReplyMessage.getMessageType())) {
			// 图片消息
			if (autoReplyMessage.getMediaId() == null) {
				addFlashMessage(redirectAttributes, Message.error("图片媒体文件ID是必填项哦！"));
				return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
			}
		} else if (MessageType.voice.equals(autoReplyMessage.getMessageType())) {
			// 语音消息
			if (autoReplyMessage.getMediaId() == null) {
				addFlashMessage(redirectAttributes, Message.error("语音媒体文件ID是必填项哦！"));
				return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
			}
		} else if (MessageType.video.equals(autoReplyMessage.getMessageType())) {
			// 视频消息
			if (autoReplyMessage.getMediaId() == null) {
				addFlashMessage(redirectAttributes, Message.error("视频媒体文件ID是必填项哦！"));
				return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
			}
		} else if (MessageType.music.equals(autoReplyMessage.getMessageType())) {
			// 音乐消息没有必填验证
		} else if (MessageType.news.equals(autoReplyMessage.getMessageType())) {
			// 图文消息
			// 循环处理删除空的图文消息
			List<NewsItem> notNullArticles = new ArrayList<NewsItem>();
			for (NewsItem article : autoReplyMessage.getArticles()) {
				if (article.getTitle() != null && article.getUrl() != null) {
					notNullArticles.add(article);
				}
			}
			// 重新设置articles
			autoReplyMessage.setArticles(notNullArticles);
			// 数量
			autoReplyMessage.setArticleCount(new Long(autoReplyMessage.getArticles().size()));

			if (autoReplyMessage.getArticleCount() <= 0) {
				addFlashMessage(redirectAttributes, Message.error("请至少添加一项图文消息哦！"));
				return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
			}
			if (autoReplyMessage.getArticleCount() > 10) {
				addFlashMessage(redirectAttributes, Message.error("最多可以添加十项图文消息哦！"));
				return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
			}
		}

		autoReplyMessageService.save(autoReplyMessage);

		// 更新 weixinAutoReplyMessages 。
		weixinAutoReplyMessages.put(autoReplyMessage.getKeyword(), autoReplyMessage.getReply());

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		autoReplyMessageService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		AutoReplyMessage autoReplyMessage = autoReplyMessageService.find(id);
		model.addAttribute("autoReplyMessage", autoReplyMessage);
		return viewPath + "/edit_" + autoReplyMessage.getMessageType().toString();
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(AutoReplyMessage autoReplyMessage, RedirectAttributes redirectAttributes) {
		if (!isValid(autoReplyMessage)) {
			return ERROR_VIEW;
		}
		if (autoReplyMessage.getKeyword() == null) {
			addFlashMessage(redirectAttributes, Message.error("关键词是必填项哦！"));
			return "redirect:list.jhtml";
		}
		AutoReplyMessage persistant = autoReplyMessageService.find(autoReplyMessage.getId());
		if (persistant != null) {
			if (MessageType.news.equals(persistant.getMessageType())) {
				// 图文消息
				// 循环处理删除空的图文消息
				List<NewsItem> notNullArticles = new ArrayList<NewsItem>();
				for (NewsItem article : autoReplyMessage.getArticles()) {
					if (article.getTitle() != null && article.getUrl() != null) {
						notNullArticles.add(article);
					}
				}
				// 重新设置articles
				autoReplyMessage.setArticles(notNullArticles);
				// 数量
				autoReplyMessage.setArticleCount(new Long(autoReplyMessage.getArticles().size()));

				if (autoReplyMessage.getArticleCount() <= 0) {
					addFlashMessage(redirectAttributes, Message.error("请至少添加一项图文消息哦！"));
					return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
				}
				if (autoReplyMessage.getArticleCount() > 10) {
					addFlashMessage(redirectAttributes, Message.error("最多可以添加十项图文消息哦！"));
					return "redirect:add.jhtml?messageType=" + autoReplyMessage.getMessageType();
				}
			}

			BeanUtils.copyProperties(autoReplyMessage, persistant, new ArrayList<String>() {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
					add("messageType");
				}
			}.toArray(new String[] {}));
			autoReplyMessageService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

			// 更新 weixinAutoReplyMessages 。
			weixinAutoReplyMessages.put(persistant.getKeyword(), persistant.getReply());

		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	@RequestMapping("/check_keyword_unique")
	@ResponseBody
	public Boolean checkKeywordUnique(final String previousKeyword, final String keyword) {
		// string类型请使用注释部分的if判断替换下面对应两个if判断
		// if (StringUtils.isEmpty(keyword)) {
		// return false;
		// }
		// if (StringUtils.equalsIgnoreCase(previousKeyword, keyword)) {
		// return true;
		// }
		if (keyword == null) {
			return false;
		}
		if (keyword.equals(previousKeyword)) {
			return true;
		}
		return autoReplyMessageService.find(Filter.eq("keyword", keyword, false)) == null;
	}

}
