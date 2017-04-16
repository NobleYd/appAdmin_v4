package com.app.buzz.dev.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_dev_entity")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_dev_entity_sequence")
public class DevEntity extends BaseEntity {

	private static final long serialVersionUID = -8409300884115684369L;

	public enum EntityType {
		normalEntity("普通实体"), treeEntity("树形实体  【不支持分页、批量删除、属性排序、属性搜索】");

		private String label;

		private EntityType(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

	}

	/** 实体类型 */
	private EntityType entityType = EntityType.normalEntity;

	/** 备注 */
	private String title;

	/** 类名(英文) */
	private String className;

	/** 类名注释(中文) */
	private String classNameDesc;

	/** 用于标识实体的属性（用于显示实体的时候显示该属性即可） */
	private String titleAttribute;

	/** 属性列表 */
	private Set<DevAttribute> attributes;

	/** 是否支持添加 */
	private Boolean needAddBtn;

	/** 是否支持批量删除 */
	private Boolean needBatchDeleteBtn;

	/** 是否支持刷新 */
	private Boolean needRefreshBtn;

	/** 是否支持分页 */
	private Boolean needPage;

	/** 是否支持搜索 */
	private Boolean needSearch;

	/** 是否支持编辑 */
	private Boolean needEditBtn;

	/** 是否支持删除 */
	private Boolean needDeleteBtn;

	/** 项目包含的实体(多对多) */
	private Set<DevProject> devProjects;

	@Transient
	public boolean isTreeEntity() {
		return EntityType.treeEntity.equals(this.entityType);
	}

	/***
	 * 返回指定名字的DevAttribute。
	 */
	@Transient
	public DevAttribute getDevAttribute(String attributeName) {
		for (DevAttribute attribute : this.attributes) {
			if (attributeName.equalsIgnoreCase(attribute.getAttributeName())) {
				return attribute;
			}
		}
		return null;
	}

	/***
	 * 返回是否存在指定名字的属性。
	 */
	@Transient
	public boolean hasAttribute(String attributeName) {
		return getDevAttribute(attributeName) != null;
	}

	/***
	 * 返回是否存在文件类型属性。
	 */
	@Transient
	public boolean hasAnyFileTypeAttribute() {
		for (DevAttribute attribute : this.attributes) {
			if (attribute.isAttributeTypeBeAnyFile()) {
				return true;
			}
		}
		return false;
	}

	/***
	 * 返回是否存在日期类型属性。
	 */
	@Transient
	public boolean hasAnyDateAttribute() {
		for (DevAttribute attribute : this.attributes) {
			if (attribute.isAttributeTypeBeDate()) {
				return true;
			}
		}
		return false;
	}

