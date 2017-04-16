/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.stats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.app.buzz.stats.bean.StatsCycle;
import com.app.buzz.stats.bean.StatsMethod;
import com.app.buzz.stats.bean.StatsValueType;
import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/***
 *
 * 实体 - 统计项目
 *
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "stats_item",uniqueConstraints = { @UniqueConstraint(columnNames = { "partnerId", "title" }) })
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "stats_item_seq")
public class StatsItem extends BaseEntity {

	private static final long serialVersionUID = -8554709677467099086L;

	/** 创建此统计项的用户ID */
	@JsonProperty
	@Column(nullable = false)
	private Long partnerId;

	/** 统计项标题(英文) */
	@JsonProperty
	@Column(nullable = false)
	private String title;

	/** 显示标题 */
	@JsonProperty
	@Column(nullable = false)
	private String showTitle;

	/** 统计值类型 */
	@JsonProperty
	@Column(nullable = false)
	private StatsValueType statsValueType = StatsValueType.number;

	/** 统计周期 */
	@JsonProperty
	@Column(nullable = false)
	private StatsCycle statsCycle = StatsCycle.second;

	/** 统计方法 */
	@JsonProperty
	@Column(nullable = false)
	private StatsMethod statsMethod = StatsMethod.sum;

	/** partnerId get */
	public Long getPartnerId() {
		return partnerId;
	}

	/** partnerId set */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/** title get */
	public String getTitle() {
		return title;
	}

	/** title set */
	public void setTitle(String title) {
		this.title = title;
	}

	/** showTitle get */
	public String getShowTitle() {
		return showTitle;
	}

	/** showTitle set */
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	/** statsValueType get */
	public StatsValueType getStatsValueType() {
		return statsValueType;
	}

	/** statsValueType set */
	public void setStatsValueType(StatsValueType statsValueType) {
		this.statsValueType = statsValueType;
	}

	/** statsCycle get */
	public StatsCycle getStatsCycle() {
		return statsCycle;
	}

	/** statsCycle set */
	public void setStatsCycle(StatsCycle statsCycle) {
		this.statsCycle = statsCycle;
	}

	/** statsMethod get */
	public StatsMethod getStatsMethod() {
		return statsMethod;
	}

	/** statsMethod set */
	public void setStatsMethod(StatsMethod statsMethod) {
		this.statsMethod = statsMethod;
	}

}
