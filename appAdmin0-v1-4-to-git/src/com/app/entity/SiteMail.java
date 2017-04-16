/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 邮件
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_site_mail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_site_mail_seq")
public class SiteMail extends TemplateEntity {

	private static final long serialVersionUID = 7104443192204326012L;

	/** 标题 */
	private String title;

	/** 发件人 */
	private Admin authorAdmin;

	/** 收件人 */
	private Admin toAdmin;// 2个收件人一个不为空即可，admin为站内信情况，contact为通讯录情况。

	/** 收件人 */
	private Contact toContact;

	public enum Location {
		draftBox("草稿箱"), sentBox("发件箱"), receivedBox("收件箱"), recycledBox("回收箱"), removed("垃圾箱");

		private String label;

		private Location(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	/** 发件位置 */
	private Location authorLocation = Location.sentBox;

	/** 收件位置 */
	private Location toLocation = Location.receivedBox;

	/** 是否已读 */
	private Boolean isRead = false;

	/** title get */
	@JsonProperty
	public String getTitle() {
		return title;
	}

	/** title set */
	public void setTitle(String title) {
		this.title = title;
	}

	/** authorAdmin get */
	@ManyToOne
	public Admin getAuthorAdmin() {
		return authorAdmin;
	}

	/** authorAdmin set */
	public void setAuthorAdmin(Admin authorAdmin) {
		this.authorAdmin = authorAdmin;
	}

	/** toAdmin get */
	@ManyToOne
	public Admin getToAdmin() {
		return toAdmin;
	}

	/** toAdmin set */
	public void setToAdmin(Admin toAdmin) {
		this.toAdmin = toAdmin;
	}

	/** toContact get */
	@ManyToOne
	public Contact getToContact() {
		return toContact;
	}

	/** toContact set */
	public void setToContact(Contact toContact) {
		this.toContact = toContact;
	}

	@Enumerated(EnumType.STRING)
	public Location getAuthorLocation() {
		return authorLocation;
	}

	public void setAuthorLocation(Location authorLocation) {
		this.authorLocation = authorLocation;
	}

	@Enumerated(EnumType.STRING)
	public Location getToLocation() {
		return toLocation;
	}

	public void setToLocation(Location toLocation) {
		this.toLocation = toLocation;
	}

	/** isRead get */
	public Boolean getIsRead() {
		return isRead;
	}

	/** isRead set */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

}
