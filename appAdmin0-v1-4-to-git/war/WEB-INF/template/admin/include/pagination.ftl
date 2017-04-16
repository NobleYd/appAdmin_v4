<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
<input type="hidden" id="searchProperty" name="searchProperty" value="${page.searchProperty}" />
<input type="hidden" id="orderProperty" name="orderProperty" value="${page.orderProperty}" />
<input type="hidden" id="orderDirection" name="orderDirection" value="${page.orderDirection}" />
[#if totalPages > 1]
<div class="pull-right">
	<ul class="pagination">
		[#if isFirst]
			<li class="disabled">
				<a href="javascript: void(0);">
					<i class="ace-icon fa fa-angle-double-left"></i>
				</a>
			</li>
		[#else]
			<li>
				<a href="javascript: $.pageSkip(${firstPageNumber});">
					<i class="ace-icon fa fa-angle-double-left"></i>
				</a>
			</li>
		[/#if]
		[#if hasPrevious]
			<li>
				<a href="javascript: $.pageSkip(${previousPageNumber});">
					<i class="ace-icon fa fa-angle-left"></i>
				</a>
			</li>
		[#else]
			<li class="disabled">
				<a href="javascript: void(0);">
					<i class="ace-icon fa fa-angle-left"></i>
				</a>
			</li>
		[/#if]
		[#list segment as segmentPageNumber]
			[#if segmentPageNumber_index == 0 && segmentPageNumber > firstPageNumber + 1]
				<li class="disabled">
					<a href="javascript: void(0);">...</a>
				</li>
			[/#if]
			[#if segmentPageNumber != pageNumber]
				<li>
					<a href="javascript: $.pageSkip(${segmentPageNumber});">${segmentPageNumber}</a>
				</li>
			[#else]
				<li class="active">
					<a href="javascript: void(0);">${segmentPageNumber}</a>
				</li>
			[/#if]
			[#if !segmentPageNumber_has_next && segmentPageNumber < lastPageNumber - 1]
				<li class="disabled">
					<a href="javascript: void(0);">...</a>
				</li>
			[/#if]
		[/#list]
		[#if hasNext]
			<li>
				<a href="javascript: $.pageSkip(${nextPageNumber});">
					<i class="ace-icon fa fa-angle-right"></i>
				</a>
			</li>
		[#else]
			<li class="disabled">
				<a href="javascript: void(0);">
					<i class="ace-icon fa fa-angle-right"></i>
				</a>
			</li>
			<span class="nextPage">&nbsp;</span>
		[/#if]
		[#if isLast]
			<li class="disabled">
				<a href="javascript: void(0);">
					<i class="ace-icon fa fa-angle-double-right"></i>
				</a>
			</li>
		[#else]
			<li>
				<a href="javascript: $.pageSkip(${lastPageNumber});">
					<i class="ace-icon fa fa-angle-double-right"></i>
				</a>
			</li>
		[/#if]
		<li class="disabled">
			<a href="javascript: void(0);">
			${message("admin.page.totalPages", totalPages)}
			</a>
		</li>
		<li>
			${message("admin.page.pageNumber", '<input type="text" id="pageNumber" name="pageNumber" />')}
		</li>
	</ul>	
	<span>
	</span>
</div>
[/#if]
