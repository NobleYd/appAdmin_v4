package com.app.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

/**
 * Entity - 树形实体基类
 * 
 * @author APP TEAM
 * @version 1.0
 */
@MappedSuperclass
public abstract class TreeEntity<T extends TreeEntity<T>> extends OrderEntity {

	private static final long serialVersionUID = -3797537877044548825L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	/**
	 * @desc 树路径{首尾都是','，使用','分隔的id串，包含[根,自身)的祖先id}
	 * @desc eg：',1,2,'：表示 根->1->2->自身
	 * @desc 第一个逗号之前必定为空，表示为根，一直到自身（自身不写入treePath中）
	 */
	private String treePath;

	/**
	 * @desc 层级
	 * @desc 根是0级，以此类推，可以理解为treePath中数字个数。用于形成层次结构的缩进。
	 */
	private Integer grade;

	/** 上级 */
	private T parent;

	/** 下级 */
	private Set<T> children = new HashSet<T>();

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Column(nullable = false)
	public String getTreePath() {
		return treePath;
	}

	/**
	 * 设置树路径
	 * 
	 * @param treePath
	 *            树路径
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	/**
	 * 获取层级
	 * 
	 * @return 层级
	 */
	@Column(nullable = false)
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 设置层级
	 * 
	 * @param grade
	 *            层级
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 获取上级分类
	 * 
	 * @return 上级分类
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	public T getParent() {
		return parent;
	}

	/**
	 * 设置上级分类
	 * 
	 * @param parent
	 *            上级分类
	 */
	public void setParent(T parent) {
		this.parent = parent;
	}

	/**
	 * 获取下级分类
	 * 
	 * @return 下级分类
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<T> getChildren() {
		return children;
	}

	/**
	 * 设置下级分类
	 * 
	 * @param children
	 *            下级分类
	 */
	public void setChildren(Set<T> children) {
		this.children = children;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Transient
	public List<Long> getTreePaths() {
		List<Long> treePaths = new ArrayList<Long>();
		String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		if (ids != null) {
			for (String id : ids) {
				treePaths.add(Long.valueOf(id));
			}
		}
		return treePaths;
	}
	
}
