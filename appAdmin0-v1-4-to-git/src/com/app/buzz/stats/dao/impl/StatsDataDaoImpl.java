/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.app.buzz.stats.bean.StatsCycle;
import com.app.buzz.stats.dao.StatsDataDao;
import com.app.buzz.stats.entity.StatsData;
import com.app.dao.impl.BaseDaoImpl;

/***
 *
 * DaoImpl - 统计项数据
 *
 * @author APP TEAM
 * @version 1.0
 */
@Repository("statsDataDao")
public class StatsDataDaoImpl extends BaseDaoImpl<StatsData, Long> implements StatsDataDao {

	private static final String jpql_find_by_stats_item_id_data_time = "select statsData from StatsData statsData where statsData.statsItem.id = ?1 and statsData.dataTime = ?2";

	@Override
	public StatsData findByStatsItemIdAndDataTime(Long statsItemId, Long dataTime) {
		List<StatsData> list = entityManager.createQuery(jpql_find_by_stats_item_id_data_time, StatsData.class)//
				.setParameter(1, statsItemId)//
				.setParameter(2, dataTime)//
				.getResultList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/** 采集数据 */
	private static final String jpql_update_text_data = "update StatsData statsData set statsData.textValue = ?1 where statsData.statsItem.id = ?3 and statsData.dataTime = ?3";
	private static final String jpql_update_number_data = "update StatsData statsData set statsData.numberValue = ?1 where statsData.statsItem.id = ?2 and statsData.dataTime = ?3";
	private static final String jpql_update_sum_number_data = "update StatsData statsData set statsData.numberValue = statsData.numberValue + ?1 where statsData.statsItem.id = ?2 and statsData.dataTime = ?3";
	private static final String jpql_update_max_number_data1 = "update StatsData statsData set statsData.numberValue = ?1 where statsData.statsItem.id = ?2 and statsData.dataTime = ?3 and statsData.numberValue < ?1";
	private static final String jpql_update_min_number_data1 = "update StatsData statsData set statsData.numberValue = ?1 where statsData.statsItem.id = ?2 and statsData.dataTime = ?3 and statsData.numberValue > ?1";
	private static final String jpql_update_avg_number_data1 = "update StatsData statsData set statsData.numberValue = ( ( statsData.numberValue * statsData.statsCount + ?1 ) / (statsData.statsCount + 1) ) , statsData.statsCount = statsData.statsCount + 1 where statsData.statsItem.id = ?2 and statsData.dataTime = ?3";

	@Override
	public int updateData(Long statsItemId, Long dataTime, BigDecimal numberValue) {
		return entityManager.createQuery(jpql_update_number_data)//
				.setParameter(1, numberValue)//
				.setParameter(2, statsItemId)//
				.setParameter(3, dataTime)//
				.executeUpdate();
	}

	@Override
	public int updateData(Long statsItemId, Long dataTime, String textValue) {
		return entityManager.createQuery(jpql_update_text_data)//
				.setParameter(1, textValue)//
				.setParameter(2, statsItemId)//
				.setParameter(3, dataTime)//
				.executeUpdate();
	}

	@Override
	public int sumData(Long statsItemId, Long dataTime, BigDecimal numberValue) {
		return entityManager.createQuery(jpql_update_sum_number_data)//
				.setParameter(1, numberValue)//
				.setParameter(2, statsItemId)//
				.setParameter(3, dataTime)//
				.executeUpdate();
	}

	@Override
	public int maxData(Long statsItemId, Long dataTime, BigDecimal numberValue) {
		return entityManager.createQuery(jpql_update_max_number_data1)//
				.setParameter(1, numberValue)//
				.setParameter(2, statsItemId)//
				.setParameter(3, dataTime)//
				.executeUpdate();
	}

	@Override
	public int minData(Long statsItemId, Long dataTime, BigDecimal numberValue) {
		return entityManager.createQuery(jpql_update_min_number_data1)//
				.setParameter(1, numberValue)//
				.setParameter(2, statsItemId)//
				.setParameter(3, dataTime)//
				.executeUpdate();
	}

	@Override
	public int avgData(Long statsItemId, Long dataTime, BigDecimal numberValue) {
		return entityManager.createQuery(jpql_update_avg_number_data1)//
				.setParameter(1, numberValue)//
				.setParameter(2, statsItemId)//
				.setParameter(3, dataTime)//
				.executeUpdate();
	}

	/** 统计数据 */

	/** statsData整除divNumber(目前方法比较复杂,没发现jpql中有整除这个东西) */
	private static final String statsData_dataTime_v1 = "( statsData.dataTime - MOD( statsData.dataTime,(:divNumber) ) ) / (:divNumber)";
	// private static final String statsData2_dataTime_v1 = "( statsData2.dataTime - MOD( statsData2.dataTime,(:divNumber) ) ) / (:divNumber)";
	private static final String statsData_dataTime_v2 = "cast ( ( statsData.dataTime / :divNumber - 0.5 ) as int )";
	private static final String statsData2_dataTime_v2 = "cast ( ( statsData2.dataTime / :divNumber - 0.5 ) as int )";

	/** 文本型数据使用这个select from语句 */
	private static final String jpql_select_text_data_clause = //
			"select new com.app.buzz.stats.bean.StatsData( " + //
					"( " + statsData_dataTime_v1 + " ), " + // divNumber为参数
					"_StatsMethod( statsData.textValue ) " + // _StatsMethod需要被替换
					") " + //
					"from StatsData statsData ";

	/** 数值型数据使用这个select from语句 */
	private static final String jpql_select_number_data_clause = //
			"select new com.app.buzz.stats.bean.StatsData( " + //
					"( " + statsData_dataTime_v1 + " ), " + // divNumber为参数
					"_StatsMethod( statsData.numberValue ) " + // _StatsMethod需要被替换
					") " + //
					"from StatsData statsData ";

	/** where子句(statsItem.id约束) */
	private static final String jpql_where_clause = "where statsData.statsItem.id = (:statsItemId) ";

	/** and子句(start约束) */
	private static final String jpql_and_start_time_clause_alias1 = "and statsData.dataTime >= (:startTime) ";

	/** and子句(end约束) */
	private static final String jpql_and_end_time_clause_alias1 = "and statsData.dataTime <= (:endTime) ";

	/** and子句(start约束) */
	private static final String jpql_and_start_time_clause_alias2 = "and statsData2.dataTime >= (:startTime) ";

	/** and子句(end约束) */
	private static final String jpql_and_end_time_clause_alias2 = "and statsData2.dataTime <= (:endTime) ";

	/** and子句(最新值统计时需要) */
	private static final String jpql_and_in_last_clause = "and statsData.dataTime in( " + //
			"select max(statsData2.dataTime) from StatsData statsData2 " + //
			"where statsData2.statsItem.id = (:statsItemId) " + //
			"_StartTimeClause _EndTimeClause " + // _StartTimeClause与_EndTimeClause需被替换
			"group by ( " + statsData2_dataTime_v2 + " ) " + //
			" )";

	private static final String group_by_clause = "group by ( " + statsData_dataTime_v1 + " ) ";

	// 下面这个有问题，因为cast是四舍五入。
	// "group by ( cast ( statsData.dataTime / :divNumber as int ) ) ";
	// 下面这个可以，但一旦认为效率不高。
	// "group by ( ( statsData.dataTime - MOD( statsData.dataTime,(:divNumber) ) ) / (:divNumber) ) ";

	@SuppressWarnings("unchecked")
	@Override
	public List<com.app.buzz.stats.bean.StatsData> findLastNumberData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		String jpql = jpql_select_number_data_clause.replace("_StatsMethod", "") //
				+ jpql_where_clause //
				+ (startDate != null ? jpql_and_start_time_clause_alias1 : "")//
				+ (endDate != null ? jpql_and_end_time_clause_alias1 : "")//
				+ jpql_and_in_last_clause.replace("_StartTimeClause", (startDate != null ? jpql_and_start_time_clause_alias2 : ""))//
						.replace("_EndTimeClause", (endDate != null ? jpql_and_end_time_clause_alias2 : ""))//
				+ group_by_clause;
		Query query = entityManager.createQuery(jpql);
		query.setParameter("divNumber", statsCycle.divNumber()).setParameter("statsItemId", statsItemId);
		if (startDate != null)
			query.setParameter("startTime", statsCycle.date2Long(startDate));
		if (endDate != null)
			query.setParameter("endTime", statsCycle.date2Long(endDate));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.app.buzz.stats.bean.StatsData> findLastTextData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		String jpql = jpql_select_text_data_clause.replace("_StatsMethod", "") //
				+ jpql_where_clause //
				+ (startDate != null ? jpql_and_start_time_clause_alias1 : "")//
				+ (endDate != null ? jpql_and_end_time_clause_alias1 : "")//
				+ jpql_and_in_last_clause.replace("_StartTimeClause", (startDate != null ? jpql_and_start_time_clause_alias2 : ""))//
						.replace("_EndTimeClause", (endDate != null ? jpql_and_end_time_clause_alias2 : ""))//
				+ group_by_clause;
		Query query = entityManager.createQuery(jpql);
		query.setParameter("divNumber", statsCycle.divNumber()).setParameter("statsItemId", statsItemId);
		if (startDate != null)
			query.setParameter("startTime", statsCycle.date2Long(startDate));
		if (endDate != null)
			query.setParameter("endTime", statsCycle.date2Long(endDate));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.app.buzz.stats.bean.StatsData> findSumData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		String jpql = jpql_select_number_data_clause.replace("_StatsMethod", "sum") //
				+ jpql_where_clause //
				+ (startDate != null ? jpql_and_start_time_clause_alias1 : "")//
				+ (endDate != null ? jpql_and_end_time_clause_alias1 : "")//
				+ group_by_clause;

		Query query = entityManager.createQuery(jpql);
		query.setParameter("divNumber", statsCycle.divNumber()).setParameter("statsItemId", statsItemId);
		if (startDate != null)
			query.setParameter("startTime", statsCycle.date2Long(startDate));
		if (endDate != null)
			query.setParameter("endTime", statsCycle.date2Long(endDate));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.app.buzz.stats.bean.StatsData> findMinData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		String jpql = jpql_select_number_data_clause.replace("_StatsMethod", "min") //
				+ jpql_where_clause //
				+ (startDate != null ? jpql_and_start_time_clause_alias1 : "")//
				+ (endDate != null ? jpql_and_end_time_clause_alias1 : "")//
				+ group_by_clause;
		Query query = entityManager.createQuery(jpql);
		query.setParameter("divNumber", statsCycle.divNumber()).setParameter("statsItemId", statsItemId);
		if (startDate != null)
			query.setParameter("startTime", statsCycle.date2Long(startDate));
		if (endDate != null)
			query.setParameter("endTime", statsCycle.date2Long(endDate));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.app.buzz.stats.bean.StatsData> findMaxData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		String jpql = jpql_select_number_data_clause.replace("_StatsMethod", "max") //
				+ jpql_where_clause //
				+ (startDate != null ? jpql_and_start_time_clause_alias1 : "")//
				+ (endDate != null ? jpql_and_end_time_clause_alias1 : "")//
				+ group_by_clause;
		Query query = entityManager.createQuery(jpql);
		query.setParameter("divNumber", statsCycle.divNumber()).setParameter("statsItemId", statsItemId);
		if (startDate != null)
			query.setParameter("startTime", statsCycle.date2Long(startDate));
		if (endDate != null)
			query.setParameter("endTime", statsCycle.date2Long(endDate));
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.app.buzz.stats.bean.StatsData> findAverageData(Long statsItemId, StatsCycle statsCycle, Date startDate, Date endDate) {
		String jpql = jpql_select_number_data_clause.replace("_StatsMethod", "avg") //
				+ jpql_where_clause //
				+ (startDate != null ? jpql_and_start_time_clause_alias1 : "")//
				+ (endDate != null ? jpql_and_end_time_clause_alias1 : "")//
				+ group_by_clause;
		Query query = entityManager.createQuery(jpql);
		query.setParameter("divNumber", statsCycle.divNumber()).setParameter("statsItemId", statsItemId);
		if (startDate != null)
			query.setParameter("startTime", statsCycle.date2Long(startDate));
		if (endDate != null)
			query.setParameter("endTime", statsCycle.date2Long(endDate));
		return query.getResultList();
	}

}
