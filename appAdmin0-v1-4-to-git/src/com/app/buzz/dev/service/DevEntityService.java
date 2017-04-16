/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.service;

import java.util.List;

import com.app.buzz.dev.entity.DevEntity;
import com.app.service.BaseService;
/**
 * @author APP TEAM
 * @version 1.0
 */
public interface DevEntityService extends BaseService<DevEntity, Long> {

	/***
	 * 根据DevEntity的 title、className、classNameDesc 3个属性前缀搜索实体
	 * 
	 * @param keyword
	 *            title关键字
	 * @param count
	 *            搜索结果数量限制
	 * @return 返回结果列表
	 */
	List<DevEntity> search(String keyword, Integer count);

}
