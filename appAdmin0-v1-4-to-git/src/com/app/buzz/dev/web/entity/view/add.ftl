<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>添加实体配置</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="devGroup" subMenu="devEntity" ]
			
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
								<span>添加实体配置</span>
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
						<form id="inputForm" action="save.jhtml" class="form-horizontal" role="form" method="post">
							<div class="row">
								<div class="col-xs-12">
									<div class="tabbable">
									    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
									        <li class="active">
									            <a aria-expanded="true" data-toggle="tab" href="#base">${message("admin.common.base")}</a>
									        </li>
									    </ul>
									    <div class="tab-content">
									        <div id="base" class="tab-pane active">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="entityType">
														<span class="text-danger">*</span>
														实体类型:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select id="entityType" name="entityType" class="col-xs-10 col-sm-5" >
																[#list entityTypes as entityType]
																	<option value="${entityType}" >${(entityType.label)!'-'}</option>
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
															<input type="text" id="title" name="title" class="col-xs-10 col-sm-5" />
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
															<input type="text" id="className" name="className" class="col-xs-10 col-sm-5" />
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
															<input type="text" id="classNameDesc" name="classNameDesc" class="col-xs-10 col-sm-5" />
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
															<input type="text" id="titleAttribute" name="titleAttribute" class="col-xs-10 col-sm-5" />
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
														            <input id="isCurrent" name="needAddBtn" value="true" checked="checked" class="ace ace-switch ace-switch-2" type="checkbox" />
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
														            <input id="needBatchDeleteBtn" name="needBatchDeleteBtn" value="true" checked="checked" class="ace ace-switch ace-switch-2" type="checkbox" />
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
														            <input id="needRefreshBtn" name="needRefreshBtn" value="true" checked="checked" class="ace ace-switch ace-switch-2" type="checkbox" />
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
														            <input id="needPage" name="needPage" value="true" checked="checked" class="ace ace-switch ace-switch-2" type="checkbox" />
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
														            <input id="needSearch" name="needSearch" value="true" checked="checked" class="ace ace-switch ace-switch-2" type="checkbox" />
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
														            <input id="needEditBtn" name="needEditBtn" value="true" checked="checked" class="ace ace-switch ace-switch-2" type="checkbox" />
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
														            <input id="needDeleteBtn" name="needDeleteBtn" value="true" checked="checked" class="ace ace-switch ace-switch-2" type="checkbox" />
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
		<script src="${base}/resources/admin/js/jquery.validate.js"></script>
		
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
							url: "check_title_unique.jhtml",
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
		});
		</script>

	</body>
</html>
