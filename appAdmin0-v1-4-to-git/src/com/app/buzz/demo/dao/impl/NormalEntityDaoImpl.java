/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.buzz.demo.dao.NormalEntityDao;
import com.app.buzz.demo.entity.NormalEntity;
import com.app.dao.impl.BaseDaoImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("normalEntityDao")
public class NormalEntityDaoImpl extends BaseDaoImpl<NormalEntity, Long> implements NormalEntityDao {
}
