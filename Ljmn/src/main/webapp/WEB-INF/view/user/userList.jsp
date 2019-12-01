<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap3/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath}/js/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<!-- 这是字体图片所需要的css -->
<%-- <link href="${pageContext.request.contextPath}/js/bootstrap3/fonts/glyphicons-halflings-regular.svg" rel="stylesheet"> --%>

<!-- 这是dataTable的css -->
<link href="${pageContext.request.contextPath}/js/DataTables/datatables.min.css" rel="stylesheet">

<!-- 这是dataTable的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/DataTables/datatables.min.js"></script>

<!-- 这是基于bootstrap的js分页工具 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrapByPage.js"></script>

<!-- 时间选择器样式表 -->
<link href="<%= request.getContextPath()%>/js/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">

<!-- 时间选择器前置脚本 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker/js/moment-with-locales.js"></script>

<!-- 时间选择器核心脚本 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">
	$(document).ready( function (){
		getData();
		
		//给对应的input框 绑定bootstrap-datetime 样式
		var beginDate  = $("#beginDate").val();
		$("#beginDate").datetimepicker({
			//设置日期格式
			format: 'YYYY-MM-DD HH:mm:ss',
			//设置为中文 默认是英文
	        locale: moment.locale('zh-CN'),
	        maxDate:new Date()
		})
		//给对应的input框 绑定bootstrap-datetime 样式
		$("#endDate").datetimepicker({
			//设置日期格式
			format: 'YYYY-MM-DD HH:mm:ss',
			//设置为中文 默认是英文
	        locale: moment.locale('zh-CN'),
	        minDate:new Date()
		})
		
		
	});
	//分页on条件
	function getData(){
		var cpage = $("#cpage").html();
		var pageSize = $("#pageSize").val();
		var userName = $("#userName").val();
		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		var pam = {"cpage":cpage,"pageSize":pageSize,"userName":userName,"beginDate":beginDate,"endDate":endDate};
		$.ajax({
			url:"<%=request.getContextPath()%>/user/findUserByPage",
			dataType:"json",
			type:"post",
			data:pam,
			success:function(res){
				/* btn-danger 这是警告的按钮 红色的 */
		 		/* btn-primary	原始按钮样式（未被操作） */
		 		/* btn-success	表示成功的动作(绿色的) */
		 		/* btn-info	该样式可用于要弹出信息的按钮(蓝色的) */
		 		/* btn-warning	表示需要谨慎操作的按钮(黄色的) */
		 		/* btn-sm	制作一个小按钮 */
		 		/* btn-lg	制作一个大按钮 */
				var dataList = res.dataList;//查询到的数据集合 和下面对应
				$("#thead").html("");//清空原有的表头不然会重复出现
				//datatable 所需要的数据格式 键值对  这里定义的是列的数据 通过和data里放的数据源对应 可以直接通过属性名取出数据
				var columName  = [
				              {"data":function(data){//这个data就是咱们查到的pageInfo中的数据集合里的对象
				            	  return '<input type="checkbox" value="'+data.id+'" class="box"/>'
				              }},
				              {"data":"userName"},
				              {"data":"realName"},
				              {"data":"loginTime"},
				              {"data":function (data){//data是你的数据集合   通过你的属性名 可以直接.出来 这个data 只是个名字
				              	return '<button name="shanchu" class="btn btn-danger btn-sm" value="'+data.id+'"><span class="glyphicon glyphicon-trash" style="color: #ffffff;"></span> '+
									   ' </button><button name="xiugai" class="btn btn-info btn-sm" value="'+data.id+'"><span class="glyphicon glyphicon-pencil" style="color: #ffffff;"></span></button>';
				              }}];
				/* 定义一个json数组 放表头所需要的数据 给表头的tr赋值   */
				var tabHead  = [{"data":"选择"},{"data":"用户名"},{"data":"真实姓名"},{"data":"登录时间"},{"data":"操作"}];
				for(var i =0;i<tabHead.length; i++){
			       	$("#thead").append("<th style='text-align: center;'>"+tabHead[i].data+"</th>");
			    }
				//重新初始化 不然数据不会变
				var table = $('#table').dataTable();//获取table表格的dataTable  table是id
				table.fnClearTable(); //清空一下table
				table.fnDestroy(); //还原初始化了的dataTable
				
				/* 渲染datatables */
				//初始化 渲染表格 附上数据
				$('#table').DataTable({
					language: {
		                zeroRecords: "您搜索的内容不存在",//table tbody内容为空时，tbody的内容。
		                //下面三者构成了总体的左下角的内容。
		                info: "",//左下角的信息显示，大写的词为关键字。
		                infoEmpty: "",//筛选为空时左下角的显示。
		                infoFiltered: ""//筛选之后的左下角筛选提示，
		            },
		            ordering: false,//是否启用排序 
		            searching: false,//关闭搜索 这个搜索只会查找已经存在数据里符合条件的  默认是true
		            paging:false,//关闭自带的分页
		            data: dataList,//查询到的数据集合 和上面对应  这是datatables所需要的数据源 这个数据源必须是数组或者集合
		            columns:columName,//设置列的初始化属性
		            createdRow : function ( row, data, index ) {
		            	$('td', row).css('font-weight',"bold").css("text-align","center");//这是让表中的数据默认居中和加粗  标签选择器
		            },
				} );
				$("#totalPage").html(res.totalPage);//把后台查到的总页数赋值给分页工具
				$("button[name='shanchu']").click(function(){
					var id=$("button[name='shanchu']").val();
					$.ajax({
						url:"<%=request.getContextPath()%>/user/deleteUser",
						data:{id:id},
						dataType:"json",
						type:"post",
						success:function(res){
							alert(res.success);
							location.reload();
						},
						error:function(res){
							alert("删除出错");
						}
					})
				})
				
				//修改用户
				$("button[name='xiugai']").click(function(){
					var id=$(this).val();
					location.href="<%=request.getContextPath()%>/user/updateJsp?id="+id;
				})
			
			}
		})
	}
	
	
	//删除用户
	
