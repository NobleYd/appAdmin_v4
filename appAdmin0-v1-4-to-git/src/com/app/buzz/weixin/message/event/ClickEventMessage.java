package com.app.buzz.weixin.message.event;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/**
 * 点击菜单拉取消息时的事件推送 点击事件消息
 * 
 * @param ToUserName
 *            开发者微信号
 * @param FromUserName
 *            发送方帐号（一个OpenID）
 * @param CreateTime
 *            消息创建时间 （整型）
 * @param MsgType
 *            消息类型，event
 * @param Event
 *            事件类型，CLICK
 * @param EventKey
 *            事件KEY值，与自定义菜单接口中KEY值对应
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class ClickEventMessage extends Message {

	// protected String Event;
	// protected String EventKey;

	public ClickEventMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getEvent() {
		return super.getEvent();
	}

	public void setEvent(String event) {
		super.setEvent(event);
	}

	public String getEventKey() {
		return super.getEventKey();
	}

	public void setEventKey(String eventKey) {
		super.setEventKey(eventKey);
	}

}
