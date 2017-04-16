package com.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.dao.TemplateDao;
import com.app.entity.Template;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Repository("templateDao")
public class TemplateDaoImpl extends BaseDaoImpl<Template, Long> implements TemplateDao {
}
