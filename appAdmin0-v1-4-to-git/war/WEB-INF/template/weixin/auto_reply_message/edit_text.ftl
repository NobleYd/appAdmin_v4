<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>添加自动回复消息 - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
	
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="weixinGroup" subMenu="autoReplyMessage" ]
			
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
								<span>添加自动回复消息</span>
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
						<form id="inputForm" action="update.jhtml" class="form-horizontal" role="form" method="post"  >
							
							<!-- Id隐藏域 -->
							<input name="id" type="hidden" value="${(autoReplyMessage.id)!}" />
							
							<div class="row">
								<div class="col-xs-12">
									<div class="tabbable">
									    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
									        <li class="active">
									            <a aria-expanded="true" data-toggle="tab" href="#base">文本消息</a>
									        </li>
									    </ul>
									    <div class="tab-content">
									        <div id="base" class="tab-pane active">
											
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="keyword">
														<span class="text-danger">*</span>
														<span>关键词</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="keyword" value="${(autoReplyMessage.keyword)!}" type="text" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="content">
														<span>文本消息内容</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="content" value="${(autoReplyMessage.content)!}" type="text" class="col-xs-10 col-sm-5" />
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

			var $inputForm = $("#inputForm");
			
			// 表单验证
			$inputForm.validate({
				rules: {
					keyword: {
						required: true,
						remote: {
							url: "check_keyword_unique.jhtml?previousKeyword=${autoReplyMessage.keyword}",
							cache: false
						},
					},
					content: {
					},
				},
				messages: {
					keyword: {
						remote: "已经存在，不允许重复！",
					},
					content: {
					},
				}
			});


		

		});
		</script>

	</body>
</html>
