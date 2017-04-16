/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.stats.bean.StatsCycle;
import com.app.buzz.stats.bean.StatsMethod;
import com.app.buzz.stats.bean.StatsValueType;
import com.app.buzz.stats.dao.StatsDataDao;
import com.app.buzz.stats.dao.StatsItemDao;
import com.app.buzz.stats.entity.StatsData;
import com.app.buzz.stats.entity.StatsItem;
import com.app.buzz.stats.service.StatsDataService;
import com.app.service.impl.BaseServiceImpl;

/***
 *
 * ServiceImpl - 统计项数据
 *
 * @author APP TEAM
 * @version 1.0
 */
@Service("statsDataService")
public class StatsDataServiceImpl extends BaseServiceImpl<StatsData, Long> implements StatsDataService {

	@Resource
	private StatsItemDao statsItemDao;

	@Resource
	private StatsDataDao statsDataDao;

	@Resource
	public void setBaseDao(StatsDataDao statsDataDao) {
		super.setBaseDao(statsDataDao);
	}

	@Override
	public StatsData findByStatsItemIdAndDataTime(Long statsItemId, Long dataTime) {
		return statsDataDao.findByStatsItemIdAndDataTime(statsItemId, dataTime);
	}

	/** 采集数据 */
	@Override
	public int acceptData(Long statsItemId, Long dataTime, BigDecimal numberValue, String textValue) {
		StatsItem statsItem = null;
		try {
			statsItem = statsItemDao.find(statsItemId);
		} catch (Exception e) {
			e.printStackTrace();
			statsItem = null;
		}
		if (statsItem == null)
			return -1;
		if (StatsValueType.number == statsItem.getStatsValueType()) {
			int updateNumber = 0;
			if (StatsMethod.last == statsItem.getStatsMethod()) {
				updateNumber = statsDataDao.updateData(statsItemId, dataTime, numberValue);
			} else if (StatsMethod.sum == statsItem.getStatsMethod()) {
				updateNumber = statsDataDao.sumData(statsItemId, dataTime, numberValue);
			} else if (StatsMethod.max == statsItem.getStatsMethod()) {
				StatsData statsData = statsDataDao.findByStatsItemIdAndDataTime(statsItemId, dataTime);
				if (statsData != null) {
					statsDataDao.maxData(statsItemId, dataTime, numberValue);
					updateNumber = 1;
				}
			} else if (StatsMethod.min == statsItem.getStatsMethod()) {
				StatsData statsData = statsDataDao.findByStatsItemIdAndDataTime(statsItemId, dataTime);
				if (statsData != null) {
					statsDataDao.minData(statsItemId, dataTime, numberValue);
					updateNumber = 1;
				}

			} else if (StatsMethod.avg == statsItem.getStatsMethod()) {
				updateNumber = statsDataDao.avgData(statsItemId, dataTime, numberValue);
			}
			if (updateNumber <= 0) {
				super.save(new StatsData(statsItem, dataTime, numberValue, null));
				return 2;
			} else {
				return 1;
			}
		} else {
			// statsItem.getStatsValueType() == StatsValueType.text
			int updateNumber = statsDataDao.updateData(statsItemId, dataTime, textValue);
			if (updateNumber <= 0) {
				super.save(new StatsData(statsItem, dataTime, null, textValue));
				return 2;
			} else {
				return 1;
			}
		}
	}

	/** 统计数据 */
	@Override
	public List<com.app.buzz.stats.bean.StatsData> findLastNumberData(Long StatsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		return statsDataDao.findLastNumberData(StatsItemId, statsCycle, startDate, endDate);
	}

	@Override
	public List<com.app.buzz.stats.bean.StatsData> findLastTextData(Long StatsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		return statsDataDao.findLastTextData(StatsItemId, statsCycle, startDate, endDate);
	}

	@Override
	public List<com.app.buzz.stats.bean.StatsData> findSumData(Long StatsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		return statsDataDao.findSumData(StatsItemId, statsCycle, startDate, endDate);
	}

	@Override
	public List<com.app.buzz.stats.bean.StatsData> findMinData(Long StatsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		return statsDataDao.findMinData(StatsItemId, statsCycle, startDate, endDate);
	}

	@Override
	public List<com.app.buzz.stats.bean.StatsData> findMaxData(Long StatsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		return statsDataDao.findMaxData(StatsItemId, statsCycle, startDate, endDate);
	}

	@Override
	public List<com.app.buzz.stats.bean.StatsData> findAverageData(Long StatsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		return statsDataDao.findAverageData(StatsItemId, statsCycle, startDate, endDate);
	}

}
