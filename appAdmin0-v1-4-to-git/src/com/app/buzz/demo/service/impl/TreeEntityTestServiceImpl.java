/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.buzz.demo.dao.TreeEntityTestDao;
import com.app.buzz.demo.entity.TreeEntityTest;
import com.app.buzz.demo.service.TreeEntityTestService;
import com.app.service.impl.BaseServiceImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("treeEntityTestService")
public class TreeEntityTestServiceImpl extends BaseServiceImpl<TreeEntityTest, Long> implements TreeEntityTestService {

	@Resource
	private TreeEntityTestDao treeEntityTestDao;

	@Resource
	public void setBaseDao(TreeEntityTestDao treeEntityTestDao) {
		super.setBaseDao(treeEntityTestDao);
	}
	
	
	@Transactional(readOnly = true)
	public List<TreeEntityTest> findRoots() {
		return treeEntityTestDao.findRoots(null);
	}

	@Transactional(readOnly = true)
	public List<TreeEntityTest> findRoots(Integer count) {
		return treeEntityTestDao.findRoots(count);
	}

	@Transactional(readOnly = true)
	public List<TreeEntityTest> findParents(TreeEntityTest treeEntityTest) {
		return treeEntityTestDao.findParents(treeEntityTest, null);
	}

	@Transactional(readOnly = true)
	public List<TreeEntityTest> findParents(TreeEntityTest treeEntityTest, Integer count) {
		return treeEntityTestDao.findParents(treeEntityTest, count);
	}

	@Transactional(readOnly = true)
	public List<TreeEntityTest> findTree() {
		return treeEntityTestDao.findChildren(null, null);
	}

	@Transactional(readOnly = true)
	public List<TreeEntityTest> findChildren(TreeEntityTest treeEntityTest) {
		return treeEntityTestDao.findChildren(treeEntityTest, null);
	}

	@Transactional(readOnly = true)
	public List<TreeEntityTest> findChildren(TreeEntityTest treeEntityTest, Integer count) {
		return treeEntityTestDao.findChildren(treeEntityTest, count);
	}


}
