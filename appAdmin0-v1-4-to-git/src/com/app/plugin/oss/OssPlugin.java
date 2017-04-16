/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.plugin.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ListObjectsRequest;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.app.FileInfo;
import com.app.entity.PluginConfig;
import com.app.plugin.StoragePlugin;

/**
 * Plugin - 阿里云存储
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Component("ossPlugin")
public class OssPlugin extends StoragePlugin {

	@Override
	public String getName() {
		return "阿里云存储";
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
		return "oss/install.jhtml";
	}

	@Override
	public String getUninstallUrl() {
		return "oss/uninstall.jhtml";
	}

	@Override
	public String getSettingUrl() {
		return "oss/setting.jhtml";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String accessId = pluginConfig.getAttribute("accessId");
			String accessKey = pluginConfig.getAttribute("accessKey");
			String bucketName = pluginConfig.getAttribute("bucketName");
			String endPoint = pluginConfig.getAttribute("endPoint");
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(file);
				OSSClient ossClient = new OSSClient(endPoint, accessId, accessKey);
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setContentType(contentType);
				objectMetadata.setContentLength(file.length());
				ossClient.putObject(bucketName, StringUtils.removeStart(path, "/"), inputStream, objectMetadata);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(inputStream);
			}
		}
	}

	@Override
	public String getUrl(String path) {
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String urlPrefix = pluginConfig.getAttribute("urlPrefix");
			return urlPrefix + path;
		}
		return null;
	}

	@Override
	public List<FileInfo> browser(String path) {
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		PluginConfig pluginConfig = getPluginConfig();
		if (pluginConfig != null) {
			String accessId = pluginConfig.getAttribute("accessId");
			String accessKey = pluginConfig.getAttribute("accessKey");
			String bucketName = pluginConfig.getAttribute("bucketName");
			String endPoint = pluginConfig.getAttribute("endPoint");
			String urlPrefix = pluginConfig.getAttribute("urlPrefix");
			try {
				OSSClient ossClient = new OSSClient(endPoint, accessId, accessKey);
				ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
				listObjectsRequest.setPrefix(StringUtils.removeStart(path, "/"));
				listObjectsRequest.setDelimiter("/");
				ObjectListing objectListing = ossClient.listObjects(listObjectsRequest);
				for (String commonPrefix : objectListing.getCommonPrefixes()) {
					FileInfo fileInfo = new FileInfo();
					fileInfo.setName(StringUtils.substringAfterLast(StringUtils.removeEnd(commonPrefix, "/"), "/"));
					fileInfo.setUrl(urlPrefix + "/" + commonPrefix);
					fileInfo.setIsDirectory(true);
					fileInfo.setSize(0L);
					fileInfos.add(fileInfo);
				}
				for (OSSObjectSummary ossObjectSummary : objectListing.getObjectSummaries()) {
					if (ossObjectSummary.getKey().endsWith("/")) {
						continue;
					}
					FileInfo fileInfo = new FileInfo();
					fileInfo.setName(StringUtils.substringAfterLast(ossObjectSummary.getKey(), "/"));
					fileInfo.setUrl(urlPrefix + "/" + ossObjectSummary.getKey());
					fileInfo.setIsDirectory(false);
					fileInfo.setSize(ossObjectSummary.getSize());
					fileInfo.setLastModified(ossObjectSummary.getLastModified());

					// ----------------------------------------------------------------------------------------
					// 这部分是签名方法，如果以后需求签名，可以解除注释（注意这个只是系统设置那部分的签名，其他页面部分不经过这里哦）。
					// Date expires = new Date(new Date().getTime() + 1000 * 60 * 60); // 1 minute to expire
					// GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, ossObjectSummary.getKey());
					// generatePresignedUrlRequest.setExpiration(expires);
					// URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
					// fileInfo.setUrl(url.toString());

					fileInfos.add(fileInfo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileInfos;
	}

}