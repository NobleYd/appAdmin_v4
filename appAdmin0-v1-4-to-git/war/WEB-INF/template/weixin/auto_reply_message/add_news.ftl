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
						<form id="inputForm" action="save.jhtml" class="form-horizontal" role="form" method="post"  >
							
							<!-- 消息类型隐藏域 -->
							<input name="messageType" type="hidden" value="${(messageType)!}" />
							
							<div class="row">
								<div class="col-xs-12">
									<div class="tabbable">
									    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
									        <li class="active">
									            <a aria-expanded="true" data-toggle="tab" href="#base">图文消息</a>
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
															<input name="keyword" type="text" class="col-xs-10 col-sm-5" />
														</div>
													</div>
												</div>
												<div class="news_item articles0">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<span>标题1</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[0].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述1</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[0].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接1</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[0].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接1</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[0].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="news_item articles1 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题2</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[1].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述2</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[1].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接2</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[1].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接2</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[1].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="news_item articles2 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题3</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[2].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述3</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[2].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接3</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[2].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接3</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[2].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="news_item articles3 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题4</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[3].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述4</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[3].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接4</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[3].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接4</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[3].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>												
												<div class="news_item articles4 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题5</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[4].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述5</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[4].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接5</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[4].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接5</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[4].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="news_item articles5 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题6</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[5].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述6</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[5].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接6</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[5].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接6</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[5].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="news_item articles6 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题7</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[6].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述7</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[6].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接7</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[6].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接7</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[6].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="news_item articles7 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题8</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[7].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述8</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[7].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接8</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[7].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接8</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[7].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="news_item articles8 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题9</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[8].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述9</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[8].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接9</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[8].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接9</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[8].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="news_item articles9 hidden">
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="title">
															<input type="button" class="deleteItem btn btn-info btn-xs" value="删除此项" />
															<span>标题10</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[9].title" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="description">
															<span>描述10</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[9].description" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="picUrl">
															<span>图片链接10</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[9].picUrl" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="url">
															<span>点击后跳转链接10</span>:
														</label>
														<div class="col-sm-9">
															<div class="clearfix">
																<input name="articles[9].url" type="text" class="col-xs-10 col-sm-5" />
															</div>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="articleCount">
														<span>&nbsp;</span>
													</label>
													<div class="col-sm-9">
														<div class="clearfix">
															<input id="newItemsBtn" type="button" value="新增图文项" class="btn btn-info btn-sm" />
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
							url: "check_keyword_unique.jhtml",
							cache: false
						},
					}
				},
				messages: {
					keyword: {
						remote: "已经存在，不允许重复！",
					}
				}
			});
			
			
			var $newItemsBtn = $("#newItemsBtn");
			$newItemsBtn.click(function(){
				$("div.news_item.hidden").first().removeClass("hidden");		
			});


			$("input.deleteItem").click(function(){
				var $this = $(this);
				var $newsItem = $this.closest("div.news_item");
				console.log($newsItem);
				$newsItem.find("input[type='text']").val('');
				$newsItem.addClass("hidden");
			});
			

		});
		</script>

	</body>
</html>
