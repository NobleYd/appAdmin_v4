/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */package com.app.buzz.weixin.message.normal;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/***
 * 图片消息
 * 
 * @param ToUserName
 *            开发者微信号
 * @param FromUserName
 *            发送方帐号（一个OpenID）
 * @param CreateTime
 *            消息创建时间 （整型）
 * @param MsgType
 *            image
 * @param PicUrl
 *            图片链接
 * @param MediaId
 *            图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
 * @param MsgId
 *            消息id，64位整型
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class ImageMessage extends Message {

	// protected String PicUrl;
	// protected String MediaId;
	// protected String MsgId;

	public ImageMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getPicUrl() {
		return super.getPicUrl();
	}

	public void setPicUrl(String picUrl) {
		super.setPicUrl(picUrl);
	}

	public String getMediaId() {
		return super.getMediaId();
	}

	public void setMediaId(String mediaId) {
		super.setMediaId(mediaId);
	}

	public String getMsgId() {
		return super.getMsgId();
	}

	public void setMsgId(String msgId) {
		super.setMsgId(msgId);
	}

}
