/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.dao;

import java.util.List;

import com.app.buzz.demo.entity.TreeEntityTest;
import com.app.dao.BaseDao;
/**
 * @author APP TEAM
 * @version 1.0
 */
public interface TreeEntityTestDao extends BaseDao<TreeEntityTest, Long>{
	
	public List<TreeEntityTest> findRoots(Integer count);

	public List<TreeEntityTest> findParents(TreeEntityTest treeEntityTest, Integer count);

	public List<TreeEntityTest> findChildren(TreeEntityTest treeEntityTest, Integer count);
		
}
