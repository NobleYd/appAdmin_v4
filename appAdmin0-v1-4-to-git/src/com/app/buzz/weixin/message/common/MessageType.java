/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.common;

import java.util.ArrayList;
import java.util.List;
/**
 * @author APP TEAM
 * @version 1.0
 */
public enum MessageType {
	// 接收+回复
	text("文本消息"), image("图片消息"), voice("语音消息"), video("视频消息"),
	// 接收
	shortvideo("小视频消息"), location("地理位置消息"), link("链接消息"), event("事件推送消息"),
	// 回复
	music("音乐消息"), news("图文消息");

	private String label;

	private MessageType(String label) {
		this.label = label;
	}

	/***
	 * @return 返回所有接收消息类型列表
	 */
	public List<MessageType> getAcceptMessageTypes() {
		List<MessageType> messageTypes = new ArrayList<MessageType>() {

			private static final long serialVersionUID = 2709638770031633808L;

			{
				add(MessageType.text);
				add(MessageType.image);
				add(MessageType.voice);
				add(MessageType.video);

				add(MessageType.shortvideo);
				add(MessageType.location);
				add(MessageType.link);
				add(MessageType.event);
			}
		};
		return messageTypes;
	}

	/***
	 * @return 返回所有回复消息类型列表
	 */
	public List<MessageType> getReplyMessageTypes() {
		List<MessageType> messageTypes = new ArrayList<MessageType>() {

			private static final long serialVersionUID = 354627650398935974L;

			{
				add(MessageType.text);
				add(MessageType.image);
				add(MessageType.voice);
				add(MessageType.video);

				add(MessageType.music);
				add(MessageType.news);
			}
		};
		return messageTypes;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
