/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.app.Page;
import com.app.Pageable;
import com.app.dao.SiteMailDao;
import com.app.entity.Admin;
import com.app.entity.SiteMail;
import com.app.entity.SiteMail.Location;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("siteMailDao")
public class SiteMailDaoImpl extends BaseDaoImpl<SiteMail, Long> implements SiteMailDao {

	@Override
	public Page<SiteMail> findPageOfRecycledBox(Pageable pageable, Admin admin) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SiteMail> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		criteriaQuery.select(criteriaQuery.from(entityClass));

		Root<SiteMail> root = getRoot(criteriaQuery);

		Predicate restrictions = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();

		// 情况1
		Predicate part1 = criteriaBuilder.conjunction();
		part1 = criteriaBuilder.and(part1, criteriaBuilder.equal(root.get("authorAdmin"), admin));
		part1 = criteriaBuilder.and(part1, criteriaBuilder.equal(root.get("authorLocation"), Location.recycledBox));
		// 情况2
		Predicate part2 = criteriaBuilder.conjunction();
		part2 = criteriaBuilder.and(part2, criteriaBuilder.equal(root.get("toAdmin"), admin));
		part2 = criteriaBuilder.and(part2, criteriaBuilder.equal(root.get("toLocation"), Location.recycledBox));

		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(part1, part2));

		criteriaQuery.where(restrictions);

		return findPage(criteriaQuery, pageable);
	}

	@Override
	public Page<SiteMail> findPageOfRemovedBox(Pageable pageable, Admin admin) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SiteMail> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		criteriaQuery.select(criteriaQuery.from(entityClass));

		Root<SiteMail> root = getRoot(criteriaQuery);

		Predicate restrictions = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction() : criteriaBuilder.conjunction();

		// 情况1
		Predicate part1 = criteriaBuilder.conjunction();
		part1 = criteriaBuilder.and(part1, criteriaBuilder.equal(root.get("authorAdmin"), admin));
		part1 = criteriaBuilder.and(part1, criteriaBuilder.equal(root.get("authorLocation"), Location.removed));
		// 情况2
		Predicate part2 = criteriaBuilder.conjunction();
		part2 = criteriaBuilder.and(part2, criteriaBuilder.equal(root.get("toAdmin"), admin));
		part2 = criteriaBuilder.and(part2, criteriaBuilder.equal(root.get("toLocation"), Location.removed));

		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(part1, part2));

		criteriaQuery.where(restrictions);

		return findPage(criteriaQuery, pageable);

	}

}
