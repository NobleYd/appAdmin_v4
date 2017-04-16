/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.app.buzz.weixin.dao.WxMenuButtonDao;
import com.app.buzz.weixin.entity.WxMenuButton;
import com.app.buzz.weixin.service.WxMenuButtonService;
import com.app.service.impl.BaseServiceImpl;

/***
 * @author APP TEAM
 * @version 1.0
 */
@Service("wxMenuButtonService")
public class WxMenuButtonServiceImpl extends BaseServiceImpl<WxMenuButton, Long> implements WxMenuButtonService {

	@Resource
	private WxMenuButtonDao wxMenuButtonDao;

	@Resource
	public void setBaseDao(WxMenuButtonDao wxMenuButtonDao) {
		super.setBaseDao(wxMenuButtonDao);
	}
	

}
