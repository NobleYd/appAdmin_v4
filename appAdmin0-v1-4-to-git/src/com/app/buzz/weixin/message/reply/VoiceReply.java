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
 * 回复语音消息
 * 
 * @param ToUserName
 *            是 接收方帐号（收到的OpenID）
 * @param FromUserName
 *            是 开发者微信号
 * @param CreateTime
 *            是 消息创建时间戳 （整型）
 * @param MsgType
 *            是 语音，voice
 * @param MediaId
 *            是 通过上传多媒体文件，得到的id
 * @author APP TEAM
 * @version 1.0
 */
public class VoiceReply extends Reply {

	private VoiceBean Voice;

	public VoiceReply(Message message, String mediaId) {
		super(message, MessageType.voice.toString());
		Voice = new VoiceBean();
		this.Voice.MediaId = mediaId;
	}

	public VoiceReply(String mediaId) {
		setMsgType(MessageType.voice.toString());
		Voice = new VoiceBean();
		this.Voice.MediaId = mediaId;
	}

	public String getMediaId() {
		return Voice.MediaId;
	}

	public void setMediaId(String mediaId) {
		Voice.MediaId = mediaId;
	}

	@Override
	public String toString() {
		return XmlUtils.object2Xml(this, "xml");
	}

}
