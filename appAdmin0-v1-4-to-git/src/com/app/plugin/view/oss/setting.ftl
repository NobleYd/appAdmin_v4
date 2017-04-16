<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		<title>${message("admin.plugin.oss.setting")} - Powered By APP TEAM</title>
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="systemGroup" subMenu="storagePlugin" ]
			
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
								<span>${message("admin.plugin.oss.setting")}</span>
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
													<label class="col-sm-3 control-label no-padding-right" for="accessId">
														<span class="text-danger">*</span>
														${message("admin.plugin.oss.accessId")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="accessId" name="accessId" value="${pluginConfig.attributes['accessId']}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="accessKey">
														<span class="text-danger">*</span>
														${message("admin.plugin.oss.accessKey")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="accessKey" name="accessKey" value="${pluginConfig.attributes['accessKey']}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="bucketName">
														<span class="text-danger">*</span>
														${message("admin.plugin.oss.bucketName")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="bucketName" name="bucketName" value="${pluginConfig.attributes['bucketName']}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="endPoint">
														<span class="text-danger">*</span>
														${message("admin.plugin.oss.endPoint")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="endPoint" name="endPoint" value="${pluginConfig.attributes['endPoint']}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="urlPrefix">
														<span class="text-danger">*</span>
														${message("admin.plugin.oss.urlPrefix")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="urlPrefix" name="urlPrefix" value="${pluginConfig.attributes['urlPrefix']}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="order">
														${message("admin.common.order")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="order" name="order" value="${(pluginConfig.order)!}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="isCurrent">
														${message("StoragePlugin.isEnabled")}:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<div class="checkbox">
																<label>
														            <input id="isEnabled" name="isEnabled" value="true" [#if pluginConfig.isEnabled] checked="checked"[/#if] class="ace ace-switch ace-switch-2" type="checkbox" />
														            <span class="lbl">&nbsp;&nbsp;启用</span>
																	<input type="hidden" name="_isEnabled" value="false" />
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
													        <input type="button" value="${message("admin.common.back")}" onclick="location.href='../list.jhtml'" class="btn btn-info btn-sm" />
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
					accessId: "required",
					accessKey: "required",
					bucketName: "required",
					endPoint: "required",
					urlPrefix: "required",
					order: "digits"
				}
			});
		});
		</script>
	</body>
</html>
