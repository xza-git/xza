<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
	<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>修改内容</strong>
	</div>
	<div class="body-content">
	<form method="post" class="form-x" action="<%=request.getContextPath()%>/brand/updateBrand" enctype="multipart/form-data">
		<div class="form-group">
			<div class="label">
				<label>品牌名称：</label>
			</div>
			<div class="field">
				<input type="hidden" value="${brandInfo.id}" name="id"/>
				<input type="text" class="input w50" value="${brandInfo.brandName}" name="brandName" data-validate="required:请输入品牌名称" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
        <div class="label">
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o"  type="submit"> 提交</button>
        </div>
      </div>
	</form>
	</div>
</div>
</body>
</html>