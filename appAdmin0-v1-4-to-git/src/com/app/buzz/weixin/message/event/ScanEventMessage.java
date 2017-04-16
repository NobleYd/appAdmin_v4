package com.app.buzz.weixin.message.event;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/**
 * 用户已关注情况下进行扫描带场景值二维码的事件消息。
 * 
 * @param 扫描带参数二维码事件
 * @param 用户未关注时，进行关注后的事件推送
 * @param ToUserName
 *            开发者微信号
 * @param FromUserName
 *            发送方帐号（一个OpenID）
 * @param CreateTime
 *            消息创建时间 （整型）
 * @param MsgType
 *            消息类型，event
 * @param Event
 *            事件类型，subscribe
 * @param EventKey
 *            事件KEY值，qrscene_为前缀，后面为二维码的参数值
 * @param Ticket
 *            二维码的ticket，可用来换取二维码图片
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class ScanEventMessage extends Message {

	// protected String Event;
	// protected String Ticket;
	// protected String EventKey;

	public ScanEventMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getEvent() {
		return super.getEvent();
	}

	public void setEvent(String event) {
		super.setEvent(event);
	}

	public String getTicket() {
		return super.getTicket();
	}

	public void setTicket(String ticket) {
		super.setTicket(ticket);
	}

	public String getEventKey() {
		return super.getEventKey();
	}

	public void setEventKey(String eventKey) {
		super.setEventKey(eventKey);
	}
}
