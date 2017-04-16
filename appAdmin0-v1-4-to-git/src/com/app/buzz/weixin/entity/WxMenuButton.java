/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/***
 *
 * 微信按钮
 *
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_wx_menu_button")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_wx_menu_button_seq")
public class WxMenuButton extends BaseEntity {

	private static final long serialVersionUID = -5393260445903806071L;

	/** 显示名称 */
	private String name;

	public enum WxMenuButtonType {
		// 注释后边是对应类型按钮支持的属性列表
		parent("父按钮"), // name,type
		click("点击推事件"), // name,type,parent,key
		view("点击跳转URL"), // name,type,parent,url
		scancode_push("扫码推事件"), // name,type,parent,key
		scancode_waitmsg("扫码推事件+<消息接收>提示"), // name,type,parent,key
		pic_sysphoto("系统拍照+发图"), // name,type,parent,key
		pic_photo_or_album("拍照或相册选择+发图"), // name,type,parent,key
		pic_weixin("弹出微信相册发图器"), // name,type,parent,key
		location_select("弹出地理位置选择器"), // name,type,parent,key
		media_id("下发消息（除文本消息）"), // name,type,parent,media_id
		view_limited("跳转图文消息URL")// name,type,parent,media_id
		;

		private String label;

		private WxMenuButtonType(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	/** 按钮类型 */
	private WxMenuButtonType type;

	/** 子按钮 */
	private Set<WxMenuButton> children;

	/** 父按钮 */
	private WxMenuButton parent;

	/** KEY */
	private String key;

	/** URL */
	private String url;

	/** 媒体ID */
	private String media_id;

	/** name get */
	@JsonProperty
	public String getName() {
		return name;
	}

	/** name set */
	public void setName(String name) {
		this.name = name;
	}

	/** type get */
	@Enumerated(EnumType.STRING)
	public WxMenuButtonType getType() {
		return type;
	}

	/** type set */
	public void setType(WxMenuButtonType type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	public Set<WxMenuButton> getChildren() {
		return children;
	}

	public void setChildren(Set<WxMenuButton> children) {
		this.children = children;
	}

	/** parent get */
	@ManyToOne
	public WxMenuButton getParent() {
		return parent;
	}

	/** parent set */
	public void setParent(WxMenuButton parent) {
		this.parent = parent;
	}

	/** key get */
	@Column(name = "attr_key")
	// 说明：Mysql语法不允许出现key，所以因此本类统一属性命名风格
	public String getKey() {
		return key;
	}

	/** key set */
	public void setKey(String key) {
		this.key = key;
	}

	/** url get */
	@Column(name = "attr_url")
	public String getUrl() {
		return url;
	}

	/** url set */
	public void setUrl(String url) {
		this.url = url;
	}

	/** media_id get */
	@Column(name = "attr_media_id")
	public String getMedia_id() {
		return media_id;
	}

	/** media_id set */
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	@Transient
	public Map<String, Object> getWxMenuButton() {
		Map<String, Object> button = new HashMap<String, Object>();
		button.put("name", this.getName());
		if (WxMenuButtonType.parent.equals(this.getType())) {
			button.put("sub_button", getChildrenWxMenuButton());
		} else {
			button.put("type", this.getType());
			if (WxMenuButtonType.click.equals(this.getType())) {
				button.put("key", this.getKey());
			} else if (WxMenuButtonType.view.equals(this.getType())) {
				button.put("url", this.getUrl());
			} else if (WxMenuButtonType.scancode_push.equals(this.getType())) {
				button.put("key", this.getKey());
			} else if (WxMenuButtonType.scancode_waitmsg.equals(this.getType())) {
				button.put("key", this.getKey());
			} else if (WxMenuButtonType.pic_sysphoto.equals(this.getType())) {
				button.put("key", this.getKey());
			} else if (WxMenuButtonType.pic_photo_or_album.equals(this.getType())) {
				button.put("key", this.getKey());
			} else if (WxMenuButtonType.pic_weixin.equals(this.getType())) {
				button.put("key", this.getKey());
			} else if (WxMenuButtonType.location_select.equals(this.getType())) {
				button.put("key", this.getKey());
			} else if (WxMenuButtonType.media_id.equals(this.getType())) {
				button.put("media_id", this.getMedia_id());
			} else if (WxMenuButtonType.view_limited.equals(this.getType())) {
				button.put("media_id", this.getMedia_id());
			}
		}
		return button;
	}

	@Transient
	public List<Map<String, Object>> getChildrenWxMenuButton() {
		List<Map<String, Object>> childrenWxButtons = new ArrayList<Map<String, Object>>();
		for (WxMenuButton wxMenuButton : this.children) {
			if (!WxMenuButtonType.parent.equals(wxMenuButton.getType())) {
				// 此处判断一下异常情况，避免出现死循环（正规操作不会出现这中情况）。
				childrenWxButtons.add(wxMenuButton.getWxMenuButton());
			}
		}
		return childrenWxButtons;
	}

}
