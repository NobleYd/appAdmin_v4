/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.normal;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/***
 * 语音消息
 * 
 * @param ToUserName
 *            开发者微信号
 * @param FromUserName
 *            发送方帐号（一个OpenID）
 * @param CreateTime
 *            消息创建时间 （整型）
 * @param MsgType
 *            语音为voice
 * @param MediaId
 *            语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
 * @param Format
 *            语音格式，如amr，speex等
 * @param MsgID
 *            消息id，64位整型
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class VoiceMessage extends Message {

	// protected String MediaId;
	// protected String Format;
	// protected String MsgId;

	public VoiceMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getMediaId() {
		return super.getMediaId();
	}

	public void setMediaId(String mediaId) {
		super.setMediaId(mediaId);
	}

	public String getFormat() {
		return super.getFormat();
	}

	public void setFormat(String format) {
		super.setFormat(format);
	}

	public String getMsgId() {
		return super.getMsgId();
	}

	public void setMsgId(String msgId) {
		super.setMsgId(msgId);
	}

}
