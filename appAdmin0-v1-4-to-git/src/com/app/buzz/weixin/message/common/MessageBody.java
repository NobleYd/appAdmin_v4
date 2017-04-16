/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.common;

/**
 * @title 这个类用于接收所有的消息。
 * @desc 经过本人考虑，多次修改，发现，很多消息类似，但也有区别。
 * @desc 1可以采用一个实体表示，会有很多冗余属性，而且可能会误操作。
 * @desc （操作了不属于当前类型的字段，这种情况会报空指针异常）
 * @desc 2也可以分开。
 * @desc 3还可以公用一部分，分开一部分。（继承）
 * @desc 最初本人想采用3的方式，后来发现越来越多的需要提前知道的字段，于是越来越多字段要提到公用的实体中。
 * @desc 而且，每次都必须先解析为公用实体，判断类型之后再解析为具体类型。
 * @desc 于是，最终修改为采用1个实体表示。
 * @desc 但是仍然使用许多具体的实体，用于提供get和set方法，这样控制了不同类型对不同字段的访问权限。
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class MessageBody {

	/** 每个消息都带有的字段，在本类提供了get/set方法 */
	protected String FromUserName;
	protected String ToUserName;
	protected Long CreateTime;
	protected String MsgType;

	protected String MsgId;
	protected String Content;
	protected String PicUrl;
	protected String MediaId;
	protected String Format;
	protected String ThumbMediaId;
	protected String Title;
	protected String Description;
	protected String Url;
	protected String Event;
	protected String EventKey;
	protected String Ticket;
	protected String Location_X;
	protected String Location_Y;
	protected String Scale;
	protected String Label;
	protected Double Latitude;
	protected Double Longitude;
	protected Double Precision;

	/*******************************************************************************************************/
	/** 提供消息类型的判断的方法 ***********************************************************************************/
	public boolean isTextMessage() {
		return MessageType.text.toString().equalsIgnoreCase(this.MsgType);
	}

	public boolean isImageMessage() {
		return MessageType.image.toString().equalsIgnoreCase(this.MsgType);
	}

	public boolean isVoiceMessage() {
		return MessageType.voice.toString().equalsIgnoreCase(this.MsgType);
	}

	public boolean isVideoMessage() {
		return MessageType.video.toString().equalsIgnoreCase(this.MsgType);
	}

	public boolean isShortVideoMessage() {
		return MessageType.shortvideo.toString().equalsIgnoreCase(this.MsgType);
	}

	public boolean isLocationMessage() {
		return MessageType.location.toString().equalsIgnoreCase(this.MsgType);
	}

	public boolean isLinkMessage() {
		return MessageType.link.toString().equalsIgnoreCase(this.MsgType);
	}

	public boolean isEventMessage() {
		return MessageType.event.toString().equalsIgnoreCase(this.MsgType);
	}

	public boolean isSubscribeEvent() {
		return isEventMessage() && EventType.subscribe.toString().equalsIgnoreCase(this.Event) && this.EventKey == null;
	}

	public boolean isUnSubscribeEvent() {
		return isEventMessage() && EventType.unsubscribe.toString().equalsIgnoreCase(this.Event);
	}

	public boolean isSubscribeScanEvent() {
		return isEventMessage() && EventType.subscribe.toString().equalsIgnoreCase(this.Event) && this.EventKey != null;
	}

	public boolean isScanEvent() {
		return isEventMessage() && EventType.SCAN.toString().equalsIgnoreCase(this.Event);
	}

	public boolean isLocationEvent() {
		return isEventMessage() && EventType.LOCATION.toString().equalsIgnoreCase(this.Event);
	}

	public boolean isClickEvent() {
		return isEventMessage() && EventType.CLICK.toString().equalsIgnoreCase(this.Event);
	}

	public boolean isViewEvent() {
		return isEventMessage() && EventType.VIEW.toString().equalsIgnoreCase(this.Event);
	}

	/*******************************************************************************************************/

}
