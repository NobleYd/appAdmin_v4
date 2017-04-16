/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.app.buzz.weixin.message.common.MessageType;
import com.app.buzz.weixin.message.common.Reply;
import com.app.buzz.weixin.message.reply.ImageReply;
import com.app.buzz.weixin.message.reply.MusicReply;
import com.app.buzz.weixin.message.reply.NewsItem;
import com.app.buzz.weixin.message.reply.NewsReply;
import com.app.buzz.weixin.message.reply.TextReply;
import com.app.buzz.weixin.message.reply.VideoReply;
import com.app.buzz.weixin.message.reply.VoiceReply;
import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 自动回复消息
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_auto_reply_message")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_auto_reply_message_seq")
public class AutoReplyMessage extends BaseEntity {

	private static final long serialVersionUID = 8447509479213096056L;

	/** 关键词 */
	private String keyword;

	/** 消息类型 */
	private MessageType messageType;

	/** 文本消息内容{文本消息} */
	private String content;

	/** 媒体文件ID{图片消息、语音消息、视频消息} */
	private String mediaId;

	/** 标题{视频消息、音乐消息} */
	private String title;

	/** 描述{视频消息、音乐消息} */
	private String description;

	/** 音乐链接{音乐消息} */
	private String musicUrl;

	/** 高质量音乐链接{音乐消息} */
	private String hqMusicUrl;

	/** 缩略图媒体ID{音乐消息} */
	private String thumbMediaId;

	/** 图文消息数{图文消息} */
	private Long articleCount;

	/** 图文消息项{图文消息} */
	private List<NewsItem> Articles;

	/** keyword get */
	@JsonProperty
	public String getKeyword() {
		return keyword;
	}

	/** keyword set */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/** messageType get */
	public MessageType getMessageType() {
		return messageType;
	}

	/** messageType set */
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	/** content get */
	public String getContent() {
		return content;
	}

	/** content set */
	public void setContent(String content) {
		this.content = content;
	}

	/** mediaId get */
	public String getMediaId() {
		return mediaId;
	}

	/** mediaId set */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/** title get */
	public String getTitle() {
		return title;
	}

	/** title set */
	public void setTitle(String title) {
		this.title = title;
	}

	/** description get */
	public String getDescription() {
		return description;
	}

	/** description set */
	public void setDescription(String description) {
		this.description = description;
	}

	/** musicUrl get */
	public String getMusicUrl() {
		return musicUrl;
	}

	/** musicUrl set */
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	/** hQMusicUrl get */
	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	/** hQMusicUrl set */
	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	/** thumbMediaId get */
	public String getThumbMediaId() {
		return thumbMediaId;
	}

	/** thumbMediaId set */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	/** articleCount get */
	public Long getArticleCount() {
		return articleCount;
	}

	/** articleCount set */
	public void setArticleCount(Long articleCount) {
		this.articleCount = articleCount;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "app_auto_reply_msg_news_items")
	public List<NewsItem> getArticles() {
		return Articles;
	}

	public void setArticles(List<NewsItem> articles) {
		Articles = articles;
	}

	@Transient
	public Reply getReply() {
		if (MessageType.text.equals(this.messageType)) {
			return new TextReply(this.content);
		} else if (MessageType.image.equals(this.messageType)) {
			return new ImageReply(this.mediaId);
		} else if (MessageType.voice.equals(this.messageType)) {
			return new VoiceReply(this.mediaId);
		} else if (MessageType.video.equals(this.messageType)) {
			return new VideoReply(this.mediaId, this.title, this.description);
		} else if (MessageType.music.equals(this.messageType)) {
			return new MusicReply(this.title, this.description, this.musicUrl, this.hqMusicUrl, this.thumbMediaId);
		} else if (MessageType.news.equals(this.messageType)) {
			return new NewsReply(this.getArticles());
		}
		return null;
	}

}
