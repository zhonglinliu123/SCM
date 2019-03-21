
//获得时间
function time(){
	var date = new Date();
	var hour = date.getHours();
	var min = date.getMinutes();
	var s = date.getSeconds();
	var str = nowDate()+" "+hour+":"+min+":"+s;
	return str;
}

function nowDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var date = year+"-"+month+"-"+day;
	return date;
}


//goodsClass的js,处理分类category

$(document).ready(function(){
	$("#cateCtrl").hide();
});


//表单保存按钮
function saveCategory(){
	var hint = $("#cateHint").text();
	$("#cateHint").text("");
	var ID = $("input[name='categoryID']").val();
	var name = $("input[name='categoryName']").val();
	var remark = $("input[name='categoryRemark']").val();
	//分类名不能为空，客户端数据校验
	if(name!=""){
		if(hint == ""){
			location.href = "/SCM/storeCan/CategoryServlet_c?categoryID="+ID+"&categoryName="+name+"&categoryRemark="+remark;
		}else{
			$("#cateHint").text("该分类已经存在！");
		}
	} else {
		$("#cateHint").text("分类名不能为空！");
	}
	
}

//判断分类名是否重复
function haveName(){
	$("#cateHint").text("");
	var name = $("input[name='categoryName']").val();
	$.post(
			"/SCM/storeCan/AjaxCategoryServlet",
			{'name':name},
			function(data){
				if(name == data.name){
					$("#cateHint").text("该分类已经存在！");
				}
			},
			"json"
	);
}

//删除
function deleteCategory(obj){
	var tr = $(obj).parent().parent();
	var id = tr.find("td").eq(0).text();
	if(confirm("确定删除？") == true){
		location.href = "/SCM/storeCan/CategoryDeleteServlet_c?categoryID="+id;
	}
	
}

//更新按钮
function updateCategory(obj){
	$("#categoryError").hide();
	$("#showcate").hide();
	$("#cateCtrl").show();
	var tr = $(obj).parent().parent();
	var ID = tr.find("td").eq(0).text();
	var name = tr.find("td").eq(1).text();
	var remark = tr.find("td").eq(2).text();
	
	$("input[name='categoryID']").val(ID);
	$("input[name='categoryID']").attr("readonly","readonly");
	$("input[name='categoryName']").val(name);
	$("input[name='categoryRemark']").val(remark);
}


//新增按钮
function addCategory(){
	$("input[name='categoryName']").val("");
	$("input[name='categoryRemark']").val("");
	$("input[name='categoryID']").val("");
	$("#categoryError").hide();
	$("#showcate").hide();
	$("#cateCtrl").show();
	$("#cateID").hide();
}



//goods的js

//页面加载后执行
$(document).ready(function(){
	$("#productCtrl").hide();
	$("#productSelect").hide();
	$("#back").hide();
	
	showPages();
	
	$.get(
			"/SCM/storeCan/AjaxCategoryServlet",//url
			{},
			function(data){//回调函数
				var typeName = $("#categoryName");
				for(var i=0;i<data.length;i++){
					typeName.append("<option value="+data[i].categoryID+">"+data[i].name+"</option>");
				}
				var type = $("#selectCategoryName");
				for(var i=0;i<data.length;i++){
					type.append("<option value="+data[i].categoryID+">"+data[i].name+"</option>");
				}
			},
			"json"
	);
});

//分页是否显示上一页、下一页
function showPages(){
	var all = $("#allPages").text();
	var nowPage = $("#nowPage").text();
	if(nowPage == 1){
		$("#prePage").hide();
		$("#firstPage").hide();
	}else{
		$("#prePage").show();
		$("#firstPage").show();
	}
	if(nowPage == all){
		$("#nextPage").hide();
		$("#lastPage").hide();
	}else{
		$("#nextPage").show();
		$("#lastPage").show();
	}
	if(all==1){
		$("#showSkip").hide();
	}
}

