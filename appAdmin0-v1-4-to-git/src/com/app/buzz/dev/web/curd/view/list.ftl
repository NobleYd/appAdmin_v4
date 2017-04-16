<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>实体配置列表</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="devGroup" subMenu="devCurd" ]
			
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
								<span>实体配置列表</span>
								<span>(${message("admin.page.total", page.total)})</span>
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
								    <a href="javascript:;" id="deleteButton" class="disabled btn btn-danger btn-sm">
								        <i class="glyphicon glyphicon-trash"></i>
								        <span>${message("admin.common.delete")}</span>
								    </a>
								    <a href="javascript:;" id="refreshButton" class="btn btn-success btn-sm">
								        <i class="glyphicon glyphicon-refresh"></i>
								        <span>${message("admin.common.refresh")}</span>
								    </a>
								</div>
								
								<!-- 页面显示记录条数调整 按钮 -->
								<div class="btn-group">
								    <a id="pageSizeSelect" aria-expanded="false" data-toggle="dropdown" class="btn btn-success btn-sm dropdown-toggle">
								        <span>${message("admin.page.pageSize")}</span>
								        <i class="ace-icon fa fa-angle-down icon-on-right"></i>
								    </a>
								    <ul id="pageSizeOption" class="dropdown-menu dropdown-success">
								        <li [#if page.pageSize == 10] class="active"[/#if]>
											<a href="javascript:;" val="10">10</a>
										</li>
										<li [#if page.pageSize == 20] class="active"[/#if]>
											<a href="javascript:;" val="20">20</a>
										</li>
										<li [#if page.pageSize == 50] class="active"[/#if]>
											<a href="javascript:;" val="50">50</a>
										</li>
										<li [#if page.pageSize == 100] class="active"[/#if]>
											<a href="javascript:;" val="100">100</a>
										</li>
								    </ul>
								</div>
								
								<!-- 工程筛选 回显 -->
								<input type="hidden" name="devProjectId" value="${devProjectId}" />
								<div class="btn-group">
								    <a aria-expanded="false" data-toggle="dropdown" class="btn btn-success btn-sm dropdown-toggle">
								        <span>工程筛选</span>
								        <i class="ace-icon fa fa-angle-down icon-on-right"></i>
								    </a>
								    <ul class="filterOption dropdown-menu dropdown-success">
								        [#list devProjects as devProject]
								        <li name="devProjectId" val="${(devProject.id)!}" >
											<a href="javascript:void(0);" >${(devProject.name)!'-'}</a>
										</li>
										[/#list]
								    </ul>
								</div>
								
								[#-- ${(checkedCurdGenType)!} 不需要回显 --]
								<div class="btn-group">
									<a aria-expanded="false" data-toggle="dropdown" class="btn btn-info btn-sm dropdown-toggle">
								       	 自动生成
								        <i class="ace-icon fa fa-angle-down"></i>
								    </a>
								    <ul id="generateOption" class="dropdown-menu dropdown-info">
										[#list curdGenTypes as curdGenType]
											<li>
												<a href="javascript:void(0);" name="curdGenType" val="${curdGenType}" >
													<span>${curdGenType.label}</span>
												</a>
											</li>
										[/#list]
								    </ul>
								</div>
								
								<!-- 列表搜索 -->
								<div class="btn-group btn-corner pull-right">
									<div class="btn-group">
										<a id="searchPropertySelect" aria-expanded="false" data-toggle="dropdown" class="btn btn-info btn-sm dropdown-toggle">
									        <i class="ace-icon fa fa-angle-down"></i>
									    </a>
									    <ul id="searchPropertyOption" class="dropdown-menu dropdown-info">
											<li [#if page.searchProperty == "title" ] class="current" [/#if] val="title">
												<a href="javascript:;">备注</a>
											</li>
											<li [#if page.searchProperty == "className" ] class="current" [/#if] val="className">
												<a href="javascript:;">类名(英文)</a>
											</li>
											<li [#if page.searchProperty == "classNameDesc" ] class="current" [/#if] val="classNameDesc">
												<a href="javascript:;">类名注释(中文)</a>
											</li>
											<li [#if page.searchProperty == "titleAttribute" ] class="current" [/#if] val="titleAttribute">
												<a href="javascript:;">实体标识属性</a>
											</li>
									    </ul>
									</div>
									<span class="input-icon pull-left">
								       <i class="ace-icon fa fa-search"></i>
								       <input id="searchValue" name="searchValue" value="${page.searchValue}" type="text" class="nav-search-input input-sm input-info" placeholder="Search ..." autocomplete="off">
								    </span>
								    <input type="submit" class="btn btn-info btn-sm pull-left" value="Search &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" />
								</div>
							</div><!-- 列表工具条部分结束/.page-header -->
							
							<!-- 列表表格开始 -->
							<div class="row">
								<div class="col-xs-12">
									<!-- 表格开始 -->
									<table id="listTable" class="list table table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">
													<label class="pos-rel">
														<input id="selectAll" type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="entityType">
														<i class="glyphicon pull-right"></i>
														实体类型
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="title">
														<i class="glyphicon pull-right"></i>
														备注
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="className">
														<i class="glyphicon pull-right"></i>
														类名(英文)
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="classNameDesc">
														<i class="glyphicon pull-right"></i>
														类名注释(中文)
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="titleAttribute">
														<i class="glyphicon pull-right"></i>
														实体标识属性
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="needAddBtn">
														<i class="glyphicon pull-right"></i>
														添加
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="needBatchDeleteBtn">
														<i class="glyphicon pull-right"></i>
														批量删除
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="needRefreshBtn">
														<i class="glyphicon pull-right"></i>
														刷新
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="needPage">
														<i class="glyphicon pull-right"></i>
														分页
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="needSearch">
														<i class="glyphicon pull-right"></i>
														搜索
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="needEditBtn">
														编辑
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="needDeleteBtn">
														<i class="glyphicon pull-right"></i>
														删除
													</a>
												</th>
												<th>
													<span>${message("admin.common.handle")}</span>
												</th>
											</tr>
										</thead>
										<tbody>
										[#list page.content as devEntity]
											<tr>
												<td class="center">
													<label class="pos-rel">
														<input type="checkbox" name="ids" class="ace" value="${devEntity.id}" />
														<span class="lbl"></span>
													</label>
												</td>
												<td>
													<span name="entityType">${abbreviate(devEntity.entityType.label, 10, "...")}</span>
												</td>
												<td>
													<span name="title">${(devEntity.title)!}</span>
												</td>
												<td>
													<span name="className">${(devEntity.className)!}</span>
												</td>
												<td>
													<span name="classNameDesc">${(devEntity.classNameDesc)!}</span>
												</td>
												<td>
													<span name="titleAttribute">${(devEntity.titleAttribute)!}</span>
												</td>
												<td>
													[#if devEntity.needAddBtn?? && devEntity.needAddBtn]
														<span class="trueIcon">&nbsp;</span>
													[#else]
														<span class="falseIcon">&nbsp;</span>
													[/#if]
												</td>
												<td>
													[#if devEntity.needBatchDeleteBtn?? && devEntity.needBatchDeleteBtn]
														<span class="trueIcon">&nbsp;</span>
													[#else]
														<span class="falseIcon">&nbsp;</span>
													[/#if]
												</td>
												<td>
													[#if devEntity.needRefreshBtn?? && devEntity.needRefreshBtn]
														<span class="trueIcon">&nbsp;</span>
													[#else]
														<span class="falseIcon">&nbsp;</span>
													[/#if]
												</td>
												<td>
													[#if devEntity.needPage?? && devEntity.needPage]
														<span class="trueIcon">&nbsp;</span>
													[#else]
														<span class="falseIcon">&nbsp;</span>
													[/#if]
												</td>
												<td>
													[#if devEntity.needSearch?? && devEntity.needSearch]
														<span class="trueIcon">&nbsp;</span>
													[#else]
														<span class="falseIcon">&nbsp;</span>
													[/#if]
												</td>
												<td>
													[#if devEntity.needEditBtn?? && devEntity.needEditBtn]
														<span class="trueIcon">&nbsp;</span>
													[#else]
														<span class="falseIcon">&nbsp;</span>
													[/#if]
												</td>
												<td>
													[#if devEntity.needDeleteBtn?? && devEntity.needDeleteBtn]
														<span class="trueIcon">&nbsp;</span>
													[#else]
														<span class="falseIcon">&nbsp;</span>
													[/#if]
												</td>
												<td>
													<!-- 大型ui -->
													<div class="hidden-sm hidden-xs btn-group">
														<a target="_blank" class="btn btn-xs btn-info" href="${base}${(devConfig.request_mapping_prefix_defaule)!}/${devEntity.unHumpClassName}/list.jhtml">
															<span>访问</span>
														</a>
														<a class="btn btn-xs btn-info" href="edit.jhtml?id=${devEntity.id}">
															<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
															<span>${message("admin.common.edit")}</span>
														</a>
														<a href="javascript:void(0);" class="deleteButton btn btn-xs btn-danger" ids="${devEntity.id}">
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
																	<a target="_blank" href="${base}${(devConfig.request_mapping_prefix_defaule)!}/${devEntity.unHumpClassName}/list.jhtml" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="blue">
																			<span>访问</span>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="edit.jhtml?id=${devEntity.id}" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="blue">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			<span>${message("admin.common.edit")}</span>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="javascript:void(0);" class="deleteButton tooltip-error" ids="${devEntity.id}" data-rel="tooltip" title="Delete">
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
									
									<!-- 引入分页部分 -->
									[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
										[#include "/admin/include/pagination.ftl"]
									[/@pagination]
									
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
		
			[@flash_message /]
		
			[#-- 删除功能 --]
			var $listTable = $("#listTable");
			var $pageTotal = $("#pageTotal");
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
									$pageTotal.text(parseInt($pageTotal.text()) - 1);
									$this.closest("tr").remove();
									if ($listTable.find("tr").size() <= 1) {
										setTimeout(function() {
											location.reload(true);
										}, 3000);
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
			
			
			var $generateOption = $("#generateOption a");
						
			$generateOption.click( function() {
				var $this = $(this);
				var $checkedIds = $("#listTable input[name='ids']:enabled:checked");
				if($checkedIds.size()==0){
					$.message({"type": "error", "content": "请至少选择一个实体！"});
					return false;
				}
				var $params = $checkedIds.serialize() + "&curdGenType=" + $this.attr("val");
				$.dialog({
					type: "warn",
					content: "您确定执行该操作么？",
					ok: message("admin.dialog.ok"),
					cancel: message("admin.dialog.cancel"),
					onOk: function() {
						$.ajax({
							url: "generate.jhtml",
							type: "POST",
							data: $params,
							dataType: "json",
							cache: false,
							success: function(message) {
								$.message(message);
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
