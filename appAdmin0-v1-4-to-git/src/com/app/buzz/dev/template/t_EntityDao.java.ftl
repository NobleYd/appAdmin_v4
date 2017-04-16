/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package ${(devConfig.javaOutputPackage)!'-'}.dao;

import java.util.List;
import ${(devConfig.javaOutputPackage)!'-'}.entity.${(devEntity.capitalizedClassName)!'-'};
import com.app.dao.BaseDao;

/***
 *
 * Dao - ${(devEntity.classNameDesc)!'-'}
 *
 * @author APP TEAM
 * @version 1.0
 */
public interface ${(devEntity.capitalizedClassName)!'-'}Dao extends BaseDao<${(devEntity.capitalizedClassName)!'-'}, Long>{
	<#if devEntity.isTreeEntity() >
	
	public List<${(devEntity.capitalizedClassName)!'-'}> findRoots(Integer count);

	public List<${(devEntity.capitalizedClassName)!'-'}> findParents(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, Integer count);

	public List<${(devEntity.capitalizedClassName)!'-'}> findChildren(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, Integer count);
		
	</#if>
}
