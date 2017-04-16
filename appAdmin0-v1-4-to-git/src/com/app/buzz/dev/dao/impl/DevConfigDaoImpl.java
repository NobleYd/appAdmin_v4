/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.buzz.dev.dao.DevConfigDao;
import com.app.buzz.dev.entity.DevConfig;
import com.app.dao.impl.BaseDaoImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("devConfigDao")
public class DevConfigDaoImpl extends BaseDaoImpl<DevConfig, Long> implements DevConfigDao {

}
