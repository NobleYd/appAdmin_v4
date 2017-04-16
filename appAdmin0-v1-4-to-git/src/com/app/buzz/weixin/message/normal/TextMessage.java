/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.normal;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/***
 * 文本消息
 * 
 * @author 一旦
 * @param ToUserName
 *            开发者微信号
 * @param FromUserName
 *            发送方帐号（一个OpenID）
 * @param CreateTime
 *            消息创建时间 （整型）
 * @param MsgType
 *            text
 * @param Content
 *            文本消息内容
 * @param MsgId
 *            消息id，64位整型
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class TextMessage extends Message {

	// protected String MsgId;
	// protected String Content;

	public TextMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getMsgId() {
		return super.getMsgId();
	}

	public void setMsgId(String msgId) {
		super.setMsgId(msgId);
	}

	public String getContent() {
		return super.getContent();
	}

	public void setContent(String content) {
		super.setContent(content);
	}

}
