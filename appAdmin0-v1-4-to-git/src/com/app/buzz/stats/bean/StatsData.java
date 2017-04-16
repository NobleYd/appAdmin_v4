package com.app.buzz.stats.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @title 用于统计查询的单条数据
 * @desc 用于统计查询的单条数据，相比同名entity去掉了不必要的，也增加了一些特殊逻辑。
 * @author nobleyd
 *
 */
public class StatsData {

	/** 此统计数据的时间 */
	@JsonProperty
	private String dataTime;

	/** 此统计数据的值 */
	@JsonProperty
	private String value;

	/** 根据 时间-值 构造一个统计数据 */
	public StatsData(Object dataTime, Object value) {
		super();
		this.dataTime = dataTime.toString();
		this.value = value.toString();
	}
	
	/** 根据 时间-值 构造一个统计数据 */
	public StatsData(Long dataTime, Object value) {
		super();
		this.dataTime = dataTime.toString();
		this.value = value.toString();
	}

	/** getters and setters */
	public String getDataTime() {
		switch (dataTime.length()) {
		// yyyyMMddHHmmss(14位)
		case 14:
			dataTime = dataTime.substring(0, 4) + "年" + //
					dataTime.substring(4, 6) + "月" + //
					dataTime.substring(6, 8) + "日 " + //
					dataTime.substring(8, 10) + "时" + //
					dataTime.substring(10, 12) + "分" + //
					dataTime.substring(12, 14) + "秒";
			break;
		// yyyyMMddHHmm(12位)
		case 12:
			dataTime = dataTime.substring(0, 4) + "年" + //
					dataTime.substring(4, 6) + "月" + //
					dataTime.substring(6, 8) + "日 " + //
					dataTime.substring(8, 10) + "时" + //
					dataTime.substring(10, 12) + "分";
			break;
		// yyyyMMddHH(10位)
		case 10:
			dataTime = dataTime.substring(0, 4) + "年" + //
					dataTime.substring(4, 6) + "月" + //
					dataTime.substring(6, 8) + "日 " + //
					dataTime.substring(8, 10) + "时";
			break;
		// yyyyMMdd(8位)
		case 8:
			dataTime = dataTime.substring(0, 4) + "年" + //
					dataTime.substring(4, 6) + "月" + //
					dataTime.substring(6, 8) + "日";
			break;
		// yyyyMM(6位)
		case 6:
			dataTime = dataTime.substring(0, 4) + "年" + //
					dataTime.substring(4, 6) + "月";
			break;
		// yyyy(4位)
		case 4:
			dataTime = dataTime.substring(0, 4) + "年";
			break;
		default:
			break;
		}
		
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
