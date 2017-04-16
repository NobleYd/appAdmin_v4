/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.Page;
import com.app.Pageable;
import com.app.dao.SiteMailDao;
import com.app.entity.Admin;
import com.app.entity.SiteMail;
import com.app.service.SiteMailService;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Service("siteMailService")
public class SiteMailServiceImpl extends TemplateBaseServiceImpl<SiteMail, Long> implements SiteMailService {

	@Resource
	private SiteMailDao siteMailDao;

	@Resource
	public void setBaseDao(SiteMailDao siteMailDao) {
		super.setBaseDao(siteMailDao);
	}

	@Override
	public Page<SiteMail> findPageOfRecycledBox(Pageable pageable, Admin admin) {
		return siteMailDao.findPageOfRecycledBox(pageable, admin);
	}

	@Override
	public Page<SiteMail> findPageOfRemovedBox(Pageable pageable, Admin admin) {
		return siteMailDao.findPageOfRemovedBox(pageable, admin);
	}

}
