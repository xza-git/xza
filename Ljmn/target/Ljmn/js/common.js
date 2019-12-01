$(document).ready(function(){
	var htmlCode = '&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="goFirst()">首页</a>&nbsp;&nbsp;<a onclick="goPri()">上一页</a>&nbsp;&nbsp;'+
				   '第<span id="cpage">1</span>页&nbsp;&nbsp;共<span id="totalPage">0</span>页&nbsp;&nbsp;'+
				   '<a onclick="goNext()">下一页</a>&nbsp;&nbsp;<a onclick="goLast()">尾页</a>&nbsp;&nbsp;展示<select id="pageSize" onchange="changePageSize()"><option value="10">10</option><option value="12">12</option><option value="14">14</option></select>条&nbsp;&nbsp;<input type="text" id="targetPage" style="width: 60px">'+
				   '&nbsp;<input type="button" value="Go" onclick="goToPage()">'+
				   '<span id="pager_tips" style="color:red;"></span>';
	$("#pager_tools").html(htmlCode);
});


function goNext(){
	var cpage = Number($("#cpage").html());
	var totalPage = Number($("#totalPage").html());
	if(cpage + 1 > totalPage){
		$("#pager_tips").html("已经是最后一页");
	}else{
		$("#pager_tips").html("");
		$("#cpage").html(cpage+1);
		getData();
	}
}

function goPri(arrId){
	var cpage = Number($("#cpage").html());
	if(cpage - 1 < 1){
		$("#pager_tips").html("已经是第一页");
	}else{
		$("#pager_tips").html("");
		$("#cpage").html(cpage-1);
		getData();
	}
}

function goFirst(){
	$("#pager_tips").html("");
	$("#cpage").html(1);
	getData();
}

function goLast(){
	$("#pager_tips").html("");
	var totalPage = Number($("#totalPage").html());
	$("#cpage").html(totalPage);
	getData();
}

function changePageSize(){
	$("#pager_tips").html("");
	$("#cpage").html(1);
	getData();
}

function goToPage(){
	var targetPage = Number($("#targetPage").val());
	var totalPage = Number($("#totalPage").html());
	if( targetPage > totalPage){
		targetPage = totalPage;
	}
	$("#pager_tips").html("");
	$("#cpage").html(targetPage);
	$("#targetPage").val(targetPage);
	getData();
}

