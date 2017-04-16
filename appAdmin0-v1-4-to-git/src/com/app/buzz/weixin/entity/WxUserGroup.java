/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信用户分组
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_wx_user_group")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_wx_user_group_seq")
public class WxUserGroup extends BaseEntity {

	private static final long serialVersionUID = 5442970449767605441L;

	/**
	 * 用户组Id 0 未分组 1 黑名单 2 星标组 , 普通用户组从100号开始
	 */
	private Long groupId;

	/** 分组名 */
	private String name;

	/** 用户数 */
	private Long count;

	/** groupId get */
	public Long getGroupId() {
		return groupId;
	}

	/** groupId set */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/** name get */
	@JsonProperty
	public String getName() {
		return name;
	}

	/** name set */
	public void setName(String name) {
		this.name = name;
	}

	/** count get */
	public Long getCount() {
		return count;
	}

	/** count set */
	public void setCount(Long count) {
		this.count = count;
	}

}
