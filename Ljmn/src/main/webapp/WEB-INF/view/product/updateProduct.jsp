<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
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
<script type="text/javascript">
function uplaodFile(){
	//h5 $("#file").get(0)节点元素；.files[0]将节点元素转换为file文件
	var form =  new FormData();
	form.append("file", $("#file").get(0).files[0]);
	$.ajax({
		contentType:false,
		processData: false,
		url:"<%=request.getContextPath()%>/upload/uploadFile",
		data:form,
		dataType:"json",
		type:"post",
		success:function(res){
			$("#poster").attr("src","<%=request.getContextPath()%>/upload/poster/"+res.name)
			$("input[name='mainImagePath']").attr("value",res.name);
			alert(res.json)
		},
		error:function(){
			alert("程序出现问题");
		}
	})
}
</script>
<script type="text/javascript">
	var aaa=${province.id};
	var ccc='${province.name}';
	$(function(){
		$("select[name='province']").html("");
		//省
		$.ajax({
			url:"<%=request.getContextPath()%>/address/getAddress",
			data:{pid:0},
			dataType:"json",
			type:"post",
			success:function(res){
				var ss="<option>----请选择----</option>";
				$("select[name='province']").append(ss);
				for (var i = 0; i < res.length; i++) {
					if(aaa==res[i].id){
						str="<option value='"+res[i].id+"' selected >"+res[i].name+"</option>";
					}else{
						str="<option value='"+res[i].id+"'>"+res[i].name+"</option>";
					}
					$("select[name='province']").append(str);
				}
			},
			error:function(res){
				alert("拉取地址失败，请重试！");
			}
		})
	})
	//市
	function toCity(){
		$("select[name='city']").html("");
		var pid=$("select[name='province']").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/address/getAddress",
			data:{pid:pid},
			dataType:"json",
			type:"post",
			success:function(res){
				var ss="<option>----请选择----</option>";
				$("select[name='city']").append(ss);
				for (var i = 0; i < res.length; i++) {
					str="<option value='"+res[i].id+"'>"+res[i].name+"</option>";
					$("select[name='city']").append(str);
				}
			},
			error:function(res){
				alert("拉取地址失败，请重试！");
			}
		})
	}
	//县
	function toCounty(){
		$("select[name='county']").html("");
		var pid=$("select[name='city']").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/address/getAddress",
			dataType:"json",
			data:{pid:pid},
			type:"post",
			success:function(res){
				var ss="<option>----请选择----</option>";
				$("select[name='province']").append(ss);
				for (var i = 0; i < res.length; i++) {
					str="<option  value='"+res[i].id+"'>"+res[i].name+"</option>";
					$("select[name='county']").append(str);
				}
			},
			error:function(res){
				alert("拉取地址失败，请重试！");
			}
		})
	}
</script>
<body>
<div class="panel admin-panel">
	<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>修改内容</strong>
	</div>
	<div class="body-content">
	<form method="post" class="form-x" action="<%=request.getContextPath()%>/product/updateProduct" enctype="multipart/form-data">
		<div class="form-group">
			<div class="label">
				<label>商品名称：</label>
			</div>
			<div class="field">
				<input type="hidden" value="${productInfo.id}" name="id"/>
				<input type="text" class="input w50" value="${productInfo.productName}" name="productName" data-validate="required:请输入商品名称" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
				<div class="label">
					<label>电影类型：</label>
				</div>
				<div class="field">
					<select name="brandName" class="input w50">
						<option value="-1">请选择</option>
						<c:forEach items="${brandList}" var="brandList">
							<option ${brandList.brandName==productInfo.brandName?"selected":""} value="${brandList.id}">${brandList.brandName}</option>
						</c:forEach>
					</select>
					<div class="tips"></div>
				</div>
			</div>
		<div class="form-group">
			<div class="label">
				<label>商品图片：</label>
			</div>
			<div class="field">
				<input type="file" id="file" onchange="uplaodFile()"/>
				<input type="hidden" value="${productInfo.mainImagePath}" name="mainImagePath"/>
				<img alt="" id="poster" src="<%=request.getContextPath()%>/upload/poster/${productInfo.mainImagePath}" width="100px;">
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>单价：</label>
			</div>
			<div class="field">
				<input type="text" class="input w50" value="${productInfo.price}" name="price" data-validate="required:例 22.3" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>库存地址：</label>
			</div>
			<div class="field">
				<select name="province" onchange="toCity()">
					<option>----请选择----</option>
				</select>省
				<select name="city" onchange="toCounty()">
					<option>----请选择----</option>
					<c:forEach items="${addressList}" var="addressList">
							<option ${addressList.id==city.id?"selected":""} value="${addressList.id}">${addressList.name}</option>
					</c:forEach>
				</select>市
				<select name="county">
					<option>----请选择----</option>
					<c:forEach items="${addressList}" var="addressList">
							<option ${addressList.id==county.id?"selected":""} value="${addressList.id}">${addressList.name}</option>
					</c:forEach>
				</select>县
				<input type="text" name="address" value="${strs}"/>
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