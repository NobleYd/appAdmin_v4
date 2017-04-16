<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>自动回复消息列表 - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="weixinGroup" subMenu="weiXinConfig" ]
			
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
								<span>状态查看</span>
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
							
							<input type="submit" name="default" class="hidden" />
								
								<!-- 增删刷新 按钮 -->
								<div class="btn-group btn-corner">
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
													<span>应用ID</span>
												</th>
												<th>
													<span>应用密钥</span>
												</th>
												<th>
													<span>接口Token</span>
												</th>
												<th>
													<span>AccessToken</span>
												</th>
												<th>
													<span>JsApi Ticket</span>
												</th>
												<th>
													<span>${message("admin.common.handle")}</span>
												</th>
											</tr>
										</thead>
										<tbody>
										<tr>
											<td>
												<span title="${(weiXinConfig.appId)!'-'}">***(鼠标移动到此处显示)***</span>
											</td>
											<td>
												<span title="${(weiXinConfig.appSecret)!'-'}">***(鼠标移动到此处显示)***</span>
											</td>
											<td>
												<span title="${(weiXinConfig.token)!'-'}">***(鼠标移动到此处显示)***</span>
											</td>
											<td>
												<textarea class="form-control" readonly="readonly" >${(weiXinConfig.accessToken)!'-'}</textarea>
												<br/>
												<span>上次刷新时间：${(weiXinConfig.formatedWeixinAccessTokenStartTime?datetime)!'-'}</span>
											</td>
											<td>
												<span>
													${(weiXinConfig.jsApiTicket)!'-'}
													<br/>
													上次刷新时间：${(weiXinConfig.formatedJsApiTicketStartTime?datetime)!'-'}
												</span>
											</td>
											<td>&nbsp;</td>
										</tr>
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
		</script>

	</body>
</html>
