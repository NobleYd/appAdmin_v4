/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.app.Filter;
import com.app.entity.TemplateEntity;
import com.app.service.TemplateBaseService;

/**
 * TemplateBaseService - 基类
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Transactional
public class TemplateBaseServiceImpl<T extends TemplateEntity, ID extends Serializable> extends BaseServiceImpl<T, ID> implements TemplateBaseService<T, ID>, ServletContextAware {

	/** servletContext */
	private ServletContext servletContext;

	/** servletContext's set method */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/** variable from app.properties */
	@Value("${template.loader_path}")
	private String[] templateLoaderPaths;

	@Override
	public String read(ID id) {
		TemplateEntity templateEntity = find(id);
		return read(templateEntity);
	}

	@Override
	public String read(TemplateEntity templateEntity) {
		return read(templateEntity.getTemplatePath());
	}

	@Override
	public String read(String templatePath) {
		if (templatePath == null || templatePath.isEmpty())
			return null;
		String templateContent = null;
		try {
			templatePath = servletContext.getRealPath(templateLoaderPaths[0] + templatePath);
			File templateFile = new File(templatePath);
			templateContent = FileUtils.readFileToString(templateFile, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return templateContent;
	}

	@Override
	public void write(ID id, String content) {
		TemplateEntity templateEntity = find(id);
		write(templateEntity, content);
	}

	@Override
	public void write(TemplateEntity templateEntity, String content) {
		write(templateEntity.getTemplatePath(), content);
	}

	@Override
	public void write(String templatePath, String content) {
		try {
			templatePath = servletContext.getRealPath(templateLoaderPaths[0] + templatePath);
			File templateFile = new File(templatePath);
			FileUtils.writeStringToFile(templateFile, content, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean fileExist(ID id) {
		TemplateEntity templateEntity = find(id);
		return fileExist(templateEntity);
	}

	@Override
	public boolean fileExist(TemplateEntity templateEntity) {
		if (templateEntity == null)
			return false;
		String templatePath = servletContext.getRealPath(templateLoaderPaths[0] + templateEntity.getTemplatePath());
		return new File(templatePath).exists();
	}

	@Override
	public boolean fileExist(String templatePath) {
		templatePath = servletContext.getRealPath(templateLoaderPaths[0] + templatePath);
		return new File(templatePath).exists();
	}

	// 下面是baseservice中方法的重载{ 实现save、update之后自动更新模板文件内容 并且情况freemarker的缓存、以及删除方法的重载、查询方法自动设置模板内容到实体中 }

	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Override
	public void save(T entity) {
		super.save(entity);
		if (entity != null && entity.getContent() != null) {
			write(entity, entity.getContent());
		}
	}

	@Override
	public T update(T entity) {
		T mergedEntity = super.update(entity);
		if (mergedEntity != null) {
			write(mergedEntity, mergedEntity.getContent());
			freeMarkerConfigurer.getConfiguration().clearTemplateCache();
		}
		return mergedEntity;
	}

	@Override
	public T update(T entity, String... ignoreProperties) {
		T persistedEntity = super.update(entity, ignoreProperties);
		if (persistedEntity != null && persistedEntity.getContent() != null) {
			write(persistedEntity, persistedEntity.getContent());
			freeMarkerConfigurer.getConfiguration().clearTemplateCache();
		}
		return persistedEntity;
	}

	// 下面俩个方法都会调用最后一个方法，所以此处只重载一个。
	// @Override
	// public void delete(ID id) {
	// super.delete(id);
	// }
	//
	// @Override
	// public void delete(ID... ids) {
	// super.delete(ids);
	// }
	// 暂时不重载。
	// @Override
	// public void delete(T entity) {
	// super.delete(entity);
	// }

	@Override
	public T find(ID id) {
		T templateEntity = super.find(id);
		if (templateEntity != null) {
			templateEntity.setContent(read(templateEntity));
		}
		return templateEntity;
	}

	@Override
	public T find(Filter... filters) {
		T templateEntity = super.find(filters);
		if (templateEntity != null) {
			templateEntity.setContent(read(templateEntity));
		}
		return templateEntity;
	}

}
