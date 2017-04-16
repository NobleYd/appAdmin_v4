/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.controller.admin;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.FileInfo.FileType;
import com.app.Message;
import com.app.Pageable;
import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.entity.Material;
import com.app.buzz.weixin.service.MaterialService;
import com.app.buzz.weixin.util.WeiXinUtils;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;
import com.app.service.FileService;
/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/material")
public class MaterialController extends BaseController {

	private String viewPath = "/weixin/material";

	@Resource(name = "fileService")
	private FileService fileService;

	@Resource
	private MaterialService materialService;

	@Resource
	private WeiXinConfig weiXinConfig;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", materialService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加素材
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addTemporary(Material.Type type, Material.MaterialType materialType, ModelMap model) {
		model.put("materialType", materialType);
		model.put("type", type);
		// return viewPath + "/add";
		return viewPath + "/add_" + materialType.toString();
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Material material, MultipartFile mediaFile, RedirectAttributes redirectAttributes) {
		if (!isValid(material)) {
			return ERROR_VIEW;
		}
		if (material.getMaterialType() == null) {
			addFlashMessage(redirectAttributes, Message.error("类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (mediaFile != null && !mediaFile.isEmpty()) {
			if (Material.MaterialType.image.equals(material.getMaterialType())) {
				if (!FilenameUtils.isExtension(mediaFile.getOriginalFilename().toLowerCase(), new String[] { "bmp", "png", "jpg", "jpeg", "gif" })) {
					addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
					return "redirect:list.jhtml";
				}
			} else if (Material.MaterialType.voice.equals(material.getMaterialType())) {
				if (!FilenameUtils.isExtension(mediaFile.getOriginalFilename().toLowerCase(), new String[] { "mp3", "wma", "wav", "amr" })) {
					addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
					return "redirect:list.jhtml";
				}
			} else if (Material.MaterialType.video.equals(material.getMaterialType())) {
				if (!FilenameUtils.isExtension(mediaFile.getOriginalFilename().toLowerCase(), new String[] { "mp4" })) {
					addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
					return "redirect:list.jhtml";
				}
			} else if (Material.MaterialType.thumb.equals(material.getMaterialType())) {
				if (!FilenameUtils.isExtension(mediaFile.getOriginalFilename().toLowerCase(), new String[] { "bmp", "png", "jpg", "jpeg", "gif" })) {
					addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
					return "redirect:list.jhtml";
				}
			} else {
				return ERROR_VIEW;
			}
			String url = fileService.upload(FileType.file, mediaFile, false);
			if (url == null) {
				addFlashMessage(redirectAttributes, ERROR_MESSAGE);
				return "redirect:list.jhtml";
			} else {
				material.setMedia(url);
			}
		}

		if (material.getMedia() == null) {
			addFlashMessage(redirectAttributes, Message.error("媒体文件是必填项哦！"));
			return "redirect:list.jhtml";
		}
		// 上传到微信服务器
		if (WeiXinUtils.addMaterial(weiXinConfig, material)) {
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			materialService.save(material);
			return "redirect:list.jhtml";
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
			return "redirect:add.jhtml?type=" + material.getType().toString() + "&materialType=" + material.getMaterialType();
		}

	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		materialService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		Material material = materialService.find(id);
		model.put("MaterialTypes", Material.MaterialType.values());
		model.addAttribute("material", material);
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Material material, MultipartFile mediaFile, RedirectAttributes redirectAttributes) {
		if (!isValid(material)) {
			return ERROR_VIEW;
		}
		ArrayList<String> ignoreFileTypeAttributeNames = new ArrayList<String>();
		if (mediaFile != null && !mediaFile.isEmpty()) {
			if (!fileService.isValid(FileType.file, mediaFile)) {
				addFlashMessage(redirectAttributes, Message.error("不合法的文件扩展名！"));
				return "redirect:list.jhtml";
			} else {
				String url = fileService.upload(FileType.file, mediaFile, false);
				if (url == null) {
					addFlashMessage(redirectAttributes, ERROR_MESSAGE);
					return "redirect:list.jhtml";
				} else {
					material.setMedia(url);
				}
			}
		} else {
			ignoreFileTypeAttributeNames.add("media");
		}
		if (material.getMaterialType() == null) {
			addFlashMessage(redirectAttributes, Message.error("类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (material.getMedia_id() == null) {
			addFlashMessage(redirectAttributes, Message.error("媒体ID是必填项哦！"));
			return "redirect:list.jhtml";
		}
		Material persistant = materialService.find(material.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(material, persistant, new ArrayList<String>(ignoreFileTypeAttributeNames) {
				{
					add(BaseEntity.ID_PROPERTY_NAME);
					add(BaseEntity.CREATE_DATE_PROPERTY_NAME);
					add(BaseEntity.MODIFY_DATE_PROPERTY_NAME);
				}
			}.toArray(new String[] {}));
			materialService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

}
