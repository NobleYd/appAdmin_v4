/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.dao.ContactDao;
import com.app.entity.Contact;

@Repository("contactDao")
public class ContactDaoImpl extends BaseDaoImpl<Contact, Long> implements ContactDao {
}
