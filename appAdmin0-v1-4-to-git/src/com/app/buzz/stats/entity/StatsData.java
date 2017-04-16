/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.buzz.stats.bean.StatsMethod;
import com.app.entity.BaseEntity;

/***
 *
 * 实体 - 统计项数据
 *
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "stats_data")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "stats_data_seq")
public class StatsData extends BaseEntity {

	private static final long serialVersionUID = -8064421115106272267L;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	/** 统计项 */
	private StatsItem statsItem;

	/** 数据时间 yyyyMMddHHmmss */
	private Long dataTime = Long.parseLong(sdf.format(new Date()));

	/** 数字值 */
	private BigDecimal numberValue = new BigDecimal("0.0");

	/** 文本值 */
	private String textValue = "默认";

	/** 统计数据量 */
	private Long statsCount = 1L;

	/** constructors */
	public StatsData() {
		super();
	}

	public StatsData(StatsItem statsItem, Long dataTime, BigDecimal numberValue, String textValue) {
		super();
		this.statsItem = statsItem;
		this.dataTime = dataTime;
		this.numberValue = numberValue;
		this.textValue = textValue;
		// 只有统计方法为平均值情况statsCount才会被记录。
		this.statsCount = statsItem.getStatsMethod() == StatsMethod.avg ? 1L : null;
	}

	/** statsItem get */
	@ManyToOne
	public StatsItem getStatsItem() {
		return statsItem;
	}

	/** statsItem set */
	public void setStatsItem(StatsItem statsItem) {
		this.statsItem = statsItem;
	}

	/** dataTime get */
	public Long getDataTime() {
		return dataTime;
	}

	/** dataTime set */
	public void setDataTime(Long dataTime) {
		this.dataTime = dataTime;
	}

	/** numberValue get */
	public BigDecimal getNumberValue() {
		return numberValue;
	}

	/** numberValue set */
	public void setNumberValue(BigDecimal numberValue) {
		this.numberValue = numberValue;
	}

	/** textValue get */
	public String getTextValue() {
		return textValue;
	}

	/** textValue set */
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	/** statsCount get */
	public Long getStatsCount() {
		return statsCount;
	}

	/** statsCount set */
	public void setStatsCount(Long statsCount) {
		this.statsCount = statsCount;
	}

}
