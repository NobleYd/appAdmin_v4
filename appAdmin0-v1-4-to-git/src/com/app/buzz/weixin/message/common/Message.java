/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.common;

/**
 * @author APP TEAM
 * @version 1.0
 */
public class Message {

	protected MessageBody messageBody;

	public Message(MessageBody messageBody) {
		super();
		this.messageBody = messageBody;
	}

	/*******************************************************************************************************/
	/** 提供消息类型的判断的方法 ***********************************************************************************/
	public boolean isTextMessage() {
		return MessageType.text.toString().equalsIgnoreCase(messageBody.MsgType);
	}

	public boolean isImageMessage() {
		return MessageType.image.toString().equalsIgnoreCase(messageBody.MsgType);
	}

	public boolean isVoiceMessage() {
		return MessageType.voice.toString().equalsIgnoreCase(messageBody.MsgType);
	}

	public boolean isVideoMessage() {
		return MessageType.video.toString().equalsIgnoreCase(messageBody.MsgType);
	}

	public boolean isShortVideoMessage() {
		return MessageType.shortvideo.toString().equalsIgnoreCase(messageBody.MsgType);
	}

	public boolean isLocationMessage() {
		return MessageType.location.toString().equalsIgnoreCase(messageBody.MsgType);
	}

	public boolean isLinkMessage() {
		return MessageType.link.toString().equalsIgnoreCase(messageBody.MsgType);
	}

	public boolean isEventMessage() {
		return MessageType.event.toString().equalsIgnoreCase(messageBody.MsgType);
	}

	public boolean isSubscribeEvent() {
		return isEventMessage() && EventType.subscribe.toString().equalsIgnoreCase(messageBody.Event) && messageBody.EventKey == null;
	}

	public boolean isUnSubscribeEvent() {
		return isEventMessage() && EventType.unsubscribe.toString().equalsIgnoreCase(messageBody.Event);
	}

	public boolean isSubscribeScanEvent() {
		return isEventMessage() && EventType.subscribe.toString().equalsIgnoreCase(messageBody.Event) && messageBody.EventKey != null;
	}

	public boolean isScanEvent() {
		return isEventMessage() && EventType.SCAN.toString().equalsIgnoreCase(messageBody.Event);
	}

	public boolean isLocationEvent() {
		return isEventMessage() && EventType.LOCATION.toString().equalsIgnoreCase(messageBody.Event);
	}

	public boolean isClickEvent() {
		return isEventMessage() && EventType.CLICK.toString().equalsIgnoreCase(messageBody.Event);
	}

	public boolean isViewEvent() {
		return isEventMessage() && EventType.VIEW.toString().equalsIgnoreCase(messageBody.Event);
	}

	/*******************************************************************************************************/

	/** 提供消息公共字段的get和set方法 */
	public String getFromUserName() {
		return messageBody.FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		messageBody.FromUserName = fromUserName;
	}

	public String getToUserName() {
		return messageBody.ToUserName;
	}

	public void setToUserName(String toUserName) {
		messageBody.ToUserName = toUserName;
	}

	public Long getCreateTime() {
		return messageBody.CreateTime;
	}

	public void setCreateTime(Long createTime) {
		messageBody.CreateTime = createTime;
	}

	public String getMsgType() {
		return messageBody.MsgType;
	}

	public void setMsgType(String msgType) {
		messageBody.MsgType = msgType;
	}

	/** 提供protected类型的get和set方法 */

	protected String getMsgId() {
		return messageBody.MsgId;
	}

	protected void setMsgId(String msgId) {
		messageBody.MsgId = msgId;
	}

	protected String getContent() {
		return messageBody.Content;
	}

	protected void setContent(String content) {
		messageBody.Content = content;
	}

	protected String getPicUrl() {
		return messageBody.PicUrl;
	}

	protected void setPicUrl(String picUrl) {
		messageBody.PicUrl = picUrl;
	}

	protected String getMediaId() {
		return messageBody.MediaId;
	}

	protected void setMediaId(String mediaId) {
		messageBody.MediaId = mediaId;
	}

	protected String getFormat() {
		return messageBody.Format;
	}

	protected void setFormat(String format) {
		messageBody.Format = format;
	}

	protected String getThumbMediaId() {
		return messageBody.ThumbMediaId;
	}

	protected void setThumbMediaId(String thumbMediaId) {
		messageBody.ThumbMediaId = thumbMediaId;
	}

	protected String getTitle() {
		return messageBody.Title;
	}

	protected void setTitle(String title) {
		messageBody.Title = title;
	}

	protected String getDescription() {
		return messageBody.Description;
	}

	protected void setDescription(String description) {
		messageBody.Description = description;
	}

	protected String getUrl() {
		return messageBody.Url;
	}

	protected void setUrl(String url) {
		messageBody.Url = url;
	}

	protected String getLocation_X() {
		return messageBody.Location_X;
	}

	protected void setLocation_X(String location_X) {
		messageBody.Location_X = location_X;
	}

	protected String getLocation_Y() {
		return messageBody.Location_Y;
	}

	protected void setLocation_Y(String location_Y) {
		messageBody.Location_Y = location_Y;
	}

	protected String getScale() {
		return messageBody.Scale;
	}

	protected void setScale(String scale) {
		messageBody.Scale = scale;
	}

	protected String getLabel() {
		return messageBody.Label;
	}

	protected void setLabel(String label) {
		messageBody.Label = label;
	}

	protected String getEvent() {
		return messageBody.Event;
	}

	protected void setEvent(String event) {
		messageBody.Event = event;
	}

	protected String getEventKey() {
		return messageBody.EventKey;
	}

	protected void setEventKey(String eventKey) {
		messageBody.EventKey = eventKey;
	}

	protected String getTicket() {
		return messageBody.Ticket;
	}

	protected void setTicket(String ticket) {
		messageBody.Ticket = ticket;
	}

	protected Double getLatitude() {
		return messageBody.Latitude;
	}

	protected void setLatitude(Double latitude) {
		messageBody.Latitude = latitude;
	}

	protected Double getLongitude() {
		return messageBody.Longitude;
	}

	protected void setLongitude(Double longitude) {
		messageBody.Longitude = longitude;
	}

	protected Double getPrecision() {
		return messageBody.Precision;
	}

	protected void setPrecision(Double precision) {
		messageBody.Precision = precision;
	}

}