</script>
<script type="text/javascript">
	var t=null;
	t=setTimeout(time,1000);   //设置定时器，一秒刷新一次
	function time(){
	    clearTimeout(t);  //清楚定时器
	    dt=new Date();
	    var y=dt.getYear()+1900;
	    var m=dt.getMonth()+1;
	    var d=dt.getDate();
	    var weekday=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
	    var day=dt.getDay();
	    var h=dt.getHours();
	    var min=dt.getMinutes();
	    var s=dt.getSeconds();
	    if(h<10){
	        h="0"+h;
	    }
	    if(min<10){
	        min="0"+min;
	    }
	    if(s<10){
	        s="0"+s;
	    }
	    document.getElementById("timeShow").innerHTML= y + "年" + m + "月" + d + "日" + weekday[day] + "" + h + ":" + min + ":" + s + "";
	    t = setTimeout(time, 1000);
	}
</script>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div style="float: left;margin-top: 15px;">当前时间:</div>
			<div id="timeShow" style="margin-left: 10px;float: left;margin-top: 15px"></div>
			<script type="text/javascript">
				WIDGET = {FID: 'aJQJCdKuSr'}
			</script>
		</div>
	</nav>
	<!-- 天气 -->
	<!-- 条件查询 -->
	<div style="float: left;"><!-- 上 左 右 -->
		<div class="input-group">
	        <span class="input-group-addon">用户名:</span>
			<input type="text" id="userName" class="form-control" placeholder="请输入用户名" style="width: 130px"/>
			<span class="input-group-addon">开始时间:</span>
			<input type="text" id="beginDate" class="form-control" placeholder="开始时间" style="width: 160px"/>
	    	<span class="input-group-addon">结束时间:</span>
			<input type="text" id="endDate" class="form-control" placeholder="结束时间" style="width: 160px"/>
		</div>
	</div>
	
	<div style="float: right;margin-top: -36px;">
		<button class="btn btn-info btn-lg" onclick="getData()">
			<span class="glyphicon glyphicon-search" style="color: rgb(4, 9, 0);"> Search</span>
		</button>
	</div>
	<!-- table 原始样式 加了这个才能加别的  -->
	<!-- table-bordered 添加边框 也就是我们以前加的border -->
	<!-- table-hover 移上变色 -->
	<!-- table-striped 斑马线条纹 -->
	<!-- table-condensed 让表格更加紧促 -->
	<table id="table" class="table table-striped table-hover" style="margin-top: 120px;width: 1000px">
		<thead>
			<tr class="active" id="thead">
			</tr>
		</thead>
		<tbody id="tbody"></tbody>
	</table>
	<div id="pager_tools"></div>
</body>
</html>