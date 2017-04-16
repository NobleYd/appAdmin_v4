<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>编辑实体配置</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
		<style>
		
		.w-15-1{
			width:15px !important;
		}
		.w-15-2{
			width:30px !important;
		}
		.w-15-3{
			width:45px !important;
		}
		.w-15-4{
			width:60px !important;
		}
		.w-15-5{
			width:75px !important;
		}
		.w-15-6{
			width:90px !important;
		}
		.w-15-7{
			width:105px !important;
		}
		.w-15-8{
			width:120px !important;
		}
		.w-15-9{
			width:135px !important;
		}
		.w-15-10{
			width:150px !important;
		}
		.w-short{
			width:60px !important;
		}
		
		.fs-12{
			font-size: 12px !important;
		}
		.fs-13{
			font-size: 13px !important;
		}
		.fs-14{
			font-size: 14px !important;
		}
		.fs-15{
			font-size: 15px !important;
		}
		
		.ff-consolas{
			font-family: consolas;
		}
		
		.none{
			display:none;
		}
		
		</style>
		
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="devGroup" subMenu="devCurd" ]
			
			<!-- 引入左侧菜单部分 -->
			[#include "/admin/include/sidebar.ftl"]
			
			<!-- 页面主体部分开始 -->
			<div class="main-content">
				
				<!-- 主页面区域 -->
				<div class="main-content-inner">
					
					<!-- 面包屑路径 + 全局搜索 -->
					<div class="breadcrumbs" >
						
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						
						<!-- 面包屑路径 -->
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="${base}/admin/common/main.jhtml">${message("admin.path.index")}</a>
							</li>
							<li class="active">
								<span>编辑实体配置</span>
							</li>
						</ul><!-- /面包屑路径结束/.breadcrumb -->
						
						<!-- 全局搜索 注释掉（暂不支持）。
                        <div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div>
                        -->
                        
					</div><!-- /面包屑路径 + 全局搜索 结束 /.breadcrumbs -->
					
					<!-- 主页面列表部分 -->
					<div class="page-content">
						<!-- 引入设置工具箱部分 -->
						[#include "/admin/include/ace-setting-box.ftl"]
						<div class="row">
							<div class="col-xs-12">
								<div class="tabbable">
								    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
								        <li class="active">
								            <a aria-expanded="true" data-toggle="tab" href="#base">${message("admin.common.base")}</a>
								        </li>
								        <li>
								            <a aria-expanded="true" data-toggle="tab" href="#attributes">属性配置</a>
								        </li>
								    </ul>
								    <div class="tab-content">
								        <div id="base" class="tab-pane active">
									        <!-- 表单开始 -->
											<form id="inputForm" action="update.jhtml" class="form-horizontal" role="form" method="post">
												<!-- 实体ID隐藏域 -->
												<input type="hidden" name="id" value="${devEntity.id}" />
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="entityType">
														<span class="text-danger">*</span>
														实体类型:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select id="entityType" name="entityType" class="col-xs-10 col-sm-5" >
																[#list entityTypes as entityType]
																	<option value="${entityType}" [#if entityType == devEntity.entityType ]selected="selected"[/#if] >${(entityType.label)!'-'}</option>
																[/#list]
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="title">
														<span class="text-danger">*</span>
														备注
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="title" name="title" value="${(devEntity.title)!}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="className">
														<span class="text-danger">*</span>
														类名(英文)
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="className" name="className" value="${(devEntity.className)!}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="classNameDesc">
														<span class="text-danger">*</span>
														类名注释(中文)
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="classNameDesc" name="classNameDesc" value="${(devEntity.classNameDesc)!}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="titleAttribute">
														<span class="text-danger">*</span>
														标识实体的属性(用于实体显示时显示该属性)
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="titleAttribute" name="titleAttribute" value="${(devEntity.titleAttribute)!}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="needAddBtn">
														是否支持添加:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="isCurrent" name="needAddBtn" value="true" [#if devEntity.needAddBtn ]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;支持</span>
																	<input type="hidden" name="_needAddBtn" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="needBatchDeleteBtn">
														是否支持批量删除:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="needBatchDeleteBtn" name="needBatchDeleteBtn" value="true" [#if devEntity.needBatchDeleteBtn ]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;支持</span>
																	<input type="hidden" name="_needBatchDeleteBtn" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="needRefreshBtn">
														是否支持刷新:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="needRefreshBtn" name="needRefreshBtn" value="true" [#if devEntity.needRefreshBtn ]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;支持</span>
																	<input type="hidden" name="_needRefreshBtn" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="needPage">
														是否支持分页:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="needPage" name="needPage" value="true" [#if devEntity.needPage ]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;支持</span>
																	<input type="hidden" name="_needPage" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="needSearch">
														是否支持搜索:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="needSearch" name="needSearch" value="true" [#if devEntity.needSearch ]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;支持</span>
																	<input type="hidden" name="_needSearch" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="needEditBtn">
														是否支持编辑:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="needEditBtn" name="needEditBtn" value="true" [#if devEntity.needEditBtn ]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;支持</span>
																	<input type="hidden" name="_needEditBtn" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="needDeleteBtn">
														是否支持删除:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="needDeleteBtn" name="needDeleteBtn" value="true" [#if devEntity.needDeleteBtn ]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;支持</span>
																	<input type="hidden" name="_needDeleteBtn" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
												    	<span class="input-icon">
													    	<i class="ace-icon fa fa-check white"></i>
													        <input type="submit" class="btn btn-info btn-sm" value="${message("admin.common.submit")}" />
														</span>
												    	<span class="input-icon">
														    <i class="ace-icon fa fa-undo white"></i>
													        <input type="button" value="${message("admin.common.back")}" onclick="window.history.back();" class="btn btn-info btn-sm" />
														</span>
												    </div>
												</div>
									        </form>
								        </div><!-- end of base -->
								        <div id="attributes" class="tab-pane">
											<table class="table table-bordered table-hover">
												<tr>
													<td colspan="15">
														<span>属性列表（双击修改，所属实体、关联实体 不可修改）</span>
														<input type="button" class="btn btn-xs btn-info" value="${message("admin.common.back")}" onclick="window.history.back();" />
													</td>
												</tr>
												<tr class="title">
													<th>
														<span>属性名称</span>
													</th>
													<th>
														<span>属性注释</span>
													</th>
													<th>
														<span>所属实体</span>
													</th>
													<th>
														<span>属性次序</span>
													</th>
													<th>
														<span>属性类型</span>
													</th>
													<th>
														<span>关联实体</span>
													</th>
													<th>
														<span>实现方式</span>
													</th>
													<th>
														<span>搜索属性</span>
													</th>
													<th>
														<span>类型辅助值</span>
													</th>
													<th>
														<span>显示</span>
													</th>
													<th>
														<span>排序</span>
													</th>
													<th>
														<span>搜索</span>
													</th>
													<th>
														<span>必须</span>
													</th>
													<th>
														<span>唯一</span>
													</th>
													<th>
														<span>${message("admin.common.handle")}</span>
													</th>
												</tr>
												[#list devEntity.attributes as devAttribute ]
													<tr devAttributeId="${devAttribute.id}" >
														<td>
															<span name="attributeName" class="editAbleText" val="${(devAttribute.attributeName)!}" >${(devAttribute.attributeName)!'---'}</span>
														</td>
														<td>
															<span name="attributeDesc" class="editAbleText" val="${(devAttribute.attributeDesc)!}">${(devAttribute.attributeDesc)!'---'}</span>
														</td>
														<td>
															<span name="devEntity" val="${(devAttribute.devEntity.className)!}" >${(devAttribute.devEntity.className)!'---'}</span>
														</td>
														<td>
															<span name="sortIndex" class="editAbleText" val="${(devAttribute.sortIndex)!}" >${(devAttribute.sortIndex)!'0'}</span>
														</td>
														<td>
															<span name="attributeType" class="editAbleText" val="${(devAttribute.attributeType)!}" >${(devAttribute.attributeType)!'---'}</span>
														</td>
														<td>
															<span name="relatedDevEntityClassName" val="${(devAttribute.relatedDevEntity.className)!}" >${(devAttribute.relatedDevEntity.className)!'---'}</span>
														</td>
														<td>
															<span class="editAbleText" name="n2oneType" val="${(devAttribute.n2oneType)!}" title="请填写select或者search" >${(devAttribute.n2oneType)!'---'}</span>
														</td>
														<td>
															<span class="editAbleText" name="relatedDevEntityAttributeNames" val="${(devAttribute.relatedDevEntityAttributeNames)!}" >${(devAttribute.relatedDevEntityAttributeNames)!'---'}</span>
														</td>
														<td>
															<span class="editAbleText" name="attributeTypeValue" val="${(devAttribute.attributeTypeValue)!}" >${(devAttribute.attributeTypeValue)!'---'}</span>
														</td>
														<td>
															[#if devAttribute.isShow?? && devAttribute.isShow]
																<span name="isShow" val="false" class="trueIcon editAbleBoolean">&nbsp;</span>
															[#else]
																<span name="isShow" val="true" class="falseIcon editAbleBoolean">&nbsp;</span>
															[/#if]
														</td>
														<td>
															[#if devAttribute.isSort?? && devAttribute.isSort]
																<span name="isSort" val="false" class="trueIcon editAbleBoolean">&nbsp;</span>
															[#else]
																<span name="isSort" val="true" class="falseIcon editAbleBoolean">&nbsp;</span>
															[/#if]
														</td>
														<td>
															[#if devAttribute.isSearch?? && devAttribute.isSearch]
																<span name="isSearch" val="false" class="trueIcon editAbleBoolean">&nbsp;</span>
															[#else]
																<span name="isSearch" val="true" class="falseIcon editAbleBoolean">&nbsp;</span>
															[/#if]
														</td>
														<td>
															[#if devAttribute.isRequired?? && devAttribute.isRequired]
																<span name="isRequired" val="false" class="trueIcon editAbleBoolean">&nbsp;</span>
															[#else]
																<span name="isRequired" val="true" class="falseIcon editAbleBoolean">&nbsp;</span>
															[/#if]
														</td>
														<td>
															[#if devAttribute.isUnique?? && devAttribute.isUnique]
																<span name="isUnique" val="false" class="trueIcon editAbleBoolean">&nbsp;</span>
															[#else]
																<span name="isUnique" val="true" class="falseIcon editAbleBoolean">&nbsp;</span>
															[/#if]
														</td>
														<td>
															<a href="javascript:;" class="deleteButton" ids="${devAttribute.id}">[${message("admin.common.delete")}]</a>
														</td>
													</tr>
												[/#list]
											</table> <!-- end of attribute list-->
											<!-- then is the attribute_add -->
											<form id="inputForm-newAttr" action="new_attr_save.jhtml" method="post">
												<input type="hidden" name="devEntityId" value="${devEntity.id}" />
												<table class="table table-bordered">
													<tr>
														<th colspan="12">
															<span>新增属性</span>
														</th>
													</tr>
													<tr class="title">
														<th>
															<span>属性名称<span class="text-danger">*</span></span>
														</th>
														<th>
															<span>属性注释<span class="text-danger">*</span></span>
														</th>
														<th>
															<span>属性次序</span>
														</th>
														<th>
															<span>属性类型<span class="text-danger">*</span></span>
														</th>
														<th>
															<span>属性配置</span>
														</th>
														<th>
															<span>${message("admin.common.handle")}</span>
														</th>
													</tr>
													<tr style="height:350px;" >
														<td>
															<input name="attributeName" title="属性名称" type="text" class="text w-15-5" />
														</td>
														<td>
															<input name="attributeDesc" title="属性注释" type="text" class="text w-15-5" />
														</td>
														<td>
															<input name="sortIndex" title="属性次序" type="text" class="text w-15-2" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" />
														</td>
														<td id="attributeTypeValueTd" >
															<span><span class="text-danger">*</span>选择：</span>
															<select id="attributeTypeSelect" class="w-15-10" style="margin-bottom:5px;display:block;">
																<option value="String" >String字符串</option>
																<option value="Long" >Long长整数</option>
																<option value="Integer" >Integer整数</option>
																<option value="Date" >Date日期</option>
																<option value="Boolean" >Boolean布尔类型</option>
																<option value="enum" >enum枚举类型</option>
																<option value="N-1" >N-1关联</option>
																[#--
																<option value="1-N" >1-N关联</option>
																<option value="N-N" >N-N关联</option>
																--]
																<option value="File" >File文件</option>
																<option value="Image" >Image图片</option>
																<option value="Media" >Media媒体</option>
																<option value="Flash" >Flash</option>
																<option value="BigDecimal" >BigDecimal大整数</option>
															</select>
															
															<span>或<span class="text-danger">*</span>自定义：</span>
															<input id="attributeType" name="attributeType" title="属性类型" type="text" value="String" class="text w-15-10" style="display:block;" />
															
															<!-- 属性类型辅助值部分，为了显示方便和类型放到了一个td中。 -->
															<div>
																<div class="attributeTypeValue enum none">
																	<span class="clearfix"><span class="text-danger">*</span>枚举辅助值填写：</span>
																	<textarea name="attributeTypeValue" title="属性类型辅助值" class="textarea w-15-10 fs-12 ff-consolas clearfix"></textarea>
																	<span class="clearfix" >枚举类型示例：male("男"),female("女")</span>
																</div>
																<div class="attributeTypeValue relatedDevEntitySelect none">
																	
																	<span title="填写title、className、classNameDesc" >填写关联实体【填其属性】</span>：
																	<input id="relatedDevEntitySelect" title="关联实体选择" class="text w-15-10" />
																	<span class="red" >谨慎填写&nbsp;不可修改</span>
																	
																	<span class="clearfix"><span class="text-danger">*</span>或填写【ID】：</span>
																	<input id="relatedDevEntityId" name="relatedDevEntityId" title="填写title、className、classNameDesc" class="text w-15-10" />
																	<span class="red" >谨慎填写&nbsp;不可修改&nbsp;修改请删除重新增加属性即可</span>
																	
																	<span class="clearfix"><span class="text-danger">*</span>N-1属性输入方式：</span>
																	<select name="n2oneType" class="w-15-10" >
																		[#list n2oneTypes as n2oneType]
																			<option value="${n2oneType}">${(n2oneType.label)!'-'}</option>
																		[/#list]
																	</select>
																	
																	<span class="clearfix"><span class="text-danger">*</span>N-1属性搜索实现方式请填 搜索属性：</span>
																	<input name="relatedDevEntityAttributeNames" title="填写希望搜索的属性，请严格按照大小写书写。" class="text w-15-10" />
																	<span class="clearfix red" >注意：模糊搜索方式：多个属性请使用半角逗号分隔，属性必须是String类型</span>
																	<span class="clearfix red" >注意：准备搜索请只填写一个唯一属性（带唯一验证的），属性必须是String类型</span>
																</div>
															</div>
														</td>
														<td>
															<div class="checkbox">
																<label>
														            <input id="isCurrent" name="isShow" value="true" class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;是否显示</span>
																	<input type="hidden" name="_isShow" value="false" />
																</label>
														    </div>
														    <div class="checkbox">
																<label>
														            <input id="isCurrent" name="isSort" value="true" class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;是否排序</span>
																	<input type="hidden" name="_isSort" value="false" />
																</label>
														    </div>
														    <div class="checkbox">
																<label>
														            <input id="isCurrent" name="isSearch" value="true" class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;是否搜索</span>
																	<input type="hidden" name="_isSearch" value="false" />
																</label>
														    </div>
														    <div class="checkbox">
																<label>
														            <input id="isCurrent" name="isRequired" value="true" class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;是否必须</span>
																	<input type="hidden" name="_isRequired" value="false" />
																</label>
														    </div>
														    <div class="checkbox">
																<label>
														            <input id="isCurrent" name="isUnique" value="true" class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;是否唯一</span>
																	<input type="hidden" name="_isUnique" value="false" />
																</label>
														    </div>
														</td>
														<td>
															<input type="button" class="btn btn-xs btn-info submitBtn" value="提交" />
														</td>
													</tr>
												</table>
											</form>
								        </div><!-- end of attributes -->
								    </div>
								</div><!-- /.tabbable 结束-->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- 页面主题部分开始 - /.main-content -->
			
			<!-- 引入页脚部分 -->
			[#include "/admin/include/footer.ftl"]
			
		</div><!-- /.main-container -->

		<!-- 引入公共基础脚本 -->
		[#include "/admin/include/scripts.ftl"]

		<!-- 页面自定义插件脚本引入区 page specific plugin scripts -->
		<script src="${base}/resources/admin/js/jquery.validate.js"></script>
		
		<script type="text/javascript" src="${base}/resources/admin/js/jquery.autocomplete.js"></script>
		
		<!-- 页面自定义内联脚本区 inline scripts related to this page -->
		<script type="text/javascript">
		
		$().ready(function() {
			[@flash_message /]
			//表单
			var $inputForm = $("#inputForm");
			// 表单验证
			$inputForm.validate({
				rules: {
					entityType: {
						required: true,
					},
					title: {
						required: true,
						remote: {
							url: "check_title_unique.jhtml?previousTitle=${devEntity.title?url}",
							cache: false
						},
					},
					className: {
						required: true,
					},
					classNameDesc: {
						required: true,
					},
					titleAttribute: {
						required: true,
					},
					needAddBtn: {
					},
					needBatchDeleteBtn: {
					},
					needRefreshBtn: {
					},
					needPage: {
					},
					needSearch: {
					},
					needEditBtn: {
					},
					needDeleteBtn: {
					},
				},
				messages: {
					title: {
						remote: "已经存在，不允许重复！",
					},
					className: {
					},
					classNameDesc: {
					},
					needAddBtn: {
					},
					needBatchDeleteBtn: {
					},
					needRefreshBtn: {
					},
					needPage: {
					},
					needSearch: {
					},
					needEditBtn: {
					},
					needDeleteBtn: {
					},
				}
			});


			//新增属性功能
			var $devEntityId = $("#inputForm input[type='hidden'][name='id']").val();
			var $newAttrSubmitBtn = $("input.submitBtn");
			$newAttrSubmitBtn.click(function(){
				var $this = $(this);
		
				var $form2 = $("#inputForm-newAttr");
				var $attributeName = $form2.find("input[name='attributeName']").val();

				if($attributeName == null || $attributeName == undefined || $attributeName == ''){
					$.message({"type": "error", "content": "属性名称不可为空！"});
					return;
				}
				var $attributeDesc = $form2.find("input[name='attributeDesc']").val();
				if($attributeDesc == null || $attributeDesc == undefined || $attributeDesc == ''){
					$.message({"type": "error", "content": "属性注释不可为空！"});
					return;
				}
		
				if ($this.hasClass("disabled")) {
					return false;
				}
				$this.addClass("disabled");
				
				$.ajax({
					url: "attr_save.jhtml",
					type: "POST",
					data: $form2.serialize(),
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							setTimeout(function() {
								location.reload(true);
							}, 1000);
						}
						$this.removeClass("disabled");
					}
				});
			});
			
			//新增属性时 属性类型 选择功能
			var $attributeTypeSelect = $("select#attributeTypeSelect");
			var $attributeTypeValueTd = $("td#attributeTypeValueTd");
			var $attributeTypeInput = $("input#attributeType");
			$attributeTypeSelect.change(function(){
				$attributeTypeValueTd.find("div.attributeTypeValue").hide();
		
				var $this = $(this);
				var $selectedValue = $this.val();
				
				$attributeTypeInput.val($selectedValue);
				
				//特殊类型处理
				if( "enum" === $selectedValue ){
					//枚举类型
					$attributeTypeValueTd.find("div.enum").show();
				}else if( "1-N" === $selectedValue || "N-1" === $selectedValue || "N-N" === $selectedValue ){
					//关联类型
					$attributeTypeValueTd.find("div.relatedDevEntitySelect").show();			
				}
				
			});
			
			//新增属性时 关联实体 选择 的自动填充实现
			var $relatedDevEntitySelect = $("input#relatedDevEntitySelect");
			var $relatedDevEntityId = $("input#relatedDevEntityId");
			$relatedDevEntitySelect.autocomplete("${base}/admin/dev/entity/search.jhtml", {
				dataType: "json",
				minChars: 0, //设置为0需要双击显示
				max: 20,
				width: 200,
				scrollHeight: 300,
				parse: function(data) {
					return $.map(data, function(item) {
						return {
							data: item,
							value: item.title
						}
					});
				},
				formatItem: function(item) {
					return '<span title="' + item.title+ '">' + item.title + '<\/span>';
				}
			}).result(function(event, item) {
				$(this).val(item.title);
				$relatedDevEntityId.val(item.id);
			});
			
			[#-- 属性删除功能 --]
			var $attrTable = $("#attributes table");
			var $deleteButton = $(".deleteButton");
			$deleteButton.click( function() {
				var $this = $(this);
				if ($this.hasClass("disabled")) {
					return false;
				}
				$this.addClass("disabled");
				var $ids = $this.attr("ids");
				$.dialog({
					type: "warn",
					content: message("admin.dialog.deleteConfirm"),
					ok: message("admin.dialog.ok"),
					cancel: message("admin.dialog.cancel"),
					onOk: function() {
						$.ajax({
							url: "attr_delete.jhtml",
							type: "POST",
							data: {ids:$ids},
							dataType: "json",
							cache: false,
							success: function(message) {
								$.message(message);
								if (message.type == "success") {
									$this.closest("tr").remove();
								}
								$deleteButton.removeClass("disabled");
							}
						});
					},
					onCancel: function(){
						$deleteButton.removeClass("disabled");
					}
				});
			});
			
			//实体属性的行内编辑功能实现(Text类型属性)
			var $editAbleTextSpan = $("span.editAbleText");
			var $editAbleTextSpanTd = $editAbleTextSpan.parent();
			$editAbleTextSpanTd.dblclick(function(){
				var $this = $(this);
				var $tr = $this.parent();
				var $span = $this.children("span.editAbleText");
				var $input = $('<input name="" value="" type="text" class="text w-15-6" style="display:none;" />');
				$input.attr("name",$span.attr("name"));
				$input.attr("value",$span.attr("val"));
				$span.after($input);
				$span.hide(100);
				$input.show(200).focus();
				//注册失去焦点事件 更新修改值
				$input.focusout(function(){
					var $this2 = $(this);
					var $devAttributeId = $tr.attr("devAttributeId");
					var $paramName = $this2.attr("name");
					var $paramValue = $this2.val();
					$.ajax({
						url: "attr_update.jhtml",
						type: "POST",
						data: {
							devAttributeId: $devAttributeId,
							paramName: $paramName,
							paramValue: $paramValue
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							$this2.remove();
							if (message.type == "success") {
								if($paramValue == null || $paramValue == undefined || $paramValue == "" ){
									$span.attr("val",'');
									$span.text('---');
								}else{
									$span.attr("val",$paramValue);
									$span.text($paramValue);
								}
							}
							$span.show(200);
						}
					});
				});
				//注册回车事件 更新修改值
				$input.keydown(function(e){
					var $this2 = $(this);
					var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
					if(key == 13) {
						e.preventDefault();
						var $devAttributeId = $tr.attr("devAttributeId");
						var $paramName = $this2.attr("name");
						var $paramValue = $this2.val();
						$.ajax({
							url: "attr_update.jhtml",
							type: "POST",
							data: {
								devAttributeId: $devAttributeId,
								paramName: $paramName,
								paramValue: $paramValue
							},
							dataType: "json",
							cache: false,
							success: function(message) {
								$.message(message);
								$this2.remove();
								if (message.type == "success") {
									if($paramValue == null || $paramValue == undefined || $paramValue == "" ){
										$span.attr("val",'');
										$span.text('---');
									}else{
										$span.attr("val",$paramValue);
										$span.text($paramValue);
									}
								}
								$span.show(200);
							}
						});
					}
				});
			});
			
			//实体属性的行内编辑功能实现(Boolean类型属性)
			
			var $editAbleBooleanSpan = $("span.editAbleBoolean");
			var $editAbleBooleanSpanTd = $editAbleBooleanSpan.parent();
			
			$editAbleBooleanSpanTd.dblclick(function(){
				var $this = $(this);
				var $tr = $this.parent();
				var $span = $this.children("span.editAbleBoolean");
				
				var $devAttributeId = $tr.attr("devAttributeId");
				var $paramName = $span.attr("name");
				var $paramValue = $span.attr("val");
					
				$.ajax({
					url: "attr_boolean_update.jhtml",
					type: "POST",
					data: {
						devAttributeId: $devAttributeId,
						paramName: $paramName,
						paramValue: $paramValue
					},
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$span.toggleClass('trueIcon');
							$span.toggleClass('falseIcon');
						}
					}
				});
			});

		});
		
		</script>

	</body>
</html>
