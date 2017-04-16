<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>树形实体测试列表 - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="demoGroup" subMenu="treeEntityTest" ]
			
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
								<span>树形实体测试列表</span>
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
									<a href="add.jhtml" class="btn btn-info btn-sm">
										<i class="glyphicon glyphicon-plus"></i>
										<span>${message("admin.common.add")}</span>
									</a>
								    <a href="javascript:;" id="refreshButton" class="btn btn-success btn-sm">
								        <i class="glyphicon glyphicon-refresh"></i>
								        <span>${message("admin.common.refresh")}</span>
								    </a>
								</div>
							
							
								
							</div><!-- 列表工具条部分结束/.page-header -->
							
							<!-- 列表表格开始 -->
							<div class="row">
								<div class="col-xs-12">
									<!-- 表格开始 -->
									<table id="listTable" class="list table table-bordered table-hover">
										<thead>
											<tr>
												<th>
													<span name="name">标题</span>
												</th>
												<th>
													<span name="order">次序</span>
												</th>
												<th>
													<span name="description">描述</span>
												</th>
												<th>
													<span>${message("admin.common.handle")}</span>
												</th>
											</tr>
										</thead>
										<tbody>
										[#list treeEntityTestTree as treeEntityTest ]
											<tr>
												<td>	
													<span style="margin-left: ${treeEntityTest.grade * 20}px;[#if treeEntityTest.grade == 0] color: #000000;[/#if]" name="name">${(treeEntityTest.name)!}</span>
												</td>
												<td>
													<span>${(treeEntityTest.order)!'-'}</span>
												</td>
												<td>				
													<span name="description">${(treeEntityTest.description)!}</span>
												</td>
												<td>
													<!-- 大型ui -->
													<div class="hidden-sm hidden-xs btn-group">
														<a class="btn btn-xs btn-info" href="edit.jhtml?id=${treeEntityTest.id}" >
															<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
															<span>${message("admin.common.edit")}</span>
														</a>
														<a href="javascript:void(0);" class="deleteButton btn btn-xs btn-danger" ids="${treeEntityTest.id}" >
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
															<span>${message("admin.common.delete")}</span>
														</a>
													</div>
													<!-- 小型ui -->
													<div class="hidden-md hidden-lg">
														<div class="inline pos-rel">
															<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
																<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
															</button>
															<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																<li>
																	<a href="edit.jhtml?id=3" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="blue">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			<span>${message("admin.common.edit")}</span>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="javascript:void(0);" class="deleteButton tooltip-error" ids="${treeEntityTest.id}" data-rel="tooltip" title="Delete">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			<span>${message("admin.common.delete")}</span>
																		</span>
																	</a>
																</li>
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
			[@flash_message /]
			
		[#-- list页表格单行操作按钮列的删除功能 --]
			var $listTable = $("#listTable");
			var $deleteButton2 = $(".deleteButton");
			$deleteButton2.click( function() {
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
							url: "delete.jhtml",
							type: "POST",
							data: {ids:$ids},
							dataType: "json",
							cache: false,
							success: function(message) {
								$.message(message);
								if (message.type == "success") {
									$this.closest("tr").remove();
									if ($listTable.find("tr").size() <= 1) {
										setTimeout(function() {
											location.reload(true);
										}, 2000);
									}
								}
								$deleteButton2.removeClass("disabled");
							}
						});
					},
					onCancel: function(){
						$deleteButton2.removeClass("disabled");
					}
				});
			});
			
		</script>

	</body>
</html>
