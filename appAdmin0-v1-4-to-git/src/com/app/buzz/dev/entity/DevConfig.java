package com.app.buzz.dev.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.app.entity.BaseEntity;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_dev_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_dev_config_sequence")
public class DevConfig extends BaseEntity {

	private static final long serialVersionUID = 7457867886677574483L;

	/** 配置名称 */
	private String name = "默认";

	/** 是否当前启用 */
	private Boolean isCurrent = false;

	/** 是否开启Debug模式（异常控制台打印） */
	private Boolean isDebug = false;

	/** 公共 模板文件路径 */
	private String commonTemplatePath = "/com/app/buzz/dev/template";

	/** 自定义 模板文件路径 */
	private String customerTemplatePath = "/com/app/buzz/dev/web/curd/template/主题模板目录名称";

	/** Java 输出文件路径 */
	private String javaOutputPath = "src/com/app/buzz/dev/gen";

	/** 页面 输出文件路径 */
	private String pageOutputPath = "主题war文件夹名称/WEB-INF/template/dev/gen";

	/** 表前缀默认值 */
	private String table_prefix_defaule = "test";

	/** 映射路径前缀默认值 */
	private String request_mapping_prefix_defaule = "/admin";

	/***
	 * 获得a.b.c格式的路径(即转换为包名)。
	 */
	@Transient
	public String getJavaOutputPackage() {
		return this.javaOutputPath.replace("src/", "")//
				.replace("/", ".");
	}

	/***
	 * 根据pageOutputPath生成viewPath。
	 */
	@Transient
	public String getViewPath() {
		if (this.pageOutputPath.contains("template")) {
			return this.pageOutputPath.substring(this.pageOutputPath.indexOf("template") + 8);
		} else {
			return null;
		}
	}

	/** name get */
	public String getName() {
		return name;
	}

	/** name set */
	public void setName(String name) {
		this.name = name;
	}

	/** isCurrent get */
	public Boolean getIsCurrent() {
		return isCurrent;
	}

	/** isCurrent set */
	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Boolean getIsDebug() {
		return isDebug;
	}

	public void setIsDebug(Boolean isDebug) {
		this.isDebug = isDebug;
	}

	/** commonTemplatePath get */
	public String getCommonTemplatePath() {
		return commonTemplatePath;
	}

	/** commonTemplatePath set */
	public void setCommonTemplatePath(String commonTemplatePath) {
		this.commonTemplatePath = commonTemplatePath;
	}

	/** customerTemplatePath get */
	public String getCustomerTemplatePath() {
		return customerTemplatePath;
	}

	/** customerTemplatePath set */
	public void setCustomerTemplatePath(String customerTemplatePath) {
		this.customerTemplatePath = customerTemplatePath;
	}

	/** javaOutputPath get */
	public String getJavaOutputPath() {
		return javaOutputPath;
	}

	/** javaOutputPath set */
	public void setJavaOutputPath(String javaOutputPath) {
		this.javaOutputPath = javaOutputPath;
	}

	/** pageOutputPath get */
	public String getPageOutputPath() {
		return pageOutputPath;
	}

	/** pageOutputPath set */
	public void setPageOutputPath(String pageOutputPath) {
		this.pageOutputPath = pageOutputPath;
	}

	/** table_prefix_defaule get */
	public String getTable_prefix_defaule() {
		return table_prefix_defaule;
	}

	/** table_prefix_defaule set */
	public void setTable_prefix_defaule(String table_prefix_defaule) {
		this.table_prefix_defaule = table_prefix_defaule;
	}

	public String getRequest_mapping_prefix_defaule() {
		return request_mapping_prefix_defaule;
	}

	public void setRequest_mapping_prefix_defaule(String request_mapping_prefix_defaule) {
		this.request_mapping_prefix_defaule = request_mapping_prefix_defaule;
	}

}
