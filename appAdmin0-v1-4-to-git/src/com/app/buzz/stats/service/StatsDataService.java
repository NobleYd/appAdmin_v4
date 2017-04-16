/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.app.buzz.stats.bean.StatsCycle;
import com.app.buzz.stats.entity.StatsData;
import com.app.service.BaseService;

/***
 *
 * Service - 统计项数据
 *
 * @author APP TEAM
 * @version 1.0
 */
public interface StatsDataService extends BaseService<StatsData, Long> {

	/** 根据统计项ID和数据时间查询某条数据记录 */
	public StatsData findByStatsItemIdAndDataTime(Long statsItemId, Long dataTime);

	/**
	 * 采集数据:某个统计项的某个时间点的数据
	 * 
	 * @param StatsItemId
	 *            统计项Id
	 * @param dataTime
	 *            数据时间
	 * @param numberValue
	 *            数值类型的值
	 */
	public int acceptData(Long statsItemId, Long dataTime, BigDecimal numberValue, String textValue);

	/** 统计数据 */

	/**
	 * 按照指定周期统计某个统计项在某段时间范围内的数据
	 * 
	 * @param statsItemId
	 *            统计项Id
	 * @param statsCycle
	 *            统计周期
	 * @param startDate
	 *            开始时间范围
	 * @param endDate
	 *            结束时间范围
	 * @return 返回List,每个元素代表一个时间的数据
	 */
	public List<com.app.buzz.stats.bean.StatsData> findLastNumberData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate);

	public List<com.app.buzz.stats.bean.StatsData> findLastTextData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate);

	public List<com.app.buzz.stats.bean.StatsData> findSumData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate);

	public List<com.app.buzz.stats.bean.StatsData> findMinData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate);

	public List<com.app.buzz.stats.bean.StatsData> findMaxData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate);

	public List<com.app.buzz.stats.bean.StatsData> findAverageData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate);

}
