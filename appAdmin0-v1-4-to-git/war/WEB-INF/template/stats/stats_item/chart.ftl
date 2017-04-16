<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入页头部分 -->
		[#include "/admin/include/header.ftl"]
		
		<title>统计项图表 - Powered By APP TEAM</title>
		
		<!-- 页面自定义插件样式引入区 page specific plugin styles -->
	
		<!-- 页面自定义内联样式 inline styles related to this page -->
	
	</head>
	<body class="no-skin">
		
		<!-- 引入顶部导航部分 -->
		[#include "/admin/include/navbar.ftl"]
		
		<div class="main-container" id="main-container">
			
			<!-- {定义左侧菜单选中项 menu="《此处填写左侧大分类》" subMenu="《此处填写小分类》" } -->
			[#assign menu="systemGroup" subMenu="statsItem" ]
			
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
								<span>统计项图表</span>
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
						<form id="inputForm" class="form-horizontal" role="form" >
							<div class="row">
								<div class="col-xs-12">
									<div class="tabbable">
									    <ul class="nav nav-tabs padding-12 tab-color-blue background-blue">
									        <li class="active">
									            <a aria-expanded="true" data-toggle="tab" href="#base">图表查看</a>
									        </li>
									    </ul>
									    <div class="tab-content">
									        <div id="base" class="tab-pane active">
												<div class="form-group">
													<div class="col-sm-12">
														<div id="chartContainer" style="height: 600px;"></div>
													</div>
												</div>
												<div class="clearfix form-actions">
												    <div class="col-sm-offset-3 col-sm-9">
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
		<script src="${base}/resources/admin/echarts/echarts.js" type="text/javascript" charset="utf-8"></script>

		<!-- 页面自定义内联脚本区 inline scripts related to this page -->
		<script type="text/javascript">
		$().ready(function() {
			[@flash_message /]
			
			require.config({
		        paths: {
		            echarts: '${base}/resources/admin/echarts'
		        }
		    });
		
			require(
		        [
		            'echarts',
		            'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
		            'echarts/chart/bar'
		        ],
		        function (ec) {
		            //myChart
		            var myChart = ec.init(document.getElementById('chartContainer'));
		            //auto resize
		            window.onresize = myChart.resize;
		            //option
		            var option = {
				        title : {
					        text: '${(statsItem.showTitle)!''}',
					        subtext: '${(statsItem.subShowTitle)!''}'
					    },
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {
					            type : 'shadow'
					        }
					    },
					    legend: {
					        data:['${(statsItem.showTitle)!''}']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            dataView : {show: true, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : false,
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    xAxis : [
					        {
					            type : 'category',
					            data : []
					        }
					    ],
					    series : [
					        {
					            name:'${(statsItem.showTitle)!''}',
					            type:'line',
					            itemStyle : { normal: {label : {show: true, position: 'top'}, barBorderRadius:5}},
					            data:[]
					        }
					    ]
					};
					
					//ajax data
					$.ajax({
						url:"${base}/admin/stats_data/stats.jhtml",
						dataType:"json",
						data:{
							statsItemId:"${(statsItem.id)!''}",
							statsCycle:"${(statsItem.statsCycle)!''}",
							statsMethod:"${(statsItem.statsMethod)!''}"
						},
						async:"true",
						success:function(obj){
							$(obj).each(function(index,element){
								option.xAxis[0].data.push(element.dataTime);
								option.series[0].data.push(element.value);
							});
							//apply data
							myChart.setOption(option);
						}
					});
		            
		        }//end of function
		    );//end of require

		});
		</script>

	</body>
</html>
