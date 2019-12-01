<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增商品页面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<script type="text/javascript">
	function saveProduct(){
		var productName=$("input[name='productName']").val()
		var mainImagePath=$("input[name='mainImagePath']").val()
		var price=$("input[name='price']").val()
		var brandName=$("select[name='brandName']").find("option:selected").val()
		var province=$("select[name='province']").find("option:selected").val()
		var city=$("select[name='city']").find("option:selected").val()
		var county=$("select[name='county']").find("option:selected").val()
		var address=$("#address").val();
		if(productName != '' && mainImagePath != '' && price != ''){
			$.ajax({
				url:"<%=request.getContextPath()%>/product/saveProduct",
				data:{"productName":productName,"mainImagePath":mainImagePath,"price":price,"brandName":brandName,"province":province,"city":city,"county":county,"address":address},
				dataType:"json",
				type:"post",
				success:function(res){
					if(res.success=='1'){
						location.href="<%=request.getContextPath()%>/product/toProductList";
					}else{
						alert("商品已存在")
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
<script type="text/javascript">
	$(function(){
		//省
		$.ajax({
			url:"<%=request.getContextPath()%>/address/getAddress",
			data:{pid:0},
			dataType:"json",
			type:"post",
			success:function(res){
				for (var i = 0; i < res.length; i++) {
					str="<option value='"+res[i].id+"'>"+res[i].name+"</option>";
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
		var pid=$("select[name='province']").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/address/getAddress",
			data:{pid:pid},
			dataType:"json",
			type:"post",
			success:function(res){
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
		var pid=$("select[name='city']").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/address/getAddress",
			data:{pid:pid},
			dataType:"json",
			type:"post",
			success:function(res){
				for (var i = 0; i < res.length; i++) {
					str="<option value='"+res[i].id+"'>"+res[i].name+"</option>";
					$("select[name='county']").append(str);
				}
			},
			error:function(res){
				alert("拉取地址失败，请重试！");
			}
		})
	}
</script>
<script>
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
				$("input[name='mainImagePath']").attr("value","http://xza-product.oss-cn-beijing.aliyuncs.com/"+res.name);
				$("#poster").attr("src","http://xza-product.oss-cn-beijing.aliyuncs.com/"+res.name)
				alert(res.json)
			},
			error:function(){
				alert("程序出现问题");
			}
		})
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
				<label>商品名称：</label>
			</div>
			<div class="field">
				<input type="hidden" name="mainImagePath"/>
				<input type="text" class="input w50" name="productName" data-validate="required:请输入商品名称" />
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>商品品牌：</label>
			</div>
			<div class="field">
				<select name="brandName" class="input w50">
					<option value="-1">请选择</option>
					<c:forEach items="${brandList}" var="brandList">
						<option value="${brandList.id}">${brandList.brandName}</option>
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
				<div class="tips"></div>
				<img alt="" id="poster" width="60px">
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>单价：</label>
			</div>
			<div class="field">
				<input type="text" class="input w50" name="price" data-validate="required:例 22.3" />
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
				</select>市
				<select name="county">
					<option>----请选择----</option>
				</select>县
				<input type="text" id="address"/>
				<div class="tips"></div>
			</div>
		</div>
		<div class="form-group">
        <div class="label">
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" onclick="saveProduct()" type="button"> 提交</button>
        </div>
      </div>
	</form>
	</div>
</div>
</body>
</html>