<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>实体属性配置列表 - Powered By APP TEAM</title>
<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP TEAM @CopyRight 2015-2025" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {

	[@flash_message /]


	[#-- 删除功能 --]
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
								}, 3000);
							}
						}
						$deleteButton2.removeClass("disabled");
					}
				});
			},
			onCancel: function(){
				$deleteButton2.removeClass("disabled");
			}
		});
	});

});
</script>
</head>
<body>
	<div class="path">
		<span>实体属性配置列表</span>
		<span>(${message("admin.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<a href="add.jhtml" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("admin.common.add")}
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("admin.common.delete")}
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("admin.common.refresh")}
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						${message("admin.page.pageSize")}<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;"[#if page.pageSize == 10] class="current" [/#if] val="10">10</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 20] class="current" [/#if] val="20">20</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 50] class="current" [/#if] val="50">50</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 100] class="current" [/#if] val="100">100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" [#if page.searchProperty == "attributeName" ] class="current" [/#if] val="attributeName">属性名称</a>
						</li>
						<li>
							<a href="javascript:;" [#if page.searchProperty == "attributeDesc" ] class="current" [/#if] val="attributeDesc">属性注释</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="attributeName">属性名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="attributeDesc">属性注释</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="devEntity">所属实体</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="sortIndex">属性次序</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="attributeType">属性类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="attributeTypeValue">属性类型辅助值</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isShow">是否在list页面显示</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isSort">是否在list页面排序</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isSearch">是否在list页面搜索</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isRequired">是否在必须属性</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isUnique">是否唯一属性</a>
				</th>
				<th>
					<span>${message("admin.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as devAttribute ]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${devAttribute.id}" />
					</td>
				
					<td>
						<span name="attributeName">${(devAttribute.attributeName)!}</span>
					</td>
					<td>
						<span name="attributeDesc">${(devAttribute.attributeDesc)!}</span>
					</td>
					<td>
						<span name="devEntity">${(devAttribute.devEntity.title)!}</span>
					</td>
					<td>
						<span name="sortIndex">${(devAttribute.sortIndex)!}</span>
					</td>
					<td>
						<span name="attributeType">${(devAttribute.attributeType)!}</span>
					</td>
					<td>
						<span name="attributeTypeValue">${(devAttribute.attributeTypeValue)!}</span>
					</td>
					<td>
						[#if devAttribute.isShow?? && devAttribute.isShow]
							<span class="trueIcon">&nbsp;</span>
						[#else]
							<span class="falseIcon">&nbsp;</span>
						[/#if]
					</td>
					<td>
						[#if devAttribute.isSort?? && devAttribute.isSort]
							<span class="trueIcon">&nbsp;</span>
						[#else]
							<span class="falseIcon">&nbsp;</span>
						[/#if]
					</td>
					<td>
						[#if devAttribute.isSearch?? && devAttribute.isSearch]
							<span class="trueIcon">&nbsp;</span>
						[#else]
							<span class="falseIcon">&nbsp;</span>
						[/#if]
					</td>
					<td>
						[#if devAttribute.isRequired?? && devAttribute.isRequired]
							<span class="trueIcon">&nbsp;</span>
						[#else]
							<span class="falseIcon">&nbsp;</span>
						[/#if]
					</td>
					<td>
						[#if devAttribute.isUnique?? && devAttribute.isUnique]
							<span class="trueIcon">&nbsp;</span>
						[#else]
							<span class="falseIcon">&nbsp;</span>
						[/#if]
					</td>
					<td>
						<a href="edit.jhtml?id=${devAttribute.id}">[${message("admin.common.edit")}]</a>
						<a href="javascript:;" class="deleteButton" ids="${devAttribute.id}">[${message("admin.common.delete")}]</a>
					</td>
				</tr>
			[/#list]
		</table>
		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
			[#include "/admin/include/pagination.ftl"]
		[/@pagination]
	</form>
</body>
</html>