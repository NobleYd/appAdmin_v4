package com.app.buzz.dev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import com.app.entity.BaseEntity;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_dev_attribute")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_dev_attribute_sequence")
public class DevAttribute extends BaseEntity {

	private static final long serialVersionUID = -5431081237041292673L;

	/** 属性类型 */
	private static final String attributeTypeString = "String";
	private static final String attributeTypeLong = "Long";
	private static final String attributeTypeInteger = "Integer";
	private static final String attributeTypeBigDecimal = "BigDecimal";
	private static final String attributeTypeEnum = "enum";
	private static final String attributeTypeBoolean = "Boolean";
	private static final String attributeTypeDate = "Date";
	private static final String attributeType_1_N = "1-N";
	private static final String attributeType_N_1 = "N-1";
	private static final String attributeType_N_N = "N-N";
	private static final String attributeType_File = "File";
	private static final String attributeType_Image = "Image";
	private static final String attributeType_Meida = "Media";
	private static final String attributeType_Flash = "Flash";

	@Transient
	public boolean isTitleAttribute() {
		return this.attributeName.equalsIgnoreCase(this.devEntity.getTitleAttribute());
	}

	@Transient
	public boolean isAttributeTypeBeString() {
		return attributeTypeString.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeLong() {
		return attributeTypeLong.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeInteger() {
		return attributeTypeInteger.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeBigDecimal() {
		return attributeTypeBigDecimal.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeEnum() {
		return attributeTypeEnum.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeBoolean() {
		return attributeTypeBoolean.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeDate() {
		return attributeTypeDate.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBe1_N() {
		return attributeType_1_N.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeN_1() {
		return attributeType_N_1.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeN_N() {
		return attributeType_N_N.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeRelatedDevEntity() {
		return isAttributeTypeBe1_N() || isAttributeTypeBeN_1() || isAttributeTypeBeN_N();
	}

	@Transient
	public boolean isAttributeTypeBeFile() {
		return attributeType_File.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeImage() {
		return attributeType_Image.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeMedia() {
		return attributeType_Meida.equalsIgnoreCase(this.attributeType);
	}

	@Transient
	public boolean isAttributeTypeBeFlash() {
		return attributeType_Flash.equalsIgnoreCase(this.attributeType);
	}

	/***
	 * @return 返回是否是任意某种文件类型属性。
	 */
	@Transient
	public boolean isAttributeTypeBeAnyFile() {
		return isAttributeTypeBeFile() || isAttributeTypeBeImage() || isAttributeTypeBeMedia() || isAttributeTypeBeFlash();
	}

	/** 属性名称 */
	private String attributeName;

	/** 属性注释 */
	private String attributeDesc;

	/** 所属实体 */
	private DevEntity devEntity = null;

	/** 属性次序 */
	private Integer sortIndex = 0;

	/** 属性类型(普通类型直接使用本属性字面值为类型，复杂类型通过辅助值共同得到属性类型。) */
	private String attributeType;

	/** 属性类型辅助值 */
	private String attributeTypeValue;

	/** 关联实体（如果没有关联则为null即可） */
	private DevEntity relatedDevEntity;

	/**
	 * 1：如果此属性为null。则不提供add/edit实现或者提供一个最简单的id实现方案，填写id，填写ids等情况。 2：如果指定了本属性。 {N-1}：关联情况 添加/编辑 页面中 autocomplete 指定本关联属性时用于搜索的指定关联实体的属性（如果没有关联则为null即可） {N-N}：待定 {1-N}：待定
	 */
	private String relatedDevEntityAttributeNames;

	/** 是否在list页面显示 */
	private Boolean isShow;

	/** 是否在list页面排序 */
	private Boolean isSort;

	/** 是否在list页面搜索 */
	private Boolean isSearch;

	/** 是否在必须属性 */
	private Boolean isRequired;

	/** 是否唯一属性 */
	private Boolean isUnique;

	/** N-1关联属性的实现方式选择{ 下拉列表、属性搜索方式 } */
	private N2OneType n2oneType;

	/***
	 * @return 返回是否是下拉列表方式实现N-1关联属性的输入。
	 */
	@Transient
	public boolean isN2OneTypeBeSelect() {
		return N2OneType.select.equals(this.n2oneType);
	}

	/***
	 * @return 返回是否是搜索方式实现N-1关联属性的输入。
	 */
	@Transient
	public boolean isN2OneTypeBeSearch() {
		return N2OneType.search.equals(this.n2oneType);
	}

	/***
	 * @return 返回是否是唯一属性搜索方式实现N-1关联属性的输入。
	 */
	@Transient
	public boolean isN2OneTypeBeUniqueSearch() {
		return N2OneType.uniqueSearch.equals(this.n2oneType);
	}

	public enum N2OneType {
		select("下拉列表方式"), uniqueSearch("唯一属性准确搜索方式"), search("多属性模糊搜索方式【用户体验不是很好】");

		private String label;

		private N2OneType(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	@Enumerated(EnumType.STRING)
	public N2OneType getN2oneType() {
		return n2oneType;
	}

	public void setN2oneType(N2OneType n2oneType) {
		this.n2oneType = n2oneType;
	}

	/***
	 * 合法性检测和转换。
	 */
	public void validate() {
		if (this.attributeType == null)
			return;
		// 文件类型不支持排序
		if (this.isAttributeTypeBeAnyFile()) {
			this.setIsSort(false);
		}
		// 枚举、布尔、图片、关联类型不支持唯一验证。
		if (this.isAttributeTypeBeAnyFile() || this.isAttributeTypeBeEnum() || this.isAttributeTypeBeBoolean() || this.isAttributeTypeBeRelatedDevEntity()) {
			this.setIsUnique(false);
		}
		// 布尔类型不支持必须验证。
		if (this.isAttributeTypeBeBoolean()) {
			this.setIsRequired(false);
		}
		// 当前支持 搜索类型 字符串 (支持1,2级搜索)。
		if (this.isAttributeTypeBeN_1()) {
			// N-1类型默认根据其titleAttribute搜索
			// DevAttribute titleDevAttribute = this.getRelatedDevEntity().getDevAttribute(this.getRelatedDevEntity().getTitleAttribute().trim());
			// if (titleDevAttribute != null || !"String".equals(titleDevAttribute.getAttributeType())) {
			// this.setIsSearch(false);
			// }
		} else if (!"String".equals(this.attributeType)) {
			this.setIsSearch(false);
		}

		// 树形实体不支持分页
		// 树形实体不支持批量删除（上下级太复杂）
		// 树形实体不支持属性搜索
		if (this.getDevEntity() != null && this.getDevEntity().isTreeEntity()) {
			devEntity.setNeedPage(false);
			devEntity.setNeedBatchDeleteBtn(false);
			devEntity.setNeedSearch(false);
		}

	}

	/***
	 * 获得首字母大写后的属性名称
	 */
	@Transient
	public String getCapitalizedName() {
		return StringUtils.capitalize(attributeName);
	}

	/***
	 * 获得首字母小写后的属性名称
	 */
	@Transient
	public String getUnCapitalizedName() {
		return StringUtils.uncapitalize(attributeName);
	}

	/**
	 * name默认是驼峰名称 返回下划线分割
	 */
	@Transient
	public String getUnHumpName() {
		String string = StringUtils.uncapitalize(attributeName);
		StringBuffer sbf = new StringBuffer("");
		for (int i = 0; i < string.length(); i++) {
			if (Character.isUpperCase(string.charAt(i))) {
				sbf.append('_');
			}
			sbf.append(Character.toLowerCase(string.charAt(i)));
		}
		return sbf.toString();
	}

	/** attributeName get */
	public String getAttributeName() {
		return attributeName;
	}

	/** attributeName set */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/** attributeDesc get */
	public String getAttributeDesc() {
		return attributeDesc;
	}

	/** attributeDesc set */
	public void setAttributeDesc(String attributeDesc) {
		this.attributeDesc = attributeDesc;
	}

	/** devEntity get */
	@ManyToOne
	public DevEntity getDevEntity() {
		return devEntity;
	}

	/** devEntity set */
	public void setDevEntity(DevEntity devEntity) {
		this.devEntity = devEntity;
	}

	/** sortIndex get */
	public Integer getSortIndex() {
		return sortIndex;
	}

	/** sortIndex set */
	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}

	/** attributeType get */
	public String getAttributeType() {
		return attributeType;
	}

	/** attributeType set */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	/** relatedDevEntityAttributeNames get */
	@Column(name = "r_e_attr_names")
	/** 解决oracle标识符长度问题 */
	public String getRelatedDevEntityAttributeNames() {
		return relatedDevEntityAttributeNames;
	}

	@Transient
	public String[] getRelatedDevEntityAttributeNamesArray() {
		if (relatedDevEntityAttributeNames == null)
			return new String[] {};
		String[] splits = relatedDevEntityAttributeNames.replace("，", ",").trim().split(",");
		return splits;
	}

	/** relatedDevEntityAttributeNames set */
	public void setRelatedDevEntityAttributeNames(String relatedDevEntityAttributeNames) {
		this.relatedDevEntityAttributeNames = relatedDevEntityAttributeNames;
	}

	/** isShow get */
	public Boolean getIsShow() {
		return isShow;
	}

	/** isShow set */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	/** isSort get */
	public Boolean getIsSort() {
		return isSort;
	}

	/** isSort set */
	public void setIsSort(Boolean isSort) {
		this.isSort = isSort;
	}

	/** isSearch get */
	public Boolean getIsSearch() {
		return isSearch;
	}

	/** 判断搜索属性是否是关联实体的属性{仅仅支持最多2级哦} */
	// @Transient
	// public Boolean getIsRelativeAttributeSearch() {
	// 是搜索属性+是N-1关联属性+设置了N-1搜索属性
	// return isSearch && this.isAttributeTypeBeN_1() && this.;
	// }

	/** isSearch set */
	public void setIsSearch(Boolean isSearch) {
		this.isSearch = isSearch;
	}

	/** isRequired get */
	public Boolean getIsRequired() {
		return isRequired;
	}

	/** isRequired set */
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	/** isUnique get */
	public Boolean getIsUnique() {
		return isUnique;
	}

	/** isUnique set */
	public void setIsUnique(Boolean isUnique) {
		this.isUnique = isUnique;
	}

	/** attributeTypeValue get */
	public String getAttributeTypeValue() {
		return attributeTypeValue;
	}

	/** attributeTypeValue set */
	public void setAttributeTypeValue(String attributeTypeValue) {
		this.attributeTypeValue = attributeTypeValue;
	}

	@Transient
	public String getCapitalizedAttributeTypeValue() {
		return StringUtils.capitalize(attributeTypeValue);
	}

	@Transient
	public String getUnCapitalizedAttributeTypeValue() {
		return StringUtils.uncapitalize(attributeTypeValue);
	}

	@ManyToOne
	public DevEntity getRelatedDevEntity() {
		return relatedDevEntity;
	}

	public void setRelatedDevEntity(DevEntity relatedDevEntity) {
		this.relatedDevEntity = relatedDevEntity;
	}

}
