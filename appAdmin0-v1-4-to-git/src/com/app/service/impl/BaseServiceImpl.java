/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.app.Filter;
import com.app.Order;
import com.app.Page;
import com.app.Pageable;
import com.app.dao.BaseDao;
import com.app.entity.BaseEntity;
import com.app.service.BaseService;

/**
 * Service - 基类
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	private static Log log = LogFactory.getLog(BaseServiceImpl.class);

	/** 更新忽略属性 */
	private static final String[] UPDATE_IGNORE_PROPERTIES = new String[] { BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME };

	/** baseDao */
	private BaseDao<T, ID> baseDao;

	public void setBaseDao(BaseDao<T, ID> baseDao) {
		this.baseDao = baseDao;
	}

	@Transactional(readOnly = true)
	public T find(ID id) {
		try {
			return baseDao.find(id);
		} catch (Exception e) {
			log.error("BaseServiceImpl.find(ID id) and so i return null.", e);
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public T find(Filter... filters) {
		try {
			return baseDao.find(filters);
		} catch (Exception e) {
			log.error("BaseServiceImpl.find(Filter... filters) and so i return null.", e);
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> search(String keyword, String[] searchAttributeNames, Integer count) {
		return baseDao.search(keyword, searchAttributeNames, count);
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> search(String keyword, String[] searchAttributeNames, Integer count, List<Filter> filters, List<Order> orders) {
		return baseDao.search(keyword, searchAttributeNames, count, filters, orders);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return findList(null, null, null, null);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findList(ID... ids) {
		List<T> result = new ArrayList<T>();
		if (ids != null) {
			for (ID id : ids) {
				T entity = find(id);
				if (entity != null) {
					result.add(entity);
				}
			}
		}
		return result;
	}

	@Transactional(readOnly = true)
	public List<T> findList(Integer count, List<Filter> filters, List<Order> orders) {
		return findList(null, count, filters, orders);
	}

	@Transactional(readOnly = true)
	public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
		try {
			return baseDao.findList(first, count, filters, orders);
		} catch (Exception e) {
			log.error("BaseServiceImpl.findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) and so i return Collections.emptyList().", e);
			return Collections.emptyList();
		}
	}

	@Transactional(readOnly = true)
	public Page<T> findPage(Pageable pageable) {
		try {
			return baseDao.findPage(pageable);
		} catch (Exception e) {
			log.error("BaseServiceImpl.findPage(Pageable pageable) and so i return null.", e);
			return null;
		}
	}

	@Transactional(readOnly = true)
	public long count() {
		return count(new Filter[] {});
	}

	@Transactional(readOnly = true)
	public long count(Filter... filters) {
		try {
			return baseDao.count(filters);
		} catch (Exception e) {
			log.error("BaseServiceImpl.count(Filter... filters) and so i return 0L.", e);
			return 0L;
		}
	}

	@Transactional(readOnly = true)
	public boolean exists(ID id) {
		try {
			return baseDao.find(id) != null;
		} catch (Exception e) {
			log.error("BaseServiceImpl.exists(ID id) and so i return false.", e);
			return false;
		}
	}

	@Transactional(readOnly = true)
	public boolean exists(Filter... filters) {
		try {
			return baseDao.count(filters) > 0;
		} catch (Exception e) {
			log.error("BaseServiceImpl.exists(Filter... filters) and so i return false.", e);
			return false;
		}
	}

	@Transactional
	public void save(T entity) {
		try {
			baseDao.persist(entity);
		} catch (Exception e) {
			log.error("BaseServiceImpl.save(T entity).", e);
		}
	}

	@Transactional
	public T update(T entity) {
		try {
			return baseDao.merge(entity);
		} catch (Exception e) {
			log.error("BaseServiceImpl.update(T entity) and so i return null.", e);
			return null;
		}
	}

	@Transactional
	public T update(T entity, String... ignoreProperties) {
		Assert.notNull(entity);
		if (baseDao.isManaged(entity)) {
			throw new IllegalArgumentException("Entity must not be managed");
		}
		T persistant;
		try {
			persistant = baseDao.find(baseDao.getIdentifier(entity));
		} catch (Exception e) {
			log.error("BaseServiceImpl.update(T entity, String... ignoreProperties) -> baseDao.find(ID id).", e);
			persistant = null;
		}
		if (persistant != null) {
			copyProperties(entity, persistant, (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
			return update(persistant);
		} else {
			return update(entity);
		}
	}

	@Transactional
	public void delete(ID id) {
		try {
			delete(baseDao.find(id));
		} catch (Exception e) {
			log.error("BaseServiceImpl.delete(ID id) -> baseDao.find(ID id).", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(ID... ids) {
		if (ids != null) {
			for (ID id : ids) {
				try {
					delete(baseDao.find(id));
				} catch (Exception e) {
					log.error("BaseServiceImpl.delete(ID... ids) -> baseDao.find(ID id).", e);
				}
			}
		}
	}

	@Transactional
	public void delete(T entity) {
		try {
			baseDao.remove(entity);
		} catch (Exception e) {
			log.error("BaseServiceImpl.delete(T entity).", e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void copyProperties(Object source, Object target, String[] ignoreProperties) throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
		List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object sourceValue = readMethod.invoke(source);
						Object targetValue = readMethod.invoke(target);
						if (sourceValue != null && targetValue != null && targetValue instanceof Collection) {
							Collection collection = (Collection) targetValue;
							collection.clear();
							collection.addAll((Collection) sourceValue);
						} else {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, sourceValue);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}
			}
		}
	}

}