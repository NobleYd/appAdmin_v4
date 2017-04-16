/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 筛选
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class Filter implements Serializable {

	private static final long serialVersionUID = -8712382358441065075L;

	/**
	 * 运算符
	 */
	public enum Operator {

		/** 等于 */
		eq,

		/** 不等于 */
		ne,

		/** 大于 */
		gt, greaterThan,

		/** 小于 */
		lt, lessThan,

		/** 大于等于 */
		ge, greaterThanOrEqualTo,

		/** 小于等于 */
		le, lessThanOrEqualTo,

		/** 相似 */
		like,

		/** 包含 */
		in,

		/** 为Null */
		isNull,

		/** 不为Null */
		isNotNull;

		/**
		 * 从String中获取Operator
		 * 
		 * @param value
		 *            值
		 * @return String对应的operator
		 */
		public static Operator fromString(String value) {
			return Operator.valueOf(value.toLowerCase());
		}
	}

	/** 默认是否忽略大小写 */
	private static final boolean DEFAULT_IGNORE_CASE = false;

	/** 属性 */
	private String property;

	/** 运算符 */
	private Operator operator;

	/** 值 */
	private Object value;

	/** 标识值value是否是另外一个属性的名称 */
	private Boolean isValuePropery = false;

	/** 是否忽略大小写(仅针对value为String类型情况有效) */
	private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

	/**
	 * 初始化一个新创建的Filter对象
	 */
	private Filter() {
	}

	/**
	 * 初始化一个新创建的Filter对象(使用Object value)
	 * 
	 * @param property
	 *            属性
	 * @param operator
	 *            运算符
	 * @param isValuePropery
	 *            value是否是属性
	 * @param value
	 *            值
	 */
	private Filter(String property, Operator operator, Object value, boolean isValuePropery) {
		this.property = property;
		this.operator = operator;
		this.value = value;
		this.isValuePropery = isValuePropery;
	}

	/**
	 * 初始化一个新创建的Filter对象(使用Object value)
	 * 
	 * @param property
	 *            属性
	 * @param operator
	 *            运算符
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @param ignoreCase
	 *            忽略大小写
	 */
	private Filter(String property, Operator operator, Object value, boolean isValuePropery, boolean ignoreCase) {
		this.property = property;
		this.operator = operator;
		this.value = value;
		this.ignoreCase = ignoreCase;
		this.isValuePropery = isValuePropery;
	}

	/**
	 * 返回等于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 等于筛选
	 */
	public static Filter eq(String property, Object value, boolean isValuePropery) {
		return new Filter(property, Operator.eq, value, isValuePropery);
	}

	/**
	 * 返回等于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @param ignoreCase
	 *            忽略大小写
	 * @return 等于筛选
	 */
	public static Filter eq(String property, Object value, boolean isValuePropery, boolean ignoreCase) {
		return new Filter(property, Operator.eq, value, isValuePropery, ignoreCase);
	}

	/**
	 * 返回不等于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 不等于筛选
	 */
	public static Filter ne(String property, Object value, boolean isValuePropery) {
		return new Filter(property, Operator.ne, value, isValuePropery);
	}

	/**
	 * 返回不等于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @param ignoreCase
	 *            忽略大小写
	 * @return 不等于筛选
	 */
	public static Filter ne(String property, Object value, boolean isValuePropery, boolean ignoreCase) {
		return new Filter(property, Operator.ne, value, isValuePropery, ignoreCase);
	}

	/**
	 * 返回大于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 大于筛选
	 */
	public static Filter gt(String property, Number value, boolean isValuePropery) {
		return new Filter(property, Operator.gt, value, isValuePropery);
	}

	/**
	 * 返回大于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 大于筛选
	 */
	public static Filter greaterThan(String property, Date value, boolean isValuePropery) {
		return new Filter(property, Operator.greaterThan, value, isValuePropery);
	}

	/**
	 * 返回小于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 小于筛选
	 */
	public static Filter lt(String property, Number value, boolean isValuePropery) {
		return new Filter(property, Operator.lt, value, isValuePropery);
	}

	/**
	 * 返回小于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 小于筛选
	 */
	public static Filter lessThan(String property, Date value, boolean isValuePropery) {
		return new Filter(property, Operator.lessThan, value, isValuePropery);
	}

	/**
	 * 返回大于等于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 大于等于筛选
	 */
	public static Filter ge(String property, Number value, boolean isValuePropery) {
		return new Filter(property, Operator.ge, value, isValuePropery);
	}

	/**
	 * 返回大于等于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 大于等于筛选
	 */
	public static Filter greaterThanOrEqualTo(String property, Date value, boolean isValuePropery) {
		return new Filter(property, Operator.greaterThanOrEqualTo, value, isValuePropery);
	}

	/**
	 * 返回小于等于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 小于等于筛选
	 */
	public static Filter le(String property, Number value, boolean isValuePropery) {
		return new Filter(property, Operator.le, value, isValuePropery);
	}

	/**
	 * 返回小于等于筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 小于等于筛选
	 */
	public static Filter lessThanOrEqualTo(String property, Date value, boolean isValuePropery) {
		return new Filter(property, Operator.lessThanOrEqualTo, value, isValuePropery);
	}

	/**
	 * 返回相似筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param isValuePropery
	 *            value是否是属性
	 * @return 相似筛选
	 */
	public static Filter like(String property, Object value) {
		return new Filter(property, Operator.like, value, false);
	}

	/**
	 * 返回包含筛选
	 * 
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @return 包含筛选
	 */
	public static Filter in(String property, Object value) {
		return new Filter(property, Operator.in, value, false);
	}

	/**
	 * 返回为Null筛选
	 * 
	 * @param property
	 *            属性
	 * @return 为Null筛选
	 */
	public static Filter isNull(String property) {
		return new Filter(property, Operator.isNull, false, false);
	}

	/**
	 * 返回不为Null筛选
	 * 
	 * @param property
	 *            属性
	 * @return 不为Null筛选
	 */
	public static Filter isNotNull(String property) {
		return new Filter(property, Operator.isNotNull, false, false);
	}

	/**
	 * 返回忽略大小写筛选
	 * 
	 * @return 忽略大小写筛选
	 */
	public Filter ignoreCase() {
		this.ignoreCase = true;
		return this;
	}

	/**
	 * 获取属性
	 * 
	 * @return 属性
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * 设置属性
	 * 
	 * @param property
	 *            属性
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * 获取运算符
	 * 
	 * @return 运算符
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * 设置运算符
	 * 
	 * @param operator
	 *            运算符
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * 获取值
	 * 
	 * @return 值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 设置值
	 * 
	 * @param value
	 *            值
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 获取value是否是属性
	 */
	public Boolean valueIsPropery() {
		return isValuePropery;
	}

	/**
	 * 设置value是否是属性
	 */
	public void setIsValuePropery(Boolean isValuePropery) {
		this.isValuePropery = isValuePropery;
	}

	/**
	 * 获取value是否是属性
	 */
	public String getComparePropery() {
		return (String) value;
	}

	/**
	 * 获取是否忽略大小写
	 * 
	 * @return 是否忽略大小写
	 */
	public Boolean getIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * 设置是否忽略大小写
	 * 
	 * @param ignoreCase
	 *            是否忽略大小写
	 */
	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Filter other = (Filter) obj;
		return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOperator(), other.getOperator()).append(getValue(), other.getValue()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
	}

}