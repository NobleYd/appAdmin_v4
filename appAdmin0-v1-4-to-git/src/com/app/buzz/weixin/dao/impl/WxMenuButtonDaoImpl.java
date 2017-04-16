/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.app.buzz.weixin.dao.WxMenuButtonDao;
import com.app.buzz.weixin.entity.WxMenuButton;
import com.app.dao.impl.BaseDaoImpl;

/***
 * @author APP TEAM
 * @version 1.0
 */
@Repository("wxMenuButtonDao")
public class WxMenuButtonDaoImpl extends BaseDaoImpl<WxMenuButton, Long> implements WxMenuButtonDao {
}
