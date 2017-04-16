/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.normal;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/***
 * 地理位置消息
 * 
 * @param ToUserName
 *            开发者微信号
 * @param FromUserName
 *            发送方帐号（一个OpenID）
 * @param CreateTime
 *            消息创建时间 （整型）
 * @param MsgType
 *            location
 * @param Location_X
 *            地理位置维度
 * @param Location_Y
 *            地理位置经度
 * @param Scale
 *            地图缩放大小
 * @param Label
 *            地理位置信息
 * @param MsgId
 *            消息id，64位整型
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class LocationMessage extends Message {

	// protected String Location_X;
	// protected String Location_Y;
	// protected String Scale;
	// protected String Label;
	// protected String MsgId;

	public LocationMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getLocation_X() {
		return super.getLocation_X();
	}

	public void setLocation_X(String location_X) {
		super.setLocation_X(location_X);
	}

	public String getLocation_Y() {
		return super.getLocation_Y();
	}

	public void setLocation_Y(String location_Y) {
		super.setLocation_Y(location_Y);
	}

	public String getScale() {
		return super.getScale();
	}

	public void setScale(String scale) {
		super.setScale(scale);
	}

	public String getLabel() {
		return super.getLabel();
	}

	public void setLabel(String label) {
		super.setLabel(label);
	}

	public String getMsgId() {
		return super.getMsgId();
	}

	public void setMsgId(String msgId) {
		super.setMsgId(msgId);
	}

}
