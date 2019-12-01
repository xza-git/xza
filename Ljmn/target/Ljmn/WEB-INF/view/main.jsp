<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手表售卖后台系统</title>
</head>
<link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/muban1/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/muban1/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.css">
</head>
<body>
<div id="loading">
   	 <div></div>
	 <div></div>
	 <span></span>
</div>
<!-- WRAPPER -->
<div id="wrapper"> 
  <!-- NAVBAR -->
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="brand"> <a href="<%=request.getContextPath()%>/user/toMain"><img src="<%=request.getContextPath()%>/muban1/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a> </div>
    <div class="container-fluid">
      <div class="navbar-btn" style="padding: 0; padding-top: 10px;">
        <button type="button" class="btn-toggle-fullwidth btn-toggle-mx"><img src="<%=request.getContextPath()%>/muban1/img/left.png" height="40px" alt=""></button>
      </div>
    </div>
  </nav>
  <!-- END NAVBAR --> 
  <!--_________________________________________________________________________________________--> 
  <!-- LEFT SIDEBAR -->
  <div id="sidebar-nav" class="sidebar">
    <div class="sidebar-scroll">
      <nav>
        <ul class="nav">
          <li><a href="<%=request.getContextPath()%>/user/toIndex" target="_blank" class="iframe_link active"><span>首页</span></a></li>
		  <li> 
			<a href="javascript:;" class="nav-togg"> <span>用户管理</span> </a>
            <div>
              <ul>
                  <li><a href="<%=request.getContextPath()%>/user/toUserList" target="_blank" class="iframe_link"><span>用户列表</span></a></li>
                  <li><a href="<%=request.getContextPath()%>/user/toSaveUserJsp" target="_blank" class="iframe_link"><span>新增用户</span></a></li>
              </ul>
            </div>
          </li>
          <li> 
			<a href="javascript:;" class="nav-togg"> <span>商品管理</span> </a>
            <div>
              <ul>
                  <li><a href="<%=request.getContextPath()%>/product/toProductList" target="_blank" class="iframe_link"><span>商品列表</span></a></li>
                  <li><a href="<%=request.getContextPath()%>/product/toSaveProductJsp" target="_blank" class="iframe_link"><span>新增商品</span></a></li>
              </ul>
            </div>
          </li>
          <li> 
			<a href="javascript:;" class="nav-togg"> <span>品牌管理</span> </a>
            <div>
              <ul>
                  <li><a href="<%=request.getContextPath()%>/brand/toBrandList" target="_blank" class="iframe_link"><span>品牌列表</span></a></li>
                  <li><a href="<%=request.getContextPath()%>/brand/toSaveBrandJsp" target="_blank" class="iframe_link"><span>新增品牌</span></a></li>
              </ul>
            </div>
          </li>
          <li> 
			<a href="javascript:;" class="nav-togg"> <span>地区管理</span> </a>
            <div>
              <ul>
                  <li><a href="<%=request.getContextPath()%>/address/toAddressList" target="_blank" class="iframe_link"><span>地区列表</span></a></li>
              </ul>
            </div>
          </li>
        </ul>
      </nav>
    </div>
  </div>
  <div class="main">
    <div class="main-content" style="height: 100%;">
      <iframe src="<%=request.getContextPath()%>/user/toIndex" class="iframe_mx uicss-cn"></iframe>
    </div>
  </div>
</div>
<script src="<%=request.getContextPath()%>/muban1/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/muban1/link/js/bootstrap.min.js"></script> 
<script src="<%=request.getContextPath()%>/muban1/js/jquery.slimscroll.min.js"></script>
<script src="<%=request.getContextPath()%>/muban1/js/klorofil-common.js" ></script> 

	<%-- <a href="<%=request.getContextPath()%>/user/toUserList">用户列表</a>
	<a href="<%=request.getContextPath()%>/user/toSaveUserJsp">新增用户</a> --%>
</body>
</html>