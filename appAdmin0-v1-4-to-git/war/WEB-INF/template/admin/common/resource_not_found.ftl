<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		<title>${message("admin.resourceNotFound.title")}[#if systemShowPowered] - Powered By APP TEAM[/#if]</title>
	</head>
	<body class="no-skin">
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		<div class="main-container" id="main-container">
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
						
						<div class="row">
							<div class="col-xs-12">
								
								<!-- #section:pages/error -->
								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="ace-icon fa fa-sitemap"></i>
												404
											</span>
											Page Not Found
										</h1>

										<hr />
										
										<h3 class="lighter smaller">We looked everywhere but we couldn't find it!</h3>
										
										<h3 class="lighter smaller">${message("admin.resourceNotFound.message")}</h3>
										
										<hr />
										<div class="space"></div>

										<div class="center">
											<a href="javascript:window.history.back();" class="btn btn-grey">
												<i class="ace-icon fa fa-arrow-left"></i>
												${message("admin.resourceNotFound.back")}
											</a>

											<a href="${base}/" class="btn btn-primary">
												<i class="ace-icon fa fa-tachometer"></i>
												${message("admin.resourceNotFound.home")}
											</a>
										</div>
									</div>
								</div><!-- /section:pages/error -->
								
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- 页面主题部分开始 - /.main-content -->
			
			<!-- 引入页脚部分 -->
			[#include "/admin/include/footer.ftl"]
			
		</div><!-- /.main-container -->

		<!-- 引入公共基础脚本 -->
		[#include "/admin/include/scripts.ftl"]

	</body>
</html>
