<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>主页</title>
    
	<link href="<%=request.getContextPath()%>/sta/css/style.css" rel="stylesheet" type="text/css" />
	
</head>

<body bgcolor="yellow">
<iframe align="right" style="margin-top: 25px;margin-right: 10px;" width="200" scrolling="no" height="15" frameborder="0" allowtransparency="true" src="//i.tianqi.com/index.php?c=code&id=11&bdc=%23FFFF00&icon=1&py=jinshuiqu&site=12"></iframe>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="javascript:;">首页</a></li>
	    </ul>
    </div>
    <div class="mainindex" style="height: 82%;">
	    <div class="welinfo">
		    <span><img src="<%=request.getContextPath()%>/sta/images/sun.png" alt="天气" /></span>
		    <b><font color="blue"><b>${user.userName }</b></font>早上好，欢迎使用商品管理系统</b>(admin@uimaker.com)
		    <a href="<%=request.getContextPath()%>/toZtreeList.action">帐号设置</a>
	    </div>
	    <div class="welinfo">
		    <span><img src="<%=request.getContextPath()%>/sta/images/time.png" alt="时间" /></span>
		    <i>您上次登录的时间：<fmt:formatDate value="${user.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></i> （不是您登录的？<a href="javascript:updatPwd()">请点这里</a>）
	    </div>
	    <div class="welinfo">
		    <span><img src="<%=request.getContextPath()%>/sta/images/dp.png" alt="登陆次数" /></span>
		    <i><font color="red"><u><b>今天您是第<b>${user.count}</b>次登录</b></u></font></i>
	    </div>
	    <div class="xline"></div>
	    <ul class="iconlist">
		    <li><img src="<%=request.getContextPath()%>/sta/images/ico01.png" /><p><a href="javascript:;">管理设置</a></p></li>
		    <li><img src="<%=request.getContextPath()%>/sta/images/ico02.png" /><p><a href="javascript:;">发布文章</a></p></li>
		    <li><img src="<%=request.getContextPath()%>/sta/images/ico03.png" /><p><a href="javascript:;">数据统计</a></p></li>
		    <li><img src="<%=request.getContextPath()%>/sta/images/ico04.png" /><p><a href="javascript:;">文件上传</a></p></li>
		    <li><img src="<%=request.getContextPath()%>/sta/images/ico05.png" /><p><a href="javascript:;">目录管理</a></p></li>
		    <li><img src="<%=request.getContextPath()%>/sta/images/ico06.png" /><p><a href="javascript:;">查询</a></p></li> 
	    </ul>
	    <div class="ibox"><a class="ibtn"><img src="<%=request.getContextPath()%>/sta/images/iadd.png" />添加新的快捷功能</a></div>
	    <div class="xline"></div>
	    <div class="box"></div>
	    <div class="welinfo">
		    <span><img src="<%=request.getContextPath()%>/sta/images/dp.png" alt="提醒" /></span>
		    <b>Uimaker电影管理系统使用指南</b>
	    </div>
	    <ul class="infolist">
		    <li><span>您可以快速进行文章发布管理操作</span><a class="ibtn">发布或管理文章</a></li>
		    <li><span>您可以快速发布产品</span><a class="ibtn">发布或管理产品</a></li>
		    <li><span>您可以进行密码修改、账户设置等操作</span><a class="ibtn">账户管理</a></li>
	    </ul>
    	<div class="xline"></div>
    	<div class="uimakerinfo"><b>查看Uimaker网站使用指南，您可以了解到多种风格的B/S后台管理界面,软件界面设计，图标设计，手机界面等相关信息</b>(<a href="http://www.5imoban.net" target="_blank">我爱模板网</a>)</div>
	    <ul class="umlist">
		    <li><a href="javascript:;">如何发布文章</a></li>
		    <li><a href="javascript:;">如何访问网站</a></li>
		    <li><a href="javascript:;">如何管理广告</a></li>
		    <li><a href="javascript:;">后台用户设置(权限)</a></li>
		    <li><a href="javascript:;">系统设置</a></li>
	    </ul>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
</body>
	<script type="text/javascript">
	function updatPwd(){
		var name='${user.userName}';
		location.href="<%=request.getContextPath()%>/updatPwd.action?name="+encodeURI(encodeURI(name));
	}
	</script>
</html>