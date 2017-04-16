<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>${message("admin.storagePlugin.list")}</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="systemGroup" subMenu="storagePlugin" ]
			
			<!-- 引入左侧菜单部分 -->
			[#include "/admin/include/sidebar.ftl"]
			
			<!-- 页面主题部分开始 -->
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
								<span>${message("admin.storagePlugin.list")}</span>
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
						
						<!-- 列表表单开始 -->
						<form id="listForm" action="list.jhtml" method="get">
							
							<!-- 列表工具条部分 -->
							<div class="page-header">
								
								<!-- 增删刷新 按钮 -->
								<div class="btn-group btn-corner">
								    <a href="javascript:;" id="refreshButton" class="btn btn-success btn-sm">
								        <i class="glyphicon glyphicon-refresh"></i>
								        <span>${message("admin.common.refresh")}</span>
								    </a>
								</div>

							</div><!-- 列表工具条部分结束/.page-header -->
							
							<!-- 列表表单开始 -->
							<div class="row">
								<div class="col-xs-12">
									<!-- 表格开始 -->
									<table id="listTable" class="list table table-bordered table-hover">
										<thead>
											<tr>
												<th>
													<span>${message("StoragePlugin.name")}</span>
												</th>
												<th>
													<span>${message("StoragePlugin.version")}</span>
												</th>
												<th>
													<span>${message("StoragePlugin.author")}</span>
												</th>
												<th>
													<span>${message("StoragePlugin.isEnabled")}</span>
												</th>
												<th>
													<span>${message("admin.common.handle")}</span>
												</th>
											</tr>
										</thead>
										<tbody>
										[#list storagePlugins as storagePlugin]
											<tr>
												<td>
													[#if storagePlugin.siteUrl??]
														<a href="${storagePlugin.siteUrl}" target="_blank">${storagePlugin.name}</a>
													[#else]
														${storagePlugin.name}
													[/#if]
												</td>
												<td>
													${storagePlugin.version!'-'}
												</td>
												<td>
													${storagePlugin.author!'-'}
												</td>
												<td>
													<span class="${storagePlugin.isEnabled?string("true", "false")}Icon">&nbsp;</span>
												</td>
												<td>
													<!-- 大型ui -->
													<div class="hidden-sm hidden-xs btn-group">
														[#if storagePlugin.isInstalled]
															[#if storagePlugin.settingUrl??]
																<a class="btn btn-xs btn-info" href="${storagePlugin.settingUrl}">[${message("admin.storagePlugin.setting")}]</a>
															[/#if]
															[#if storagePlugin.uninstallUrl??]
																<a class="uninstall btn btn-xs btn-info" href="${storagePlugin.uninstallUrl}" >[${message("admin.storagePlugin.uninstall")}]</a>
															[/#if]
														[#else]
															[#if storagePlugin.installUrl??]
																<a class="install btn btn-xs btn-info" href="${storagePlugin.installUrl}" >[${message("admin.storagePlugin.install")}]</a>
															[/#if]
														[/#if]
													</div>
													<!-- 小型ui -->
													<div class="hidden-md hidden-lg">
														<div class="inline pos-rel">
															<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
																<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
															</button>
															<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																[#if storagePlugin.isInstalled]
																	[#if storagePlugin.settingUrl??]
																		<li>
																			<a href="${storagePlugin.settingUrl}" class="tooltip-success" data-rel="tooltip" title="Edit">
																				<span class="blue">
																					<span>${message("admin.storagePlugin.setting")}</span>
																				</span>
																			</a>
																		</li>
																	[/#if]
																	[#if storagePlugin.uninstallUrl??]
																		<li>
																			<a href="${storagePlugin.uninstallUrl}" class="uninstall tooltip-success" data-rel="tooltip" title="Edit">
																				<span class="blue">
																					<span>${message("admin.storagePlugin.uninstall")}</span>
																				</span>
																			</a>
																		</li>
																	[/#if]
																[#else]
																	[#if storagePlugin.installUrl??]
																		<li>
																			<a href="${storagePlugin.installUrl}" class="install tooltip-success" data-rel="tooltip" title="Edit">
																				<span class="blue">
																					<span>${message("admin.storagePlugin.install")}</span>
																				</span>
																			</a>
																		</li>
																	[/#if]
																[/#if]
																
															</ul>
														</div>
													</div>
												</td>
											</tr>
										[/#list]
										</tbody>
									</table><!-- /表格部分结束 -->
									
								</div><!-- /.col -->
							</div><!-- /.row -->
						</form><!-- /列表表单结束 /form -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- 页面主题部分开始 - /.main-content -->
			
			<!-- 引入页脚部分 -->
			[#include "/admin/include/footer.ftl"]
			
		</div><!-- /.main-container -->

		<!-- 引入公共基础脚本 -->
		[#include "/admin/include/scripts.ftl"]

		<!-- 页面自定义插件脚本引入区 page specific plugin scripts -->
		<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
		
		<!-- 页面自定义内联脚本区 inline scripts related to this page -->
		<script type="text/javascript">
			$().ready(function() {
			
				var $listTable = $("#listTable");
				var $install = $("#listTable a.install");
				var $uninstall = $("#listTable a.uninstall");
				
				[@flash_message /]
			
				// 安装
				$install.click(function() {
					var $this = $(this);
					$.dialog({
						type: "warn",
						content: "${message("admin.storagePlugin.installConfirm")}",
						onOk: function() {
							$.ajax({
								url: $this.attr("href"),
								type: "POST",
								dataType: "json",
								cache: false,
								success: function(message) {
									if (message.type == "success") {
										location.reload(true);
									} else {
										$.message(message);
									}
								}
							});
						}
					});
					return false;
				});
				
				// 卸载
				$uninstall.click(function() {
					var $this = $(this);
					$.dialog({
						type: "warn",
						content: "${message("admin.storagePlugin.uninstallConfirm")}",
						onOk: function() {
							$.ajax({
								url: $this.attr("href"),
								type: "POST",
								dataType: "json",
								cache: false,
								success: function(message) {
									if (message.type == "success") {
										location.reload(true);
									} else {
										$.message(message);
									}
								}
							});
						}
					});
					return false;
				});
			
			});
		</script>

	</body>
</html>
