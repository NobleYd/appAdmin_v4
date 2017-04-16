package com.app.buzz.dev.web.curd;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.Filter;
import com.app.Message;
import com.app.Pageable;
import com.app.buzz.dev.bean.CurdGenType;
import com.app.buzz.dev.entity.DevAttribute;
import com.app.buzz.dev.entity.DevAttribute.N2OneType;
import com.app.buzz.dev.entity.DevConfig;
import com.app.buzz.dev.entity.DevEntity;
import com.app.buzz.dev.entity.DevProject;
import com.app.buzz.dev.entity.DevEntity.EntityType;
import com.app.buzz.dev.service.DevAttributeService;
import com.app.buzz.dev.service.DevConfigService;
import com.app.buzz.dev.service.DevEntityService;
import com.app.buzz.dev.service.DevProjectService;
import com.app.controller.admin.BaseController;
import com.app.entity.BaseEntity;

/**
 * CURD 控制器基类
 * 
 * @author APP TEAM
 * @version 1.0
 */
public abstract class CurdBaseController extends BaseController {

	protected String viewPath;

	abstract protected String getViewPath();

	@Resource
	protected DevConfigService devConfigService;

	@Resource
	protected DevEntityService devEntityService;

	@Resource
	protected DevAttributeService devAttributeService;

	@Resource
	protected DevProjectService devProjectService;

	public CurdBaseController() {
		super();
		viewPath = getViewPath();
	}

