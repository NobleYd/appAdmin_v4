/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.entity;

import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Entity - 历史实体基类{可以记录实体历史}
 * 
 * @author APP TEAM
 * @version 1.0
 */
@MappedSuperclass
public abstract class HistoryEntity<T extends HistoryEntity<T>> extends BaseEntity {

	private static final long serialVersionUID = -6893774529503128737L;

	/** 历史实体{关联维护端} */
	private T historyEntity;

	/** 下一个版本实体{关联被维护端} */
	private T nextEntity;

	/** 是否过期{true表示是历史实体，false表示是当前最新实体} */
	private Boolean isExpired = false;

	/***
	 * @return 获得当前实体的历史实体列表（包含当前实体） 按照先后次序
	 */
	@Transient
	public ArrayList<HistoryEntity<T>> getHistorys() {
		ArrayList<HistoryEntity<T>> historys = new ArrayList<HistoryEntity<T>>();
		HistoryEntity<T> history = this;
		while (history != null) {
			historys.add(history);
			history = history.getHistoryEntity();
		}
		// 将次序反转
		Collections.reverse(historys);
		return historys;
	}

	@OneToOne(optional = true)
	public T getHistoryEntity() {
		return historyEntity;
	}

	public void setHistoryEntity(T historyEntity) {
		this.historyEntity = historyEntity;
	}

	@OneToOne(mappedBy = "historyEntity", optional = true)
	public T getNextEntity() {
		return nextEntity;
	}

	public void setNextEntity(T nextEntity) {
		this.nextEntity = nextEntity;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

}
