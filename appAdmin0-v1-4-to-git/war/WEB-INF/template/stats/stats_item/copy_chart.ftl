<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<meta name="author" content="APP TEAM" />
<meta name="copyright" content="APP" />
<title></title>

<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>

<script src="${base}/resources/admin/js/echarts/echarts.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
	*{
		font-family: "微软雅黑";
	}
</style>


<script type="text/javascript">
$(function(){
	
	require.config({
        paths: {
            echarts: '${base}/resources/stats/js/echarts'
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
				url:"${base}/stats_data/stats.jhtml",
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

});//end of $(function(){})
     
</script>
</head>
<body>
	<div id="chartContainer" style="width: 100%; height: 800px;">
	</div>
</body>
</html>
