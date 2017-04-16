/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.WebServer.start;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author APP TEAM
 * @version 1.0
 */
public class AppAdminWebRun {

	private static Log log = LogFactory.getLog(AppAdminWebRun.class);

	private static final String appConfigure = "appConfig";

	public static void main(String[] args) {

		String home = System.getProperty(appConfigure);
		if (home == null) {
			log.warn("System property 'appConfig' NOT SET!! setting example(put following string into java startup command): -DappConfig=conf/" + "\nReading default configer file from: <working dir>/conf/");
			home = "conf/";
		}

		String jettyPropPath = home + File.separator + "jetty.xml";
		String[] paths = { jettyPropPath };

		System.out.println("Load Spring Properties File:" + jettyPropPath);
		log.info("Load Spring Properties File:" + jettyPropPath);

		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(paths);
		ctx.registerShutdownHook();

		log.info("App Web Application init succeed.");
		System.out.println("App Web Application init succeed.");

	}

}
