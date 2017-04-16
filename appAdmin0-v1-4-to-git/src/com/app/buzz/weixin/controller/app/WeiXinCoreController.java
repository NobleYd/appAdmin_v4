/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.controller.app;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.constants.WeixinAutoReplyMessages;
import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.Reply;
import com.app.buzz.weixin.message.event.ClickEventMessage;
import com.app.buzz.weixin.message.event.ScanEventMessage;
import com.app.buzz.weixin.message.event.SubscribeEventMessage;
import com.app.buzz.weixin.message.event.SubscribeScanEventMessage;
import com.app.buzz.weixin.message.event.UnSubscribeEventMessage;
import com.app.buzz.weixin.message.event.ViewEventMessage;
import com.app.buzz.weixin.message.normal.ImageMessage;
import com.app.buzz.weixin.message.normal.LinkMessage;
import com.app.buzz.weixin.message.normal.LocationMessage;
import com.app.buzz.weixin.message.normal.ShortVideoMessage;
import com.app.buzz.weixin.message.normal.TextMessage;
import com.app.buzz.weixin.message.normal.VideoMessage;
import com.app.buzz.weixin.message.normal.VoiceMessage;
import com.app.buzz.weixin.message.reply.ImageReply;
import com.app.buzz.weixin.message.reply.TextReply;
import com.app.buzz.weixin.message.reply.VideoReply;
import com.app.buzz.weixin.message.reply.VoiceReply;
import com.app.buzz.weixin.util.WeiXinUtils;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinCoreController {

	/** 本server网址 */
	private static final String url = "http://115.28.229.77/";
	/** 素材和资源 */
	private static final String videoMediaId = "yV2HX5g3mnmYsxPjgKfcllf8yAZpYHM_dUbRy0scifUZmpvDXs_NJEHuUMW6xly1";
	private static final String musicURL = url + "testRes/test.mp3";
	private static final String HQmusicURL = url + "testRes/test.mp3";

	private static Log log = LogFactory.getLog(WeiXinCoreController.class);

	@Resource
	private WeiXinConfig weiXinConfig;

	@Resource
	private WeixinAutoReplyMessages weixinAutoReplyMessages;

	/***
	 * 微信接入验证
	 * 
	 * @param signature
	 *            签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @param echostr
	 *            随机字符串
	 * @return 验证通过返回 echostr
	 */
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	@ResponseBody
	public String accept(String signature, String timestamp, String nonce, String echostr) {
		if (weiXinConfig == null) {
			log.info("weiXinConfig is null");
			return null;
		}
		if (weiXinConfig.getToken() == null) {
			log.info("weiXinConfig.getToken() is null");
			return null;
		}
		if (WeiXinUtils.checkSignature(weiXinConfig.getToken(), signature, timestamp, nonce)) {
			log.info("WeXinUtils.checkSignature(" + weiXinConfig.getToken() + ", " + signature + ", " + timestamp + ", " + nonce + ") return true");
			return echostr;
		}
		log.info("WeXinUtils.checkSignature(" + weiXinConfig.getToken() + ", " + signature + ", " + timestamp + ", " + nonce + ") return false");
		return null;
	}

	/**
	 * 微信消息交互处理
	 *
	 * @param signature
	 *            签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @param echostr
	 *            随机字符串
	 * @return 回应消息
	 */
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	@ResponseBody
	public String acceptContentMessage(String signature, String timestamp, String nonce, String echostr, @RequestBody String xmlContent, HttpServletResponse response) {

		response.setContentType("text/xml;charset=UTF-8");

		log.info("accept post, xmlContent: \n\r" + xmlContent);

		if (weiXinConfig == null) {
			log.info("weiXinConfig is null");
			return null;
		}
		if (weiXinConfig.getToken() == null) {
			log.info("weiXinConfig.getToken() is null");
			return null;
		}
		if (!WeiXinUtils.checkSignature(weiXinConfig.getToken(), signature, timestamp, nonce)) {
			log.info("WeXinUtils.checkSignature(" + weiXinConfig.getToken() + ", " + signature + ", " + timestamp + ", " + nonce + ") return false");
			return "error";
		}

		Message message = WeiXinUtils.parseMessage(xmlContent);
		if (message == null) {
			return "message is null";
		} else if (message.isTextMessage()) {
			TextMessage textMessage = (TextMessage) message;
			String content = textMessage.getContent().trim();
			Reply reply = weixinAutoReplyMessages.get(content);
			if (reply != null) {
				log.info(reply.setFromMessage(textMessage).toString());
				return reply.setFromMessage(textMessage).toString();
			}else{
				return new TextReply(textMessage, "谢谢教会我说：" + textMessage.getContent()).toString();
			}
//			if ("music".equalsIgnoreCase(content)) {
//				return new MusicReply(message, "这是一首歌的标题！", "这是一首歌的描述！", musicURL, HQmusicURL, null).toString();
//			} else if ("news".equalsIgnoreCase(content)) {
//				NewsReply newsReply = new NewsReply(message);
//				newsReply.addNewsItem("titleA", "descriptionA", url + "testRes/1.jpg", "http://www.baidu.com");
//				newsReply.addNewsItem("titleB", "descriptionB", url + "testRes/2.jpg", "http://www.baidu.com");
//				newsReply.addNewsItem("titleC", "descriptionC", url + "testRes/1.jpg", "http://www.baidu.com");
//				newsReply.addNewsItem("titleD", "descriptionD", url + "testRes/2.jpg", "http://www.baidu.com");
//				return newsReply.toString();
//			} else {
//				return new TextReply(textMessage, "" + textMessage.getContent()).toString();
//			}
		} else if (message.isImageMessage()) {
			ImageMessage imageMessage = (ImageMessage) message;
			return new ImageReply(imageMessage, imageMessage.getMediaId()).toString();
		} else if (message.isVoiceMessage()) {
			VoiceMessage voiceMessage = (VoiceMessage) message;
			return new VoiceReply(voiceMessage, voiceMessage.getMediaId()).toString();
		} else if (message.isVideoMessage()) {
			VideoMessage videoMessage = (VideoMessage) message;
			return new VideoReply(videoMessage, videoMediaId, "replyTitle", "replyDescription").toString();
		} else if (message.isShortVideoMessage()) {
			ShortVideoMessage shortVideoMessage = (ShortVideoMessage) message;
			return new VideoReply(shortVideoMessage, videoMediaId, "i am yd , this is the shortVideo's reply", "hello world...").toString();
		} else if (message.isLocationMessage()) {
			LocationMessage locationMessage = (LocationMessage) message;
			return new TextReply(locationMessage, "location X:" + locationMessage.getLocation_X() + " Y:" + locationMessage.getLocation_Y()).toString();
		} else if (message.isLinkMessage()) {
			LinkMessage linkMessage = (LinkMessage) message;
			return new TextReply(linkMessage, "link url:" + linkMessage.getUrl()).toString();
		}
		/** event begin */
		else if (message.isSubscribeEvent()) {
			SubscribeEventMessage subscribeMessage = (SubscribeEventMessage) message;
			return new TextReply(message, "this is an subscribeMessage").toString();
		} else if (message.isUnSubscribeEvent()) {
			UnSubscribeEventMessage unSubscribeMessage = (UnSubscribeEventMessage) message;
			return new TextReply(message, "unSubscribeMessage").toString();
		} else if (message.isSubscribeScanEvent()) {
			SubscribeScanEventMessage subscribeScanMessage = (SubscribeScanEventMessage) message;
			return new TextReply(message, "subscribeScanMessage key:" + subscribeScanMessage.getEventKey()).toString();
		} else if (message.isScanEvent()) {
			ScanEventMessage scanMessage = (ScanEventMessage) message;
			return new TextReply(message, "scanMessage").toString();
		} else if (message.isClickEvent()) {
			ClickEventMessage clickMessage = (ClickEventMessage) message;
			return new TextReply(message, "clickMessage key:" + clickMessage.getEventKey()).toString();
		} else if (message.isViewEvent()) {
			ViewEventMessage viewMessage = (ViewEventMessage) message;
			return new TextReply(message, "viewMessage key:" + viewMessage.getEventKey()).toString();
		} else {
			return new TextReply(message, "unknown message").toString();
		}

	}
}