<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>${message("admin.cache.clear")}</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项} -->
			[#assign menu="systemGroup" subMenu="cache" ]
			
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
								<span>${message("admin.cache.clear")}</span>
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
						<form id="listForm" action="clear.jhtml" method="post">
							<!-- 列表表格开始 -->
							<div class="row">
								<div class="col-xs-12">
									<!-- 表格开始 -->
									<table id="listTable" class="list table table-bordered table-hover">
										<tr>
											<th>
												<span>${message("admin.cache.cacheSize")}:</span>
											</th>
											<td>
												<span>${cacheSize}</span>
											</td>
										</tr>
										<tr>
											<th>
												<span>${message("admin.cache.freeMemory")}:</span>
											</th>
											<td>
												<span>
												[#if maxMemory?? && totalMemory?? && freeMemory??]
													${(maxMemory - totalMemory + freeMemory)?string("0.##")}MB
												[#else]
													-
												[/#if]
												</span>
											</td>
										</tr>
										<tr>
											<th>
												<span>${message("admin.cache.diskStorePath")}:</span>
											</th>
											<td>
												<span>${diskStorePath}</span>
											</td>
										</tr>
										<tr>
											<th>
												&nbsp;
											</th>
											<td>
												<span class="input-icon">
											    	<i class="ace-icon fa fa-check white"></i>
											        <input type="submit" class="btn btn-info btn-xs" value="${message("admin.common.submit")}" />
												</span>
										    	<span class="input-icon">
												    <i class="ace-icon fa fa-undo white"></i>
											        <input type="button" class="btn btn-info btn-xs" value="${message("admin.common.back")}" onclick="location.href='../common/main.jhtml'" />
												</span>
											</td>
										</tr>
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
		</script>

	</body>
</html>
