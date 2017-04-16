package com.app.buzz.dev.web.curd.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.Filter;
import com.app.Message;
import com.app.buzz.dev.bean.CurdGenType;
import com.app.buzz.dev.entity.DevConfig;
import com.app.buzz.dev.entity.DevEntity;
import com.app.buzz.dev.web.curd.CurdBaseController;
import com.app.buzz.dev.web.curd.tools.CurdTools;

/**
 * CURD 控制器
 * 
 * @author APP TEAM
 * @version 1.0
 */
@RequestMapping(value = "/admin/dev/curd")
@Controller
public class CurdController extends CurdBaseController {

	@Override
	protected String getViewPath() {
		return "/com/app/buzz/dev/web/curd/view";
	}

	@Resource
	private CurdTools curdTools;

	/**
	 * 生成文件
	 */
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	@ResponseBody
	public Message generate(Long[] ids, CurdGenType curdGenType) {
		// 检查是否存在唯一启用的配置。
		List<DevConfig> devConfigs = devConfigService.findList(null, new ArrayList<Filter>() {
			private static final long serialVersionUID = 1468530287667434194L;

			{
				add(Filter.eq("isCurrent", true, false));
			}
		}, null);
		if (devConfigs == null || devConfigs.size() != 1) {
			return Message.error("当前无唯一启用的配置，请在【基本配置】中设置！");
		}
		// 设置唯一启用配置(注意每次都要设置，因为每次生成之间配置可能会被修改)
		curdTools.setDevConfig(devConfigs.get(0));
		curdTools.initTemplateFtl();
		// 检查ids合法性。
		List<DevEntity> devEntitys = devEntityService.findList(ids);
		if (devEntitys == null || devEntitys.size() <= 0) {
			return Message.error("请至少选择一个实体！");
		}
		// 生成文件情况统计。
		int total = devEntitys.size();
		int success = 0;
		int fail = 0;
		// className->errorInfo
		Map<String, Object> errorInfo = new HashMap<String, Object>();
		// 开始根据生成类型生成文件。
		if (curdGenType != null) {
			switch (curdGenType) {
			case gen_entity:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateEntity2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_dao:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateEntityDao2file(devEntity);
						curdTools.generateEntityDaoImpl2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_service:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateEntityService2file(devEntity);
						curdTools.generateEntityServiceImpl2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_controller:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateEntityController2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_java:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateEntity2file(devEntity);
						curdTools.generateEntityDao2file(devEntity);
						curdTools.generateEntityDaoImpl2file(devEntity);
						curdTools.generateEntityService2file(devEntity);
						curdTools.generateEntityServiceImpl2file(devEntity);
						curdTools.generateEntityController2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_list:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateListFtl2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_add:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateAddFtl2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_edit:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateEditFtl2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_page:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateListFtl2file(devEntity);
						curdTools.generateAddFtl2file(devEntity);
						curdTools.generateEditFtl2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_quick_info:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateQuickInfo2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			case gen_all:
				for (DevEntity devEntity : devEntitys) {
					try {
						curdTools.generateEntity2file(devEntity);
						curdTools.generateEntityDao2file(devEntity);
						curdTools.generateEntityDaoImpl2file(devEntity);
						curdTools.generateEntityService2file(devEntity);
						curdTools.generateEntityServiceImpl2file(devEntity);
						curdTools.generateEntityController2file(devEntity);
						curdTools.generateListFtl2file(devEntity);
						curdTools.generateAddFtl2file(devEntity);
						curdTools.generateEditFtl2file(devEntity);
						curdTools.generateQuickInfo2file(devEntity);
						success += 1;
					} catch (Exception e) {
						fail += 1;
						errorInfo.put(devEntity.getClassName(), e.toString());
						if (curdTools.getDevConfig().getIsDebug() != null && curdTools.getDevConfig().getIsDebug()) {
							System.out.println(e);
						}
					}
				}
				break;
			}
			if (fail == 0) {
				return Message.success("共" + total + "个实体，生成成功" + success + "个，失败" + fail + "个！").setData(errorInfo);
			} else {
				return Message.error("共" + total + "个实体，生成成功" + success + "个，失败" + fail + "个！").setData(errorInfo);
			}
		} else {
			return ERROR_MESSAGE;
		}
	}
}
