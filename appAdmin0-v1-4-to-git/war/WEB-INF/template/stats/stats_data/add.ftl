<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>添加统计项数据 - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
	
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="systemGroup" subMenu="statsData" ]
			
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
								<span>添加统计项数据</span>
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
													<label class="col-sm-3 control-label no-padding-right" for="statsItemSelect">
														<span class="text-danger">*</span>
														<span>统计项</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="statsItemSelect" title="请填写关联实体的 title 属性" class="col-xs-5 col-sm-2" />
															&nbsp;&nbsp;
															<label class="selectedLabel control-label" style="display:none;" >
																<span class="red" >当前选择：</span>
																<span class="value" ></span>
															</label>
															&nbsp;&nbsp;
															<input type="hidden" style="width:50px!important;" id="statsItemId" name="statsItemId" title="请填写关联实体的 【ID】属性" class="col-xs-5 col-sm-2" />
															<a href="javascript:void(0);" style="width:50px!important; display:none;" class="clearInputs btn btn-info btn-xs" >清空</a>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="dataTime">
														<span class="text-danger">*</span>
														<span>数据时间</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="dataTime" type="text" value="${(statsData.dataTime)!''}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="numberValue">
														<span class="text-danger">*</span>
														<span>数字值</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="numberValue" type="text" value="${(statsData.numberValue)!''}" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="textValue">
														<span class="text-danger">*</span>
														<span>文本值</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="textValue" type="text" value="${(statsData.textValue)!''}" class="col-xs-10 col-sm-5" />
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
					statsItemId: {
						required: true,
					},
					dataTime: {
						required: true,
						digits: true,
					},
					numberValue: {
						required: true,
						decimal: {
							integer: 12,
							fraction: ${setting.bigDecimalScale}
						},
					},
					textValue: {
						required: true,
					},
					statsCount: {
						required: true,
						digits: true,
					},
				},
				messages: {
					statsItemId: {
					},
					dataTime: {
						digits: "${message("admin.validate.digits")}",
					},
					numberValue: {
						decimal: "${message("admin.validate.decimal")}",
					},
					textValue: {
					},
					statsCount: {
						digits: "${message("admin.validate.digits")}",
					},
				}
			});

			//statsItem 的 autocomplete 实现
			var $statsItemSelect = $("input#statsItemSelect");
			var $statsItemClearFixDiv = $statsItemSelect.parent(".clearfix");
			var $statsItemId = $("input#statsItemId");
			$statsItemSelect.autocomplete("${base}/admin/stats_data/searchStatsItem.jhtml", {
				dataType: "json",
				minChars: 0, //设置为0需要双击显示
				max: 20,
				width: 200,
				scrollHeight: 300,
				parse: function(data) {
					return $.map(data, function(item) {
						return {
							data: item,
							value: item.title
						}
					});
				},
				formatItem: function(item) {
					return '<span title="' + item.title + '">' + item.title + '<\/span>';
				}
			}).result(function(event, item) {
				$(this).val(item.title);
				$statsItemId.val(item.id);
				$statsItemClearFixDiv.find("label span.value").html(item.title);
				$statsItemClearFixDiv.find("label").show();
				$statsItemClearFixDiv.find("a.clearInputs").show();
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
