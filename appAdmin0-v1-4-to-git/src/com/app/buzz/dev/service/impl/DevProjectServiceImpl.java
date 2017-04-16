/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.dev.dao.DevProjectDao;
import com.app.buzz.dev.entity.DevProject;
import com.app.buzz.dev.service.DevProjectService;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("devProjectService")
public class DevProjectServiceImpl extends BaseServiceImpl<DevProject, Long> implements DevProjectService {

	@Resource
	private DevProjectDao devProjectDao;

	@Resource
	public void setBaseDao(DevProjectDao devProjectDao) {
		super.setBaseDao(devProjectDao);
	}

}
