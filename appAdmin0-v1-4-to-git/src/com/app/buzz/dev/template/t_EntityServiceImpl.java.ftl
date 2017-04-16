/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package ${(devConfig.javaOutputPackage)!'-'}.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ${(devConfig.javaOutputPackage)!'-'}.dao.${(devEntity.capitalizedClassName)!'-'}Dao;
import ${(devConfig.javaOutputPackage)!'-'}.entity.${(devEntity.capitalizedClassName)!'-'};
import ${(devConfig.javaOutputPackage)!'-'}.service.${(devEntity.capitalizedClassName)!'-'}Service;
import com.app.service.impl.BaseServiceImpl;

/***
 *
 * ServiceImpl - ${(devEntity.classNameDesc)!'-'}
 *
 * @author APP TEAM
 * @version 1.0
 */
@Service("${(devEntity.unCapitalizedClassName)!'-'}Service")
public class ${(devEntity.capitalizedClassName)!'-'}ServiceImpl extends BaseServiceImpl<${(devEntity.capitalizedClassName)!'-'}, Long> implements ${(devEntity.capitalizedClassName)!'-'}Service {

	@Resource
	private ${(devEntity.capitalizedClassName)!'-'}Dao ${(devEntity.unCapitalizedClassName)!'-'}Dao;

	@Resource
	public void setBaseDao(${(devEntity.capitalizedClassName)!'-'}Dao ${(devEntity.unCapitalizedClassName)!'-'}Dao) {
		super.setBaseDao(${(devEntity.unCapitalizedClassName)!'-'}Dao);
	}
	
	<#if devEntity.isTreeEntity() >
	
	@Transactional(readOnly = true)
	public List<${(devEntity.capitalizedClassName)!'-'}> findRoots() {
		return ${(devEntity.unCapitalizedClassName)!'-'}Dao.findRoots(null);
	}

	@Transactional(readOnly = true)
	public List<${(devEntity.capitalizedClassName)!'-'}> findRoots(Integer count) {
		return ${(devEntity.unCapitalizedClassName)!'-'}Dao.findRoots(count);
	}

	@Transactional(readOnly = true)
	public List<${(devEntity.capitalizedClassName)!'-'}> findParents(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}) {
		return ${(devEntity.unCapitalizedClassName)!'-'}Dao.findParents(${(devEntity.unCapitalizedClassName)!'-'}, null);
	}

	@Transactional(readOnly = true)
	public List<${(devEntity.capitalizedClassName)!'-'}> findParents(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, Integer count) {
		return ${(devEntity.unCapitalizedClassName)!'-'}Dao.findParents(${(devEntity.unCapitalizedClassName)!'-'}, count);
	}

	@Transactional(readOnly = true)
	public List<${(devEntity.capitalizedClassName)!'-'}> findTree() {
		return ${(devEntity.unCapitalizedClassName)!'-'}Dao.findChildren(null, null);
	}

	@Transactional(readOnly = true)
	public List<${(devEntity.capitalizedClassName)!'-'}> findChildren(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}) {
		return ${(devEntity.unCapitalizedClassName)!'-'}Dao.findChildren(${(devEntity.unCapitalizedClassName)!'-'}, null);
	}

	@Transactional(readOnly = true)
	public List<${(devEntity.capitalizedClassName)!'-'}> findChildren(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, Integer count) {
		return ${(devEntity.unCapitalizedClassName)!'-'}Dao.findChildren(${(devEntity.unCapitalizedClassName)!'-'}, count);
	}

	</#if>

}