//新增按钮绑定事件
function addProduct(){
	$("#back").hide();
	$("input[name='productCode']").removeAttr("readonly");
	$("input[name='productCode']").val("");
	$("input[name='productName']").val("");
	$("input[name='price']").val("");
	$("input[name='unitName']").val("");
	$("input[name='createDate']").val("");
	$("input[name='produatRemark']").val("");
	$("#categoryName").val();
	
	$("#deleteGoodsHint").hide();
	$("#paging").hide();
	$("#productSelect").hide();
	$("#showProduct").hide();
	$("#productCtrl").show();
	
	$("input[name='createDate']").val(time());
	$("input[name='createDate']").attr("readonly","readonly");
	$("#saveProduct").click(function(){
		var productCode = $("input[name='productCode']").val();
		var productName = $("input[name='productName']").val();
		var price = $("input[name='price']").val();
		var unitName = $("input[name='unitName']").val();
		var createDate = $("input[name='createDate']").val();
		var produatRemark = $("input[name='produatRemark']").val();
		var categoryID = $("#categoryName").val();
		
		//alert(categoryName);
		
		var codeReg = "[0-9a-zA-Z]{4,20}";
		if(!(productCode.match(codeReg))){
			$("#productCodeHint").text("产品编号是数字或字母，长度为 4~20字符");
			return;
		}
		if(""==productName || productName.length>100){
			$("#productNameHint").text("产品名称不为空，不超过100字符");
			return;
		}
		if(price<0){
			$("#productPriceHint").text("产品价格不能小于0！");
			return;
		}
		if(""==unitName){
			$("#productUnitNameHint").text("数量单位不能为空！");
			return;
		}
		
		location.href = "/SCM/storeCan/ProductAddServlet_c?productCode="+productCode+"&productName="+productName+
		"&price="+price+"&unitName="+unitName+"&createDate="+createDate+"&remark="
		+produatRemark+"&categoryID="+categoryID;
	});
}

//用ajax验证productCode是否重复
function productCodeChange(){
	var productCode = $("input[name='productCode']").val();
	//get请求
	$.get(
			"/SCM/storeCan/AjaxProductServlet",//url
			{"productCode":productCode},//
			function(data){
				//alert(data);
				if(data.productCode == productCode){
					$("#productCodeHint").text("这个商品已存在！");
					$("#saveProduct").attr("disabled",true);
				}else{
					$("#productCodeHint").text("");
					$("#saveProduct").removeAttr("disabled");
				}
			},
			"json"
	);
}


//修改按钮绑定事件
function updateProduct(obj){
	$("#deleteGoodsHint").hide();
	$("#paging").hide();
	$("#productSelect").hide();
	$("#showProduct").hide();
	$("#productCtrl").show();
	$("#back").hide();
	
	var tr = $(obj).parent().parent();
	var productCode = $("input[name='productCode']").val(tr.find("td").eq(0).text());
	$("input[name='productCode']").attr("readonly","readonly");
	var productName = $("input[name='productName']").val(tr.find("td").eq(1).text());
	var price = $("input[name='price']").val(tr.find("td").eq(5).text());
	var unitName = $("input[name='unitName']").val(tr.find("td").eq(2).text());
	var createDate = $("input[name='createDate']").val(time());
	$("input[name='createDate']").attr("readonly","readonly");
	var produatRemark = $("input[name='produatRemark']").val(tr.find("td").eq(6).text());
	var categoryID = $("#categoryName option[text='"+tr.find("td").eq(3).text()+"']").attr("selected",true);
	
	$("#saveProduct").click(function(){
		var productCode = $("input[name='productCode']").val();
		var productName = $("input[name='productName']").val();
		var price = $("input[name='price']").val();
		var unitName = $("input[name='unitName']").val();
		var createDate = $("input[name='createDate']").val();
		var produatRemark = $("input[name='produatRemark']").val();
		var categoryID = $("#categoryName").val();
		
		if(""==productName || productName.length>100){
			$("#productNameHint").text("产品名称不为空，不超过100字符");
			return;
		}
		if(price<0){
			$("#productPriceHint").text("产品价格不能小于0！");
			return;
		}
		if(""==unitName){
			$("#productUnitNameHint").text("数量单位不能为空！");
			return;
		}
		
		location.href = "/SCM/storeCan/ProductAddServlet_c?productCode="+productCode+"&productName="+productName+
		"&price="+price+"&unitName="+unitName+"&createDate="+createDate+"&remark="
		+produatRemark+"&categoryID="+categoryID+"&update="+true;
	});
}

