/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.weixin.dao.MaterialDao;
import com.app.buzz.weixin.entity.Material;
import com.app.buzz.weixin.service.MaterialService;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("materialService")
public class MaterialServiceImpl extends BaseServiceImpl<Material, Long> implements MaterialService {

	@Resource
	private MaterialDao materialDao;

	@Resource
	public void setBaseDao(MaterialDao materialDao) {
		super.setBaseDao(materialDao);
	}
	

}
