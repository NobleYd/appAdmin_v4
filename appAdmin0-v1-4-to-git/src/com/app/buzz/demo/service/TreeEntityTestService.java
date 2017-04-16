/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.service;

import java.util.List;

import com.app.buzz.demo.entity.TreeEntityTest;
import com.app.service.BaseService;
/**
 * @author APP TEAM
 * @version 1.0
 */
public interface TreeEntityTestService extends BaseService<TreeEntityTest, Long>{
	/**
	 * 查找顶级树形实体测试
	 */
	List<TreeEntityTest> findRoots();

	/**
	 * 查找顶级树形实体测试
	 * @param count
	 *            数量
	 */
	List<TreeEntityTest> findRoots(Integer count);

	/**
	 * 查找上级树形实体测试
	 * @param treeEntityTest 树形实体测试
	 * @return 上级树形实体测试
	 */
	List<TreeEntityTest> findParents(TreeEntityTest treeEntityTest);

	/**
	 * 查找上级树形实体测试
	 * @param treeEntityTest 树形实体测试
	 * @param count
	 *            数量
	 */
	List<TreeEntityTest> findParents(TreeEntityTest treeEntityTest, Integer count);

	/**
	 * 查找树形实体测试树
	 * @return 树形实体测试树
	 */
	List<TreeEntityTest> findTree();

	/**
	 * 查找下级树形实体测试
	 * @param treeEntityTest 树形实体测试
	 */
	List<TreeEntityTest> findChildren(TreeEntityTest treeEntityTest);

	/**
	 * 查找下级树形实体测试
	 * @param treeEntityTest 树形实体测试
	 * @param count
	 *            数量
	 */
	List<TreeEntityTest> findChildren(TreeEntityTest treeEntityTest, Integer count);
	
	
}