//删除product
function deleteProduct(obj){
	var tr = $(obj).parent().parent();
	var productCode = tr.find("td").eq(0).text();
	if(confirm("确定删除？") == true){
		location.href = "/SCM/storeCan/ProductDeleteServlet_c?productCode="+productCode;
	}
	
}


//product查询
function selectProduct(){
	$("#deleteGoodsHint").hide();
	$("#paging").hide();
	$("#productSelect").show();
	$("#showProduct").show();
	$("#productCtrl").hide();
	$("#back").show();
	
	//产品编号输入框绑定事件
	$("input[name='selectProductCode']").keyup(function(){
		var code = $("input[name='selectProductCode']").val();
		var name = $("input[name='selectProductName']").val();
		var type = $("#selectCategoryName").find("option:selected").text();
		//alert(type);
		ajaxSelectProduct(code,name,type,0);
	});
	//产品名称
	$("input[name='selectProductName']").keyup(function(){
		var code = $("input[name='selectProductCode']").val();
		var name = $("input[name='selectProductName']").val();
		var type = $("#selectCategoryName").find("option:selected").text();
		ajaxSelectProduct(code,name,type,0);
	});
	
	$("#selectCategoryName").change(function(){
		var code = $("input[name='selectProductCode']").val();
		var name = $("input[name='selectProductName']").val();
		var type = $("#selectCategoryName").find("option:selected").text();
		ajaxSelectProduct(code,name,type,0);
	});
	
}

//获得查询结果，并分页显示
function ajaxSelectProduct(code,name,type,page){
	$.get(
			"/SCM/storeCan/AjaxProductSelectServlet_c",
			{"code":code,"name":name,"categoryName":type,"page":page},
			function(data){
				var tab = document.getElementById("showProduct");
				while(tab.rows.length>1){
					tab.deleteRow(1);
				}
				for(var i=0;i<data.length;i++){
					var row = tab.insertRow(-1);
					var cell = row.insertCell(-1);
					cell.innerHTML = data[i].productCode;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].productName;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].unitName;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].categoryName;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].createDate;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].price;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].remark;
					cell = row.insertCell(-1);
					cell.innerHTML = "<a style='cursor:pointer;' onclick='updateProduct(this)'>修改</a>&nbsp;&nbsp;"+
									 "<a style='cursor:pointer;' onclick='deleteProduct(this)'>删除</a>"
				}
			},
			"json"
	);
}

//上一页
function previousPage(){
	var now = parseInt($("#nowPage").text());
	if(now>1){
		now = now-1;
		ajaxSelectProduct("","","",now);
		$("#nowPage").text(now);
	}
	
	//按钮是否显示
	showPages();
}


//下一页
function nextPage(){
	var now = parseInt($("#nowPage").text());
	var all = $("#allPages").text();
	if(now<all){
		now = now+1;
		ajaxSelectProduct("","","",now);
		$("#nowPage").text(now);
	}
	//按钮是否显示
	showPages();
}

//跳转按钮
function skip(){
	var num = parseInt($("input[name='skipPage']").val());
	var all = parseInt($("#allPages").text());
	if(num>0 && num<=all){
		ajaxSelectProduct("","","",num);
		$("#nowPage").text(num);
	}else{
		alert("输入的页数要在1到"+all+"之间！");
	}
	showPages();
}

//首页
function firstPage(){
	ajaxSelectProduct("","","",1);
	$("#nowPage").text(1);
	showPages();
}

//尾页
function lastPage(){
	var all = parseInt($("#allPages").text());
	ajaxSelectProduct("","","",all);
	$("#nowPage").text(all);
	showPages();
}





