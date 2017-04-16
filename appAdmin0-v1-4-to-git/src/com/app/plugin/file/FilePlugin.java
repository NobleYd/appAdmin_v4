/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.plugin.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import com.app.FileInfo;
import com.app.Setting;
import com.app.plugin.StoragePlugin;
import com.app.util.SettingUtils;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * Plugin - 本地文件存储
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Component("filePlugin")
public class FilePlugin extends StoragePlugin implements ServletContextAware {

	/** servletContext */
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public String getName() {
		return "本地文件存储";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "APP";
	}

	@Override
	public String getSiteUrl() {
		return "http://www.com.app";
	}

	@Override
	public String getInstallUrl() {
		return "file/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "file/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "file/setting.jhtml";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		File destFile = new File(servletContext.getRealPath(path));
		try {
			FileUtils.moveFile(file, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUrl(String path) {
		Setting setting = SettingUtils.get();
		return setting.getSiteUrl() + path;
	}

	@Override
	public List<FileInfo> browser(String path) {
		Setting setting = SettingUtils.get();
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		File directory = new File(servletContext.getRealPath(path));
		if (directory.exists() && directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				FileInfo fileInfo = new FileInfo();
				fileInfo.setName(file.getName());
				fileInfo.setUrl(setting.getSiteUrl() + path + file.getName());
				fileInfo.setIsDirectory(file.isDirectory());
				fileInfo.setSize(file.length());
				fileInfo.setLastModified(new Date(file.lastModified()));
				fileInfos.add(fileInfo);
			}
		}
		return fileInfos;
	}

}