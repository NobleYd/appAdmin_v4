/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service;

import com.app.entity.PluginConfig;

/**
 * Service - 插件配置
 * 
 * @author APP TEAM
 * @version 1.0
 */
public interface PluginConfigService extends BaseService<PluginConfig, Long> {

	/**
	 * 判断插件ID是否存在
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return 插件ID是否存在
	 */
	boolean pluginIdExists(String pluginId);

	/**
	 * 根据插件ID查找插件配置
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return 插件配置，若不存在则返回null
	 */
	PluginConfig findByPluginId(String pluginId);

}