package com.app.buzz.dev.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.entity.BaseEntity;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_dev_project")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_dev_project_seq")
public class DevProject extends BaseEntity {

	private static final long serialVersionUID = -1437015095118060975L;

	/** 项目名称 */
	private String name;

	/** 项目描述 */
	private String description;

	/** 项目包含的实体(多对多) */
	private Set<DevEntity> devEntities;

	/** name get */
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

	@ManyToMany
	public Set<DevEntity> getDevEntities() {
		return devEntities;
	}

	public void setDevEntities(Set<DevEntity> devEntities) {
		this.devEntities = devEntities;
	}

}
