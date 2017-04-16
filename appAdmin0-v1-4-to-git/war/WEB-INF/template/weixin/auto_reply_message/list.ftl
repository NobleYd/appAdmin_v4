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
			[#assign menu="weixinGroup" subMenu="autoReplyMessage" ]
			
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
								<span>自动回复消息列表</span>
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
							
							<input type="submit" name="default" class="hidden" />
								<!-- 页面显示记录条数调整 按钮 -->
								<div class="btn-group">
								    <a aria-expanded="false" data-toggle="dropdown" class="btn btn-info btn-sm dropdown-toggle">
								        <i class="glyphicon glyphicon-plus"></i>
								        <span>${message("admin.common.add")}</span>
								        <i class="ace-icon fa fa-angle-down icon-on-right"></i>
								    </a>
								    <ul class="dropdown-menu dropdown-info">
								        <li>
											<a href="add.jhtml?messageType=text">添加文本回复消息</a>
										</li>
										<li>
											<a href="add.jhtml?messageType=image">添加图片回复消息</a>
										</li>
										<li>
											<a href="add.jhtml?messageType=voice">添加语音回复消息</a>
										</li>
										<li>
											<a href="add.jhtml?messageType=video">添加视频回复消息</a>
										</li>
										<li>
											<a href="add.jhtml?messageType=music">添加音乐回复消息</a>
										</li>
										<li>
											<a href="add.jhtml?messageType=news">添加图文回复消息</a>
										</li>
								    </ul>
								</div>
							
								<!-- 增删刷新 按钮 -->
								<div class="btn-group btn-corner">
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
											<li [#if page.searchProperty == "keyword" ] class="active" [/#if] val="keyword" >
												<a href="javascript:;" >关键词</a>
											</li>
											<li [#if page.searchProperty == "content" ] class="active" [/#if] val="content" >
												<a href="javascript:;" >文本消息内容</a>
											</li>
											<li [#if page.searchProperty == "mediaId" ] class="active" [/#if] val="mediaId" >
												<a href="javascript:;" >媒体文件ID</a>
											</li>
											<li [#if page.searchProperty == "title" ] class="active" [/#if] val="title" >
												<a href="javascript:;" >标题</a>
											</li>
											<li [#if page.searchProperty == "description" ] class="active" [/#if] val="description" >
												<a href="javascript:;" >描述</a>
											</li>
											<li [#if page.searchProperty == "musicUrl" ] class="active" [/#if] val="musicUrl" >
												<a href="javascript:;" >音乐链接</a>
											</li>
											<li [#if page.searchProperty == "hqMusicUrl" ] class="active" [/#if] val="hqMusicUrl" >
												<a href="javascript:;" >高质量音乐链接</a>
											</li>
											<li [#if page.searchProperty == "thumbMediaId" ] class="active" [/#if] val="thumbMediaId" >
												<a href="javascript:;" >缩略图媒体ID</a>
											</li>
									    </ul>
									</div>
									<span class="input-icon pull-left">
								       <i class="ace-icon fa fa-search"></i>
								       <input id="searchValue" name="searchValue" value="${page.searchValue}" type="text" class="nav-search-input input-sm input-info" placeholder="Search ..." autocomplete="off" />
								    </span>
								    <input type="submit" name="search" class="btn btn-info btn-sm pull-left" value="Search &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" />
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
													<a href="javascript:;" class="sort" name="keyword">
														关键词
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="messageType">
														消息类型
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="content">
														文本消息内容
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="mediaId">
														媒体文件ID
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="title">
														标题
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="description">
														描述
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="musicUrl">
														音乐链接
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="hqMusicUrl">
														高质量音乐链接
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="thumbMediaId">
														缩略图媒体ID
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<a href="javascript:;" class="sort" name="articleCount">
														图文消息数
														<i class="glyphicon pull-right"></i>
													</a>
												</th>
												<th>
													<span>${message("admin.common.handle")}</span>
												</th>
											</tr>
										</thead>
										<tbody>
										[#list page.content as autoReplyMessage ]
											<tr>
												<td class="center">
													<label class="pos-rel">
														<input type="checkbox" name="ids" class="ace" value="${autoReplyMessage.id}" />
														<span class="lbl"></span>
													</label>
												</td>
												<td>				
													<span name="keyword">${(autoReplyMessage.keyword)!}</span>
												</td>
												<td>				
													<span name="messageType">${(autoReplyMessage.messageType.label)!}</span>
												</td>
												<td colspan="8">
													[#if "text" == autoReplyMessage.messageType ]
														<span>文本消息内容：</span>
														<span name="content">
															${(autoReplyMessage.content)!}
														</span>
													[#elseif "image" == autoReplyMessage.messageType ]
														<span>图片 媒体文件ID：</span>
														<span name="mediaId">
															${(autoReplyMessage.mediaId)!}
														</span>
													[#elseif "voice" == autoReplyMessage.messageType ]
														<span>语言 媒体文件ID：</span>
														<span name="mediaId">
															${(autoReplyMessage.mediaId)!}
														</span>
													[#elseif "video" == autoReplyMessage.messageType ]
														<span>视频 标题：</span>
														${(autoReplyMessage.title)!}
														<br/>
														<span>视频 描述：</span>
														${(autoReplyMessage.description)!}
														<br/>
														<span>视频 媒体文件ID：</span>
														<span name="mediaId">
															${(autoReplyMessage.mediaId)!}
														</span>
													[#elseif "music" == autoReplyMessage.messageType ]
														<span>音乐 标题：</span>
														${(autoReplyMessage.title)!}
														<br/>
														<span>音乐 描述：</span>
														${(autoReplyMessage.description)!}
														<br/>
														<span>音乐链接：</span>
														${(autoReplyMessage.musicUrl)!}
														<br/>
														<span>高质量音乐链接：</span>
														${(autoReplyMessage.hqMusicUrl)!}
														<br/>
														<span>音乐 缩略图媒体ID：</span>
														${(autoReplyMessage.thumbMediaId)!}
													[#elseif "news" == autoReplyMessage.messageType ]
														<span>图文消息数：</span>
														${(autoReplyMessage.articleCount)!}
														[#list autoReplyMessage.articles as newsItem]
															<br/>
															<span>标题：</span>
															${newsItem.title}
															<br/>
															<span>描述：</span>
															${newsItem.description}
															<br/>
															<span>图片链接：</span>
															${newsItem.picUrl}
															<br/>
															<span>跳转链接：</span>
															${newsItem.url}
														[/#list]
													[/#if]
												</td>
												[#-- 
												<td>			
													<span name="content">
														<textarea class="form-control" readonly="readonly" >${(autoReplyMessage.content)!}</textarea>
													</span>
												</td>
												<td>				
													<span name="mediaId">
														<textarea class="form-control" readonly="readonly" >${(autoReplyMessage.mediaId)!}</textarea>
													</span>
												</td>
												<td>				
													<span name="title">${(autoReplyMessage.title)!}</span>
												</td>
												<td>				
													<span name="description">
														<textarea class="form-control" readonly="readonly" >${(autoReplyMessage.description)!}</textarea>
													</span>
												</td>
												<td>				
													<span name="musicUrl">
														<textarea class="form-control" readonly="readonly" >${(autoReplyMessage.musicUrl)!}</textarea>
													</span>
												</td>
												<td>				
													<span name="hqMusicUrl">
														<textarea class="form-control" readonly="readonly" >${(autoReplyMessage.hqMusicUrl)!}</textarea>
													</span>
												</td>
												<td>				
													<span name="thumbMediaId">
														<textarea class="form-control" readonly="readonly" >${(autoReplyMessage.thumbMediaId)!}</textarea>
													</span>
												</td>
												<td>			
													<span name="articleCount">${(autoReplyMessage.articleCount)!}</span>
												</td>
												--]
												<td>
													<!-- 大型ui -->
													<div class="hidden-sm hidden-xs btn-group">
														<a class="btn btn-xs btn-info" href="edit.jhtml?id=${autoReplyMessage.id}" >
															<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
															<span>${message("admin.common.edit")}</span>
														</a>
														<a href="javascript:void(0);" class="deleteButton btn btn-xs btn-danger" ids="${autoReplyMessage.id}" >
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
																	<a href="edit.jhtml?id=${autoReplyMessage.id}" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="blue">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			<span>${message("admin.common.edit")}</span>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="javascript:void(0);" class="deleteButton tooltip-error" ids="${autoReplyMessage.id}" data-rel="tooltip" title="Delete">
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
			[@flash_message /]
			
		[#-- list页表格单行操作按钮列的删除功能 --]
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
										}, 2000);
									}
								}
								$deleteButton2.removeClass("disabled");
							}
						});
					},
					onCancel: function(){
						$deleteButton2.removeClass("disabled");
					},
					onClose: function(){
						$deleteButton2.removeClass("disabled");
					}
				});
			});
			
		</script>

	</body>
</html>
