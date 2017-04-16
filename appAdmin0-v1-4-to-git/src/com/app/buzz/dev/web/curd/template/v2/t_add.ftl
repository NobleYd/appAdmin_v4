<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>添加${(devEntity.classNameDesc)!'-'} - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
	<#if devEntity.hasAnyDateAttribute() >
		<link rel="stylesheet" href="${r'$'}{base}/resources/admin/datePicker/skin/WdatePicker.css" />
	</#if>
	
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="devGroup" subMenu="${(devEntity.unCapitalizedClassName)!'-'}" ]
			
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
								<a href="${r'$'}{base}/admin/common/main.jhtml">${r'$'}{message("admin.path.index")}</a>
							</li>
							<li class="active">
								<span>添加${(devEntity.classNameDesc)!'-'}</span>
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
						
						<!-- 表单开始 -->
						<form id="inputForm" action="save.jhtml" class="form-horizontal" role="form" method="post" <#if devEntity.hasAnyFileTypeAttribute() >enctype="multipart/form-data"</#if> >
							<div class="row">
								<div class="col-xs-12">
									<div class="tabbable">
									    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
									        <li class="active">
									            <a aria-expanded="true" data-toggle="tab" href="#base">${r'$'}{message("admin.common.base")}</a>
									        </li>
									    </ul>
									    <div class="tab-content">
									        <div id="base" class="tab-pane active">
											<#-- 如果是树形实体，此处增加俩行，一行是parent，一行是sort次序。 -->
											<#if devEntity.isTreeEntity() >
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="parentId">
														父${(devEntity.classNameDesc)!'-'}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select id="parentId" name="parentId" class="col-xs-10 col-sm-5" >
																<option value="">顶级${(devEntity.classNameDesc)!'-'}</option>
																[#list ${(devEntity.unCapitalizedClassName)!'-'}Tree as ${(devEntity.unCapitalizedClassName)!'-'}Tmp ]
																	<option value="${r'$'}{${(devEntity.unCapitalizedClassName)!'-'}Tmp.id}" >
																		[#if ${(devEntity.unCapitalizedClassName)!'-'}Tmp.grade != 0]
																			[#list 1..${(devEntity.unCapitalizedClassName)!'-'}Tmp.grade as i]
																				&nbsp;&nbsp;
																			[/#list]
																		[/#if]
																		${r'$'}{${(devEntity.unCapitalizedClassName)!'-'}Tmp.${(devEntity.titleAttribute)!'-'}}
																	</option>
																[/#list]
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="order">
														次序
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="order" name="order" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
											</#if>
											
											<#list devEntity.sortedAttributes as attribute >
												<div class="form-group">
												<#if attribute.isAttributeTypeBeAnyFile() >	
													<label class="col-sm-3 control-label no-padding-right" for="${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}">
												<#elseif attribute.isAttributeTypeBeN_1() && attribute.isN2OneTypeBeSearch() >
													<label class="col-sm-3 control-label no-padding-right" for="${(attribute.unCapitalizedName)!'-'}Select">
												<#elseif attribute.isAttributeTypeBeN_1() && attribute.isN2OneTypeBeSelect() >
													<label class="col-sm-3 control-label no-padding-right" for="${(attribute.unCapitalizedName)!'-'}Id">
												<#else>
													<label class="col-sm-3 control-label no-padding-right" for="${(attribute.unCapitalizedName)!'-'}">
												</#if>
														<#if attribute.isRequired && !(attribute.isAttributeTypeBeAnyFile()) >
														<span class="text-danger">*</span>
														</#if>
														<span>${(attribute.attributeDesc)!'-'}</span>:
													</label>
													<div class="col-sm-9">
													<#-- 布尔类型 属性处理 -->
													<#if attribute.isAttributeTypeBeBoolean() >
														<div class="clearfix">	
															<div class="checkbox">
																<label>
															        <input id="${(attribute.unCapitalizedName)!'-'}" name="${(attribute.unCapitalizedName)!'-'}" value="true" class="ace ace-switch ace-switch-2" type="checkbox" />
															        <span class="lbl">&nbsp;&nbsp;是</span>
																	<input type="hidden" name="_${(attribute.unCapitalizedName)!'-'}" value="false" />
																</label>
															</div>
														</div>
													<#-- 日期类型 属性处理 -->
													<#elseif attribute.isAttributeTypeBeDate() >
														<div class="clearfix">
															<input type="text" id="${(attribute.unCapitalizedName)!'-'}" name="${(attribute.unCapitalizedName)!'-'}" class="Wdate col-xs-10 col-sm-5" onfocus="WdatePicker()" maxlength="100" />			
														</div>
													<#-- 枚举类型 属性处理 -->
													<#elseif attribute.isAttributeTypeBeEnum() >
														<div class="clearfix">
															<select name="${(attribute.unCapitalizedName)!'-'}" class="col-xs-10 col-sm-5" >
																<#if !(attribute.isRequired) >
																<option value="" >默认值</option>
																</#if>
																[#list ${attribute.capitalizedName}s as ${attribute.capitalizedName}]
																<option value="${r'$'}{${attribute.capitalizedName}}" >${r'$'}{(${attribute.capitalizedName}.label)!'-'}</option>
																[/#list]
															</select>	
														</div>
													<#-- 文件类型 属性处理 -->
													<#elseif attribute.isAttributeTypeBeAnyFile() >	
														<div class="clearfix col-xs-10 col-sm-5">
															<input id="${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}" name="${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}" type="file" class="" >
														</div>
													<#-- N-1关联类型 属性处理(搜索属性) -->
													<#elseif attribute.isAttributeTypeBeN_1() && attribute.isN2OneTypeBeSearch() >
														<div class="clearfix">
															<input type="text" id="${(attribute.unCapitalizedName)!'-'}Select" title="请填写关联实体的 ${(attribute.relatedDevEntityAttributeNames)!'-'} 属性" class="col-xs-5 col-sm-2" />
															&nbsp;&nbsp;
															<label class="selectedLabel control-label" style="display:none;" >
																<span class="red" >当前选择：</span>
																<span class="value" ></span>
															</label>
															&nbsp;&nbsp;
															<input type="hidden" style="width:50px!important;" id="${(attribute.unCapitalizedName)!'-'}Id" name="${(attribute.unCapitalizedName)!'-'}Id" title="请填写关联实体的 【ID】属性" class="col-xs-5 col-sm-2" />
															<a href="javascript:void(0);" style="width:50px!important; display:none;" class="clearInputs btn btn-info btn-xs" >清空</a>
														</div>
													<#-- N-1关联类型 属性处理(下拉列表) -->
													<#elseif attribute.isAttributeTypeBeN_1() && attribute.isN2OneTypeBeSelect() >
														<div class="clearfix">
															<select name="${(attribute.unCapitalizedName)!'-'}Id" class="col-xs-10 col-sm-5" >
																<#if !(attribute.isRequired) >
																<option value="" >默认值</option>
																</#if>
																[#list ${attribute.capitalizedName}s as ${attribute.capitalizedName}]
																<option value="${r'$'}{${attribute.capitalizedName}.id}" >${r'$'}{(${attribute.capitalizedName}.${(attribute.relatedDevEntity.titleAttribute)!'-'})!'-'}</option>
																[/#list]
															</select>
														</div>
													<#-- 1-N和N-N暂无实现，注意不要按照默认实现，否则出错。 -->
													<#elseif attribute.isAttributeTypeBe1_N() >
													<#elseif attribute.isAttributeTypeBeN_N() >
													<#-- 其他类型 属性处理 -->
													<#else>
														<div class="clearfix">
															<input name="${(attribute.unCapitalizedName)!'-'}" type="text" class="col-xs-10 col-sm-5" />
														</div>
													</#if>
													</div><#-- end of col-sm-9 -->
												</div><#-- end of form-group -->
											</#list>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
												    	<span class="input-icon">
													    	<i class="ace-icon fa fa-check white"></i>
													        <input type="submit" class="btn btn-info btn-sm" value="${r'$'}{message("admin.common.submit")}" />
														</span>
												    	<span class="input-icon">
														    <i class="ace-icon fa fa-undo white"></i>
													        <input type="button" value="${r'$'}{message("admin.common.back")}" onclick="window.history.back();" class="btn btn-info btn-sm" />
														</span>
												    </div>
												</div>
									        </div><!-- end of base -->
									    </div>
									</div><!-- /.tabbable 结束-->
								</div><!-- /.col -->
							</div><!-- /.row -->
						</form><!-- /表单结束 /form -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- 页面主题部分开始 - /.main-content -->
			
			<!-- 引入页脚部分 -->
			[#include "/admin/include/footer.ftl"]
			
		</div><!-- /.main-container -->

		<!-- 引入公共基础脚本 -->
		[#include "/admin/include/scripts.ftl"]

		<!-- 页面自定义插件脚本引入区 page specific plugin scripts -->
		<script src="${r'$'}{base}/resources/admin/js/jquery.validate.js"></script>
		
	<#if devEntity.hasAnyN_1Attribute() >
		<script type="text/javascript" src="${r'$'}{base}/resources/admin/js/jquery.autocomplete.js"></script>
	</#if>
	<#if devEntity.hasAnyDateAttribute() >
		<script type="text/javascript" src="${r'$'}{base}/resources/admin/datePicker/WdatePicker.js"></script>
	</#if>

		<!-- 页面自定义内联脚本区 inline scripts related to this page -->
		<script type="text/javascript">
		$().ready(function() {

			[@flash_message /]

			var $inputForm = $("#inputForm");
			
			// 表单验证
			$inputForm.validate({
				rules: {
					<#list devEntity.sortedAttributes as attribute >
					<#-- 文件类型的字段name -->
					<#if attribute.isAttributeTypeBeAnyFile() >
					${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}: {
					<#-- N-1类型的字段name -->
					<#elseif attribute.isAttributeTypeBeN_1() >
					${(attribute.unCapitalizedName)!'-'}Id: {
					<#-- 普通其他类型的字段name -->
					<#else>
					${(attribute.unCapitalizedName)!'-'}: {
					</#if>
					<#if attribute.isRequired >
						required: true,
					</#if>
					<#if attribute.isUnique >
						remote: {
							url: "check_${(attribute.unHumpName)!'-'}_unique.jhtml",
							cache: false
						},
					</#if>
					<#if attribute.isAttributeTypeBeLong() || attribute.isAttributeTypeBeInteger() >
						digits: true,
					<#elseif attribute.isAttributeTypeBeBigDecimal() >
						decimal: {
							integer: 12,
							fraction: ${r'$'}{setting.bigDecimalScale}
						},
					</#if>
					},
					</#list>
				},
				messages: {
		<#list devEntity.sortedAttributes as attribute >
			<#-- 文件类型的字段name -->
			<#if attribute.isAttributeTypeBeAnyFile() >
					${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}: {
			<#-- N-1类型的字段name -->
			<#elseif attribute.isAttributeTypeBeN_1() >
					${(attribute.unCapitalizedName)!'-'}Id: {
			<#-- 普通其他类型的字段name -->
			<#else>
					${(attribute.unCapitalizedName)!'-'}: {
			</#if>
			<#if attribute.isUnique >
						remote: "已经存在，不允许重复！",
			</#if>
			<#if attribute.attributeType == "Long" || attribute.attributeType == "Integer" >
						digits: "${r'$'}{message("admin.validate.digits")}",
			<#elseif attribute.attributeType == "BigDecimal" >
						decimal: "${r'$'}{message("admin.validate.decimal")}",
			</#if>
					},
		</#list>
				}
			});

		<#-- N-1关联类型选择 处理 -->
		<#list devEntity.sortedAttributes as attribute >
		<#if attribute.isAttributeTypeBeN_1() && attribute.isN2OneTypeBeSearch() > 
			//${(attribute.unCapitalizedName)!'-'} 的 autocomplete 实现
			var ${r'$'}${(attribute.unCapitalizedName)!'-'}Select = $("input#${(attribute.unCapitalizedName)!'-'}Select");
			var ${r'$'}${(attribute.unCapitalizedName)!'-'}ClearFixDiv = ${r'$'}${(attribute.unCapitalizedName)!'-'}Select.parent(".clearfix");
			var ${r'$'}${(attribute.unCapitalizedName)!'-'}Id = $("input#${(attribute.unCapitalizedName)!'-'}Id");
			${r'$'}${(attribute.unCapitalizedName)!'-'}Select.autocomplete("${r'$'}{base}${(devConfig.request_mapping_prefix_defaule)!''}/${(devEntity.unHumpClassName)!'-'}/search${(attribute.capitalizedName)!'-'}.jhtml", {
				dataType: "json",
				minChars: 0, //设置为0需要双击显示
				max: 20,
				width: 200,
				scrollHeight: 300,
				parse: function(data) {
					return $.map(data, function(item) {
						return {
							data: item,
							value: item.${(attribute.relatedDevEntity.titleAttribute)!'-'}
						}
					});
				},
				formatItem: function(item) {
					return '<span title="' + item.${(attribute.relatedDevEntity.titleAttribute)!'-'} + '">' + item.${(attribute.relatedDevEntity.titleAttribute)!'-'} + '<\/span>';
				}
			}).result(function(event, item) {
				$(this).val(item.${(attribute.relatedDevEntity.titleAttribute)!'-'});
				${r'$'}${(attribute.unCapitalizedName)!'-'}Id.val(item.id);
				${r'$'}${(attribute.unCapitalizedName)!'-'}ClearFixDiv.find("label span.value").html(item.${(attribute.relatedDevEntity.titleAttribute)!'-'});
				${r'$'}${(attribute.unCapitalizedName)!'-'}ClearFixDiv.find("label").show();
				${r'$'}${(attribute.unCapitalizedName)!'-'}ClearFixDiv.find("a.clearInputs").show();
			});	

		</#if>
		</#list>

		<#-- N-1关联属性的清空按钮功能实现 -->
		<#if devEntity.hasAnyN_1Attribute() >
			//N-1关联属性的清空按钮功能实现
			$(".clearInputs").unbind('click').bind('click', function(){
				//清空属性输入框和ID输入框
				$(this).siblings("input").val('');
				//清空当前选择提示span内容
				$(this).siblings("label").find("span.value").html('');
				//隐藏自身和当前选择label
				$(this).siblings("label").hide();
				$(this).hide();
			});
		</#if>
		
		<#-- 文件类型 -->
		<#if devEntity.hasAnyFileTypeAttribute() >
			$('<#list devEntity.sortedFileAttributes as attribute ><#if attribute.isAttributeTypeBeAnyFile() ><#if attribute_index == 0>#${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}<#else>,#${(attribute.unCapitalizedName)!'-'}${(attribute.attributeType)!'-'}</#if></#if></#list>').ace_file_input({
				style:"well",
				btn_choose: 'Drop files here or click to choose',
				btn_change: null,
				no_icon: 'ace-icon fa fa-cloud-upload',
				droppable: true,
				thumbnail: 'small'
				/*
				,before_change:function(files, dropped) {
					//Check an example below
					//or examples/file-upload.html
					return true;
				}
				,before_remove : function() {
					return true;
				}
				*/
		
			}).on('change', function(){
				//console.log($(this).data('ace_input_files'));
				//console.log($(this).data('ace_input_method'));
			});
		</#if>

		});
		</script>

	</body>
</html>
