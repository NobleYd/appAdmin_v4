/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package ${(devConfig.javaOutputPackage)!'-'}.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Filter;
import com.app.Message;
import com.app.Pageable;
import ${(devConfig.javaOutputPackage)!'-'}.entity.${(devEntity.capitalizedClassName)!'-'};
import ${(devConfig.javaOutputPackage)!'-'}.service.${(devEntity.capitalizedClassName)!'-'}Service;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;

<#if devEntity.hasAnyFileTypeAttribute() >
import org.springframework.web.multipart.MultipartFile;
import com.app.FileInfo.FileType;
import com.app.service.FileService;
</#if>

/***
 *
 * 控制器 - ${(devEntity.classNameDesc)!'-'}
 *
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("${(devConfig.request_mapping_prefix_defaule)!''}/${(devEntity.unHumpClassName)!'-'}")
public class ${(devEntity.capitalizedClassName)!'-'}Controller extends BaseController {

	private String viewPath = "${(devConfig.viewPath)!'-'}/${(devEntity.unHumpClassName)!'-'}";
	
	<#if devEntity.hasAnyFileTypeAttribute() >
	@Resource(name = "fileService")
	private FileService fileService;
	</#if>
	
	<#list devEntity.sortedAttributes as attribute>
		<#if attribute.isAttributeTypeBeRelatedDevEntity() >
	@Resource
	private ${(attribute.relatedDevEntity.capitalizedClassName)!'-'}Service ${(attribute.relatedDevEntity.unCapitalizedClassName)!'-'}Service;	
		</#if>
	</#list>
	
	@Resource
	private ${(devEntity.capitalizedClassName)!'-'}Service ${(devEntity.unCapitalizedClassName)!'-'}Service;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
	<#if devEntity.isTreeEntity() >
		model.addAttribute("${(devEntity.unCapitalizedClassName)!'-'}Tree", ${(devEntity.unCapitalizedClassName)!'-'}Service.findTree());
	<#else>
		<#if !devEntity.needPage>
		pageable.setUnlimitPageSize();
		</#if>
		model.addAttribute("page", ${(devEntity.unCapitalizedClassName)!'-'}Service.findPage(pageable));
	</#if>
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
<#-- 树形列表情况 -->
<#if devEntity.isTreeEntity() >
		model.addAttribute("${(devEntity.unCapitalizedClassName)!'-'}Tree", ${(devEntity.unCapitalizedClassName)!'-'}Service.findTree());
</#if>
<#list devEntity.sortedAttributes as attribute>
	<#-- 枚举类型需要回显列表 -->
	<#if attribute.attributeType?starts_with("enum") >
		model.put("${attribute.capitalizedName}s", ${(devEntity.capitalizedClassName)!'-'}.${attribute.capitalizedName}.values());
	<#-- N-1关联属性如果是使用'下拉列表'方式实现，则需要回显下拉列表选项 -->
	<#elseif attribute.isAttributeTypeBeN_1() && attribute.isN2OneTypeBeSelect() >
		model.put("${attribute.capitalizedName}s", ${(attribute.relatedDevEntity.unCapitalizedClassName)!'-'}Service.findAll());
	</#if>
</#list>
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, <#if devEntity.isTreeEntity() >Long parentId, </#if><#list devEntity.sortedAttributes as attribute><#if attribute.isAttributeTypeBeN_1() >Long ${(attribute.unCapitalizedName)!'-'}Id, </#if></#list><#list devEntity.sortedAttributes as attribute><#if attribute.isAttributeTypeBeAnyFile() >MultipartFile ${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}, </#if></#list>RedirectAttributes redirectAttributes) {
		if (!isValid(${(devEntity.unCapitalizedClassName)!'-'})) {
			return ERROR_VIEW;
		}
<#-- N-1关联类型属性的处理 -->
<#list devEntity.sortedAttributes as attribute>
	<#if attribute.isAttributeTypeBeN_1() > 
		if (${(attribute.unCapitalizedName)!'-'}Id != null) {
			${(attribute.relatedDevEntity.capitalizedClassName)!'-'} ${(attribute.unCapitalizedName)!'-'} = ${(attribute.relatedDevEntity.unCapitalizedClassName)!'-'}Service.find(${(attribute.unCapitalizedName)!'-'}Id);
			${(devEntity.unCapitalizedClassName)!'-'}.set${(attribute.capitalizedName)!'-'}(${(attribute.unCapitalizedName)!'-'});
		}
	</#if>
</#list>
<#list devEntity.sortedAttributes as attribute>
	<#if attribute.isAttributeTypeBeAnyFile() >
		if (${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'} != null && !${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}.isEmpty()) {
			if (!fileService.isValid(FileType.${(attribute.attributeType?lower_case)!'-'}, ${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'})) {
				addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
				return "redirect:list.jhtml";
			} else {
				String url = fileService.upload(FileType.${(attribute.attributeType?lower_case)!'-'}, ${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}, false);
				if (url == null) {
					addFlashMessage(redirectAttributes, ERROR_MESSAGE);
					return "redirect:list.jhtml";
				} else {
					${(devEntity.unCapitalizedClassName)!'-'}.set${(attribute.capitalizedName)!'-'}(url);
				}
			}
		}
	</#if>
</#list>
<#list devEntity.sortedAttributes as attribute>
	<#if attribute.isRequired >
		if(${(devEntity.unCapitalizedClassName)!'-'}.get${attribute.capitalizedName}() == null){
			addFlashMessage(redirectAttributes, Message.error("${(attribute.attributeDesc)!'-'}是必填项哦！"));
			return "redirect:list.jhtml";
		}
	</#if>
</#list>
		<#if devEntity.isTreeEntity() >
		${(devEntity.unCapitalizedClassName)!'-'}.setParent(${(devEntity.unCapitalizedClassName)!'-'}Service.find(parentId));
		</#if>

		${(devEntity.unCapitalizedClassName)!'-'}Service.save(${(devEntity.unCapitalizedClassName)!'-'});
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		<#-- 如果是树形实体，需要特殊检测，不允许删除带有下级的实体，只允许一个一个删除。 -->
		<#if devEntity.isTreeEntity() >
		List<${(devEntity.capitalizedClassName)!'-'}> ${(devEntity.unCapitalizedClassName)!'-'}s = ${(devEntity.unCapitalizedClassName)!'-'}Service.findList(ids);
		for( ${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'} : ${(devEntity.unCapitalizedClassName)!'-'}s ){
			if( ${(devEntity.unCapitalizedClassName)!'-'}.getChildren() != null && ${(devEntity.unCapitalizedClassName)!'-'}.getChildren().size() > 0 ){
				return ERROR_MESSAGE;
			}else{
				${(devEntity.unCapitalizedClassName)!'-'}Service.delete(${(devEntity.unCapitalizedClassName)!'-'});
			}
		}
		<#else>
		try {
			${(devEntity.unCapitalizedClassName)!'-'}Service.delete(ids);
		} catch (Exception e) {
			return ERROR_MESSAGE;
		}
		</#if>
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'} = ${(devEntity.unCapitalizedClassName)!'-'}Service.find(id);
<#if devEntity.isTreeEntity() >
		model.addAttribute("${(devEntity.unCapitalizedClassName)!'-'}Tree", ${(devEntity.unCapitalizedClassName)!'-'}Service.findTree());
		model.addAttribute("children", ${(devEntity.unCapitalizedClassName)!'-'}Service.findChildren(${(devEntity.unCapitalizedClassName)!'-'}));
</#if>
<#list devEntity.sortedAttributes as attribute>
	<#if attribute.attributeType?starts_with("enum") >
		model.put("${attribute.capitalizedName}s", ${(devEntity.capitalizedClassName)!'-'}.${attribute.capitalizedName}.values());
	<#-- N-1关联属性如果是使用'下拉列表'方式实现，则需要回显下拉列表选项 -->
	<#elseif attribute.isAttributeTypeBeN_1() && attribute.isN2OneTypeBeSelect() >
		model.put("${attribute.capitalizedName}s", ${(attribute.relatedDevEntity.unCapitalizedClassName)!'-'}Service.findAll());
	</#if>
</#list>
		model.addAttribute("${(devEntity.unCapitalizedClassName)!'-'}", ${(devEntity.unCapitalizedClassName)!'-'});
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(${(devEntity.capitalizedClassName)!'-'} ${(devEntity.unCapitalizedClassName)!'-'}, <#if devEntity.isTreeEntity() >Long parentId, </#if><#list devEntity.sortedAttributes as attribute><#if attribute.isAttributeTypeBeN_1() >Long ${(attribute.unCapitalizedName)!'-'}Id, </#if></#list><#list devEntity.sortedAttributes as attribute><#if attribute.isAttributeTypeBeAnyFile() >MultipartFile ${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}, </#if></#list>RedirectAttributes redirectAttributes) {
		if (!isValid(${(devEntity.unCapitalizedClassName)!'-'})) {
			return ERROR_VIEW;
		}
<#-- N-1关联类型属性的处理 -->
<#list devEntity.sortedAttributes as attribute>
	<#if attribute.isAttributeTypeBeN_1() > 
		if (${(attribute.unCapitalizedName)!'-'}Id != null) {
			${(attribute.relatedDevEntity.capitalizedClassName)!'-'} ${(attribute.unCapitalizedName)!'-'} = ${(attribute.relatedDevEntity.unCapitalizedClassName)!'-'}Service.find(${(attribute.unCapitalizedName)!'-'}Id);
			${(devEntity.unCapitalizedClassName)!'-'}.set${(attribute.capitalizedName)!'-'}(${(attribute.unCapitalizedName)!'-'});
		}
	</#if>
</#list>
	<#if devEntity.isTreeEntity() >
		${(devEntity.unCapitalizedClassName)!'-'}.setParent(${(devEntity.unCapitalizedClassName)!'-'}Service.find(parentId));
	</#if>
	<#-- 文件域必填情况 并且 更新时候无文件上传 则表示不更新该字段（更新逻辑：无文件则表示不更新该文件字段） -->
	<#-- 文件域非必填情况 并且 更新时候无文件上传 则表示删除该字段原先文件 -->
	<#if devEntity.hasAnyFileTypeAttribute() >
		ArrayList<String> ignoreFileTypeAttributeNames = new ArrayList<String>();
	</#if>
<#-- 文件上传处理，无则不上传，有则上传 以及后缀验证+上传返回url保存。 -->
<#list devEntity.sortedAttributes as attribute>
	<#if attribute.isAttributeTypeBeAnyFile() >
		if (${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'} != null && !${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}.isEmpty()) {
			if (!fileService.isValid(FileType.${(attribute.attributeType?lower_case)!'-'}, ${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'})) {
				addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
				return "redirect:list.jhtml";
			} else {
				String url = fileService.upload(FileType.${(attribute.attributeType?lower_case)!'-'}, ${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}, false);
				if (url == null) {
					addFlashMessage(redirectAttributes, ERROR_MESSAGE);
					return "redirect:list.jhtml";
				} else {
					${(devEntity.unCapitalizedClassName)!'-'}.set${(attribute.capitalizedName)!'-'}(url);
				}
			}
		}
		<#-- 如果是该文件类型属性必须的，则必须在更新情况下无该文件上传时候添加这个忽略属性。 
			 暂时注释掉，这个逻辑解决了必须图片属性编辑的时候默认情况不上传表示不更改的问题，但是引入了非必须属性编辑的时候默认情况表删除的意义。所以此处决定去除这部分，也就是无上传文件一定表示不更新而不是删除图片。
		<#if attribute.isRequired >
		</#if>
		-->
		else{
			ignoreFileTypeAttributeNames.add("${(attribute.unCapitalizedName)!'-'}");
		}
	</#if>
</#list>
<#list devEntity.sortedAttributes as attribute>
	<#if attribute.isRequired && !attribute.isAttributeTypeBeAnyFile() >
		if(${(devEntity.unCapitalizedClassName)!'-'}.get${attribute.capitalizedName}() == null){
			addFlashMessage(redirectAttributes, Message.error("${(attribute.attributeDesc)!'-'}是必填项哦！"));
			return "redirect:list.jhtml";
		}
	</#if>
</#list>
		${(devEntity.capitalizedClassName)!'-'} persistant = ${(devEntity.unCapitalizedClassName)!'-'}Service.find(${(devEntity.unCapitalizedClassName)!'-'}.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(${(devEntity.unCapitalizedClassName)!'-'}, persistant, 
				new ArrayList<String>(<#if devEntity.hasAnyFileTypeAttribute() >ignoreFileTypeAttributeNames</#if>) {
					{
						add(BaseEntity.ID_PROPERTY_NAME);
						add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
						add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
					}
				}.toArray(new String[]{})
			);
			${(devEntity.unCapitalizedClassName)!'-'}Service.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

<#list devEntity.sortedAttributes as attribute>
<#if attribute.isUnique >
	@RequestMapping("/check_${(attribute.unHumpName)!'-'}_unique")
	@ResponseBody
	public Boolean check${attribute.capitalizedName}Unique(final ${attribute.attributeType} previous${attribute.capitalizedName}, final ${attribute.attributeType} ${(attribute.unCapitalizedName)!'-'}) {
		//string类型请使用注释部分的if判断替换下面对应两个if判断
		//if (StringUtils.isEmpty(${(attribute.unCapitalizedName)!'-'})) {
		//	return false;
		//}
		//if (StringUtils.equalsIgnoreCase(previous${attribute.capitalizedName}, ${(attribute.unCapitalizedName)!'-'})) {
		//	return true;
		//}
		if (${(attribute.unCapitalizedName)!'-'} == null) {
			return false;
		}
		if (${(attribute.unCapitalizedName)!'-'}.equals(previous${attribute.capitalizedName})) {
			return true;
		}
		return ${(devEntity.unCapitalizedClassName)!'-'}Service.find(Filter.eq("${(attribute.unCapitalizedName)!'-'}", ${(attribute.unCapitalizedName)!'-'}, false)) == null;
	}
	
<#elseif attribute.isAttributeTypeBeRelatedDevEntity() >
	/***
	 * 根据${(attribute.relatedDevEntityAttributeNames)!'-'}这些属性搜索${(attribute.relatedDevEntity.capitalizedClassName)!'-'}实体
	 */
	@RequestMapping(value = "/search${(attribute.capitalizedName)!'-'}", method = RequestMethod.GET)
	public @ResponseBody List<${(attribute.relatedDevEntity.capitalizedClassName)!'-'}> search${(attribute.capitalizedName)!'-'}(String q) {
		return ${(attribute.relatedDevEntity.unCapitalizedClassName)!'-'}Service.search(q, new String[] { <#list attribute.relatedDevEntityAttributeNamesArray as relatedDevEntityAttributeName><#if relatedDevEntityAttributeName_index == 0>"${relatedDevEntityAttributeName}"<#else>, "${relatedDevEntityAttributeName}"</#if></#list>}, 20);
	}
	
</#if>
</#list>

}
