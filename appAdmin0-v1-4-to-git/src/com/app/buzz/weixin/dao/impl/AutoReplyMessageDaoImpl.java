/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.buzz.weixin.dao.AutoReplyMessageDao;
import com.app.buzz.weixin.entity.AutoReplyMessage;
import com.app.dao.impl.BaseDaoImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("autoReplyMessageDao")
public class AutoReplyMessageDaoImpl extends BaseDaoImpl<AutoReplyMessage, Long> implements AutoReplyMessageDao {
}
