/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package ${(devConfig.javaOutputPackage)!'-'}.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import ${(devConfig.javaOutputPackage)!'-'}.dao.${(devEntity.capitalizedClassName)!'-'}Dao;
import ${(devConfig.javaOutputPackage)!'-'}.entity.${(devEntity.capitalizedClassName)!'-'};
import com.app.dao.impl.BaseDaoImpl;

/***
 *
 * DaoImpl - ${(devEntity.classNameDesc)!'-'}
 *
 * @author APP TEAM
 * @version 1.0
 */
@Repository("${(devEntity.unCapitalizedClassName)!'-'}Dao")
public class ${(devEntity.capitalizedClassName)!'-'}DaoImpl extends BaseDaoImpl<${(devEntity.capitalizedClassName)!'-'}, Long> implements ${(devEntity.capitalizedClassName)!'-'}Dao {
	<#if devEntity.isTreeEntity() >

	@Override
	public List<${(devEntity.capitalizedClassName)!'-'}> findRoots(Integer count) {
		String jpql = "select ${(devEntity.unCapitalizedClassName)!'-'} from ${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'} where ${(devEntity.unCapitalizedClassName)!'-'}.parent is null order by ${(devEntity.unCapitalizedClassName)!'-'}.order asc";
		TypedQuery<${(devEntity.capitalizedClassName)!'-'}> query = entityManager.createQuery(jpql, ${(devEntity.capitalizedClassName)!'-'}.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@Override
	public List<${(devEntity.capitalizedClassName)!'-'}> findParents(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, Integer count) {
		if (${(devEntity.unCapitalizedClassName)!'-'} == null || ${(devEntity.unCapitalizedClassName)!'-'}.getParent() == null) {
			return Collections.<${(devEntity.capitalizedClassName)!'-'}> emptyList();
		}
		String jpql = "select ${(devEntity.unCapitalizedClassName)!'-'} from ${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'} where ${(devEntity.unCapitalizedClassName)!'-'}.id in (:ids) order by ${(devEntity.unCapitalizedClassName)!'-'}.grade asc";
		TypedQuery<${(devEntity.capitalizedClassName)!'-'}> query = entityManager.createQuery(jpql, ${(devEntity.capitalizedClassName)!'-'}.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", ${(devEntity.unCapitalizedClassName)!'-'}.getTreePaths());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	@Override
	public List<${(devEntity.capitalizedClassName)!'-'}> findChildren(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, Integer count) {
		TypedQuery<${(devEntity.capitalizedClassName)!'-'}> query;
		if (${(devEntity.unCapitalizedClassName)!'-'} != null) {
			String jpql = "select ${(devEntity.unCapitalizedClassName)!'-'} from ${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'} where ${(devEntity.unCapitalizedClassName)!'-'}.treePath like :treePath order by ${(devEntity.unCapitalizedClassName)!'-'}.order asc";
			query = entityManager.createQuery(jpql, ${(devEntity.capitalizedClassName)!'-'}.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + ${(devEntity.capitalizedClassName)!'-'}.TREE_PATH_SEPARATOR + ${(devEntity.unCapitalizedClassName)!'-'}.getId() + ${(devEntity.capitalizedClassName)!'-'}.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select ${(devEntity.unCapitalizedClassName)!'-'} from ${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'} order by ${(devEntity.unCapitalizedClassName)!'-'}.order asc";
			query = entityManager.createQuery(jpql, ${(devEntity.capitalizedClassName)!'-'}.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), ${(devEntity.unCapitalizedClassName)!'-'});
	}	
	
	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param ${(devEntity.unCapitalizedClassName)!'-'}
	 */
	@Override
	public void persist(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}) {
		Assert.notNull(${(devEntity.unCapitalizedClassName)!'-'});
		setValue(${(devEntity.unCapitalizedClassName)!'-'});
		super.persist(${(devEntity.unCapitalizedClassName)!'-'});
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param ${(devEntity.unCapitalizedClassName)!'-'}
	 */
	@Override
	public ${(devEntity.capitalizedClassName)!'-'} merge(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}) {
		Assert.notNull(${(devEntity.unCapitalizedClassName)!'-'});
		setValue(${(devEntity.unCapitalizedClassName)!'-'});
		for (${(devEntity.capitalizedClassName)!'-'} category : findChildren(${(devEntity.unCapitalizedClassName)!'-'}, null)) {
			setValue(category);
		}
		return super.merge(${(devEntity.unCapitalizedClassName)!'-'});
	}

	/**
	 * 排序
	 * @param productCategories
	 * @param parent
	 *            上级
	 */
	private List<${(devEntity.capitalizedClassName)!'-'}> sort(List<${(devEntity.capitalizedClassName)!'-'}> productCategories, ${(devEntity.capitalizedClassName)!'-'} parent) {
		List<${(devEntity.capitalizedClassName)!'-'}> result = new ArrayList<${(devEntity.capitalizedClassName)!'-'}>();
		if (productCategories != null) {
			for (${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'} : productCategories) {
				if ((${(devEntity.unCapitalizedClassName)!'-'}.getParent() != null && ${(devEntity.unCapitalizedClassName)!'-'}.getParent().equals(parent)) || (${(devEntity.unCapitalizedClassName)!'-'}.getParent() == null && parent == null)) {
					result.add(${(devEntity.unCapitalizedClassName)!'-'});
					result.addAll(sort(productCategories, ${(devEntity.unCapitalizedClassName)!'-'}));
				}
			}
		}
		return result;
	}

	/**
	 * 设置值
	 * 
	 * @param ${(devEntity.unCapitalizedClassName)!'-'}
	 */
	private void setValue(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}) {
		if (${(devEntity.unCapitalizedClassName)!'-'} == null) {
			return;
		}
		${(devEntity.capitalizedClassName)!'-'} parent = ${(devEntity.unCapitalizedClassName)!'-'}.getParent();
		if (parent != null) {
			${(devEntity.unCapitalizedClassName)!'-'}.setTreePath(parent.getTreePath() + parent.getId() + ${(devEntity.capitalizedClassName)!'-'}.TREE_PATH_SEPARATOR);
		} else {
			${(devEntity.unCapitalizedClassName)!'-'}.setTreePath(${(devEntity.capitalizedClassName)!'-'}.TREE_PATH_SEPARATOR);
		}
		${(devEntity.unCapitalizedClassName)!'-'}.setGrade(${(devEntity.unCapitalizedClassName)!'-'}.getTreePaths().size());
	}
	
	</#if>
}
