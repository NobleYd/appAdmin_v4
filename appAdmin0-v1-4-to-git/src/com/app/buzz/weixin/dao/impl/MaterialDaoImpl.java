/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.buzz.weixin.dao.MaterialDao;
import com.app.buzz.weixin.entity.Material;
import com.app.dao.impl.BaseDaoImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("materialDao")
public class MaterialDaoImpl extends BaseDaoImpl<Material, Long> implements MaterialDao {
}
