<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<script type="text/javascript">
	function saveUser(){
		var userName=$("input[name='userName']").val()
		var userPwd=$("input[name='userPwd']").val()
		var pwd=$("input[name='pwd']").val()
		var realName=$("input[name='realName']").val()
		if(userName != '' && userPwd != '' && pwd != '' && realName != ''){
			if(userPwd == pwd){
				$.ajax({
					url:"<%=request.getContextPath()%>/user/saveUser",
					data:{"userName":userName,"userPwd":userPwd,"realName":realName},
					dataType:"json",
					type:"post",
					success:function(res){
						if(res.success=='1'){
							location.href="<%=request.getContextPath()%>/user/toMain";
						}else{
							alert("用户名已存在")
						}
					},
					error:function(res){
						alert("注册失败，请重试！");
					}
				})
			}else{
				alert("密码与确认密码不一致");
			}
		}else{
			alert("所有信息为必填")
		}
	}

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
				<label>用户名：</label>
			</div>
			<div class="field">
				<input type="text" class="input w50" name="userName" data-validate="required:请输入用户名" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>真实姓名：</label>
			</div>
			<div class="field">
				<input type="text" class="input w50" name="realName" data-validate="required:请输入真实姓名" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>密码：</label>
			</div>
			<div class="field">
				<input type="password" class="input w50" name="userPwd" data-validate="required:1男2女" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>确认密码：</label>
			</div>
			<div class="field">
				<input type="password" class="input w50" name="pwd" data-validate="required:请输入手机号" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
        <div class="label">
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" onclick="saveUser()" type="button"> 提交</button>
        </div>
      </div>
	</form>
	</div>
</div>
</body>
</html>