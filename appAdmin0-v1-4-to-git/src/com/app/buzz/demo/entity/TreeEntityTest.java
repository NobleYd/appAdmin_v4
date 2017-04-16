/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.entity.TreeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "demo_tree_entity_test")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "demo_tree_entity_test_seq")
public class TreeEntityTest extends TreeEntity<TreeEntityTest> {

	private static final long serialVersionUID = 7175471296273562746L;

	/** 标题 */
	private String name;

	/** 描述 */
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

	/** description get */
	public String getDescription() {
		return description;
	}

	/** description set */
	public void setDescription(String description) {
		this.description = description;
	}

}
