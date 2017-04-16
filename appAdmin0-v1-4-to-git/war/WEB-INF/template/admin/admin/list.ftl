<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>${message("admin.admin.list")}</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="systemGroup" subMenu="admin" ]
			
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
								<span>${message("admin.admin.list")}</span>
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
								
								<!-- 列表搜索 -->
								<div class="btn-group btn-corner pull-right">
									<div class="btn-group">
										<a id="searchPropertySelect" aria-expanded="false" data-toggle="dropdown" class="btn btn-info btn-sm dropdown-toggle">
									        <i class="ace-icon fa fa-angle-down"></i>
									    </a>
									    <ul id="searchPropertyOption" class="dropdown-menu dropdown-info">
											<li [#if page.searchProperty == "username"] class="active"[/#if] val="username">
											    <a href="javascript:;">${message("Admin.username")}</a>
											</li>
											<li [#if page.searchProperty == "email"] class="active"[/#if] val="email">
												<a href="javascript:;">${message("Admin.email")}</a>
											</li>
											<li [#if page.searchProperty == "name"] class="active"[/#if] val="name">
											    <a href="javascript:;">${message("Admin.name")}</a>
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
													<a href="javascript:;" class="sort" name="username">
														${message("Admin.username")}
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="email">
														${message("Admin.email")}
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="name">
														${message("Admin.name")}
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="department">
														${message("Admin.department")}
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="loginDate">
														${message("Admin.loginDate")}
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="loginIp">
														${message("Admin.loginIp")}
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<span>${message("admin.admin.status")}</span>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="createDate">
														<i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>
														${message("admin.common.createDate")}
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<span>${message("admin.common.handle")}</span>
												</th>
											</tr>
										</thead>
										<tbody>
										[#list page.content as admin]
											<tr>
												<td class="center">
													<label class="pos-rel">
														<input type="checkbox" name="ids" class="ace" value="${admin.id}" />
														<span class="lbl"></span>
													</label>
												</td>
												<td>
													${admin.username}
												</td>
												<td>
													${admin.email}
												</td>
												<td>
													${admin.name}
												</td>
												<td>
													${admin.department}
												</td>
												<td>
													[#if admin.loginDate??]
														<span title="${admin.loginDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.loginDate}</span>
													[#else]
														-
													[/#if]
												</td>
												<td>
													${(admin.loginIp)!"-"}
												</td>
												<td>
													[#if !admin.isEnabled]
														<span class="red">${message("admin.admin.disabled")}</span>
													[#elseif admin.isLocked]
														<span class="red"> ${message("admin.admin.locked")} </span>
													[#else]
														<span class="green">${message("admin.admin.normal")}</span>
													[/#if]
												</td>
												<td>
													<span title="${admin.createDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.createDate}</span>
												</td>
												<td>
													<!-- 大型ui -->
													<div class="hidden-sm hidden-xs btn-group">
														<a class="btn btn-xs btn-info" href="edit.jhtml?id=${admin.id}">
															<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
															<span>${message("admin.common.edit")}</span>
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
																	<a href="edit.jhtml?id=${admin.id}" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="blue">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			<span>${message("admin.common.edit")}</span>
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
			[@flash_message /]	
		</script>

	</body>
</html>
