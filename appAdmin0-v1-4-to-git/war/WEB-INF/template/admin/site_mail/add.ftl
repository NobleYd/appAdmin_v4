<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>添加邮件 - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
	
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="siteMailGroup" subMenu="siteMail" ]
			
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
								<span>添加邮件</span>
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
						<form id="inputForm" action="save.jhtml" class="form-horizontal" role="form" method="post"  >
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
													<label class="col-sm-3 control-label no-padding-right" for="title">
														<span class="text-danger">*</span>
														<span>标题</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="title" type="text" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="authorAdminId">
														<span>发件人</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select name="authorAdminId" class="col-xs-10 col-sm-5" >
																<option value="" >默认值</option>
																[#list AuthorAdmins as AuthorAdmin]
																<option value="${AuthorAdmin.id}" >${(AuthorAdmin.name)!'-'}</option>
																[/#list]
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="toAdminId">
														<span>收件人</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select name="toAdminId" class="col-xs-10 col-sm-5" >
																<option value="" >默认值</option>
																[#list ToAdmins as ToAdmin]
																<option value="${ToAdmin.id}" >${(ToAdmin.name)!'-'}</option>
																[/#list]
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="toContactId">
														<span>收件人</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select name="toContactId" class="col-xs-10 col-sm-5" >
																<option value="" >默认值</option>
																[#list ToContacts as ToContact]
																<option value="${ToContact.id}" >${(ToContact.name)!'-'}</option>
																[/#list]
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="authorLocation">
														<span>authorLocation位置</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select name="authorLocation" class="col-xs-10 col-sm-5" >
																<option value="" >默认值</option>
																[#list Locations as Location]
																<option value="${Location}" >${(Location.label)!'-'}</option>
																[/#list]
															</select>	
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="toLocation">
														<span>toLocation位置</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select name="toLocation" class="col-xs-10 col-sm-5" >
																<option value="" >默认值</option>
																[#list Locations as Location]
																<option value="${Location}" >${(Location.label)!'-'}</option>
																[/#list]
															</select>	
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="isRead">
														<span>是否已读</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">	
															<div class="checkbox">
																<label>
															        <input id="isRead" name="isRead" value="true" class="ace ace-switch ace-switch-2" type="checkbox" />
															        <span class="lbl">&nbsp;&nbsp;是</span>
																	<input type="hidden" name="_isRead" value="false" />
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
		
		<script type="text/javascript" src="${base}/resources/admin/js/jquery.autocomplete.js"></script>

		<!-- 页面自定义内联脚本区 inline scripts related to this page -->
		<script type="text/javascript">
		$().ready(function() {

			[@flash_message /]

			var $inputForm = $("#inputForm");
			
			// 表单验证
			$inputForm.validate({
				rules: {
					title: {
						required: true,
					},
					authorAdminId: {
					},
					toAdminId: {
					},
					toContactId: {
					},
					location: {
					},
					isRead: {
					},
				},
				messages: {
					title: {
					},
					authorAdminId: {
					},
					toAdminId: {
					},
					toContactId: {
					},
					location: {
					},
					isRead: {
					},
				}
			});


			//N-1关联属性的清空按钮功能实现
			$(".clearInputs").unbind('click').bind('click', function(){
				//清空属性输入框和ID输入框
				$(this).siblings("input").val('');
				//清空当前选择提示span内容
				$(this).siblings("label").find("span.value").html('');
				//隐藏自身和当前选择label
				$(this).siblings("label").hide();
				$(this).hide();
			});
		

		});
		</script>

	</body>
</html>
