package com.app.buzz.stats.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @title 统计周期
 * @desc 统计周期
 * @author nobleyd
 *
 */
public enum StatsCycle {
	/** 年 */
	year("年"),
	/** 余额 */
	month("月"),
	/** 天 */
	day("天"),
	/** 时 */
	hour("时"),
	/** 分 */
	minute("分"),
	/** 秒 */
	second("秒");

	private String label;

	private StatsCycle(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/** 日期格式格式化 */
	private static final SimpleDateFormat sdf_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat sdf_yyyyMMddHHmm00 = new SimpleDateFormat("yyyyMMddHHmm00");
	private static final SimpleDateFormat sdf_yyyyMMddHH0000 = new SimpleDateFormat("yyyyMMddHH0000");
	private static final SimpleDateFormat sdf_yyyyMMdd000000 = new SimpleDateFormat("yyyyMMdd000000");
	private static final SimpleDateFormat sdf_yyyyMM00000000 = new SimpleDateFormat("yyyyMM00000000");
	private static final SimpleDateFormat sdf_yyyy0000000000 = new SimpleDateFormat("yyyy0000000000");

	/** 根据此统计周期将某个Date转化为Long,低位补0 */
	public Long date2Long(Date date) {
		Long ret = null;
		String dateStr = null;

		switch (this) {
		case year:
			dateStr = sdf_yyyy0000000000.format(date);
			ret = Long.parseLong(dateStr);
			break;
		case month:
			dateStr = sdf_yyyyMM00000000.format(date);
			ret = Long.parseLong(dateStr);
			break;
		case day:
			dateStr = sdf_yyyyMMdd000000.format(date);
			ret = Long.parseLong(dateStr);
			break;
		case hour:
			dateStr = sdf_yyyyMMddHH0000.format(date);
			ret = Long.parseLong(dateStr);
			break;
		case minute:
			dateStr = sdf_yyyyMMddHHmm00.format(date);
			ret = Long.parseLong(dateStr);
			break;
		case second:
			dateStr = sdf_yyyyMMddHHmmss.format(date);
			ret = Long.parseLong(dateStr);
			break;
		default:
			break;
		}
		System.out.println(ret);
		return ret;
	}

	/** 根据此统计周期返回一个将某个yyyyMMddHHmmss格式Long数据去除低位0需要div的数值 */
	public Long divNumber() {
		Long ret = null;

		switch (this) {
		case year:
			ret = 10000000000L;
			break;
		case month:
			ret = 100000000L;
			break;
		case day:
			ret = 1000000L;
			break;
		case hour:
			ret = 10000L;
			break;
		case minute:
			ret = 100L;
			break;
		case second:
			ret = 1L;
			break;
		default:
			break;
		}

		return ret;
	}

}
