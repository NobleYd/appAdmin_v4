/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.reply;

import javax.persistence.Embeddable;

/***
 * 图文消息中的item
 * @author APP TEAM
 * @version 1.0
 */
@Embeddable
public class NewsItem {

	public String Title;
	public String Description;
	public String PicUrl;
	public String Url;

	public NewsItem() {
		super();
	}

	/**
	 * @param Title 否 图文消息标题
	 * @param Description 否 图文消息描述
	 * @param PicUrl 否 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 * @param Url 否 点击图文消息跳转链接
	 */
	public NewsItem(String title, String description, String picUrl, String url) {
		super();
		Title = title;
		Description = description;
		PicUrl = picUrl;
		Url = url;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	
}
