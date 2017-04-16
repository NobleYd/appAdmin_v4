<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加实体属性配置 - Powered By APP TEAM</title>
<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP TEAM @CopyRight 2015-2025" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	[@flash_message /]
	
	// 表单验证
	$inputForm.validate({
		rules: {
			attributeName: {
				required: true,
			},
			attributeDesc: {
				required: true,
			},
			sortIndex: {
				digits: true,
			},
			attributeType: {
				required: true,
			},
			isShow: {
			},
			isSort: {
			},
			isSearch: {
			},
			isRequired: {
			},
			isUnique: {
			},
			attributeTypeValue: {
			},
		},
		messages: {
			attributeName: {
			},
			attributeDesc: {
			},
			sortIndex: {
			},
			attributeType: {
			},
			isShow: {
			},
			isSort: {
			},
			isSearch: {
			},
			isRequired: {
			},
			isUnique: {
			},
			attributeTypeValue: {
			},
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<span>添加实体属性配置</span>
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>
					<span>属性名称</span>:
				</th>
				<td>
					<input type="text" name="attributeName" class="text" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>
					<span>属性注释</span>:
				</th>
				<td>
					<input type="text" name="attributeDesc" class="text" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					<span>属性次序</span>:
				</th>
				<td>
					<input type="text" name="sortIndex" value="0" class="text" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>
					<span>属性类型</span>:
				</th>
				<td>
					<input type="text" name="attributeType" class="text" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					<span>属性类型辅助值</span>:
				</th>
				<td>
					<input type="text" name="attributeTypeValue" class="text" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					<span>是否在list页面显示</span>:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isShow" value="true" checked="checked" />是否在list页面显示
						<input type="hidden" name="_isShow" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span>是否在list页面排序</span>:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isSort" value="true" checked="checked" />是否在list页面排序
						<input type="hidden" name="_isSort" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span>是否在list页面搜索</span>:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isSearch" value="true" checked="checked" />是否在list页面搜索
						<input type="hidden" name="_isSearch" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span>是否在必须属性</span>:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isRequired" value="true" checked="checked" />是否在必须属性
						<input type="hidden" name="_isRequired" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					<span>是否唯一属性</span>:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isUnique" value="true" checked="checked" />是否唯一属性
						<input type="hidden" name="_isUnique" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="window.history.back();" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>