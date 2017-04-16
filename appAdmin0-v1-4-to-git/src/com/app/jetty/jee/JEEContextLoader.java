/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.jetty.jee;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

public class JEEContextLoader extends ContextLoader
{
    protected ApplicationContext loadParentContext(ServletContext servletContext)
    throws BeansException 
    {
    	ApplicationContext applicationContext = (ApplicationContext)servletContext.getAttribute("applicationContext");
    	return applicationContext;
    }
}
