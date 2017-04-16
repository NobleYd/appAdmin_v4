/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.dao.impl;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.app.dao.SnDao;
import com.app.entity.Sn;
import com.app.entity.Sn.Type;
import com.app.util.FreemarkerUtils;

import freemarker.template.TemplateException;

/**
 * Dao - 序列号
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Repository("snDao")
public class SnDaoImpl implements SnDao, InitializingBean {

	private HiloOptimizer contractHiloOptimizer;

	@PersistenceContext
	private EntityManager entityManager;

	@Value("${sn.contract.prefix}")
	private String contractPrefix;
	@Value("${sn.contract.maxLo}")
	private int contractMaxLo;

	public void afterPropertiesSet() throws Exception {
		contractHiloOptimizer = new HiloOptimizer(Type.contract, contractPrefix, contractMaxLo);
	}

	public String generate(Type type) {
		Assert.notNull(type);
		if (type == Type.contract) {
			return contractHiloOptimizer.generate();
		}
		return null;
	}

	private long getLastValue(Type type) {
		String jpql = "select sn from Sn sn where sn.type = :type";
		Sn sn = entityManager.createQuery(jpql, Sn.class).setFlushMode(FlushModeType.COMMIT).setLockMode(LockModeType.PESSIMISTIC_WRITE).setParameter("type", type).getSingleResult();
		long lastValue = sn.getLastValue();
		sn.setLastValue(lastValue + 1);
		entityManager.merge(sn);
		return lastValue;
	}

	/**
	 * 高低位算法
	 */
	private class HiloOptimizer {

		private Type type;
		private String prefix;
		private int maxLo;
		private int lo;
		private long hi;
		private long lastValue;

		public HiloOptimizer(Type type, String prefix, int maxLo) {
			this.type = type;
			this.prefix = prefix != null ? prefix.replace("{", "${") : "";
			this.maxLo = maxLo;
			this.lo = maxLo + 1;
		}

		public synchronized String generate() {
			if (lo > maxLo) {
				lastValue = getLastValue(type);
				lo = lastValue == 0 ? 1 : 0;
				hi = lastValue * (maxLo + 1);
			}
			try {
				return FreemarkerUtils.process(prefix, null) + (hi + lo++);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			return String.valueOf(hi + lo++);
		}
	}

}