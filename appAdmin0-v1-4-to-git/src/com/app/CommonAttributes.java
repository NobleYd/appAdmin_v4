/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app;

/**
 * 公共参数
 * 
 * @author APP TEAM
 * @version 1.0
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] {
			// 年
			"yyyy",
			// 年月
			"yyyy-MM", "yyyyMM", "yyyy/MM",
			// 年月
			"yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd",
			// 年月日 时
			"yyyy-MM-dd HH", "yyyyMMddHH", "yyyy/MM/dd HH",
			// 年月日 时分
			"yyyy-MM-dd HH:mm", "yyyyMMddHHmm", "yyyy/MM/dd HH:mm",
			// 年月日 时分秒
			"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** app.xml文件路径 */
	public static final String APP_XML_PATH = "/app.xml";

	/** app.properties文件路径 */
	public static final String APP_PROPERTIES_PATH = "/app.properties";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}