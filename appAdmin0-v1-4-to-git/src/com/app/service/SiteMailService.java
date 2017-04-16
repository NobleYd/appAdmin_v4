/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service;

import com.app.Page;
import com.app.Pageable;
import com.app.entity.Admin;
import com.app.entity.SiteMail;

/**
 * @author APP TEAM
 * @version 1.0
 */
public interface SiteMailService extends TemplateBaseService<SiteMail, Long> {

	Page<SiteMail> findPageOfRecycledBox(Pageable pageable, Admin admin);

	Page<SiteMail> findPageOfRemovedBox(Pageable pageable, Admin currentAdmin);

}
