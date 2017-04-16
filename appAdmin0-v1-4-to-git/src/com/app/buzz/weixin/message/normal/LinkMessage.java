/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */package com.app.buzz.weixin.message.normal;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/**
 * 链接消息
 * 
 * @param ToUserName
 *            接收方微信号
 * @param FromUserName
 *            发送方微信号，若为普通用户，则是一个OpenID
 * @param CreateTime
 *            消息创建时间
 * @param MsgType
 *            消息类型，link
 * @param Title
 *            消息标题
 * @param Description
 *            消息描述
 * @param Url
 *            消息链接
 * @param MsgId
 *            消息id，64位整型
 * 
 * @author APP TEAM
 * @version 1.0
 * 
 */
public class LinkMessage extends Message {

	// protected String Title;
	// protected String Description;
	// protected String Url;
	// protected String MsgId;

	public LinkMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getTitle() {
		return super.getTitle();
	}

	public void setTitle(String title) {
		super.setTitle(title);
	}

	public String getDescription() {
		return super.getDescription();
	}

	public void setDescription(String description) {
		super.setDescription(description);
	}

	public String getUrl() {
		return super.getUrl();
	}

	public void setUrl(String url) {
		super.setUrl(url);
	}

	public String getMsgId() {
		return super.getMsgId();
	}

	public void setMsgId(String msgId) {
		super.setMsgId(msgId);
	}

}
