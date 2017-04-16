/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.app.buzz.dev.dao.DevEntityDao;
import com.app.buzz.dev.entity.DevEntity;
import com.app.dao.impl.BaseDaoImpl;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("devEntityDao")
public class DevEntityDaoImpl extends BaseDaoImpl<DevEntity, Long> implements DevEntityDao {

	@Override
	public List<DevEntity> search(String keyword, Integer count) {
		if (StringUtils.isEmpty(keyword)) {
			keyword = "";
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DevEntity> criteriaQuery = criteriaBuilder.createQuery(DevEntity.class);
		Root<DevEntity> root = criteriaQuery.from(DevEntity.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();

		Predicate searchs = criteriaBuilder.or(criteriaBuilder.like((criteriaBuilder.lower(root.<String> get("title"))), "%" + keyword.toLowerCase() + "%"),
				criteriaBuilder.like((criteriaBuilder.lower(root.<String> get("className"))), "%" + keyword.toLowerCase() + "%"),
				criteriaBuilder.like((criteriaBuilder.lower(root.<String> get("classNameDesc"))), "%" + keyword.toLowerCase() + "%"));

		restrictions = criteriaBuilder.and(restrictions, searchs);

		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, count, null, null);
	}

}