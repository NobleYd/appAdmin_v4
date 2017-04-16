/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service;

import java.io.Serializable;

import com.app.entity.TemplateEntity;

/**
 * TemplateBaseService - 基类
 * 
 * @author APP TEAM
 * @version 1.0
 */
public interface TemplateBaseService<T extends TemplateEntity, ID extends Serializable> extends BaseService<T, ID> {

	/**
	 * 读取模板实体的内容
	 * 
	 * @param id
	 *            模板实体的ID
	 * @return 模板实体的内容
	 */
	String read(ID id);

	/**
	 * 读取模板实体的内容
	 * 
	 * @param templateEntity
	 *            模板实体
	 * @return 模板实体的内容
	 */
	String read(TemplateEntity templateEntity);

	/**
	 * 读取指定路径模板的内容
	 * 
	 * @param templatePath
	 *            模板路径
	 * @return 模板内容
	 */
	String read(String templatePath);

	/**
	 * 将指定内容写入指定模板实体
	 * 
	 * @param id
	 *            指定的模板实体的Id
	 * @param content
	 *            写入内容
	 */
	void write(ID id, String content);

	/**
	 * 将指定内容写入指定模板实体
	 * 
	 * @param template
	 *            指定的模板实体
	 * @param content
	 *            写入内容
	 */
	void write(TemplateEntity templateEntity, String content);

	/**
	 * 写入模板文件内容
	 * 
	 * @param templatePath
	 *            模板路径
	 * @param content
	 *            模板文件内容
	 */
	/**
	 * 将指定内容写入指定路径的模板
	 * 
	 * @param templatePath
	 *            模板路径
	 * @return 写入内容
	 */
	void write(String templatePath, String content);

	/**
	 * 判断指定实体的模板文件是否存在
	 * 
	 * @param id
	 *            ID
	 * @return 模板文件是否存在
	 */
	boolean fileExist(ID id);

	/**
	 * 判断指定实体德模板文件是否存在
	 * 
	 * @param template
	 *            指定的模板实体
	 * @return 模板文件是否存在
	 */
	boolean fileExist(TemplateEntity templateEntity);

	/**
	 * 判断指定路径的模板文件是否存在
	 * 
	 * @param templatePath
	 *            模板路径
	 * @return 模板文件是否存在
	 */
	boolean fileExist(String templatePath);

}
