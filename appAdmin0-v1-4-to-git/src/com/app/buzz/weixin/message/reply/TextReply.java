/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.reply;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageType;
import com.app.buzz.weixin.message.common.Reply;
import com.app.buzz.weixin.util.XmlUtils;

/**
 * 回复文本消息
 * 
 * @param ToUserName
 *            是 接收方帐号（收到的OpenID）
 * @param FromUserName
 *            是 开发者微信号
 * @param CreateTime
 *            是 消息创建时间 （整型）
 * @param MsgType
 *            是 text
 * @param Content
 *            是 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
 * @author APP TEAM
 * @version 1.0
 */
public class TextReply extends Reply {

	private String Content;

	public TextReply(Message message, String content) {
		super(message, MessageType.text.toString());
		this.Content = content;
	}

	public TextReply(String content) {
		setMsgType(MessageType.text.toString());
		this.Content = content;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

	@Override
	public String toString() {
		return XmlUtils.object2Xml(this, "xml");
	}

}
