package com.app.buzz.stats.bean;

/**
 * @title 统计值类型
 * @desc 统计值类型
 * @author nobleyd
 *
 */
public enum StatsValueType {
	/** 数值 */
	number("数值"),
	/** 文本(注意文本类型只可以进行last统计方法。) */
	text("文本");

	private String label;

	private StatsValueType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
