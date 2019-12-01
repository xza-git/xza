<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增品牌页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<script type="text/javascript">
	function saveBrand(){
		var brandName=$("input[name='brandName']").val()
		if(brandName != ''){
			$.ajax({
				url:"<%=request.getContextPath()%>/brand/saveBrand",
				data:{"brandName":brandName},
				dataType:"json",
				type:"post",
				success:function(res){
					if(res.success=='1'){
						location.href="<%=request.getContextPath()%>/brand/toBrandList";
					}else{
						alert("品牌已存在")
					}
				},
				error:function(res){
					alert("新增失败，请重试！");
				}
			})
		}else{
			alert("所有信息为必填")
		}
	}
</script>
<script>
</script>
<body>
<div class="panel admin-panel">
	<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>增加内容</strong>
	</div>
	<div class="body-content">
	<form method="post" class="form-x" action="<%=request.getContextPath()%>/saveUser.action" enctype="multipart/form-data">
		<div class="form-group">
			<div class="label">
				<label>品牌名称：</label>
			</div>
			<div class="field">
				<input type="text" class="input w50" name="brandName" data-validate="required:请输入品牌名称" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
        <div class="label">
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" onclick="saveBrand()" type="button"> 提交</button>
        </div>
      </div>
	</form>
	</div>
</div>
</body>
</html>