/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package ${(devConfig.javaOutputPackage)!'-'}.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import com.app.entity.TreeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
<#if devEntity.hasAnyDateAttribute() >
import java.util.Date;
</#if>
import java.math.BigDecimal;

import com.app.entity.BaseEntity;

/***
 *
 * 实体 - ${(devEntity.classNameDesc)!'-'}
 *
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "${(devConfig.table_prefix_defaule)!'-'}_${(devEntity.unHumpClassName)!'-'}")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "${(devConfig.table_prefix_defaule)!'-'}_${(devEntity.unHumpClassName)!'-'}_seq")
<#if devEntity.isTreeEntity() >
public class ${(devEntity.capitalizedClassName)!'-'} extends TreeEntity<${(devEntity.capitalizedClassName)!'-'}> {
<#else>
public class ${(devEntity.capitalizedClassName)!'-'} extends BaseEntity {
</#if>
<#list devEntity.sortedAttributes as attribute>
	<#if attribute.attributeType?starts_with("enum") >
	public enum ${attribute.capitalizedName} {
		${(attribute.attributeTypeValue)!'-'};

		private String label;

		private ${attribute.capitalizedName}(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}
	
	/** ${(attribute.attributeDesc)!'-'} */
	private ${attribute.capitalizedName} ${(attribute.unCapitalizedName)!'-'};
	
	<#elseif attribute.isAttributeTypeBeN_1() >
	/** ${(attribute.attributeDesc)!'-'} */
	private ${(attribute.relatedDevEntity.capitalizedClassName)!'-'} ${(attribute.unCapitalizedName)!'-'};
	
	<#elseif attribute.isAttributeTypeBe1_N() >
	/** ${(attribute.attributeDesc)!'-'} */
	private Set<${(attribute.relatedDevEntity.capitalizedClassName)!'-'}> ${(attribute.unCapitalizedName)!'-'}s;
	
	<#elseif attribute.isAttributeTypeBeN_N() >
	/** ${(attribute.attributeDesc)!'-'} */
	private Set<${(attribute.relatedDevEntity.capitalizedClassName)!'-'}> ${(attribute.unCapitalizedName)!'-'}s;
	
	<#elseif attribute.isAttributeTypeBeAnyFile() >
	/** ${(attribute.attributeDesc)!'-'} */
	private String ${(attribute.unCapitalizedName)!'-'};
	
	<#else>
	/** ${(attribute.attributeDesc)!'-'} */
	private ${attribute.attributeType} ${(attribute.unCapitalizedName)!'-'};
	
	</#if>
</#list>

<#list devEntity.sortedAttributes as attribute>
	
	<#-- 实体标识属性加@JsonProperty -->
	<#if attribute.isTitleAttribute() >
	@JsonProperty
	</#if>
	
	<#if attribute.attributeType?starts_with("enum") >
	/** ${(attribute.unCapitalizedName)!'-'} get */
	@Enumerated(EnumType.STRING)
	public ${attribute.capitalizedName} get${attribute.capitalizedName}(){
		return ${(attribute.unCapitalizedName)!'-'};
	}
	
	/** ${(attribute.unCapitalizedName)!'-'} set */
	public void set${attribute.capitalizedName}(${attribute.capitalizedName} ${(attribute.unCapitalizedName)!'-'}){
		this.${(attribute.unCapitalizedName)!'-'} = ${(attribute.unCapitalizedName)!'-'};
	}
	
	<#elseif attribute.isAttributeTypeBeN_1() >
	/** ${(attribute.unCapitalizedName)!'-'} get */
	@ManyToOne
	public ${(attribute.relatedDevEntity.capitalizedClassName)!'-'} get${attribute.capitalizedName}(){
		return ${(attribute.unCapitalizedName)!'-'};
	}
	
	/** ${(attribute.unCapitalizedName)!'-'} set */
	public void set${attribute.capitalizedName}(${(attribute.relatedDevEntity.capitalizedClassName)!'-'} ${(attribute.unCapitalizedName)!'-'}){
		this.${(attribute.unCapitalizedName)!'-'} = ${(attribute.unCapitalizedName)!'-'};
	}
	
	<#elseif attribute.isAttributeTypeBe1_N() >
	/** ${(attribute.unCapitalizedName)!'-'}s get */
	@OneToMany
	public Set<${(attribute.relatedDevEntity.capitalizedClassName)!'-'}> get${attribute.capitalizedName}(){
		return ${(attribute.unCapitalizedName)!'-'}s;
	}
	
	/** ${(attribute.unCapitalizedName)!'-'}s set */
	public void set${attribute.capitalizedName}(Set<${(attribute.relatedDevEntity.capitalizedClassName)!'-'}> ${(attribute.unCapitalizedName)!'-'}s){
		this.${(attribute.unCapitalizedName)!'-'}s = ${(attribute.unCapitalizedName)!'-'}s;
	}
	
	<#elseif attribute.isAttributeTypeBeN_N() >
	/** ${(attribute.unCapitalizedName)!'-'}s get */
	@ManyToMany
	public Set<${(attribute.relatedDevEntity.capitalizedClassName)!'-'}> get${attribute.capitalizedName}(){
		return ${(attribute.unCapitalizedName)!'-'}s;
	}
	
	/** ${(attribute.unCapitalizedName)!'-'}s set */
	public void set${attribute.capitalizedName}(Set<${(attribute.relatedDevEntity.capitalizedClassName)!'-'}> ${(attribute.unCapitalizedName)!'-'}s){
		this.${(attribute.unCapitalizedName)!'-'}s = ${(attribute.unCapitalizedName)!'-'}s;
	}
	<#elseif attribute.isAttributeTypeBeAnyFile() >
	/** ${(attribute.attributeTypeValue)!'-'} get */
	public String get${attribute.capitalizedName}(){
		return ${(attribute.unCapitalizedName)!'-'};
	}
	
	/** ${(attribute.attributeTypeValue)!'-'} set */
	public void set${attribute.capitalizedName}(String ${(attribute.unCapitalizedName)!'-'}){
		this.${(attribute.unCapitalizedName)!'-'} = ${(attribute.unCapitalizedName)!'-'};
	}
	
	<#else>
	/** ${(attribute.unCapitalizedName)!'-'} get */
	public ${attribute.attributeType} get${attribute.capitalizedName}(){
		return ${(attribute.unCapitalizedName)!'-'};
	}
	
	/** ${(attribute.unCapitalizedName)!'-'} set */
	public void set${attribute.capitalizedName}(${attribute.attributeType} ${(attribute.unCapitalizedName)!'-'}){
		this.${(attribute.unCapitalizedName)!'-'} = ${(attribute.unCapitalizedName)!'-'};
	}
	
	</#if>
</#list>
}
