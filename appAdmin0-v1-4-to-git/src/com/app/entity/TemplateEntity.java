/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Entity - 模板实体 {所有需要以文件格式保存，数据库仅仅保存路径的实体都只需要继承这个类即可实现。}
 * 
 * @author APP TEAM
 * @version 1.0
 */
@MappedSuperclass
public abstract class TemplateEntity extends BaseEntity {

	private static final long serialVersionUID = 6861553823084249092L;

	/** "templatePath"属性名称 */
	public static final String TEMPLATE_PATH_PROPERTY_NAME = "templatePath";

	/** 模板路径 */
	private String templatePath;

	/** 静态化路径 */
	private String staticPath;

	/** 内容 */
	private String content = "";

	/** templatePath get */
	public String getTemplatePath() {
		return templatePath;
	}

	/** templatePath set */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	/** staticPath get */
	public String getStaticPath() {
		return staticPath;
	}

	/** staticPath set */
	public void setStaticPath(String staticPath) {
		this.staticPath = staticPath;
	}

	// 不存储到数据库
	@Transient
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// 获取时间戳文件名字
	@Transient
	public String getNameOfTimeStamp() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + new Random().nextInt(9999) + ".ftl";
	}

}
