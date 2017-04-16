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
 * 回复图片消息
 * 
 * @param ToUserName
 *            是 接收方帐号（收到的OpenID）
 * @param FromUserName
 *            是 开发者微信号
 * @param CreateTime
 *            是 消息创建时间 （整型）
 * @param MsgType
 *            是 image
 * @param MediaId
 *            是 通过上传多媒体文件，得到的id。
 * 
 * @author APP TEAM
 * @version 1.0
 * 
 */
public class ImageReply extends Reply {

	private ImageBean Image;

	public ImageReply(Message message, String mediaId) {
		super(message, MessageType.image.toString());
		Image = new ImageBean();
		this.Image.MediaId = mediaId;
	}

	public ImageReply(String mediaId) {
		this.setMsgType(MessageType.image.toString());
		Image = new ImageBean();
		this.Image.MediaId = mediaId;
	}

	public String getMediaId() {
		return this.Image.MediaId;
	}

	public void setMediaId(String mediaId) {
		this.Image.MediaId = mediaId;
	}

	@Override
	public String toString() {
		return XmlUtils.object2Xml(this, "xml");
	}

}
