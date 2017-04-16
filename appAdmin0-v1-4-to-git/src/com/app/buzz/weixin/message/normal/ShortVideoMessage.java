/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.normal;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/**
 * 小视频消息
 * 
 * @param ToUserName
 *            开发者微信号
 * @param FromUserName
 *            发送方帐号（一个OpenID）
 * @param CreateTime
 *            消息创建时间 （整型）
 * @param MsgType
 *            小视频为shortvideo
 * @param MediaId
 *            视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
 * @param ThumbMediaId
 *            视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
 * @param MsgId
 *            消息id，64位整型
 *            
 * @author APP TEAM
 * @version 1.0
 */
public class ShortVideoMessage extends Message {

	// protected String MediaId;
	// protected String ThumbMediaId;
	// protected String MsgId;

	public ShortVideoMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getMediaId() {
		return super.getMediaId();
	}

	public void setMediaId(String mediaId) {
		super.setMediaId(mediaId);
	}

	public String getThumbMediaId() {
		return super.getThumbMediaId();
	}

	public void setThumbMediaId(String thumbMediaId) {
		super.setThumbMediaId(thumbMediaId);
	}

	public String getMsgId() {
		return super.getMsgId();
	}

	public void setMsgId(String msgId) {
		super.setMsgId(msgId);
	}

}
