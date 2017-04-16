/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.dev.dao.DevConfigDao;
import com.app.buzz.dev.entity.DevConfig;
import com.app.buzz.dev.service.DevConfigService;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("devConfigService")
public class DevConfigServiceImpl extends BaseServiceImpl<DevConfig, Long> implements DevConfigService {

	@Resource
	private DevConfigDao devConfigDao;

	@Resource
	public void setBaseDao(DevConfigDao devConfigDao) {
		super.setBaseDao(devConfigDao);
	}

}
