<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/js/jquery.ztree.all.min.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$.ajax({
		url:"<%=request.getContextPath()%>/address/queryAddressList",
		type:"post",
		dataType:"json",
		success:function(data){
			res=data;
			var setting = {
					callback: {
						
						},
					//使用简单 Array 格式的数据
					data: {
						simpleData: {
							enable: true
						}
					},
					//需要显示 checkbox
					check: {
						enable: true,
						//checkbox 勾选操作，只影响父级节点；取消勾选操作，只影响子级节点
						chkStyle: "checkbox",
						chkboxType: { "Y": "s", "N": "s" }
					},
				}; 
			var nodes = data,
			zTreeObj = $.fn.zTree.init($("#tree"), setting, nodes);
		},
		error:function(data){
			alert("加载失败");
		}
	})
});
</script>
<body bgcolor="white">
<ul id="tree" class="ztree" style="width: 230px;overflow: auto;"></ul>
</body>
</html>