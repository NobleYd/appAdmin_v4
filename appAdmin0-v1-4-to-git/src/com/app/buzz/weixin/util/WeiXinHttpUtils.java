/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.constants.WeiXinURL;
import com.app.util.HttpUtils;
import com.app.util.JacksonUtils;

/**
 * Utils - HTTP
 * 
 * @author APP TEAM
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public final class WeiXinHttpUtils {

	/** 下面几个方法是直接调用HttpUtils实现 */

	/**
	 * 连接Map键值对
	 * 
	 * @param map
	 *            Map
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @param separator
	 *            连接符
	 * @param ignoreEmptyValue
	 *            忽略空值
	 * @param ignoreKeys
	 *            忽略Key
	 * @return 字符串
	 */
	public static String joinKeyValue(Map<String, Object> map, String prefix, String suffix, String separator, boolean ignoreEmptyValue, String... ignoreKeys) {
		return HttpUtils.joinKeyValue(map, prefix, suffix, separator, ignoreEmptyValue, ignoreKeys);
	}

	/**
	 * 连接Map值
	 * 
	 * @param map
	 *            Map
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @param separator
	 *            连接符
	 * @param ignoreEmptyValue
	 *            忽略空值
	 * @param ignoreKeys
	 *            忽略Key
	 * @return 字符串
	 */
	public static String joinValue(Map<String, Object> map, String prefix, String suffix, String separator, boolean ignoreEmptyValue, String... ignoreKeys) {
		List<String> list = new ArrayList<String>();
		if (map != null) {
			for (Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = ConvertUtils.convert(entry.getValue());
				if (StringUtils.isNotEmpty(key) && !ArrayUtils.contains(ignoreKeys, key) && (!ignoreEmptyValue || StringUtils.isNotEmpty(value))) {
					list.add(value != null ? value : "");
				}
			}
		}
		return (prefix != null ? prefix : "") + StringUtils.join(list, separator) + (suffix != null ? suffix : "");
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 *            URL
	 * @param parameterMap
	 *            请求参数
	 * @return 返回结果
	 */
	public static String get(String url, Map<String, Object> parameterMap) {
		return HttpUtils.get(url, parameterMap);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            URL
	 * @param parameterMap
	 *            请求参数
	 * @return 返回结果
	 */
	public static String post(String url, Map<String, Object> parameterMap) {
		return HttpUtils.post(url, parameterMap);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            URL
	 * @param parameterMap
	 *            请求参数
	 * @param fileParameterMap
	 *            文件参数
	 * @return 返回结果
	 */
	public static String post(String url, Map<String, Object> parameterMap, Map<String, File> fileParameterMap) {
		return HttpUtils.post(url, parameterMap, fileParameterMap);
	}

	// ------------------------------------------------------------
	/** 下面是针对微信提供的更方便的方法{ 使用WeiXinURL枚举指定url } */

	/**
	 * GET请求
	 * 
	 * @param url
	 *            URL
	 * @param weiXinConfig
	 *            微信配置
	 * @param wxUrlParams
	 *            微信URL中占位符参数
	 * @param parameterMap
	 *            请求参数
	 * @return 返回结果
	 */
	public static String get(WeiXinURL url, WeiXinConfig weiXinConfig, Map<String, String> wxUrlParams, Map<String, Object> parameterMap) {
		return HttpUtils.get(url.getUrl(weiXinConfig, wxUrlParams), parameterMap);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            URL
	 * @param weiXinConfig
	 *            微信配置
	 * @param wxUrlParams
	 *            微信URL中占位符参数
	 * @param parameterMap
	 *            请求参数
	 * @return 返回结果
	 */
	public static String post(WeiXinURL url, WeiXinConfig weiXinConfig, Map<String, String> wxUrlParams, Map<String, Object> parameterMap) {
		return HttpUtils.post(url.getUrl(weiXinConfig, wxUrlParams), parameterMap);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            URL
	 * @param weiXinConfig
	 *            微信配置
	 * @param wxUrlParams
	 *            微信URL中占位符参数
	 * @param parameterMap
	 *            请求参数
	 * @param fileParameterMap
	 *            文件参数
	 * @return 返回结果
	 */
	public static String post(WeiXinURL url, WeiXinConfig weiXinConfig, Map<String, String> wxUrlParams, Map<String, Object> parameterMap, Map<String, File> fileParameterMap) {
		return HttpUtils.post(url.getUrl(weiXinConfig, wxUrlParams), parameterMap, fileParameterMap);
	}

	// ------------------------------------------------------------
	/** 下面是针对微信上面方法提供的返回Map数据的方法{ 将返回的json数据直接转换为Map然后返回 } */

	/**
	 * GET请求
	 * 
	 * @param url
	 *            URL
	 * @param weiXinConfig
	 *            微信配置
	 * @param wxUrlParams
	 *            微信URL中占位符参数
	 * @param parameterMap
	 *            请求参数
	 * @return 返回结果
	 */
	public static Map<String, Object> getJson(WeiXinURL url, WeiXinConfig weiXinConfig, Map<String, String> wxUrlParams, Map<String, Object> parameterMap) {
		String jsonString = HttpUtils.get(url.getUrl(weiXinConfig, wxUrlParams), parameterMap);
		return JacksonUtils.toObject(jsonString, HashMap.class);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            URL
	 * @param weiXinConfig
	 *            微信配置
	 * @param wxUrlParams
	 *            微信URL中占位符参数
	 * @param parameterMap
	 *            请求参数
	 * @return 返回结果
	 */

	public static Map<String, Object> postForJsonWithStringEntity(WeiXinURL url, WeiXinConfig weiXinConfig, Map<String, String> wxUrlParams, Map<String, Object> parameterMap) {
		String jsonString = HttpUtils.post(url.getUrl(weiXinConfig, wxUrlParams), parameterMap);
		return JacksonUtils.toObject(jsonString, HashMap.class);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            URL
	 * @param weiXinConfig
	 *            微信配置
	 * @param wxUrlParams
	 *            微信URL中占位符参数
	 * @param parameterMap
	 *            请求参数
	 * @param fileParameterMap
	 *            文件参数
	 * @return 返回结果
	 */
	public static Map<String, Object> postForJson(WeiXinURL url, WeiXinConfig weiXinConfig, Map<String, String> wxUrlParams, Map<String, Object> parameterMap, Map<String, File> fileParameterMap) {
		String jsonString = HttpUtils.post(url.getUrl(weiXinConfig, wxUrlParams), parameterMap, fileParameterMap);
		return JacksonUtils.toObject(jsonString, HashMap.class);
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *            URL
	 * @param weiXinConfig
	 *            微信配置
	 * @param wxUrlParams
	 *            微信URL中占位符参数
	 * @param stringEntityContent
	 *            post内容
	 * @return 返回结果
	 */
	public static Map<String, Object> postForJson(WeiXinURL url, WeiXinConfig weiXinConfig, Map<String, String> wxUrlParams, String stringEntityContent) {
		String jsonString = HttpUtils.post(url.getUrl(weiXinConfig, wxUrlParams), stringEntityContent);
		return JacksonUtils.toObject(jsonString, HashMap.class);
	}
}