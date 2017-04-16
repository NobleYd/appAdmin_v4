package com.app.buzz.stats.bean;

/**
 * @title 统计方法
 * @desc 统计方法
 * @author nobleyd
 *
 */
public enum StatsMethod {

	/** 在统计周期内的多次数据记录中，后来记录覆盖之前记录的值。 */
	last("求最新值"),
	/** 在统计周期内的多次数据记录中，后来记录累加到之前的记录上去。 */
	sum("求和"),
	/** 在统计周期内的多次数据记录中，后来记录只有小于之前记录才会覆盖之前记录的值。 */
	min("求最小值"),
	/** 在统计周期内的多次数据记录中，后来记录只有大于之前记录才会覆盖之前记录的值。 */
	max("求最大值"),
	/** 在统计周期内的多次数据会被计算平均值。 */
	avg("求平均值");

	private String label;

	private StatsMethod(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
