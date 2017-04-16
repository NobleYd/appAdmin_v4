/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package ${(devConfig.javaOutputPackage)!'-'}.service;

import java.util.List;

import ${(devConfig.javaOutputPackage)!'-'}.entity.${(devEntity.capitalizedClassName)!'-'};
import com.app.service.BaseService;

/***
 *
 * Service - ${(devEntity.classNameDesc)!'-'}
 *
 * @author APP TEAM
 * @version 1.0
 */
public interface ${(devEntity.capitalizedClassName)!'-'}Service extends BaseService<${(devEntity.capitalizedClassName)!'-'}, Long>{
	<#if devEntity.isTreeEntity() >
	/**
	 * 查找顶级${(devEntity.classNameDesc)!'-'}
	 */
	List<${(devEntity.capitalizedClassName)!'-'}> findRoots();

	/**
	 * 查找顶级${(devEntity.classNameDesc)!'-'}
	 * @param count
	 *            数量
	 */
	List<${(devEntity.capitalizedClassName)!'-'}> findRoots(Integer count);

	/**
	 * 查找上级${(devEntity.classNameDesc)!'-'}
	 * @param ${(devEntity.unCapitalizedClassName)!'-'} ${(devEntity.classNameDesc)!'-'}
	 * @return 上级${(devEntity.classNameDesc)!'-'}
	 */
	List<${(devEntity.capitalizedClassName)!'-'}> findParents(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'});

	/**
	 * 查找上级${(devEntity.classNameDesc)!'-'}
	 * @param ${(devEntity.unCapitalizedClassName)!'-'} ${(devEntity.classNameDesc)!'-'}
	 * @param count
	 *            数量
	 */
	List<${(devEntity.capitalizedClassName)!'-'}> findParents(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, Integer count);

	/**
	 * 查找${(devEntity.classNameDesc)!'-'}树
	 * @return ${(devEntity.classNameDesc)!'-'}树
	 */
	List<${(devEntity.capitalizedClassName)!'-'}> findTree();

	/**
	 * 查找下级${(devEntity.classNameDesc)!'-'}
	 * @param ${(devEntity.unCapitalizedClassName)!'-'} ${(devEntity.classNameDesc)!'-'}
	 */
	List<${(devEntity.capitalizedClassName)!'-'}> findChildren(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'});

	/**
	 * 查找下级${(devEntity.classNameDesc)!'-'}
	 * @param ${(devEntity.unCapitalizedClassName)!'-'} ${(devEntity.classNameDesc)!'-'}
	 * @param count
	 *            数量
	 */
	List<${(devEntity.capitalizedClassName)!'-'}> findChildren(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, Integer count);
	
	</#if>
	
}
