/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message;
/**
 * @author APP TEAM
 * @version 1.0
 */
public class WeChatException extends Exception {

	private static final long serialVersionUID = -4514272781307331611L;
	
	/***
	 * 定义出错的时候是否将错误信息推送到client微信端。 调试的时候可能需要这么做。
	 */
	/** 不推送 */
	public static final String ModeA = "ModeA";
	/** 推送id */
	public static final String ModeB = "ModeB";
	/** 推送id和msg */
	public static final String ModeC = "ModeC";

	public static final String mode = ModeC;

	/** 错误id */
	private Long id;

	/***
	 * 
	 * @param id 错误id
	 * @param msg 错误描述
	 */
	public WeChatException(Long id, String msg) {
		super(msg);
		this.id = id;
	}

	@Override
	public String toString() {
		if(mode.equals(ModeA)){
			return "";
		}else if(mode.equals(ModeB)){
			return "WeChatException [id=" + id + "]";
		}else if(mode.equals(ModeC)){
			return "WeChatException [id=" + id + " msg=" + getMessage() +"]";
		}else{
			return "WeChatException";
		}
	}

}
