/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.app.buzz.demo.dao.TreeEntityTestDao;
import com.app.buzz.demo.entity.TreeEntityTest;
import com.app.dao.impl.BaseDaoImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("treeEntityTestDao")
public class TreeEntityTestDaoImpl extends BaseDaoImpl<TreeEntityTest, Long> implements TreeEntityTestDao {

	@Override
	public List<TreeEntityTest> findRoots(Integer count) {
		String jpql = "select treeEntityTest from TreeEntityTest treeEntityTest where treeEntityTest.parent is null order by treeEntityTest.order asc";
		TypedQuery<TreeEntityTest> query = entityManager.createQuery(jpql, TreeEntityTest.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@Override
	public List<TreeEntityTest> findParents(TreeEntityTest treeEntityTest, Integer count) {
		if (treeEntityTest == null || treeEntityTest.getParent() == null) {
			return Collections.<TreeEntityTest> emptyList();
		}
		String jpql = "select treeEntityTest from TreeEntityTest treeEntityTest where treeEntityTest.id in (:ids) order by treeEntityTest.grade asc";
		TypedQuery<TreeEntityTest> query = entityManager.createQuery(jpql, TreeEntityTest.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", treeEntityTest.getTreePaths());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@Override
	public List<TreeEntityTest> findChildren(TreeEntityTest treeEntityTest, Integer count) {
		TypedQuery<TreeEntityTest> query;
		if (treeEntityTest != null) {
			String jpql = "select treeEntityTest from TreeEntityTest treeEntityTest where treeEntityTest.treePath like :treePath order by treeEntityTest.order asc";
			query = entityManager.createQuery(jpql, TreeEntityTest.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + TreeEntityTest.TREE_PATH_SEPARATOR + treeEntityTest.getId() + TreeEntityTest.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select treeEntityTest from TreeEntityTest treeEntityTest order by treeEntityTest.order asc";
			query = entityManager.createQuery(jpql, TreeEntityTest.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), treeEntityTest);
	}	
	
	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param treeEntityTest
	 */
	@Override
	public void persist(TreeEntityTest treeEntityTest) {
		Assert.notNull(treeEntityTest);
		setValue(treeEntityTest);
		super.persist(treeEntityTest);
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param treeEntityTest
	 */
	@Override
	public TreeEntityTest merge(TreeEntityTest treeEntityTest) {
		Assert.notNull(treeEntityTest);
		setValue(treeEntityTest);
		for (TreeEntityTest category : findChildren(treeEntityTest, null)) {
			setValue(category);
		}
		return super.merge(treeEntityTest);
	}

	/**
	 * 排序
	 * @param productCategories
	 * @param parent
	 *            上级
	 */
	private List<TreeEntityTest> sort(List<TreeEntityTest> productCategories, TreeEntityTest parent) {
		List<TreeEntityTest> result = new ArrayList<TreeEntityTest>();
		if (productCategories != null) {
			for (TreeEntityTest treeEntityTest : productCategories) {
				if ((treeEntityTest.getParent() != null && treeEntityTest.getParent().equals(parent)) || (treeEntityTest.getParent() == null && parent == null)) {
					result.add(treeEntityTest);
					result.addAll(sort(productCategories, treeEntityTest));
				}
			}
		}
		return result;
	}

	/**
	 * 设置值
	 * 
	 * @param treeEntityTest
	 */
	private void setValue(TreeEntityTest treeEntityTest) {
		if (treeEntityTest == null) {
			return;
		}
		TreeEntityTest parent = treeEntityTest.getParent();
		if (parent != null) {
			treeEntityTest.setTreePath(parent.getTreePath() + parent.getId() + TreeEntityTest.TREE_PATH_SEPARATOR);
		} else {
			treeEntityTest.setTreePath(TreeEntityTest.TREE_PATH_SEPARATOR);
		}
		treeEntityTest.setGrade(treeEntityTest.getTreePaths().size());
	}
	
}
