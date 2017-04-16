package com.app.buzz.weixin.message.event;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;

/***
 * 上报地理位置事件消息
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
 *            事件类型，LOCATION
 * @param Latitude
 *            地理位置纬度
 * @param Longitude
 *            地理位置经度
 * @param Precision
 *            地理位置精度
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class LocationEventMessage extends Message {

	// protected String Event;
	// protected Double Latitude;
	// protected Double Longitude;
	// protected Double Precision;

	public LocationEventMessage(MessageBody messageBody) {
		super(messageBody);
	}

	public String getEvent() {
		return super.getEvent();
	}

	public void setEvent(String event) {
		super.setEvent(event);
	}

	public Double getLatitude() {
		return super.getLatitude();
	}

	public void setLatitude(Double latitude) {
		super.setLatitude(latitude);
	}

	public Double getLongitude() {
		return super.getLongitude();
	}

	public void setLongitude(Double longitude) {
		super.setLongitude(longitude);
	}

	public Double getPrecision() {
		return super.getPrecision();
	}

	public void setPrecision(Double precision) {
		super.setPrecision(precision);
	}

}
