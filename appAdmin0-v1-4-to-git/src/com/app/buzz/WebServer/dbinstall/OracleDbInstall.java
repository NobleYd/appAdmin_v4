/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.WebServer.dbinstall;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.app.util.FreemarkerUtils;

import freemarker.template.TemplateException;

/**
 * Utils - OracleDbInstall
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class OracleDbInstall {

	public static final String DATABASE_ENCODING = "UTF-8";
	public static final String SQL_FILE_PATH = "tools/initdata";
	public static final String DEMO_IMAGE_URL = "${base}/";

	public static String databaseType = "oracle";// mysql,sqlserver,oracle
	public static String databaseHost = "localhost";
	public static String databasePort = "1521";
	public static String databaseUsername = "nobleyd";
	public static String databasePassword = "Passw0rd";
	public static String databaseName = "orcl";
	public static String adminUsername = "admin";
	public static String adminPassword = "123456";
	public static String contextPath = "localhost/";

	public static void main(String[] args) {

		String status = "success";
		String message = "";
		String exception = "";

		if (StringUtils.isEmpty(databaseType)) {
			status = "error";
			message = "数据库类型不允许为空!";
		} else if (StringUtils.isEmpty(databaseHost)) {
			status = "error";
			message = "数据库主机不允许为空!";
		} else if (StringUtils.isEmpty(databasePort)) {
			status = "error";
			message = "数据库端口不允许为空!";
		} else if (StringUtils.isEmpty(databaseUsername)) {
			status = "error";
			message = "数据库用户名不允许为空!";
		} else if (StringUtils.isEmpty(databaseName)) {
			status = "error";
			message = "数据库名称不允许为空!";
		} else if (StringUtils.isEmpty(adminUsername)) {
			status = "error";
			message = "管理员用户名不允许为空!";
		} else if (adminUsername.length() < 2 || adminUsername.length() > 20) {
			status = "error";
			message = "管理员用户名长度必须在2-20之间!";
		} else if (StringUtils.isEmpty(adminPassword)) {
			status = "error";
			message = "管理员密码不允许为空!";
		} else if (adminPassword.length() < 4 || adminPassword.length() > 40) {
			status = "error";
			message = "管理员密码长度必须在4-20之间!";
		}

		String jdbcUrl = null;
		File sqlFile = null;
		Integer databaseMajorVersion = null;
		Integer databaseMinorVersion = null;

		if (status.equals("success")) {
			if (databaseType.equalsIgnoreCase("mysql")) {
				Connection connection = null;
				try {
					jdbcUrl = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName + "?useUnicode=true&characterEncoding=" + DATABASE_ENCODING;
					connection = DriverManager.getConnection(jdbcUrl, databaseUsername, databasePassword);
				} catch (SQLException e) {
					jdbcUrl = "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName + "?useUnicode=true";
				} finally {
					try {
						if (connection != null) {
							connection.close();
							connection = null;
						}
					} catch (SQLException e) {
					}
				}
				sqlFile = new File(SQL_FILE_PATH + "//mysql/init.sql");
			} else if (databaseType.equalsIgnoreCase("sqlserver")) {
				jdbcUrl = "jdbc:sqlserver://" + databaseHost + ":" + databasePort + ";DatabaseName=" + databaseName;
				sqlFile = new File(SQL_FILE_PATH + "/sqlserver/init.sql");
			} else if (databaseType.equalsIgnoreCase("oracle")) {
				jdbcUrl = "jdbc:oracle:thin:@" + databaseHost + ":" + databasePort + ":" + databaseName;
				sqlFile = new File(SQL_FILE_PATH + "/oracle/init.sql");
			} else {
				status = "error";
				message = "参数错误!";
			}
		}

		if (status.equals("success") && (sqlFile == null || !sqlFile.exists())) {
			status = "error";
			message = "INIT.SQL文件不存在!";
		}

		if (status.equals("success")) {
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(jdbcUrl, databaseUsername, databasePassword);
				DatabaseMetaData databaseMetaData = connection.getMetaData();
				databaseMajorVersion = databaseMetaData.getDatabaseMajorVersion();
				databaseMinorVersion = databaseMetaData.getDatabaseMinorVersion();
			} catch (SQLException e) {
				status = "error";
				message = "JDBC执行错误!";
				exception = stackToString(e);
			} finally {
				try {
					if (connection != null) {
						connection.close();
						connection = null;
					}
				} catch (SQLException e) {
					status = "error";
					message = "JDBC执行错误!";
					exception = stackToString(e);
				}
			}
		}

		if (status.equals("success")) {
			Connection connection = null;
			Statement statement = null;
			String currentSQL = null;

			try {
				connection = DriverManager.getConnection(jdbcUrl, databaseUsername, databasePassword);
				connection.setAutoCommit(false);
				statement = connection.createStatement();

				Map<String, Object> model = new HashMap<String, Object>();
				model.put("base", contextPath);
				model.put("adminUsername", adminUsername);
				model.put("adminPassword", DigestUtils.md5Hex(adminPassword));
				model.put("demoImageUrl", DEMO_IMAGE_URL);

				if (databaseType.equalsIgnoreCase("mysql")) {
					String bit0 = null;
					String bit1 = null;
					if (databaseMajorVersion < 5) {
						bit0 = "'0'";
						bit1 = "'1'";
					} else {
						bit0 = "b'0'";
						bit1 = "b'1'";
					}
					model.put("bit0", bit0);
					model.put("bit1", bit1);
				}
				Calendar calendar = DateUtils.toCalendar(new Date());
				calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
				calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
				calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
				int i = 0;
				for (String line : FileUtils.readLines(sqlFile, "utf-8")) {
					if (StringUtils.isNotBlank(line) && !StringUtils.startsWith(line, "--")) {
						model.put("date", DateUtils.addSeconds(calendar.getTime(), i++));
						currentSQL = FreemarkerUtils.process(line, model);
						// System.out.println(currentSQL);
						statement.executeUpdate(currentSQL);
					}
				}
				connection.commit();
				currentSQL = null;
			} catch (SQLException e) {
				status = "error";
				message = "JDBC执行错误!";
				exception = stackToString(e);
				if (currentSQL != null) {
					exception = "SQL: " + currentSQL + "<br />" + exception;
				}
			} catch (IOException e) {
				status = "error";
				message = "INIT.SQL文件读取失败!";
				exception = stackToString(e);
			} catch (TemplateException e) {
				status = "error";
				message = "FreemarkerUtils.process(line, model)失败!";
				exception = stackToString(e);
			} finally {
				try {
					if (statement != null) {
						statement.close();
						statement = null;
					}
					if (connection != null) {
						connection.close();
						connection = null;
					}
				} catch (SQLException e) {
					status = "error";
					message = "JDBC执行错误!";
					exception = stackToString(e);
				}
			}
		}

		Map<String, String> data = new HashMap<String, String>();
		data.put("status", status);
		data.put("message", message);
		data.put("exception", exception);

		System.out.println(data);
	}

	public static String stackToString(Exception exception) {
		try {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			exception.printStackTrace(printWriter);
			return stringWriter.toString();
			// return stringWriter.toString().replaceAll("\\r?\\n", "</br>");
		} catch (Exception e) {
			return "stackToString error";
		}
	}
}
