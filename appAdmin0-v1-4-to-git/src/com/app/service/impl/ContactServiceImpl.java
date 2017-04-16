/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.ContactDao;
import com.app.entity.Contact;
import com.app.service.ContactService;

@Service("contactService")
public class ContactServiceImpl extends BaseServiceImpl<Contact, Long> implements ContactService {

	@Resource
	private ContactDao contactDao;

	@Resource
	public void setBaseDao(ContactDao contactDao) {
		super.setBaseDao(contactDao);
	}
	

}
