/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service.impl;

import javax.annotation.Resource;

import com.app.dao.SnDao;
import com.app.entity.Sn.Type;
import com.app.service.SnService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 序列号
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Service("snService")
public class SnServiceImpl implements SnService {

	@Resource(name = "snDao")
	private SnDao snDao;

	@Transactional
	public String generate(Type type) {
		return snDao.generate(type);
	}

}