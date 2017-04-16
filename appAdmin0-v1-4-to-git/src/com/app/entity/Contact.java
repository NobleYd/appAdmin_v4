/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 联系人
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_contact")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_contact_seq")
public class Contact extends BaseEntity {

	private static final long serialVersionUID = 6794629713811756519L;

	/** 姓名/昵称 */
	private String name;

	public enum Sex {
		male("男"), female("女");

		private String label;

		private Sex(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	/** 性别 */
	private Sex sex;

	/** 联系电话 */
	private String mobile;

	/** 电子邮箱 */
	private String email;

	/** 备注 */
	private String remark;

	/** name get */
	@JsonProperty
	public String getName() {
		return name;
	}

	/** name set */
	public void setName(String name) {
		this.name = name;
	}

	/** sex get */
	@Enumerated(EnumType.STRING)
	public Sex getSex() {
		return sex;
	}

	/** sex set */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/** mobile get */
	public String getMobile() {
		return mobile;
	}

	/** mobile set */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** email get */
	public String getEmail() {
		return email;
	}

	/** email set */
	public void setEmail(String email) {
		this.email = email;
	}

	/** remark get */
	public String getRemark() {
		return remark;
	}

	/** remark set */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
