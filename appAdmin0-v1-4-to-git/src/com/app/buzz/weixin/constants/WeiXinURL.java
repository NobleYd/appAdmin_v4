/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.constants;

import java.util.Map;

import com.app.buzz.weixin.config.WeiXinConfig;

/**
 * @author APP TEAM
 * @version 1.0
 */
public enum WeiXinURL {
	// AccessToken
	get_accessTokenUrl("获取AccessToken", "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"), //
	// 素材管理相关
	post_add_temporary_material("新增临时素材", "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE"), //
	get_query_temporary_material("获取临时素材", "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"), //
	post_add_permanent_material("新增永久素材", "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN"), //
	post_add_permanent_news_material("新增永久图文素材", "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN"), //
	post_query_permanent_material("获取永久素材", "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN"), //
	post_delete_permanent_material("删除永久素材", "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN"), //
	post_update_permanent_news_material("修改永久图文素材", "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN"), //
	get_query_permanent_material_total_number("获取永久素材总数", "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN"), //
	post_query_permanent_material_list("获取永久素材列表", "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN"), //

	// 用户分组、用户管理相关
	get_query_all_group("查询所有分组", "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN"), //
	post_create_group("创建分组", "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN"), //
	post_update_group("修改分组名", "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN"), //
	post_delete_group("删除分组", "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN"), //

	post_query_group_by_openid("查询用户所在分组", "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN"), //
	post_move_user2group("移动用户分组", "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN"), //
	post_batch_move_user2group("批量移动用户分组", "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN"), //

	post_remark_wxuser("设置用户备注名", "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN"), //
	get_wxuser_info("获取用户基本信息", "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN"), //
	get_query_server_wxuser_list("获取用户列表", "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID"), //

	// 自定义菜单相关
	post_menu_create("创建自定义菜单", "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"), //
	get_menu_clear("清空自定义菜单", "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN"), //
	get_menu_all("查询自定义菜单", "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN");

	// 2个构造函数
	private WeiXinURL(String url) {
		this.url = url;
	}

	private WeiXinURL(String name, String url) {
		this.name = name;
		this.url = url;
	}

	// 2个成员变量以及其get和set方法
	private String name;
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static String APPID = "APPID";
	public static String APPSECRET = "APPSECRET";
	public static String ACCESS_TOKEN = "ACCESS_TOKEN";
	public static String TYPE = "TYPE";

	/***
	 * 根据 weiXinConfig 和 params 初始化 WeiXinURL 中占位符参数后返回URL。
	 * 
	 * @param weiXinConfig
	 * @param params
	 */
	public String getUrl(WeiXinConfig weiXinConfig, Map<String, String> params) {
		String tmpUrl = url;
		if (weiXinConfig != null) {
			tmpUrl = tmpUrl.replace(APPID, weiXinConfig.getAppId() != null ? weiXinConfig.getAppId() : "");
			tmpUrl = tmpUrl.replace(APPSECRET, weiXinConfig.getAppSecret() != null ? weiXinConfig.getAppSecret() : "");
			tmpUrl = tmpUrl.replace(ACCESS_TOKEN, weiXinConfig.getAccessToken() != null ? weiXinConfig.getAccessToken() : "");
		}
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (entry.getKey() != null && entry.getValue() != null) {
					tmpUrl = tmpUrl.replace(entry.getKey(), entry.getValue());
				}
			}
		}
		return tmpUrl;
	}
}