	/**
	 * 列表
	 */
	@SuppressWarnings("serial")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, Long devProjectId, ModelMap model) {
		// 获取唯一启用配置（此处不需要严格判断唯一）。
		DevConfig devConfig = new DevConfig();
		List<DevConfig> devConfigs = devConfigService.findList(null, new ArrayList<Filter>() {
			{
				add(Filter.eq("isCurrent", true, false));
			}
		}, null);
		if (devConfigs != null && devConfigs.size() >= 1) {
			devConfig = devConfigs.get(0);
		}
		if (devProjectId != null) {
			DevProject devProject = devProjectService.find(devProjectId);
			if (devProject != null) {
				List<Long> ids = new ArrayList<Long>();
				for (DevEntity devEntity : devProject.getDevEntities()) {
					ids.add(devEntity.getId());
				}
				pageable.getFilters().add(Filter.in("id", ids));
				model.addAttribute("devProjectId", devProjectId);
			}
		}
		model.addAttribute("page", devEntityService.findPage(pageable));
		model.addAttribute("devProjects", devProjectService.findAll());
		model.put("curdGenTypes", CurdGenType.values());
		model.addAttribute("devConfig", devConfig);
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
		model.put("n2oneTypes", N2OneType.values());
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
	 * 为指定实体新增加一个属性配置。
	 */
	@RequestMapping(value = "/attr_save", method = RequestMethod.POST)
	@ResponseBody
	public Message newAttrSave(Long devEntityId, Long relatedDevEntityId, DevAttribute devAttribute) {
		if (!isValid(devAttribute)) {
			return ERROR_MESSAGE;
		}
		// 合法检测和转换
		devAttribute.validate();

		if (StringUtils.isBlank(devAttribute.getAttributeName())) {
			return Message.error("属性名称是必填项哦！");
		}
		if (StringUtils.isBlank(devAttribute.getAttributeDesc())) {
			return Message.error("属性注释是必填项哦！");
		}
		if (StringUtils.isBlank(devAttribute.getAttributeType())) {
			return Message.error("属性类型是必填项哦！");
		}

		// 如果是关联属性
		if (devAttribute.isAttributeTypeBeRelatedDevEntity()) {
			// 根据title找到关联实体
			DevEntity relatedDevEntity = devEntityService.find(relatedDevEntityId);
			if (relatedDevEntity == null) {
				return Message.error("关联实体【ID】是必填项哦！");
			}
			// 如果是N-1关联，必须填写搜索属性。
			if (devAttribute.isAttributeTypeBeN_1() && devAttribute.isN2OneTypeBeSearch()) {
				if (StringUtils.isBlank(devAttribute.getRelatedDevEntityAttributeNames())) {
					return Message.error("关联实体的搜索属性是必填项哦！");
				} else {
					// 检测搜索属性是否是关联实体中存在的属性，类型是否是String，非String类型暂不支持搜索。
					for (String relatedDevEntityAttributeName : devAttribute.getRelatedDevEntityAttributeNamesArray()) {
						DevAttribute relatedDevEntityAttribute = relatedDevEntity.getDevAttribute(relatedDevEntityAttributeName);
						if (relatedDevEntityAttribute == null) {
							return Message.error("关联实体的搜索属性【" + relatedDevEntityAttributeName + "】不存在哦！");
						} else if (!relatedDevEntityAttribute.isAttributeTypeBeString()) {
							return Message.error("关联实体的搜索属性【" + relatedDevEntityAttributeName + "】类型不正确，必须是String类型哦！");
						}
					}
				}
			}
			devAttribute.setRelatedDevEntity(relatedDevEntity);
		}

		DevEntity devEntity = devEntityService.find(devEntityId);
		if (devEntity == null) {
			return ERROR_MESSAGE;
		}
		devAttribute.setDevEntity(devEntity);

		// 如果没有设置sortIndex，则设置为最大值+1.
		if (devAttribute.getSortIndex() == null) {
			devAttribute.setSortIndex(1 + devEntity.getMaxAttributeSortIndex());
		}

		// 合法检测和转换(第二次检测，因为这个时候设置了关联的实体，需要检测当前属性的设置是否符合关联实体类型的要求)
		devAttribute.validate();

		// save
		devAttributeService.save(devAttribute);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 删除属性
	 */
	@RequestMapping(value = "/attr_delete")
	public @ResponseBody Message attrDelete(Long[] ids) {
		devAttributeService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 更新属性
	 */
	@RequestMapping(value = "/attr_update")
	public @ResponseBody Message attrUpdate(Long devAttributeId, String paramName, String paramValue) {
		DevAttribute devAttribute = devAttributeService.find(devAttributeId);
		if (devAttribute == null) {
			return Message.error("不存在属性配置id=" + devAttributeId);
		}
		Object paramValueObj = paramValue;
		if ("n2oneType".equals(paramName)) {
			paramValueObj = N2OneType.valueOf(paramValue);
		}
		try {
			org.apache.commons.beanutils.BeanUtils.setProperty(devAttribute, paramName, paramValueObj);
		} catch (Exception e) {
			return ERROR_MESSAGE;
		}
		// 合法检测和转换
		devAttribute.validate();
		// 如果没修改成功（返回错误消息）
		try {
			if (!org.apache.commons.beanutils.BeanUtils.getProperty(devAttribute, paramName).equalsIgnoreCase(paramValueObj.toString())) {
				return ERROR_MESSAGE;
			}
		} catch (Exception e) {
			return ERROR_MESSAGE;
		}
		// 合法检测和转换
		devAttribute.validate();
		// 如果没修改成功（返回错误消息）
		try {
			if (!org.apache.commons.beanutils.BeanUtils.getProperty(devAttribute, paramName).equalsIgnoreCase(paramValueObj.toString())) {
				return ERROR_MESSAGE;
			}
		} catch (Exception e) {
			return ERROR_MESSAGE;
		}
		devAttributeService.update(devAttribute);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 更新Boolean类型属性
	 */
	@RequestMapping(value = "/attr_boolean_update")
	public @ResponseBody Message attrUpdate(Long devAttributeId, String paramName, Boolean paramValue) {
		DevAttribute devAttribute = devAttributeService.find(devAttributeId);
		if (devAttribute == null) {
			return Message.error("不存在属性配置id=" + devAttributeId);
		}
		try {
			org.apache.commons.beanutils.BeanUtils.setProperty(devAttribute, paramName, paramValue);
		} catch (Exception e) {
			return ERROR_MESSAGE;
		}
		// 合法检测和转换
		devAttribute.validate();
		// 如果没修改成功（返回错误消息）
		try {
			if (!org.apache.commons.beanutils.BeanUtils.getProperty(devAttribute, paramName).equalsIgnoreCase(paramValue.toString())) {
				return ERROR_MESSAGE;
			}
		} catch (Exception e) {
			return ERROR_MESSAGE;
		}
		devAttributeService.update(devAttribute);
		return SUCCESS_MESSAGE;
	}

}
