/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.weixin.dao.AutoReplyMessageDao;
import com.app.buzz.weixin.entity.AutoReplyMessage;
import com.app.buzz.weixin.service.AutoReplyMessageService;
import com.app.service.impl.BaseServiceImpl;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("autoReplyMessageService")
public class AutoReplyMessageServiceImpl extends BaseServiceImpl<AutoReplyMessage, Long> implements AutoReplyMessageService {

	@Resource
	private AutoReplyMessageDao autoReplyMessageDao;

	@Resource
	public void setBaseDao(AutoReplyMessageDao autoReplyMessageDao) {
		super.setBaseDao(autoReplyMessageDao);
	}

}
