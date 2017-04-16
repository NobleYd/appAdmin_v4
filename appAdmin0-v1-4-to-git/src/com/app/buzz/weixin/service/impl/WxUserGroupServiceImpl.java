/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.weixin.dao.WxUserGroupDao;
import com.app.buzz.weixin.entity.WxUserGroup;
import com.app.buzz.weixin.service.WxUserGroupService;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("wxUserGroupService")
public class WxUserGroupServiceImpl extends BaseServiceImpl<WxUserGroup, Long> implements WxUserGroupService {

	@Resource
	private WxUserGroupDao wxUserGroupDao;

	@Resource
	public void setBaseDao(WxUserGroupDao wxUserGroupDao) {
		super.setBaseDao(wxUserGroupDao);
	}
	

}
