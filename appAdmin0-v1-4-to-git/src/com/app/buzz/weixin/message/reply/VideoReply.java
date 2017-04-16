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
 * 回复视频消息
 * 
 * @param ToUserName 是 接收方帐号（收到的OpenID）
 * @param FromUserName 是 开发者微信号
 * @param CreateTime 是 消息创建时间 （整型）
 * @param MsgType 是 video
 * @param MediaId 是 通过上传多媒体文件，得到的id
 * @param Title 否 视频消息的标题
 * @param Description 否 视频消息的描述
 * @author APP TEAM
 * @version 1.0
 */
public class VideoReply extends Reply {

	private VideoBean Video;

	public VideoReply(Message message, String mediaId, String title, String description) {
		super(message, MessageType.video.toString());
		Video = new VideoBean();
		this.Video.MediaId = mediaId;
		this.Video.Title = title;
		this.Video.Description = description;
	}
	
	public VideoReply(String mediaId, String title, String description) {
		setMsgType(MessageType.video.toString());
		Video = new VideoBean();
		this.Video.MediaId = mediaId;
		this.Video.Title = title;
		this.Video.Description = description;
	}

	public String getMediaId() {
		return Video.MediaId;
	}

	public void setMediaId(String mediaId) {
		Video.MediaId = mediaId;
	}

	public String getTitle() {
		return Video.Title;
	}

	public void setTitle(String title) {
		Video.Title = title;
	}

	public String getDescription() {
		return Video.Description;
	}

	public void setDescription(String description) {
		Video.Description = description;
	}

	@Override
	public String toString() {
		return XmlUtils.object2Xml(this, "xml");
	}

}
