/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.app.Filter;
import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.constants.WeiXinURL;
import com.app.buzz.weixin.dao.WxUserDao;
import com.app.buzz.weixin.entity.WxUser;
import com.app.buzz.weixin.service.WxUserService;
import com.app.buzz.weixin.util.WeiXinHttpUtils;
import com.app.buzz.weixin.util.WeiXinUtils;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("wxUserService")
public class WxUserServiceImpl extends BaseServiceImpl<WxUser, Long> implements WxUserService {

	private static Log log = LogFactory.getLog(WxUserServiceImpl.class);

	@Resource
	private WeiXinConfig weiXinConfig;

	@Resource
	private WxUserDao wxUserDao;

	@Resource
	public void setBaseDao(WxUserDao wxUserDao) {
		super.setBaseDao(wxUserDao);
	}

	@Override
	public void refreshWxUsetInfoFromServer() {

		String nextOpenId = "";
		HashMap<String, String> wxUrlParams = new HashMap<String, String>();
		do {
			wxUrlParams.put("NEXT_OPENID", nextOpenId);
			Map<String, Object> json = WeiXinHttpUtils.getJson(WeiXinURL.get_query_server_wxuser_list, weiXinConfig, wxUrlParams, null);
			System.out.println(json);
			if (json.containsKey("total") && json.containsKey("count") && json.containsKey("next_openid")) {
				json.get("total");
				json.get("count");
				nextOpenId = null;
				nextOpenId = json.get("next_openid").toString().trim();
				if (json.containsKey("data")) {
					HashMap data = (HashMap) json.get("data");

					if (data.containsKey("openid")) {
						List<String> openIds = (List<String>) data.get("openid");
						for (String openId : openIds) {
							WxUser wxUser = find(Filter.eq("openid", openId.trim(), false));
							if (wxUser == null) {
								// 保存
								WxUser wxUser2 = new WxUser();
								wxUser2.setOpenid(openId.trim());
								if (!WeiXinUtils.refreshWxUserInfo(weiXinConfig, wxUser2)) {
									log.error(wxUser2.getOpenid() + " 信息拉取失败！");
								}
								save(wxUser2);

							} else {
								// 本地已有，不保存。
							}
						}
					} else {
						log.error("WxUserServiceImpl.refreshWxUsetInfoFromServer() error. json = " + json);
						break;
					}
				}
			} else {
				log.error("WxUserServiceImpl.refreshWxUsetInfoFromServer() error. json = " + json);
				break;
			}
		} while (StringUtils.isNotEmpty(nextOpenId));
	}

}
