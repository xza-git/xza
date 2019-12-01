<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手表售卖后台系统</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<link rel="icon" href="${pageContext.request.contextPath}/loginSpecial/images/favicon.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/loginSpecial/images/favicon.ico" type="image/x-icon"/>
<link href="${pageContext.request.contextPath}/loginSpecial/css/default.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/loginSpecial/css/styles.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/loginSpecial/css/demo.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/loginSpecial/css/loaders.css" rel="stylesheet" type="text/css" />

</head>
<script type="text/javascript">
	<%-- function login(){
		var userName=$("input[name='userName']").val();
		var userPwd=$("input[name='userPwd']").val();
		if(userName != '' && userPwd != ''){
			$.ajax({
				url:"<%=request.getContextPath()%>/user/login",
				data:{"userName":userName,"userPwd":userPwd},
				dataType:"json",
				type:"post",
				success:function(res){
					if(res.success=='1'){
						location.href="<%=request.getContextPath()%>/user/toMain";
					}else{
						alert("用户或密码错误")
					}
				},
				error:function(res){
					alert("登录失败，请重试！");
				}
			})
		}else{
			if(userName == ''){
				alert("用户名不能为空")
			}else{
				alert("密码不能为空")
			}
		}
	} --%>
</script>
<body style="background-color:black; background-image:url(<%=request.getContextPath() %>/sta/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
   <div class='login'>
    <div class='login_title'>
        <span>商品后台管理系统</span>
    </div>
    <div class='login_fields'>
        <div class='login_fields__user'>
            <div class='icon'>
            </div>
            <input name="userName" placeholder='用户名' maxlength="16" class="username" type='text' autocomplete="off"/>
            <div class='validation'>
            </div>
        </div>
        <div class='login_fields__password'>
            <div class='icon'>
            </div>
            <input name="userPwd" class="passwordNumder" placeholder='密码' maxlength="16" type="password" autocomplete="off">
            <div class='validation'>
            </div>
        </div>
        <div class='login_fields__submit'>
            <input type="button"  value='登录'>
        </div>
    </div>
    <div class='success'>
    </div>
    <div class='disclaimer'>
        <p>欢迎登陆接入平台</p>
    </div>
</div>
<!-- </form> -->
<div class='authent'>
    <div class="loader" style="height: 60px;width: 60px;margin-left: 28px;margin-top: 40px">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
    <p>登陆中...</p>
</div>
<div class="OverWindows"></div>
<!--<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/loginSpecial/js/jquery-ui.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/loginSpecial/js/stopExecutionOnTimeout.js?t=1'></script>
<script src="${pageContext.request.contextPath}/loginSpecial/layui/layui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/loginSpecial/js/Particleground.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/loginSpecial/js/Treatment.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/loginSpecial/js/jquery.mockjax.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/loginSpecial/js/controlLogin.js" type="text/javascript"></script>
</body>
<body>
	用户名：<input type="text" name="userName"/><br>
	密    码：<input type="password" name="userPwd"/><br>
	<input type="button" value="登录" onclick="login()"/>
</body>
</html>