	/***
	 * 返回是否存在N-1关联类型属性。
	 */
	@Transient
	public boolean hasAnyN_1Attribute() {
		for (DevAttribute attribute : this.attributes) {
			if (attribute.isAttributeTypeBeN_1()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回实体类型
	 */
	@Enumerated(EnumType.STRING)
	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	/***
	 * 首字母大写后的className
	 */
	@Transient
	public String getCapitalizedClassName() {
		return StringUtils.capitalize(className);
	}

	/***
	 * 首字母小写后的className
	 */
	@Transient
	public String getUnCapitalizedClassName() {
		return StringUtils.uncapitalize(className);
	}

	/**
	 * className默认是驼峰名称 返回下划线名称
	 */
	@Transient
	public String getUnHumpClassName() {
		String string = StringUtils.uncapitalize(className);
		StringBuffer sbf = new StringBuffer("");
		for (int i = 0; i < string.length(); i++) {
			if (Character.isUpperCase(string.charAt(i))) {
				sbf.append('_');
			}
			sbf.append(Character.toLowerCase(string.charAt(i)));
		}
		return sbf.toString();
	}

	/***
	 * 获取根据sortIndex排序后的属性数组。
	 */
	@Transient
	public DevAttribute[] getSortedAttributes() {
		DevAttribute[] devAttributes = this.attributes.toArray(new DevAttribute[] {});
		Arrays.sort(devAttributes, new Comparator<DevAttribute>() {
			@Override
			public int compare(DevAttribute o1, DevAttribute o2) {
				return o1.getSortIndex() - o2.getSortIndex();
			}
		});
		return devAttributes;
	}

	/***
	 * 获取根据sortIndex排序后的文件类型属性数组。
	 */
	@Transient
	public DevAttribute[] getSortedFileAttributes() {
		Set<DevAttribute> fileAttributes = new HashSet<DevAttribute>();
		for (DevAttribute devAttribute : this.attributes) {
			if (devAttribute.isAttributeTypeBeAnyFile()) {
				fileAttributes.add(devAttribute);
			}
		}
		DevAttribute[] sortedFileAttributes = fileAttributes.toArray(new DevAttribute[] {});
		Arrays.sort(sortedFileAttributes, new Comparator<DevAttribute>() {
			@Override
			public int compare(DevAttribute o1, DevAttribute o2) {
				return o1.getSortIndex() - o2.getSortIndex();
			}
		});
		return sortedFileAttributes;
	}

	/***
	 * 获取最大的sortIndex值。
	 */
	@Transient
	public Integer getMaxAttributeSortIndex() {
		Integer sortIndex = 0;
		for (DevAttribute attribute : this.attributes) {
			if (attribute.getSortIndex() > sortIndex)
				sortIndex = attribute.getSortIndex();
		}
		return sortIndex;
	}

	public String getTitleAttribute() {
		return titleAttribute;
	}

	public void setTitleAttribute(String titleAttribute) {
		this.titleAttribute = titleAttribute;
	}

	/** title get */
	@JsonProperty
	public String getTitle() {
		return title;
	}

	/** title set */
	public void setTitle(String title) {
		this.title = title;
	}

	/** className get */
	@JsonProperty
	public String getClassName() {
		return className;
	}

	/** className set */
	public void setClassName(String className) {
		this.className = className;
	}

	/** classNameDesc get */
	@JsonProperty
	public String getClassNameDesc() {
		return classNameDesc;
	}

	/** classNameDesc set */
	public void setClassNameDesc(String classNameDesc) {
		this.classNameDesc = classNameDesc;
	}

	@OneToMany(mappedBy = "devEntity", cascade = CascadeType.REMOVE)
	@OrderBy(value = "sortIndex asc")
	public Set<DevAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<DevAttribute> attributes) {
		this.attributes = attributes;
	}

	/** needAddBtn get */
	public Boolean getNeedAddBtn() {
		return needAddBtn;
	}

	/** needAddBtn set */
	public void setNeedAddBtn(Boolean needAddBtn) {
		this.needAddBtn = needAddBtn;
	}

	/** needBatchDeleteBtn get */
	public Boolean getNeedBatchDeleteBtn() {
		return needBatchDeleteBtn;
	}

	/** needBatchDeleteBtn set */
	public void setNeedBatchDeleteBtn(Boolean needBatchDeleteBtn) {
		this.needBatchDeleteBtn = needBatchDeleteBtn;
	}

	/** needRefreshBtn get */
	public Boolean getNeedRefreshBtn() {
		return needRefreshBtn;
	}

	/** needRefreshBtn set */
	public void setNeedRefreshBtn(Boolean needRefreshBtn) {
		this.needRefreshBtn = needRefreshBtn;
	}

	/** needPage get */
	public Boolean getNeedPage() {
		return needPage;
	}

	/** needPage set */
	public void setNeedPage(Boolean needPage) {
		this.needPage = needPage;
	}

	/** needSearch get */
	public Boolean getNeedSearch() {
		return needSearch;
	}

	/** needSearch set */
	public void setNeedSearch(Boolean needSearch) {
		this.needSearch = needSearch;
	}

	/** needEditBtn get */
	public Boolean getNeedEditBtn() {
		return needEditBtn;
	}

	/** needEditBtn set */
	public void setNeedEditBtn(Boolean needEditBtn) {
		this.needEditBtn = needEditBtn;
	}

	/** needDeleteBtn get */
	public Boolean getNeedDeleteBtn() {
		return needDeleteBtn;
	}

	/** needDeleteBtn set */
	public void setNeedDeleteBtn(Boolean needDeleteBtn) {
		this.needDeleteBtn = needDeleteBtn;
	}

	@ManyToMany(mappedBy = "devEntities")
	public Set<DevProject> getDevProjects() {
		return devProjects;
	}

	public void setDevProjects(Set<DevProject> devProjects) {
		this.devProjects = devProjects;
	}

}
