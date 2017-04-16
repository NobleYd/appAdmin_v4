/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.Converter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.ExcelView;
import com.app.FileInfo.FileType;
import com.app.Filter;
import com.app.Message;
import com.app.Page;
import com.app.Pageable;
import com.app.buzz.demo.entity.NormalEntity;
import com.app.buzz.demo.service.NormalEntityService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;
import com.app.service.FileService;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/normal_entity")
public class NormalEntityController extends BaseController {

	private String viewPath = "/demo/normal_entity";

	@Resource(name = "fileService")
	private FileService fileService;

	@Resource
	private NormalEntityService normalEntityService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", normalEntityService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 报表导出
	 */
	@RequestMapping(value = "/list", params = "export", method = RequestMethod.GET)
	public ModelAndView export(Pageable pageable, ModelMap model) {
		pageable.setUnlimitPageSize();
		Page<NormalEntity> page = normalEntityService.findPage(pageable);

		String filename = "normal_entity_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xls";
		String sheetName = null;
		String[] properties = new String[] { "name", "sex", "age", "birthDate", "score" };
		String[] titles = new String[] { "姓名", "性别", "年龄", "出生日期", "综合评分" };
		Integer[] widths = null;
		Converter[] converters = null;
		// data 数据
		String[] contents = null;// 附加内容
		return new ModelAndView(new ExcelView(filename, sheetName, properties, titles, widths, converters, page.getContent(), contents), model);
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.put("Sexs", NormalEntity.Sex.values());
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(NormalEntity normalEntity, Long loverId, MultipartFile pictureImage, MultipartFile attachmentFile, RedirectAttributes redirectAttributes) {
		if (!isValid(normalEntity)) {
			return ERROR_VIEW;
		}
		if (loverId != null) {
			NormalEntity lover = normalEntityService.find(loverId);
			normalEntity.setLover(lover);
		}
		if (pictureImage != null && !pictureImage.isEmpty()) {
			if (!fileService.isValid(FileType.image, pictureImage)) {
				addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
				return "redirect:list.jhtml";
			} else {
				String url = fileService.upload(FileType.image, pictureImage, false);
				if (url == null) {
					addFlashMessage(redirectAttributes, ERROR_MESSAGE);
					return "redirect:list.jhtml";
				} else {
					normalEntity.setPicture(url);
				}
			}
		}
		if (attachmentFile != null && !attachmentFile.isEmpty()) {
			if (!fileService.isValid(FileType.file, attachmentFile)) {
				addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
				return "redirect:list.jhtml";
			} else {
				String url = fileService.upload(FileType.file, attachmentFile, false);
				if (url == null) {
					addFlashMessage(redirectAttributes, ERROR_MESSAGE);
					return "redirect:list.jhtml";
				} else {
					normalEntity.setAttachment(url);
				}
			}
		}
		if (normalEntity.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getSex() == null) {
			addFlashMessage(redirectAttributes, Message.error("性别是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getAge() == null) {
			addFlashMessage(redirectAttributes, Message.error("年龄是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getBirthDate() == null) {
			addFlashMessage(redirectAttributes, Message.error("出生日期是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getPicture() == null) {
			addFlashMessage(redirectAttributes, Message.error("照片是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getScore() == null) {
			addFlashMessage(redirectAttributes, Message.error("综合评分[满分100]是必填项哦！"));
			return "redirect:list.jhtml";
		}

		normalEntityService.save(normalEntity);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		normalEntityService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		NormalEntity normalEntity = normalEntityService.find(id);
		model.put("Sexs", NormalEntity.Sex.values());
		model.addAttribute("normalEntity", normalEntity);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(NormalEntity normalEntity, Long loverId, MultipartFile pictureImage, MultipartFile attachmentFile, RedirectAttributes redirectAttributes) {
		if (!isValid(normalEntity)) {
			return ERROR_VIEW;
		}
		if (loverId != null) {
			NormalEntity lover = normalEntityService.find(loverId);
			normalEntity.setLover(lover);
		}
		ArrayList<String> ignoreFileTypeAttributeNames = new ArrayList<String>();
		if (pictureImage != null && !pictureImage.isEmpty()) {
			if (!fileService.isValid(FileType.image, pictureImage)) {
				addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
				return "redirect:list.jhtml";
			} else {
				String url = fileService.upload(FileType.image, pictureImage, false);
				if (url == null) {
					addFlashMessage(redirectAttributes, ERROR_MESSAGE);
					return "redirect:list.jhtml";
				} else {
					normalEntity.setPicture(url);
				}
			}
		} else {
			ignoreFileTypeAttributeNames.add("picture");
		}
		if (attachmentFile != null && !attachmentFile.isEmpty()) {
			if (!fileService.isValid(FileType.file, attachmentFile)) {
				addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
				return "redirect:list.jhtml";
			} else {
				String url = fileService.upload(FileType.file, attachmentFile, false);
				if (url == null) {
					addFlashMessage(redirectAttributes, ERROR_MESSAGE);
					return "redirect:list.jhtml";
				} else {
					normalEntity.setAttachment(url);
				}
			}
		} else {
			ignoreFileTypeAttributeNames.add("attachment");
		}
		if (normalEntity.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getSex() == null) {
			addFlashMessage(redirectAttributes, Message.error("性别是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getAge() == null) {
			addFlashMessage(redirectAttributes, Message.error("年龄是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getBirthDate() == null) {
			addFlashMessage(redirectAttributes, Message.error("出生日期是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (normalEntity.getScore() == null) {
			addFlashMessage(redirectAttributes, Message.error("综合评分[满分100]是必填项哦！"));
			return "redirect:list.jhtml";
		}
		NormalEntity persistant = normalEntityService.find(normalEntity.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(normalEntity, persistant, new ArrayList<String>(ignoreFileTypeAttributeNames) {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
				}
			}.toArray(new String[] {}));
			normalEntityService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	@RequestMapping("/check_name_unique")
	@ResponseBody
	public Boolean checkNameUnique(final String previousName, final String name) {
		// string类型请使用注释部分的if判断替换下面对应两个if判断
		// if (StringUtils.isEmpty(name)) {
		// return false;
		// }
		// if (StringUtils.equalsIgnoreCase(previousName, name)) {
		// return true;
		// }
		if (name == null) {
			return false;
		}
		if (name.equals(previousName)) {
			return true;
		}
		return normalEntityService.find(Filter.eq("name", name, false)) == null;
	}

	/***
	 * 根据name这些属性搜索NormalEntity实体
	 */
	@RequestMapping(value = "/searchLover", method = RequestMethod.GET)
	public @ResponseBody List<NormalEntity> searchLover(String q) {
		return normalEntityService.search(q, new String[] { "name" }, 20);
	}

}
