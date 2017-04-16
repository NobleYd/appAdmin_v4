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

/***
 * 回复音乐消息
 * 
 * @param ToUserName
 *            是 接收方帐号（收到的OpenID）
 * @param FromUserName
 *            是 开发者微信号
 * @param CreateTime
 *            是 消息创建时间 （整型）
 * @param MsgType
 *            是 music
 * @param Title
 *            否 音乐标题
 * @param Description
 *            否 音乐描述
 * @param MusicURL
 *            否 音乐链接
 * @param HQMusicUrl
 *            否 高质量音乐链接，WIFI环境优先使用该链接播放音乐
 * @param ThumbMediaId
 *            是 缩略图的媒体id，通过上传多媒体文件，得到的id
 * @author APP TEAM
 * @version 1.0
 * 
 */
public class MusicReply extends Reply {

	private MusicBean Music;

	public MusicReply(Message message, String title, String description, String musicUrl, String HQMusicUrl, String thumbMediaId) {
		super(message, MessageType.music.toString());
		this.Music = new MusicBean();
		this.Music.Title = title;
		this.Music.Description = description;
		this.Music.MusicUrl = musicUrl;
		this.Music.HQMusicUrl = HQMusicUrl;
		this.Music.ThumbMediaId = thumbMediaId;
	}

	public MusicReply(String title, String description, String musicUrl, String HQMusicUrl, String thumbMediaId) {
		setMsgType(MessageType.music.toString());
		this.Music = new MusicBean();
		this.Music.Title = title;
		this.Music.Description = description;
		this.Music.MusicUrl = musicUrl;
		this.Music.HQMusicUrl = HQMusicUrl;
		this.Music.ThumbMediaId = thumbMediaId;
	}

	public String getTitle() {
		return Music.Title;
	}

	public void setTitle(String title) {
		Music.Title = title;
	}

	public String getDescription() {
		return Music.Description;
	}

	public void setDescription(String description) {
		Music.Description = description;
	}

	public String getMusicUrl() {
		return Music.MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		Music.MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return Music.HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		Music.HQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return Music.ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		Music.ThumbMediaId = thumbMediaId;
	}

	@Override
	public String toString() {
		return XmlUtils.object2Xml(this, "xml");
	}

}
