/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.jetty;

import org.apache.commons.lang.StringUtils;
import org.mortbay.jetty.NCSARequestLog;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.ajp.Ajp13SocketConnector;
import org.mortbay.jetty.handler.RequestLogHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author APP TEAM
 * @version 1.0
 */
public class JettyServer implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private Server server = null;
	HttpMainThread mainThread = null;

	private int minThreads = 2;
	private int lowThreads = 10;
	private int maxThreads = 50;

	private int port = 10010;
	private int ajpPort = -1;
	private int maxIdleTime = 30000;
	private int acceptors = 2;
	private int confidentialPort = 8443;
	private String war = "war";
	private String requestLogFile = ""; // ./logs/access_log.yyyy_mm_dd

	class HttpMainThread extends Thread {
		public void run() {
			try {
				server.start();
			} catch (InterruptedException e) {
				return;
			} catch (Exception e) {
				return;
			}
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void start() throws Exception {

		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setLowThreads(lowThreads);
		threadPool.setMinThreads(minThreads);
		threadPool.setMaxThreads(maxThreads);

		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		connector.setMaxIdleTime(maxIdleTime);
		connector.setAcceptors(acceptors);
		connector.setConfidentialPort(confidentialPort);

		server = new Server();
		server.setThreadPool(threadPool);
		server.addConnector(connector);

		if (ajpPort > 0) {
			Ajp13SocketConnector ajp13 = new Ajp13SocketConnector();
			ajp13.setPort(ajpPort);
			ajp13.setMaxIdleTime(maxIdleTime);
			connector.setAcceptors(acceptors);
			ajp13.setThreadPool(threadPool);
			server.addConnector(ajp13);
		}

		if (!StringUtils.isEmpty(requestLogFile)) {
			NCSARequestLog log = new org.mortbay.jetty.NCSARequestLog(requestLogFile);
			log.setRetainDays(30);
			log.setAppend(true);
			log.setFilenameDateFormat("yyyy-MM-dd");
			log.setExtended(true);
			log.setLogDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
		}

		WebAppContext context = new WebAppContext();
		context.setClassLoader(applicationContext.getClassLoader());
		context.setAttribute("applicationContext", applicationContext);
		context.setServer(server);
		context.setContextPath("/");
		context.setWar(war);
		context.setMaxFormContentSize(1024 * 2000);
		context.setDefaultsDescriptor("jetty-webdefault.xml");

		server.addHandler(context);

		if (!StringUtils.isEmpty(requestLogFile)) {
			RequestLogHandler logHandler = new org.mortbay.jetty.handler.RequestLogHandler();

			NCSARequestLog log = new org.mortbay.jetty.NCSARequestLog(requestLogFile);
			log.setRetainDays(30);
			log.setAppend(true);
			log.setFilenameDateFormat("yyyy-MM-dd");
			log.setExtended(true);
			log.setLogDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
			logHandler.setRequestLog(log);
			server.addHandler(logHandler);
		}

		mainThread = new HttpMainThread();
		mainThread.setDaemon(false);
		mainThread.start();
	}

	public void stop() throws Exception {
		mainThread.interrupt();
		server.stop();
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @param war
	 *            the war to set
	 */
	public void setWar(String war) {
		this.war = war;
	}

	/**
	 * @param ajpPort
	 *            the ajpPort to set
	 */
	public void setAjpPort(int ajpPort) {
		this.ajpPort = ajpPort;
	}

	public String getRequestLogFile() {
		return requestLogFile;
	}

	public void setRequestLogFile(String requestLogFile) {
		this.requestLogFile = requestLogFile;
	}
}
