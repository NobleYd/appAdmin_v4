/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.buzz.stats.dao.StatsItemDao;
import com.app.buzz.stats.entity.StatsItem;
import com.app.dao.impl.BaseDaoImpl;

/***
 *
 * DaoImpl - 统计项目
 *
 * @author APP TEAM
 * @version 1.0
 */
@Repository("statsItemDao")
public class StatsItemDaoImpl extends BaseDaoImpl<StatsItem, Long> implements StatsItemDao {
}
