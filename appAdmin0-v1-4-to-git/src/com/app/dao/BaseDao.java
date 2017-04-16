/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.LockModeType;

import com.app.Filter;
import com.app.Order;
import com.app.Page;
import com.app.Pageable;

/**
 * Dao - 基类
 * 
 * @author APP TEAM
 * @version 1.0
 */
public interface BaseDao<T, ID extends Serializable> {

	/**
	 * 查找实体对象
	 * 
	 * @param id
	 *            ID
	 * @return 实体对象，若不存在则返回null
	 */
	T find(ID id) throws Exception;

	/**
	 * 查找实体对象
	 * 
	 * @param id
	 *            ID
	 * @param lockModeType
	 *            锁定方式
	 * @return 实体对象，若不存在则返回null
	 */
	T find(ID id, LockModeType lockModeType) throws Exception;

	/**
	 * 查询实体对象
	 * 
	 * @param filters
	 *            筛选
	 * @return 实体对象
	 */
	T find(Filter... filters) throws Exception;

	/**
	 * 根据属性模糊搜索
	 * 
	 * @param keyword
	 *            模糊搜索属性值
	 * @param searchAttributeNames
	 *            模糊搜索属性列表
	 * @param count
	 *            最大返回结果数量
	 * @return 返回搜索结果
	 */
	List<T> search(String keyword, String[] searchAttributeNames, Integer count);

	/**
	 * 根据属性模糊搜索
	 * 
	 * @param keyword
	 *            模糊搜索属性值
	 * @param searchAttributeNames
	 *            模糊搜索属性列表
	 * @param count
	 *            最大返回结果数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 返回搜索结果
	 */
	List<T> search(String keyword, String[] searchAttributeNames, Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 查找实体对象集合
	 * 
	 * @param first
	 *            起始记录
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 实体对象集合
	 */
	List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) throws Exception;

	/**
	 * 查找实体对象分页
	 * 
	 * @param pageable
	 *            分页信息
	 * @return 实体对象分页
	 */
	Page<T> findPage(Pageable pageable) throws Exception;

	/**
	 * 查询实体对象数量
	 * 
	 * @param filters
	 *            筛选
	 * @return 实体对象数量
	 */
	long count(Filter... filters) throws Exception;

	/**
	 * 持久化实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	void persist(T entity) throws Exception;

	/**
	 * 合并实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象
	 */
	T merge(T entity) throws Exception;

	/**
	 * 移除实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	void remove(T entity) throws Exception;

	/**
	 * 刷新实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	void refresh(T entity) throws Exception;

	/**
	 * 刷新实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @param lockModeType
	 *            锁定方式
	 */
	void refresh(T entity, LockModeType lockModeType) throws Exception;

	/**
	 * 获取实体对象ID
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体对象ID
	 */
	ID getIdentifier(T entity);

	/**
	 * 判断是否为托管状态
	 * 
	 * @param entity
	 *            实体对象
	 * @return 是否为托管状态
	 */
	boolean isManaged(T entity);

	/**
	 * 设置为游离状态
	 * 
	 * @param entity
	 *            实体对象
	 */
	void detach(T entity) throws Exception;

	/**
	 * 锁定实体对象
	 * 
	 * @param entity
	 *            实体对象
	 * @param lockModeType
	 *            锁定方式
	 */
	void lock(T entity, LockModeType lockModeType) throws Exception;

	/**
	 * 清除缓存
	 */
	void clear() throws Exception;

	/**
	 * 同步数据
	 */
	void flush() throws Exception;

}