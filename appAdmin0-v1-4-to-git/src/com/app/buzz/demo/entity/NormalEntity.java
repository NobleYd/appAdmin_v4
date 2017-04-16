/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "demo_normal_entity")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "demo_normal_entity_seq")
public class NormalEntity extends BaseEntity {

	private static final long serialVersionUID = -6195076040379139737L;

	/** 名称 */
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

	/** 年龄 */
	private Integer age;

	/** 出生日期 */
	private Date birthDate;

	/** 健康状况 */
	private Boolean headlthState;

	/** 爱人 */
	private NormalEntity lover;

	/** 照片 */
	private String picture;

	/** 综合评分[满分100] */
	private BigDecimal score;

	/** 附件 */
	private String attachment;

	@JsonProperty

	/** name get */
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

	/** age get */
	public Integer getAge() {
		return age;
	}

	/** age set */
	public void setAge(Integer age) {
		this.age = age;
	}

	/** birthDate get */
	public Date getBirthDate() {
		return birthDate;
	}

	/** birthDate set */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/** headlthState get */
	public Boolean getHeadlthState() {
		return headlthState;
	}

	/** headlthState set */
	public void setHeadlthState(Boolean headlthState) {
		this.headlthState = headlthState;
	}

	/** lover get */
	@ManyToOne
	public NormalEntity getLover() {
		return lover;
	}

	/** lover set */
	public void setLover(NormalEntity lover) {
		this.lover = lover;
	}

	/** - get */
	public String getPicture() {
		return picture;
	}

	/** - set */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/** score get */
	public BigDecimal getScore() {
		return score;
	}

	/** score set */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	/** - get */
	public String getAttachment() {
		return attachment;
	}

	/** - set */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

}
