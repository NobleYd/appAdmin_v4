/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.demo.dao.NormalEntityDao;
import com.app.buzz.demo.entity.NormalEntity;
import com.app.buzz.demo.service.NormalEntityService;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("normalEntityService")
public class NormalEntityServiceImpl extends BaseServiceImpl<NormalEntity, Long> implements NormalEntityService {

	@Resource
	private NormalEntityDao normalEntityDao;

	@Resource
	public void setBaseDao(NormalEntityDao normalEntityDao) {
		super.setBaseDao(normalEntityDao);
	}
	

}
