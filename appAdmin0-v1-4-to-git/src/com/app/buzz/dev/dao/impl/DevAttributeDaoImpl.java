/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.buzz.dev.dao.DevAttributeDao;
import com.app.buzz.dev.entity.DevAttribute;
import com.app.dao.impl.BaseDaoImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("devAttributeDao")
public class DevAttributeDaoImpl extends BaseDaoImpl<DevAttribute, Long> implements DevAttributeDao {

}