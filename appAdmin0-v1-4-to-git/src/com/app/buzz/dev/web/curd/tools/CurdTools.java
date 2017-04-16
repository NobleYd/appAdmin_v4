package com.app.buzz.dev.web.curd.tools;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.app.buzz.dev.core.CurdBaseTools;
import com.app.buzz.dev.entity.DevEntity;
import com.app.util.FreemarkerUtils;

import freemarker.template.TemplateException;

/**
 * CURD 工具类
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Component
public class CurdTools extends CurdBaseTools {

	private static String template_controller_ftl = "";
	private static String template_list_ftl = "";
	private static String template_add_ftl = "";
	private static String template_edit_ftl = "";

	public CurdTools() {
		super();
	}

	public void initTemplateFtl() {
		try {
			// 调用父类的方法初始化Entity、Dao、Service模板。
			super.initTemplateFtl();

			// template_controller_ftl
			String path = CurdTools.class.getResource(devConfig.getCustomerTemplatePath() + "/t_EntityController.java.ftl").getPath();
			File file = new File(URLDecoder.decode(path, "utf-8"));
			template_controller_ftl = FileUtils.readFileToString(file);
			// listFtl
			path = CurdTools.class.getResource(devConfig.getCustomerTemplatePath() + "/t_list.ftl").getPath();
			file = new File(URLDecoder.decode(path, "utf-8"));
			template_list_ftl = FileUtils.readFileToString(file);
			// addFtl
			path = CurdTools.class.getResource(devConfig.getCustomerTemplatePath() + "/t_add.ftl").getPath();
			file = new File(URLDecoder.decode(path, "utf-8"));
			template_add_ftl = FileUtils.readFileToString(file);
			// editFtl
			path = CurdTools.class.getResource(devConfig.getCustomerTemplatePath() + "/t_edit.ftl").getPath();
			file = new File(URLDecoder.decode(path, "utf-8"));
			template_edit_ftl = FileUtils.readFileToString(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成Controller
	 */
	public String generateEntityController(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_controller_ftl, model, null);
	}

	/**
	 * 生成Controller到文件
	 */
	public void generateEntityController2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateEntityController(devEntity);
		String path = devConfig.getJavaOutputPath() + "/controller/" + devEntity.getCapitalizedClassName() + "Controller.java";
		output2file(path, content);
	}

	/**
	 * 生成list页面
	 */
	public String generateListFtl(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_list_ftl, model, null);
	}

	/**
	 * 生成list页面到文件
	 */
	public void generateListFtl2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateListFtl(devEntity);
		String path = devConfig.getPageOutputPath() + "/" + devEntity.getUnHumpClassName() + "/list.ftl";
		output2file(path, content);
	}

	/**
	 * 生成add页面
	 */
	public String generateAddFtl(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_add_ftl, model, null);
	}

	/**
	 * 生成add页面到文件
	 */
	public void generateAddFtl2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateAddFtl(devEntity);
		String path = devConfig.getPageOutputPath() + "/" + devEntity.getUnHumpClassName() + "/add.ftl";
		output2file(path, content);
	}

	/**
	 * 生成edit页面
	 */
	public String generateEditFtl(DevEntity devEntity) throws IOException, TemplateException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("devConfig", this.devConfig);
		model.put("devEntity", devEntity);
		return FreemarkerUtils.process(template_edit_ftl, model, null);
	}

	/**
	 * 生成edit页面到文件
	 */
	public void generateEditFtl2file(DevEntity devEntity) throws IOException, TemplateException {
		String content = generateEditFtl(devEntity);
		String path = devConfig.getPageOutputPath() + "/" + devEntity.getUnHumpClassName() + "/edit.ftl";
		output2file(path, content);
	}

}
