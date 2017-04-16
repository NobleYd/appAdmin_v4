/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.stats.dao.StatsItemDao;
import com.app.buzz.stats.entity.StatsItem;
import com.app.buzz.stats.service.StatsItemService;
import com.app.service.impl.BaseServiceImpl;

/***
 *
 * ServiceImpl - 统计项目
 *
 * @author APP TEAM
 * @version 1.0
 */
@Service("statsItemService")
public class StatsItemServiceImpl extends BaseServiceImpl<StatsItem, Long> implements StatsItemService {

	@Resource
	private StatsItemDao statsItemDao;

	@Resource
	public void setBaseDao(StatsItemDao statsItemDao) {
		super.setBaseDao(statsItemDao);
	}

}
