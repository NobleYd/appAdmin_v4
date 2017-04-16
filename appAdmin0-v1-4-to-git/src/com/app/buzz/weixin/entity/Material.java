/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 素材
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_material")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_material_seq")
public class Material extends BaseEntity {
	private static final long serialVersionUID = 2761631827322127142L;

	/** 标题/备注 */
	private String name;

	/** 是否永久素材 */
	public enum Type {
		temporary("临时素材"), permanent("永久素材");

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

	/** 是否永久素材 */
	private Type type;

	public enum MaterialType {
		image("图片"), voice("语音"), video("视频"), thumb("缩略图");

		private String label;

		private MaterialType(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	/** 类型 */
	private MaterialType materialType;

	/** 媒体ID */
	private String media_id;

	/** 媒体文件 */
	private String media;

	/** 网址{只有图片素材需要} */
	private String url;

	/** 标题{只有视频素材需要} */
	private String title;

	/** 介绍{只有视频素材需要} */
	private String introduction;

	/** name get */
	@JsonProperty
	public String getName() {
		return name;
	}

	/** name set */
	public void setName(String name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	/** materialType get */
	@Enumerated(EnumType.STRING)
	public MaterialType getMaterialType() {
		return materialType;
	}

	/** materialType set */
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	/** media_id get */
	public String getMedia_id() {
		return media_id;
	}

	/** media_id set */
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	/** - get */
	public String getMedia() {
		return media;
	}

	/** - set */
	public void setMedia(String media) {
		this.media = media;
	}

	/** url get */
	public String getUrl() {
		return url;
	}

	/** url set */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
