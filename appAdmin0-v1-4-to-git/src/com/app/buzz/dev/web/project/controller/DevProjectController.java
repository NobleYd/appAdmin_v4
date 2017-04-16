package com.app.buzz.dev.web.project.controller;

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
import com.app.buzz.dev.entity.DevProject;
import com.app.buzz.dev.service.DevProjectService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/dev/project")
public class DevProjectController extends BaseController {

	private String viewPath = "/com/app/buzz/dev/web/project/view";

	@Resource
	private DevProjectService devProjectService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", devProjectService.findPage(pageable));
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(DevProject project, RedirectAttributes redirectAttributes) {
		if (!isValid(project)) {
			return ERROR_VIEW;
		}
		if (project.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("项目名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (project.getDescription() == null) {
			addFlashMessage(redirectAttributes, Message.error("项目描述是必填项哦！"));
			return "redirect:list.jhtml";
		}
		devProjectService.save(project);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		devProjectService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("project", devProjectService.find(id));
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(DevProject project, RedirectAttributes redirectAttributes) {
		if (!isValid(project)) {
			return ERROR_VIEW;
		}
		if (project.getName() == null) {
			addFlashMessage(redirectAttributes, Message.error("项目名称是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (project.getDescription() == null) {
			addFlashMessage(redirectAttributes, Message.error("项目描述是必填项哦！"));
			return "redirect:list.jhtml";
		}
		DevProject persistant = devProjectService.find(project.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(project, persistant, new String[] { BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME });
			devProjectService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	@RequestMapping("/check_name_unique")
	@ResponseBody
	public Boolean checkNameUnique(final String previousName, final String name) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}
		if (StringUtils.equalsIgnoreCase(previousName, name)) {
			return true;
		}
		return devProjectService.find(Filter.eq("name", name, false)) == null;
	}

}
