/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.dev.dao.DevEntityDao;
import com.app.buzz.dev.entity.DevEntity;
import com.app.buzz.dev.service.DevEntityService;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("devEntity")
public class DevEntityImpl extends BaseServiceImpl<DevEntity, Long> implements DevEntityService{

	@Resource(name = "devEntityDao")
	private DevEntityDao devEntityDao;

	@Resource(name = "devEntityDao")
	public void setBaseDao(DevEntityDao devEntityDao) {
		super.setBaseDao(devEntityDao);
	}

	@Override
	public List<DevEntity> search(String keyword, Integer count) {
		return devEntityDao.search(keyword,count);
	}

}
