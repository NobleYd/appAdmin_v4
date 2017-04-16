/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.dev.bean;
/**
 * @author APP TEAM
 * @version 1.0
 */
public enum CurdGenType {

	gen_all("所有文件"), //

	gen_java("所有Java文件"), //
	gen_entity("实体"), //
	gen_dao("数据访问层"), //
	gen_service("数据业务层"), //
	gen_controller("控制器"), //

	gen_page("所有页面"), //
	gen_list("list页面"), //
	gen_add("add页面"), //
	gen_edit("edit页面"), //

	gen_quick_info("快捷信息");

	private String label;

	private CurdGenType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
