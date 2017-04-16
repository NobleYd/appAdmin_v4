/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.dev.dao.DevAttributeDao;
import com.app.buzz.dev.entity.DevAttribute;
import com.app.buzz.dev.service.DevAttributeService;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("devAttribute")
public class DevAttributeImpl extends BaseServiceImpl<DevAttribute, Long> implements DevAttributeService {

	@Resource(name = "devAttributeDao")
	private DevAttributeDao devAttributeDao;

	@Resource(name = "devAttributeDao")
	public void setBaseDao(DevAttributeDao devAttributeDao) {
		super.setBaseDao(devAttributeDao);
	}

}
