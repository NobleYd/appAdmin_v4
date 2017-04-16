<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>添加普通实体演示 - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<link rel="stylesheet" href="${base}/resources/admin/datePicker/skin/WdatePicker.css" />
	
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="demoGroup" subMenu="normalEntity" ]
			
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
								<span>添加普通实体演示</span>
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
						<form id="inputForm" action="save.jhtml" class="form-horizontal" role="form" method="post" enctype="multipart/form-data" >
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
													<label class="col-sm-3 control-label no-padding-right" for="name">
														<span class="text-danger">*</span>
														<span>名称</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="name" type="text" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="sex">
														<span class="text-danger">*</span>
														<span>性别</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<select name="sex" class="col-xs-10 col-sm-5" >
																[#list Sexs as Sex]
																<option value="${Sex}" >${(Sex.label)!'-'}</option>
																[/#list]
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="age">
														<span class="text-danger">*</span>
														<span>年龄</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="age" type="text" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="birthDate">
														<span class="text-danger">*</span>
														<span>出生日期</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="birthDate" name="birthDate" class="Wdate col-xs-10 col-sm-5" onfocus="WdatePicker()" maxlength="100" />			
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="headlthState">
														<span>健康状况</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">	
															<div class="checkbox">
																<label>
															        <input id="headlthState" name="headlthState" value="true" class="ace ace-switch ace-switch-2" type="checkbox" />
															        <span class="lbl">&nbsp;&nbsp;是</span>
																	<input type="hidden" name="_headlthState" value="false" />
																</label>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="loverSelect">
														<span>爱人</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input type="text" id="loverSelect" title="请填写关联实体的 name 属性" class="col-xs-5 col-sm-2" />
															&nbsp;&nbsp;
															<label class="selectedLabel control-label" style="display:none;" >
																<span class="red" >当前选择：</span>
																<span class="value" ></span>
															</label>
															&nbsp;&nbsp;
															<input type="hidden" style="width:50px!important;" id="loverId" name="loverId" title="请填写关联实体的 【ID】属性" class="col-xs-5 col-sm-2" />
															<a href="javascript:void(0);" style="width:50px!important; display:none;" class="clearInputs btn btn-info btn-xs" >清空</a>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="pictureImage">
														<span>照片</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix col-xs-10 col-sm-5">
															<input id="pictureImage" name="pictureImage" type="file" class="" >
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="score">
														<span class="text-danger">*</span>
														<span>综合评分[满分100]</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input name="score" type="text" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="attachmentFile">
														<span>附件</span>:
													</label>
													<div class="col-sm-9">
														<div class="clearfix col-xs-10 col-sm-5">
															<input id="attachmentFile" name="attachmentFile" type="file" class="" >
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
		<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>

		<!-- 页面自定义内联脚本区 inline scripts related to this page -->
		<script type="text/javascript">
		$().ready(function() {

			[@flash_message /]

			var $inputForm = $("#inputForm");
			
			// 表单验证
			$inputForm.validate({
				rules: {
					name: {
						required: true,
						remote: {
							url: "check_name_unique.jhtml",
							cache: false
						},
					},
					sex: {
						required: true,
					},
					age: {
						required: true,
						digits: true,
					},
					birthDate: {
						required: true,
					},
					headlthState: {
					},
					loverId: {
					},
					pictureImage: {
						required: true,
					},
					score: {
						required: true,
						decimal: {
							integer: 12,
							fraction: ${setting.bigDecimalScale}
						},
					},
					attachmentFile: {
					},
				},
				messages: {
					name: {
						remote: "已经存在，不允许重复！",
					},
					sex: {
					},
					age: {
						digits: "${message("admin.validate.digits")}",
					},
					birthDate: {
					},
					headlthState: {
					},
					loverId: {
					},
					pictureImage: {
					},
					score: {
						decimal: "${message("admin.validate.decimal")}",
					},
					attachmentFile: {
					},
				}
			});

			//lover 的 autocomplete 实现
			var $loverSelect = $("input#loverSelect");
			var $loverClearFixDiv = $loverSelect.parent(".clearfix");
			var $loverId = $("input#loverId");
			$loverSelect.autocomplete("${base}/admin/normal_entity/searchLover.jhtml", {
				dataType: "json",
				minChars: 0, //设置为0需要双击显示
				max: 20,
				width: 200,
				scrollHeight: 300,
				parse: function(data) {
					return $.map(data, function(item) {
						return {
							data: item,
							value: item.name
						}
					});
				},
				formatItem: function(item) {
					return '<span title="' + item.name + '">' + item.name + '<\/span>';
				}
			}).result(function(event, item) {
				$(this).val(item.name);
				$loverId.val(item.id);
				$loverClearFixDiv.find("label span.value").html(item.name);
				$loverClearFixDiv.find("label").show();
				$loverClearFixDiv.find("a.clearInputs").show();
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
		
			$('#pictureImage,#attachmentFile').ace_file_input({
				style:"well",
				btn_choose: 'Drop files here or click to choose',
				btn_change: null,
				no_icon: 'ace-icon fa fa-cloud-upload',
				droppable: true,
				thumbnail: 'small'
				/*
				,before_change:function(files, dropped) {
					//Check an example below
					//or examples/file-upload.html
					return true;
				}
				,before_remove : function() {
					return true;
				}
				*/
		
			}).on('change', function(){
				//console.log($(this).data('ace_input_files'));
				//console.log($(this).data('ace_input_method'));
			});

		});
		</script>

	</body>
</html>
