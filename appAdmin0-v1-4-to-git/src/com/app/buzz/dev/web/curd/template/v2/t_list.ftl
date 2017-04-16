<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>${(devEntity.classNameDesc)!'-'}列表 - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="devGroup" subMenu="${(devEntity.unCapitalizedClassName)!'-'}" ]
			
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
								<a href="${r'$'}{base}/admin/common/main.jhtml">${r'$'}{message("admin.path.index")}</a>
							</li>
							<li class="active">
								<span>${(devEntity.classNameDesc)!'-'}列表</span>
								<#if devEntity.needPage?? && devEntity.needPage>
								<span>(${r'$'}{message("admin.page.total", page.total)})</span>
								</#if>
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
							
								<#-- 下面这个按钮无实际作用，仅仅是为了避免表单的默认提交事件（即回车会按照第一个type=submit的按钮提交，不这么做，后边的导出按钮和搜索按钮会冲突，导致搜索回车之后默认提交为导出功能，显然这是不被希望的。） -->
								<input type="submit" name="default" class="hidden" />
							
							<#-- 增删刷 三个任意一个需要才显示 -->
							<#if (devEntity.needAddBtn?? && devEntity.needAddBtn) || (devEntity.needBatchDeleteBtn?? && devEntity.needBatchDeleteBtn) || (devEntity.needRefreshBtn?? && devEntity.needRefreshBtn) >
								<!-- 增删刷新 按钮 -->
								<div class="btn-group btn-corner">
								<#if devEntity.needAddBtn?? && devEntity.needAddBtn >
									<a href="add.jhtml" class="btn btn-info btn-sm">
										<i class="glyphicon glyphicon-plus"></i>
										<span>${r'$'}{message("admin.common.add")}</span>
									</a>
								</#if>
								<#if devEntity.needBatchDeleteBtn?? && devEntity.needBatchDeleteBtn >
									<a href="javascript:;" id="deleteButton" class="disabled btn btn-danger btn-sm">
								        <i class="glyphicon glyphicon-trash"></i>
								        <span>${r'$'}{message("admin.common.delete")}</span>
								    </a>
								</#if>
								<#if devEntity.needRefreshBtn?? && devEntity.needRefreshBtn >
								    <a href="javascript:;" id="refreshButton" class="btn btn-success btn-sm">
								        <i class="glyphicon glyphicon-refresh"></i>
								        <span>${r'$'}{message("admin.common.refresh")}</span>
								    </a>
								</#if>	
								</div>
							</#if>
							
							<#if devEntity.needPage?? && devEntity.needPage>
								<!-- 页面显示记录条数调整 按钮 -->
								<div class="btn-group">
								    <a id="pageSizeSelect" aria-expanded="false" data-toggle="dropdown" class="btn btn-success btn-sm dropdown-toggle">
								        <span>${r'$'}{message("admin.page.pageSize")}</span>
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
							</#if>
							
							<#-- 非树形结构实体 且配置为需要搜索 的实现这部分 -->
							<#if !devEntity.isTreeEntity() && devEntity.needSearch?? && devEntity.needSearch >
								<!-- 列表搜索 -->
								<div class="btn-group btn-corner pull-right">
									<div class="btn-group">
										<a id="searchPropertySelect" aria-expanded="false" data-toggle="dropdown" class="btn btn-info btn-sm dropdown-toggle">
									        <i class="ace-icon fa fa-angle-down"></i>
									    </a>
									    <ul id="searchPropertyOption" class="dropdown-menu dropdown-info">
										<#list devEntity.sortedAttributes as attribute >
											<#if attribute.isSearch && attribute.isAttributeTypeBeN_1() >
											<!-- 根据N-1类型的titleAttribute搜索 -->
											<li [#if page.searchProperty == "${(attribute.unCapitalizedName)!'-'}.${(attribute.relatedDevEntity.titleAttribute)!'-'}" ] class="active" [/#if] val="${(attribute.unCapitalizedName)!'-'}.${(attribute.relatedDevEntity.titleAttribute)!'-'}" >
												<a href="javascript:;" >${(attribute.attributeDesc)!'-'}</a>
											</li>
											<#elseif attribute.isSearch >
											<#-- 否则就是String类型 -->
											<li [#if page.searchProperty == "${(attribute.unCapitalizedName)!'-'}" ] class="active" [/#if] val="${(attribute.unCapitalizedName)!'-'}" >
												<a href="javascript:;" >${(attribute.attributeDesc)!'-'}</a>
											</li>
											</#if>
										</#list>
									    </ul>
									</div>
									<span class="input-icon pull-left">
								       <i class="ace-icon fa fa-search"></i>
								       <input id="searchValue" name="searchValue" value="${r'$'}{page.searchValue}" type="text" class="nav-search-input input-sm input-info" placeholder="Search ..." autocomplete="off" />
								    </span>
								    <input type="submit" name="search" class="btn btn-info btn-sm pull-left" value="Search &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" />
								</div>
							</#if>
								
							</div><!-- 列表工具条部分结束/.page-header -->
							
							<!-- 列表表格开始 -->
							<div class="row">
								<div class="col-xs-12">
									<!-- 表格开始 -->
									<table id="listTable" class="list table table-bordered table-hover">
										<thead>
											<tr>
												<#-- 如果需要批量删除功能-》列表第一列才显示checkbox -->
												<#if devEntity.needBatchDeleteBtn?? && devEntity.needBatchDeleteBtn >
												<th class="center">
													<label class="pos-rel">
														<input id="selectAll" type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</th>
												</#if>
										<#-- 列表第一行属性名称（排序、非排序等逻辑判断） -->
										<#list devEntity.sortedAttributes as attribute>
												<th>
											<#if attribute.isSort>
													<a href="javascript:;" class="sort" name="${(attribute.unCapitalizedName)!'-'}">
														<#if attribute.isAttributeTypeBeDate()>
														<i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>
														</#if>
														${(attribute.attributeDesc)!'-'}
														<i class="glyphicon pull-right"></i>
													</a>
											<#else>
													<span name="${(attribute.unCapitalizedName)!'-'}">${(attribute.attributeDesc)!'-'}</span>
											</#if>
											<#-- 如果是treeEntity并且是titleAttribute，则在titleAttribute列之后跟随一个sort列 -->
											<#if devEntity.isTreeEntity() && attribute.unCapitalizedName == devEntity.titleAttribute >
												</th>
												<th>
													<span name="order">次序</span>
											</#if>
												</th>
										</#list>
												<th>
													<span>${r'$'}{message("admin.common.handle")}</span>
												</th>
											</tr>
										</thead>
										<tbody>
									<#-- 根据约定，树形实体和非树形实体返回数据不同，非树形实体返回page，树形实体不是。 -->
									<#if devEntity.isTreeEntity() >
										[#list ${(devEntity.unCapitalizedClassName)!'-'}Tree as ${(devEntity.unCapitalizedClassName)!'-'} ]
									<#else>
										[#list page.content as ${(devEntity.unCapitalizedClassName)!'-'} ]
									</#if>
											<tr>
											<#-- 如果需要批量删除功能-》列表第一列才显示checkbox -->
											<#if devEntity.needBatchDeleteBtn?? && devEntity.needBatchDeleteBtn >
												<td class="center">
													<label class="pos-rel">
														<input type="checkbox" name="ids" class="ace" value="${r'$'}{${(devEntity.unCapitalizedClassName)!'-'}.id}" />
														<span class="lbl"></span>
													</label>
												</td>
											</#if>
										<#list devEntity.sortedAttributes as attribute>
											<#-- 枚举显示其类型提供的label属性（label有get方法） -->
											<#if attribute.attributeType?starts_with("enum") >
												<td>
													<span name="${(attribute.unCapitalizedName)!'-'}">${r'$'}{(${(devEntity.unCapitalizedClassName)!'-'}.${(attribute.unCapitalizedName)!'-'}.label)!}</span>
												</td>
											<#-- N-1显示关联对象的titleAttribute属性 -->
											<#elseif attribute.isAttributeTypeBeN_1() >
												<td>	
													<span name="${(attribute.unCapitalizedName)!'-'}">${r'$'}{(${(devEntity.unCapitalizedClassName)!'-'}.${(attribute.unCapitalizedName)!'-'}.${(attribute.relatedDevEntity.titleAttribute)!'-'})!}</span>
												</td>
											<#-- 如果是树形实体，并且是titleAttribute那一列，则进行层次显示！（通过缩进实现） -->
											<#elseif devEntity.isTreeEntity() && attribute.unCapitalizedName == devEntity.titleAttribute >
												<td>	
													<span style="margin-left: ${r'$'}{${(devEntity.unCapitalizedClassName)!'-'}.grade * 20}px;[#if ${(devEntity.unCapitalizedClassName)!'-'}.grade == 0] color: #000000;[/#if]" name="${(attribute.unCapitalizedName)!'-'}">${r'$'}{(${(devEntity.unCapitalizedClassName)!'-'}.${(attribute.unCapitalizedName)!'-'})!}</span>
												</td>
											<#-- boolean类型显示trueIcon或者falseIcon -->
											<#elseif attribute.isAttributeTypeBeBoolean() >
												<td>
													[#if ${(devEntity.unCapitalizedClassName)!'-'}.${(attribute.unCapitalizedName)!'-'}?? && ${(devEntity.unCapitalizedClassName)!'-'}.${(attribute.unCapitalizedName)!'-'}]
														<span name="${(attribute.unCapitalizedName)!'-'}"" val="false" class="trueIcon">&nbsp;</span>
													[#else]
														<span name="${(attribute.unCapitalizedName)!'-'}"" val="true" class="falseIcon">&nbsp;</span>
													[/#if]
												</td>
											<#-- 文件类型显示一个查看/下载链接(或按钮)即可 -->
											<#elseif attribute.isAttributeTypeBeAnyFile() >
												<td>
													<a title="${(attribute.unCapitalizedName)!'-'}" href="${r'$'}{(${(devEntity.unCapitalizedClassName)!'-'}.${(attribute.unCapitalizedName)!'-'})!}" target="_blank" >查看/下载</a>	
												</td>
											<#-- 日期类型格式化显示 -->
											<#elseif attribute.isAttributeTypeBeDate() >
												<td>
													<span name="${(attribute.unCapitalizedName)!'-'}">${r'$'}{(${(devEntity.unCapitalizedClassName)!'-'}.${(attribute.unCapitalizedName)!'-'}?string("yyyy-MM-dd HH:mm:ss"))!}</span>
												</td>
											<#-- 除以上以外的类型统计直接显示 -->
											<#else>
												<td>				
													<span name="${(attribute.unCapitalizedName)!'-'}">${r'$'}{(${(devEntity.unCapitalizedClassName)!'-'}.${(attribute.unCapitalizedName)!'-'})!}</span>
												</td>
											</#if>
											<#-- 如果是treeEntity并且是titleAttribute，则在titleAttribute列之后跟随一个sort列 -->
											<#if devEntity.isTreeEntity() && attribute.unCapitalizedName == devEntity.titleAttribute >
												<td>
													<span>${r'$'}{(${(devEntity.unCapitalizedClassName)!'-'}.order)!'-'}</span>
												</td>
											</#if>
										</#list>
											<#-- 操作列按钮 -->
												<td>
													<!-- 大型ui -->
													<div class="hidden-sm hidden-xs btn-group">
													<#if devEntity.needEditBtn?? && devEntity.needEditBtn>
														<a class="btn btn-xs btn-info" href="edit.jhtml?id=${r'$'}{${(devEntity.unCapitalizedClassName)!'-'}.id}" >
															<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
															<span>${r'$'}{message("admin.common.edit")}</span>
														</a>
													</#if>
													<#if devEntity.needDeleteBtn?? && devEntity.needDeleteBtn>
														<a href="javascript:void(0);" class="deleteButton btn btn-xs btn-danger" ids="${r'$'}{${(devEntity.unCapitalizedClassName)!'-'}.id}" >
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
															<span>${r'$'}{message("admin.common.delete")}</span>
														</a>
													</#if>
													</div>
													<!-- 小型ui -->
													<div class="hidden-md hidden-lg">
														<div class="inline pos-rel">
															<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
																<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
															</button>
															<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<#if devEntity.needEditBtn?? && devEntity.needEditBtn>
																<li>
																	<a href="edit.jhtml?id=${r'$'}{${(devEntity.unCapitalizedClassName)!'-'}.id}" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="blue">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			<span>${r'$'}{message("admin.common.edit")}</span>
																		</span>
																	</a>
																</li>
															</#if>
															<#if devEntity.needDeleteBtn?? && devEntity.needDeleteBtn>
																<li>
																	<a href="javascript:void(0);" class="deleteButton tooltip-error" ids="${r'$'}{${(devEntity.unCapitalizedClassName)!'-'}.id}" data-rel="tooltip" title="Delete">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			<span>${r'$'}{message("admin.common.delete")}</span>
																		</span>
																	</a>
																</li>
															</#if>
															</ul>
														</div>
													</div>
												</td>
											</tr>
										[/#list]
										</tbody>
									</table><!-- /表格部分结束 -->
									
									<#if devEntity.needPage?? && devEntity.needPage >
									<!-- 引入分页部分 -->
									[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
										[#include "/admin/include/pagination.ftl"]
									[/@pagination]
									</#if>
									
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
		<script type="text/javascript" src="${r'$'}{base}/resources/admin/js/list.js"></script>
		
		<!-- 页面自定义内联脚本区 inline scripts related to this page -->
		<script type="text/javascript">
			[@flash_message /]
			
		[#-- list页表格单行操作按钮列的删除功能 --]
		<#if devEntity.needDeleteBtn?? && devEntity.needDeleteBtn>
			var $listTable = $("#listTable");
			<#if devEntity.needPage?? && devEntity.needPage>
			var $pageTotal = $("#pageTotal");
			</#if>
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
									<#if devEntity.needPage?? && devEntity.needPage>
									$pageTotal.text(parseInt($pageTotal.text()) - 1);
									</#if>
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
		</#if>
			
		</script>

	</body>
</html>
