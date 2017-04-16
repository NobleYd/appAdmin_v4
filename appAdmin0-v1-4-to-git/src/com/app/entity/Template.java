/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
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
 * Entity - 模板
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_template")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_template_seq")
public class Template extends TemplateEntity {

	private static final long serialVersionUID = 7230540340220645714L;

	/** 模板名称 */
	private String name;

	public enum Type {
		mail("邮件模板");

		private String label;

		private Type(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	/** 模板类型 */
	private Type type;

	/** 模板描述 */
	private String description;

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
	public Type getType() {
		return type;
	}

	/** type set */
	public void setType(Type type) {
		this.type = type;
	}

	/** description get */
	public String getDescription() {
		return description;
	}

	/** description set */
	public void setDescription(String description) {
		this.description = description;
	}

}
