package com.app.buzz.dev.web.entity.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

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
import com.app.buzz.dev.entity.DevEntity;
import com.app.buzz.dev.entity.DevEntity.EntityType;
import com.app.buzz.dev.entity.DevProject;
import com.app.buzz.dev.service.DevAttributeService;
import com.app.buzz.dev.service.DevEntityService;
import com.app.buzz.dev.service.DevProjectService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;

/**
 * @author APP TEAM
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/dev/entity")
public class DevEntityController extends BaseController {

	protected String viewPath = "/com/app/buzz/dev/web/entity/view";

	@Resource
	private DevEntityService devEntityService;

	@Resource
	private DevAttributeService devAttributeService;

	@Resource
	private DevProjectService devProjectService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, Long devProjectId, ModelMap model) {
		if (devProjectId != null) {
			DevProject devProject = devProjectService.find(devProjectId);
			if (devProject != null && devProject.getDevEntities() != null ) {
				List<Long> ids = new ArrayList<Long>();
				ids.add(-1L);
				for (DevEntity devEntity : devProject.getDevEntities()) {
					ids.add(devEntity.getId());
				}
				pageable.getFilters().add(Filter.in("id", ids));
				model.addAttribute("devProjectId", devProjectId);
			}
		}
		model.addAttribute("page", devEntityService.findPage(pageable));
		model.addAttribute("devProjects", devProjectService.findAll());
		return viewPath + "/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.put("entityTypes", EntityType.values());
		return viewPath + "/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(DevEntity devEntity, RedirectAttributes redirectAttributes) {
		if (!isValid(devEntity)) {
			return ERROR_VIEW;
		}
		if (devEntity.getEntityType() == null) {
			addFlashMessage(redirectAttributes, Message.error("实体类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devEntity.getTitle() == null) {
			addFlashMessage(redirectAttributes, Message.error("备注是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devEntity.getClassName() == null) {
			addFlashMessage(redirectAttributes, Message.error("类名(英文)是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devEntity.getClassNameDesc() == null) {
			addFlashMessage(redirectAttributes, Message.error("类名注释(中文)是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devEntity.getTitleAttribute() == null) {
			addFlashMessage(redirectAttributes, Message.error("实体标识属性是必填项哦！"));
			return "redirect:list.jhtml";
		}

		// 树形实体不支持分页
		// 树形实体不支持批量删除（上下级太复杂）
		// 树形实体不支持属性搜索
		if (devEntity.isTreeEntity()) {
			devEntity.setNeedPage(false);
			devEntity.setNeedBatchDeleteBtn(false);
			devEntity.setNeedSearch(false);
		}

		devEntityService.save(devEntity);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Message delete(Long[] ids) {
		devEntityService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.put("entityTypes", EntityType.values());
		model.addAttribute("devEntity", devEntityService.find(id));
		return viewPath + "/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(DevEntity devEntity, RedirectAttributes redirectAttributes) {
		if (!isValid(devEntity)) {
			return ERROR_VIEW;
		}
		if (devEntity.getEntityType() == null) {
			addFlashMessage(redirectAttributes, Message.error("实体类型是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devEntity.getTitle() == null) {
			addFlashMessage(redirectAttributes, Message.error("备注是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devEntity.getClassName() == null) {
			addFlashMessage(redirectAttributes, Message.error("类名(英文)是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devEntity.getClassNameDesc() == null) {
			addFlashMessage(redirectAttributes, Message.error("类名注释(中文)是必填项哦！"));
			return "redirect:list.jhtml";
		}
		if (devEntity.getTitleAttribute() == null) {
			addFlashMessage(redirectAttributes, Message.error("实体标识属性是必填项哦！"));
			return "redirect:list.jhtml";
		}

		// 树形实体不支持分页
		// 树形实体不支持批量删除（上下级太复杂）
		// 树形实体不支持属性搜索
		if (devEntity.isTreeEntity()) {
			devEntity.setNeedPage(false);
			devEntity.setNeedBatchDeleteBtn(false);
			devEntity.setNeedSearch(false);
		}

		DevEntity persistant = devEntityService.find(devEntity.getId());
		if (persistant != null) {
			BeanUtils.copyProperties(devEntity, persistant, new String[] { BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME });
			devEntityService.update(persistant);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		} else {
			addFlashMessage(redirectAttributes, ERROR_MESSAGE);
		}
		return "redirect:list.jhtml";
	}

	/**
	 * 设置所属的项目
	 */
	@RequestMapping(value = "/add_to_project")
	public @ResponseBody Message add2project(Long[] ids, Long devProjectId) {

		if (devProjectId == null)
			return ERROR_MESSAGE;

		DevProject devProject = devProjectService.find(devProjectId);
		if (devProject == null)
			return ERROR_MESSAGE;

		List<DevEntity> devEntities = devEntityService.findList(ids);
		devProject.setDevEntities(new HashSet<DevEntity>(devEntities));
		devProjectService.update(devProject);

		return SUCCESS_MESSAGE;
	}

	@RequestMapping("/check_title_unique")
	@ResponseBody
	public Boolean checkTitleUnique(final String previousTitle, final String title) {
		if (title == null) {
			return false;
		}
		if (title.equals(previousTitle)) {
			return true;
		}
		return devEntityService.find(Filter.eq("title", title, false)) == null;
	}

	/***
	 * 根据DevEntity的 title、className、classNameDesc 3个属性前缀搜索实体
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody List<DevEntity> productSelect(String q) {
		return devEntityService.search(q, new String[] { "title", "className", "classNameDesc" }, 20);
	}

}
