/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.common;

import java.util.Date;

import com.app.buzz.weixin.util.XmlUtils;

/**
 * 公用的回复消息。
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class Reply {

	protected String FromUserName;
	protected String ToUserName;
	protected Long CreateTime;
	protected String MsgType;

	public Reply() {
		super();
	}

	public Reply(String fromUserName, String toUserName, Long createTime, String msgType) {
		super();
		this.FromUserName = fromUserName;
		this.ToUserName = toUserName;
		this.CreateTime = createTime;
		this.MsgType = msgType;
	}

	public Reply(Message message, String msgType) {
		super();
		/** 根据原始消息生成回复消息的发送方和接收方，对调位置即可。 */
		this.FromUserName = message.getToUserName();
		this.ToUserName = message.getFromUserName();
		/** createTime使用当前时间即可。 */
		this.CreateTime = new Date().getTime();
		this.MsgType = msgType;
	}

	/***
	 * 设置从接收到的Message来的几个字段。
	 */
	public Reply setFromMessage(Message message) {
		/** 根据原始消息生成回复消息的发送方和接收方，对调位置即可。 */
		this.FromUserName = message.getToUserName();
		this.ToUserName = message.getFromUserName();
		/** createTime使用当前时间即可。 */
		this.CreateTime = new Date().getTime();
		return this;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	@Override
	public String toString() {
		return XmlUtils.object2Xml(this, "xml");
	}

}
