<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>编辑开发工具配置</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="devGroup" subMenu="devConfig" ]
			
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
								<span>编辑开发工具配置</span>
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
						<form id="inputForm" action="update.jhtml" class="form-horizontal" role="form" method="post">
							<!-- 实体ID隐藏域 -->
							<input type="hidden" name="id" value="${devConfig.id}" />
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
													<label class="col-sm-3 control-label no-padding-right" for="name">
														<span class="text-danger">*</span>
														配置名称:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="name" name="name" value="${(devConfig.name)!}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="isCurrent">
														是否当前启用:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="isCurrent" name="isCurrent" value="true" [#if (devConfig.isCurrent)!]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;启用</span>
																	<input type="hidden" name="_isCurrent" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="isDebug">
														是否开启Debug模式:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="isCurrent" name="isDebug" value="true" [#if (devConfig.isDebug)!]checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;启用</span>
																	<input type="hidden" name="_isDebug" value="false" />
																</label>
														    </div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="commonTemplatePath">
														<span class="text-danger">*</span>
														公共 模板文件路径:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="commonTemplatePath" name="commonTemplatePath" value="${(devConfig.commonTemplatePath)!}" class="col-xs-10 col-sm-5" />
													    </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="customerTemplatePath">
														<span class="text-danger">*</span>
														自定义 模板文件路径:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="customerTemplatePath" name="customerTemplatePath" value="${(devConfig.customerTemplatePath)!}" class="col-xs-10 col-sm-5" />
													    </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="javaOutputPath">
														<span class="text-danger">*</span>
														Java 输出文件路径:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="javaOutputPath" name="javaOutputPath" value="${(devConfig.javaOutputPath)!}" class="col-xs-10 col-sm-5" />
													    </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="pageOutputPath">
														<span class="text-danger">*</span>
														页面 输出文件路径:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="pageOutputPath" name="pageOutputPath" value="${(devConfig.pageOutputPath)!}" class="col-xs-10 col-sm-5" />
													    </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="table_prefix_defaule">
														表前缀默认值:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="table_prefix_defaule" name="table_prefix_defaule" value="${(devConfig.table_prefix_defaule)!}" class="col-xs-10 col-sm-5" />
													    </div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="request_mapping_prefix_defaule">
														映射路径前缀默认值:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="request_mapping_prefix_defaule" name="request_mapping_prefix_defaule" value="${(devConfig.request_mapping_prefix_defaule)!}" class="col-xs-10 col-sm-5" />
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
			var $inputForm = $("#inputForm");
	
			[@flash_message /]
			
			// 表单验证
			$inputForm.validate({
				rules: {
					name: {
						required: true,
						remote: {
							url: "check_name_unique.jhtml?previousName=${devConfig.name?url}",
							cache: false
						},
					},
					commonTemplatePath: {
						required: true,
					},
					customerTemplatePath: {
						required: true,
					},
					javaOutputPath: {
						required: true,
					},
					pageOutputPath: {
						required: true,
					}
				},
				messages: {
					name: {
						remote: "已经存在，不允许重复！",
					}
				}
			});
		});
		</script>
	</body>
</html>
