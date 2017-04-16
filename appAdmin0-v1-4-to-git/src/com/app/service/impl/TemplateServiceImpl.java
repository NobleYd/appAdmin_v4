/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.TemplateDao;
import com.app.entity.Template;
import com.app.service.TemplateService;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("templateService")
public class TemplateServiceImpl extends TemplateBaseServiceImpl<Template, Long> implements TemplateService {

	@Resource
	private TemplateDao templateDao;

	@Resource
	public void setBaseDao(TemplateDao templateDao) {
		super.setBaseDao(templateDao);
	}

}
