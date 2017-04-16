/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.core;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.app.buzz.dev.entity.DevConfig;
import com.app.buzz.dev.entity.DevEntity;
import com.app.util.FreemarkerUtils;

import freemarker.template.TemplateException;
/**
 * @author APP TEAM
 * @version 1.0
 */
public class CurdBaseTools {

	protected DevConfig devConfig;

	public DevConfig getDevConfig() {
		return devConfig;
	}

	public void setDevConfig(DevConfig devConfig) {
		this.devConfig = devConfig;
	}

	private static String template_entity = "";
	private static String template_entity_dao = "";
	private static String template_entity_dao_impl = "";
	private static String template_entity_service = "";
	private static String template_entity_service_impl = "";
	private static String template_quick_info = "";

	public CurdBaseTools() {
		super();
	}

	public void initTemplateFtl() {
		try {
			// entity
			String path = CurdBaseTools.class.getResource(devConfig.getCommonTemplatePath() + "/t_Entity.java.ftl").getPath();
			File file = new File(URLDecoder.decode(path, "utf-8"));
			template_entity = FileUtils.readFileToString(file);
			// dao
			path = CurdBaseTools.class.getResource(devConfig.getCommonTemplatePath() + "/t_EntityDao.java.ftl").getPath();
			file = new File(URLDecoder.decode(path, "utf-8"));
			template_entity_dao = FileUtils.readFileToString(file);
			// daoImpl
			path = CurdBaseTools.class.getResource(devConfig.getCommonTemplatePath() + "/t_EntityDaoImpl.java.ftl").getPath();
			file = new File(URLDecoder.decode(path, "utf-8"));
			template_entity_dao_impl = FileUtils.readFileToString(file);
			// service
			path = CurdBaseTools.class.getResource(devConfig.getCommonTemplatePath() + "/t_EntityService.java.ftl").getPath();
			file = new File(URLDecoder.decode(path, "utf-8"));
			template_entity_service = FileUtils.readFileToString(file);
			// serviceImpl
			path = CurdBaseTools.class.getResource(devConfig.getCommonTemplatePath() + "/t_EntityServiceImpl.java.ftl").getPath();
			file = new File(URLDecoder.decode(path, "utf-8"));
			template_entity_service_impl = FileUtils.readFileToString(file);

			// template_quick_info
			path = CurdBaseTools.class.getResource(devConfig.getCommonTemplatePath() + "/t_quick_info.txt.ftl").getPath();
			file = new File(URLDecoder.decode(path, "utf-8"));
			template_quick_info = FileUtils.readFileToString(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成一个entity
	 */
	public String generateEntity(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_entity, model, null);
	}

	/**
	 * 生成一个entity到文件
	 */
	public void generateEntity2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateEntity(devEntity);
		String path = devConfig.getJavaOutputPath() + "/entity/" + devEntity.getCapitalizedClassName() + ".java";
		output2file(path, content);
	}

	/**
	 * 生成一个entityDao
	 */
	public String generateEntityDao(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_entity_dao, model, null);
	}

	/**
	 * 生成一个entityDao到文件
	 */
	public void generateEntityDao2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateEntityDao(devEntity);
		String path = devConfig.getJavaOutputPath() + "/dao/" + devEntity.getCapitalizedClassName() + "Dao.java";
		output2file(path, content);
	}

	/**
	 * 生成一个entityDaoImpl
	 */
	public String generateEntityDaoImpl(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_entity_dao_impl, model, null);
	}

	/**
	 * 生成一个entityDaoImpl到文件
	 */
	public void generateEntityDaoImpl2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateEntityDaoImpl(devEntity);
		String path = devConfig.getJavaOutputPath() + "/dao/impl/" + devEntity.getCapitalizedClassName() + "DaoImpl.java";
		output2file(path, content);
	}

	/**
	 * 生成一个entityService
	 */
	public String generateEntityService(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_entity_service, model, null);
	}

	/**
	 * 生成一个entityService到文件
	 */
	public void generateEntityService2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateEntityService(devEntity);
		String path = devConfig.getJavaOutputPath() + "/service/" + devEntity.getCapitalizedClassName() + "Service.java";
		output2file(path, content);
	}

	/**
	 * 生成一个entityServiceImpl
	 */
	public String generateEntityServiceImpl(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_entity_service_impl, model, null);
	}

	/**
	 * 生成一个entityServiceImpl到文件
	 */
	public void generateEntityServiceImpl2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateEntityServiceImpl(devEntity);
		String path = devConfig.getJavaOutputPath() + "/service/impl/" + devEntity.getCapitalizedClassName() + "ServiceImpl.java";
		output2file(path, content);
	}

	/**
	 * 生成quickInfo
	 */
	public String generateQuickInfo(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_quick_info, model, null);
	}

	/**
	 * 生成quickInfo到文件
	 */
	public void generateQuickInfo2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateQuickInfo(devEntity);
		String path = devConfig.getJavaOutputPath() + "/entity/" + devEntity.getCapitalizedClassName() + "_QuickInfo.txt";
		output2file(path, content);
	}

	/**
	 * 将内容写入到某个文件
	 */
	public static void output2file(String filePath, String content) throws IOException {
		File file = new File(URLDecoder.decode(filePath, "utf-8"));
		FileUtils.writeStringToFile(file, content, "utf-8");
	}

}
