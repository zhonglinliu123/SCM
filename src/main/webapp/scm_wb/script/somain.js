
function addItem() {
	var detailTable = document.getElementById("soitemTab");
	var oRow = detailTable.insertRow(-1);//在表格最后添加一行
	oRow.align = "center";
	oRow.className = "toolbar";
	oCell = oRow.insertCell(0);//添加单元格
	oCell.innerHTML = oRow.rowIndex;
	oCell = oRow.insertCell(1);
	oCell.innerHTML = "<input type='text' name='productCode' size='10' readonly/> <span class='LL'>" +
			"<image src='/SCM/images/selectDate.gif' onclick='chooseSoitem(this)'/></span>";
    
    // oCell.class= ;
	oCell = oRow.insertCell(2);
	oCell.innerHTML = "<input type='text' name='productName' size='15' readonly/>";
	oCell = oRow.insertCell(3);
	oCell.innerHTML = "<input type='number' name='productNumber' size='10' value='0' onkeyup='changeNum(this)'/>";
	oCell = oRow.insertCell(4);
	oCell.innerHTML = "<input type='text' name='productUnitName' size='10' readonly/>";
	oCell = oRow.insertCell(5);
	oCell.innerHTML = "<input type='number' name='productPrice' size='10' value='0' onkeyup='changeNum(this)'/>";
	oCell = oRow.insertCell(6);
	oCell.innerHTML = "<input type='text' name='itemPrice' size='10' value='0' readonly/>";

	oCell = oRow.insertCell(7);
	oCell.innerHTML = "<image src='/SCM/images/delete.gif' class='LL' onclick='delItem(this)'/>";
}

//删除明细
function delItem(obj){
	var tab = document.getElementById("soitemTab");
	var index = obj.parentNode.parentNode.rowIndex;
	//alert(index);
	tab.deleteRow(index);
	
	var arr = $("input[name='itemPrice']");
	var all=0;
	for(var i=0;i<arr.length;i++){
		all += parseInt(arr[i].value);
	}
	$("input[name='productTotal']").val(all);
	$("input[name='soTotal']").val(all+parseInt($("input[name='tipFee']").val()));
	
}

//获得时间
function time(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var s = date.getSeconds();
	var str = year+"-"+month+"-"+day+" "+hour+":"+min+":"+s;
	return str;
}

//订单号
function soid(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var s = date.getSeconds();
	var str = ""+year+month+day+hour+min+s;
	return str;
}

//页面加载后执行
$(document).ready(function(){
	$("#showSomain").show();
	$("#somainCtrl").hide();
	$("#soItem").hide();
	$(".showCustomerCode").hide();
	$(".showAccount").hide();
	$("#userAccount").hide();
	$("#Customer").hide();
	$("#customerCode").hide();
	$("#SELECT").hide();
	$("#selectCustomer").hide();
	
	var payType = $("#chooseType").find("option:selected").text();
	if("预付款到发货" == payType){
		$("input[name='prePayFee']").removeAttr("readonly");
	}else{
		$("input[name='prePayFee']").attr("readonly","readonly");
	}
	
	$.ajax({
		url:"/SCM/storeCan/AjaxCategoryServlet",//url
		type:"get",//发出请求
		async:true,
		dataType:"json",
		//data:formdata,
		success:function(data){//回调函数
			var typeName = $("#categoryName");
			for(var i=0;i<data.length;i++){
				typeName.append("<option value="+data[i].categoryID+">"+data[i].name+"</option>");
			}
			var type = $("#selectCategoryName");
			for(var i=0;i<data.length;i++){
				type.append("<option value="+data[i].categoryID+">"+data[i].name+"</option>");
			}
		},
	}
);
});

//选付款方式后最低预付款可能可以输入
function selectType(){
	var payType = $("#chooseType").find("option:selected").text();
	//alert(payType);
	if("预付款到发货" == payType){
		$("input[name='prePayFee']").removeAttr("readonly");
	}else{
		$("input[name='prePayFee']").attr("readonly","readonly");
	}
}


var userName="";

//新增销售单
function addSomain(){
	$("#soHint").text("");
	$("#SELECT").hide();
	$("#showSomain").hide();
	$("#somainCtrl").show();
	$("#soItem").hide();
	$("#button").show();
	//编号
	$("input[name='soid']").val(soid());
	$("input[name='soid']").attr("readonly","readonly");
	
	$("input[name='customerName']").removeAttr("disabled");
	$("#chooseType").removeAttr("disabled",true);
	$("input[name='soRemark']").removeAttr("readonly");
	$("input[name='tipFee']").removeAttr("readonly");
	$("input[name='productNumber']").removeAttr("readonly");
	$("input[name='productPrice']").removeAttr("readonly");
	
	//创建时间
	$("input[name='createTime']").val(time());
	$("input[name='createTime']").attr("readonly","readonly");
	//用户只读
	$("input[name='userAccount']").attr("readonly","readonly");
	//产品总价只读
	$("input[name='productTotal']").attr("readonly","readonly");
	//销售单总价只读
	$("input[name='soTotal']").attr("readonly","readonly");
	
	//销售单新增清空原有数据
	$("#customerCode").text("");
	$("input[name='customerName']").val("");
	$("input[name='productTotal']").val("0");
	$("input[name='soTotal']").val("0");
	$("input[name='prePayFee']").val("0");
	if(userName != ""){
		$("input[name='userAccount']").val(userName);
	}
	
	
	
	var tab = document.getElementById("soitemTab");
	while(tab.rows.length>1){
		tab.deleteRow(1);
	}
	
	
	
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
	
	
	
	
	//保存绑定事件
	$("#saveSomain").click(function(){
		//下一次点击保存先清除提示信息
		$("#soHint").text("");
		
		var soid = $("input[name='soid']").val();
		var createTime = $("input[name='createTime']").val();
		var customerCode = $("#customerCode").text();
		var account = $("#userAccount").text();
		var tipFee = $("input[name='tipFee']").val();
		var productTotal = $("input[name='productTotal']").val();
		var soTotal = parseInt($("input[name='soTotal']").val());
		var payType = $("#chooseType").find("option:selected").text();
		var soRemark = $("input[name='soRemark']").val();
		var prePayFee = parseInt($("input[name='prePayFee']").val());
		
		var tab = document.getElementById("soitemTab");
		var len = tab.rows.length;
		
		
		if("" == customerCode){
			$("#soHint").text("请选择客户，点击选择的用户才是有效用户！");
			return;
		}
		if(tipFee<0){
			$("#soHint").text("附加费用不能小于0！");
			return;
		}
		if(("预付款到发货" != payType) && (prePayFee != 0)){
			$("#soHint").text("不是预付款方式，不用预付款！");
			return;
		}
		if(prePayFee<0 || prePayFee>soTotal){
			$("#soHint").text("预付金额不能小于0，不能超过销售单总价！");
			return;
		}
		if(len>1){
			//拼接一个对象
			var soitem = "[";
			//如果每一行都没有数据不能提交
			var flag = false;
			for(var i=1;i<len;i++){
				if(tab.rows[i].cells[1].childNodes[0].value !=""){
					soitem +="{'productCode':'"+tab.rows[i].cells[1].childNodes[0].value+"',";
					soitem +="'productName':'"+tab.rows[i].cells[2].childNodes[0].value+"',"
					soitem +="'productNumber':"+tab.rows[i].cells[3].childNodes[0].value+","
					soitem +="'unitName':'"+tab.rows[i].cells[4].childNodes[0].value+"',"
					soitem +="'unitPrice':"+tab.rows[i].cells[5].childNodes[0].value+","
					soitem +="'itemPrice':"+tab.rows[i].cells[6].childNodes[0].value+"},"
					flag=true;
				}
			}
			soitem = soitem.substring(0,soitem.length-1);
			soitem +="]";
			//alert(productTotal);
			if(flag){
				location.href="/SCM/storeCan/SomainAddServlet_c?soid="+soid+"&createTime="+createTime
				+"&customerCode="+customerCode+"&account="+account+"&tipFee="+tipFee+"&productTotal="+productTotal
				+"&soTotal="+soTotal+"&payType="+payType+"&remark="+soRemark+"&prePayFee="+prePayFee
				+"&json="+soitem;
			}else{
				$("#soHint").text("请先添加明细");
				return;
			}
		}else{
			$("#soHint").text("请先添加明细");
			return;
		}
		
	});
	
}



//获得查询结果，并分页显示
function ajaxSelectProduct(code,name,type,page){
	$.get(
			"/SCM/storeCan/AjaxProductSelectServlet_c",
			{"code":code,"name":name,"categoryName":type,"page":page},
			function(data){
				var tab = document.getElementById("productTab");
				while(tab.rows.length>1){
					tab.deleteRow(1);
				}
				for(var i=0;i<data.length;i++){
					var row = tab.insertRow(-1);
					var cell = row.insertCell(-1);
					cell.innerHTML = "<input name='chooseProduct' type='radio'/>"
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].productCode;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].productName;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].unitName;
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='number' value='1'/>";
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].price;
				}
			},
			"json"
	);
}



//显示可选用户
function showChooseCustomer(){
	$("#Customer").show();
	
	$("input[name='customerName']").keyup(function(){
		var tab = document.getElementById("chooseCustomerTab");
		
		var se = $("input[name='customerName']").val();
		
		$.get(
				"/SCM/storeCan/AjaxCustomerServlet_c",
				{"se":se},
				function(data){
					var len = tab.rows.length-1;
					for(var k=len;k>=0;k--){
						tab.deleteRow(k);
					}
					for(var i=0;i<data.length;i++){
						var row = tab.insertRow(-1);
						var cell = row.insertCell(-1);
						cell.innerHTML = "<a style='cursor:pointer;' onclick='chooseCustomer(this)'>"+
							"<span>"+data[i].customerCode+"</span>"+
							"<span>"+data[i].customerName+"</span></a>";
					}
				},
				"json"
		);
		
	});
}


//选择用户
function chooseCustomer(obj){
	var a = $(obj);
	var customerCode = a.find("span").eq(0).text();
	var customerName = a.find("span").eq(1).text();
	
	$("#customerCode").text(customerCode);
	
	$("input[name='customerName']").val(customerCode+" "+customerName);
	$("#Customer").hide();
}

//第几行的添加
var hang=1;
//选择商品
function chooseSoitem(obj){
	$("#showSomain").hide();
	$("#somainCtrl").hide();
	$("#soItem").show();
	//清除提示信息
	$("#productHint").text("");
	
	var sotr = $(obj).parent().parent().parent();
	sotr = sotr.get(0);
	hang = sotr.rowIndex;

//	alert(index);
	
//	//确定按钮绑定事件
//	$("#queding").click(function(){
//		//产品的table
//		var ptr = $("input:radio:checked").parent().parent();
//		//明细的table
//		var tab = document.getElementById("soitemTab");
//		
//		var code = ptr.find("td").eq(1).text();
//		var name = ptr.find("td").eq(2).text();
//		var unitName = ptr.find("td").eq(3).text();
//		var num = parseInt(ptr.find("input").eq(1).val());
//		var price = parseInt(ptr.find("td").eq(5).text());
//		alert("shijian:"+index);
//		if(num>0){
//			sotr.find("input[name='productCode']").val(code);
//			sotr.find("input[name='productName']").val(name);
//			sotr.find("input[name='productNumber']").val(num);
//			sotr.find("input[name='productUnitName']").val(unitName);
//			sotr.find("input[name='productPrice']").val(price);
//			sotr.find("input[name='itemPrice']").val(num*price);
//			
//			tab.rows[index].cells[1].childNodes[0].value = code;
//			tab.rows[index].cells[2].childNodes[0].value = name;
//			tab.rows[index].cells[3].childNodes[0].value = num;
//			tab.rows[index].cells[4].childNodes[0].value = unitName;
//			tab.rows[index].cells[5].childNodes[0].value = price;
//			tab.rows[index].cells[6].childNodes[0].value = num*price;
//			
//			//新增明细后计算产品总价并修改
//			var arr = $("input[name='itemPrice']");
//			var all=0;
//			for(var i=0;i<arr.length;i++){
//				all += parseInt(arr[i].value);
//			}
//			$("input[name='productTotal']").val(all);
//			$("input[name='soTotal']").val(all+parseInt($("input[name='tipFee']").val()));
//			
//			$("#showSomain").hide();
//			$("#somainCtrl").show();
//			$("#soItem").hide();
//			return;
//		}else{
//			$("#productHint").text("产品数量必须大于0！");
//		}
//		
//		
//	});
}

//取消
function cancel(){
	$("#showSomain").hide();
	$("#somainCtrl").show();
	$("#soItem").hide();
}


//确定按钮
function queding(){
	//alert(hang);
	//产品的table
	var ptr = $("input:radio:checked").parent().parent();
	//alert(ptr);
	//明细的table
	var tab = document.getElementById("soitemTab");
	
	var code = ptr.find("td").eq(1).text();
	var name = ptr.find("td").eq(2).text();
	var unitName = ptr.find("td").eq(3).text();
	var num = parseInt(ptr.find("input").eq(1).val());
	var price = parseInt(ptr.find("td").eq(5).text());
	
	var i=1;
	for(;i<tab.rows.length;i++){
		if(code == tab.rows[i].cells[1].childNodes[0].value){
			break;
		}
	}
	//alert(tab.rows.length);
	//如果已经添加了则添加数量
	if(i<tab.rows.length){
		if(num>0){
			var n = parseInt(tab.rows[i].cells[3].childNodes[0].value);
			tab.rows[i].cells[3].childNodes[0].value = n+num;
			var price = tab.rows[i].cells[5].childNodes[0].value;
			var num = tab.rows[i].cells[3].childNodes[0].value;
			tab.rows[i].cells[6].childNodes[0].value = num*price;
			//新增明细后计算产品总价并修改
			var arr = $("input[name='itemPrice']");
			var all=0;
			for(var k=0;k<arr.length;k++){
				all += parseInt(arr[k].value);
			}
			$("input[name='productTotal']").val(all);
			$("input[name='soTotal']").val(all+parseInt($("input[name='tipFee']").val()));
			
			$("#showSomain").hide();
			$("#somainCtrl").show();
			$("#soItem").hide();
			return;
		}else{
			$("#productHint").text("需要选中商品，且产品数量必须大于0！");
			return;
		}
		
	}else{//没有添加过则添加
		if(num>0){
			tab.rows[hang].cells[1].childNodes[0].value = code;
			tab.rows[hang].cells[2].childNodes[0].value = name;
			tab.rows[hang].cells[3].childNodes[0].value = num;
			tab.rows[hang].cells[4].childNodes[0].value = unitName;
			tab.rows[hang].cells[5].childNodes[0].value = price;
			tab.rows[hang].cells[6].childNodes[0].value = num*price;
			
			//新增明细后计算产品总价并修改
			var arr = $("input[name='itemPrice']");
			var all=0;
			for(var k=0;k<arr.length;k++){
				all += parseInt(arr[k].value);
			}
			$("input[name='productTotal']").val(all);
			$("input[name='soTotal']").val(all+parseInt($("input[name='tipFee']").val()));
			
			$("#showSomain").hide();
			$("#somainCtrl").show();
			$("#soItem").hide();
		}else{
			$("#productHint").text("产品数量必须大于0！");
		}
	}
}

//附加费用改变销售单总价也改变
function tipFee(){
	$("#soHint").text("");
	var tip = $("input[name='tipFee']").val()
	var fee = parseInt(tip);
	if(fee<0||tip==""){
		$("#soHint").text("附加费用不能小于0");
		$("input[name='tipFee']").val(0);
	}
	var Total = parseInt($("input[name='productTotal']").val());
	$("input[name='soTotal']").val(Total+fee);
}

//数量改变销售单总价也改变
function changeNum(obj){
	var tr = $(obj).parent().parent();
	tr = tr.get(0);
	hang = tr.rowIndex;
	//alert(hang);
	
	var tab = document.getElementById("soitemTab");
	
	$("#soHint").text("");
	var number = $("input[name='productNumber']").val();
	var pri = $("input[name='productPrice']").val();
	var num = parseInt(number);
	var price = parseInt(pri);
	if(num<0||number==""){
		$("#soHint").text("数量不能小于1");
		$("input[name='productNumber']").val(1);
	}
	if(price<0||pri==""){
		$("#soHint").text("单价不能小于1");
		$("input[name='productPrice']").val(1);
	}
	
	var code = tab.rows[hang].cells[1].childNodes[0].value;
	if(""!=code){
		var price = tab.rows[hang].cells[5].childNodes[0].value;
		var num = tab.rows[hang].cells[3].childNodes[0].value;
		tab.rows[hang].cells[6].childNodes[0].value = num*price;
		
		var arr = $("input[name='itemPrice']");
		var all=0;
		for(var i=0;i<arr.length;i++){
			all += parseInt(arr[i].value);
		}
		$("input[name='productTotal']").val(all);
		$("input[name='soTotal']").val(all+parseInt($("input[name='tipFee']").val()));
	}
}

//点击销售单编号或修改显示销售单详细信息
function showSomain(obj){
	$("#SELECT").hide();
	var soid = obj.innerHTML;
	var reg = /\d+/;
	//判断是查看还是修改
	var flag = reg.test(soid);
	
	//判断销售单创建用户是不是当前用户，如果不是不能修改
	var tr = $(obj).parent().parent();
	var account = $("#userAccount").text();
	var userAccount = tr.find("span").eq(1).text();
	var userFlag = (account != userAccount);
	
	
	
	if(flag){
		//设置是否可以更改
		$("input[name='soid']").attr("readonly","readonly");
		$("input[name='createTime']").attr("readonly","readonly");
		$("input[name='customerName']").attr("disabled",true);
		$("input[name='userAccount']").attr("readonly","readonly");
		$("input[name='tipFee']").attr("readonly","readonly");
		$("input[name='productTotal']").attr("readonly","readonly");
		$("input[name='soTotal']").attr("readonly","readonly");
		$("#chooseType").attr("disabled",true);
		$("input[name='soRemark']").attr("readonly","readonly");
		$("input[name='prePayFee']").attr("readonly","readonly");
		
		
		$("#button").hide();
		
	}else{
		$("#button").show();
		
		$("input[name='soid']").attr("readonly","readonly");
		$("input[name='createTime']").attr("readonly","readonly");
		$("input[name='userAccount']").attr("readonly","readonly");
		$("input[name='productTotal']").attr("readonly","readonly");
		$("input[name='soTotal']").attr("readonly","readonly");
		$("input[name='soRemark']").removeAttr("readonly");
		
//		$("input[name='productCode']").attr("readonly","readonly");
//		$("input[name='productName']").attr("readonly","readonly");
//		$("input[name='productNumber']").removeAttr("readonly");
//		$("input[name='productUnitName']").attr("readonly","readonly");
//		$("input[name='productPrice']").removeAttr("readonly");
//		$("input[name='itemPrice']").attr("readonly","readonly");
		
//		var tr = $(obj).parent().parent();
		soid = tr.find("td").eq(0).find("a").eq(0).text();
	}
	
	$.get(
			"/SCM/storeCan/AjaxSomainServlet_c",
			{"soid":soid},
			function(data){
				$("input[name='soid']").val(data.soid);
				$("input[name='createTime']").val(data.createTime);
				$("#customerCode").text(data.customerCode);
				$("input[name='customerName']").val(data.customerCode+" "+data.customerName);
				
				
				if(account == data.account){
					$("#userAccount").text(data.account);
				}
				userName = $("input[name='userAccount']").val();
				$("input[name='userAccount']").val(data.userName);
				$("input[name='tipFee']").val(data.tipFee);
				$("input[name='productTotal']").val(data.productTotal);
				$("input[name='soTotal']").val(data.soTotal);
				//
				$("#chooseType option[value='"+data.payType+"']").attr("selected",true);
				$("input[name='soRemark']").val(data.remark);
				$("input[name='prePayFee']").val(data.prePayFee);
				
			},
			"json"
	);
	
	$.post(
			"/SCM/storeCan/AjaxSomainServlet_c",
			{"soid":soid},
			function(data){
				var tab = document.getElementById("soitemTab");
				for(var i=1;i<tab.rows.length;i++){
					tab.deleteRow(1);
				}
				
				for(var i=0;i<data.length;i++){
					var row = tab.insertRow(-1);
					var cell = row.insertCell(-1);
					cell.innerHTML = i+1;
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='text' name='productCode' value='"+data[i].productCode
					+"' size='10' readonly/> <span class='LL'>" +
					"<image src='/SCM/images/selectDate.gif' onclick='chooseSoitem(this)' class='ADD'/></span>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='text' name='productName' value='"+data[i].productName+"' size='15' readonly/>"
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='number' name='productNumber' size='10' value='"+data[i].productNumber
					+"' onkeyup='changeNum(this)'/>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='text' name='productUnitName' value='"+data[i].unitName+"' size='10' readonly/>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='number' name='productPrice' value='"+data[i].unitPrice
					+"' size='10' value='0' onkeyup='changeNum(this)'/>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='text' name='itemPrice' size='10' value='"+data[i].itemPrice
					+"' readonly/>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<image src='/SCM/images/delete.gif' onclick='delItem(this)' class='DEL'/>";
				}
				if(flag){
					$("input[name='productCode']").attr("readonly","readonly");
					$("input[name='productName']").attr("readonly","readonly");
					$("input[name='productNumber']").attr("readonly","readonly");
					$("input[name='productUnitName']").attr("readonly","readonly");
					$("input[name='productPrice']").attr("readonly","readonly");
					$("input[name='itemPrice']").attr("readonly","readonly");
					$(".ADD").hide();
					$(".DEL").hide();
				}else{
					$("input[name='productCode']").attr("readonly","readonly");
					$("input[name='productName']").attr("readonly","readonly");
					$("input[name='productNumber']").removeAttr("readonly");
					$("input[name='productUnitName']").attr("readonly","readonly");
					$("input[name='productPrice']").removeAttr("readonly");
					$("input[name='itemPrice']").attr("readonly","readonly");
					$(".ADD").show();
					$(".DEL").show();
				}
				if(userFlag){
					$(".ADD").hide();
					$(".DEL").hide();
					$("#button").hide();
					$("input[name='productNumber']").attr("readonly","readonly");
					$("input[name='productPrice']").attr("readonly","readonly");
				}
			},
			"json"
	);
	
	$("#showSomain").hide();
	$("#somainCtrl").show();
	$("#soItem").hide();
	
	var userAccount = tr.find("span").eq(1).text();
	if(account == userAccount){
		$("#saveSomain").click(function(){
			//下一次点击保存先清除提示信息
			$("#soHint").text("");
			
			var soid = $("input[name='soid']").val();
			var createTime = $("input[name='createTime']").val();
			var customerCode = $("#customerCode").text();
			var account = $("#userAccount").text();
			var tipFee = $("input[name='tipFee']").val();
			var productTotal = $("input[name='productTotal']").val();
			var soTotal = parseInt($("input[name='soTotal']").val());
			var payType = $("#chooseType").find("option:selected").text();
			var soRemark = $("input[name='soRemark']").val();
			var prePayFee = parseInt($("input[name='prePayFee']").val());
			
			var tab = document.getElementById("soitemTab");
			var len = tab.rows.length;
			
			
			if("" == customerCode){
				$("#soHint").text("请选择客户，点击选择的用户才是有效用户！");
				return;
			}
			if(tipFee<0){
				$("#soHint").text("附加费用不能小于0！");
				return;
			}
			if(("预付款到发货" != payType) && (prePayFee != 0)){
				$("#soHint").text("不是预付款方式，不用预付款！");
				return;
			}
			if(prePayFee<0 || prePayFee>soTotal){
				$("#soHint").text("预付金额不能小于0，不能超过销售单总价！");
				return;
			}
			if(len>1){
				//拼接一个对象
				var soitem = "[";
				//如果每一行都没有数据不能提交
				var flag = false;
				for(var i=1;i<len;i++){
					if(tab.rows[i].cells[1].childNodes[0].value !=""){
						soitem +="{'productCode':'"+tab.rows[i].cells[1].childNodes[0].value+"',";
						soitem +="'productName':'"+tab.rows[i].cells[2].childNodes[0].value+"',"
						soitem +="'productNumber':"+tab.rows[i].cells[3].childNodes[0].value+","
						soitem +="'unitName':'"+tab.rows[i].cells[4].childNodes[0].value+"',"
						soitem +="'unitPrice':"+tab.rows[i].cells[5].childNodes[0].value+","
						soitem +="'itemPrice':"+tab.rows[i].cells[6].childNodes[0].value+"},"
						flag=true;
					}
				}
				soitem = soitem.substring(0,soitem.length-1);
				soitem +="]";
				//alert(productTotal);
				if(flag){
					location.href="/SCM/storeCan/SomainAddServlet_c?soid="+soid+"&createTime="+createTime
					+"&customerCode="+customerCode+"&account="+account+"&tipFee="+tipFee+"&productTotal="+productTotal
					+"&soTotal="+soTotal+"&payType="+payType+"&remark="+soRemark+"&prePayFee="+prePayFee
					+"&json="+soitem;
				}else{
					$("#soHint").text("请先添加明细");
					return;
				}
			}else{
				$("#soHint").text("请先添加明细");
				return;
			}
		});
	}else{
		$(".ADD").hide();
		$(".DEL").hide();
		$("#soHint").text("这个销售单不是你创建的，你只能查看！");
	}
	
}

//删除
function deleteSomain(obj){
	var tr = $(obj).parent().parent();
	var userAccount = tr.find("span").eq(1).text();
	//alert(userAccount);
	var soid = tr.find("td").eq(0).find("a").eq(0).text();
	var account = $("#userAccount").text();
	if(account == userAccount){
		if(confirm("确定删除？") == true){
			location.href = "/SCM/storeCan/SomainDeleteServlet_c?soid="+soid;
		}else{
			return;
		}
	}else{
		alert("这个销售单不是你创建的，你不能删除！");
	}
	
}


//显示可选用户
function selectChooseCustomer(){
	$("#selectCustomer").show();
	
	$("input[name='cuName']").keyup(function(){
		var tab = document.getElementById("selectCustomerTab");
		
		var se = $("input[name='cuName']").val();
		
		$.get(
				"/SCM/storeCan/AjaxCustomerServlet_c",
				{"se":se},
				function(data){
					var len = tab.rows.length-1;
					for(var k=len;k>=0;k--){
						tab.deleteRow(k);
					}
					
					for(var i=0;i<data.length;i++){
						var row = tab.insertRow(-1);
						var cell = row.insertCell(-1);
						cell.innerHTML = "<a style='cursor:pointer;' onclick='selectCustomer(this)'>"+
							"<span>"+data[i].customerCode+"</span>"+
							"<span>"+data[i].customerName+"</span></a>";
					}
				},
				"json"
		);
		
	});
}

function selectCustomer(obj){
	var a = $(obj);
	var customerCode = a.find("span").eq(0).text();
	var customerName = a.find("span").eq(1).text();
	
	$("input[name='cuName']").val(customerCode+" "+customerName);
	
	$("#selectCustomer").hide();
	search(customerCode);
}

//查询
function search(customerCode){
	var customerCode = (undefined==customerCode?"":customerCode);
	var code = $("input[name='cuName']").val();
	//alert(code);
	if(""!=code && undefined!=code){
		var arr = code.split(" ");
		customerCode = arr[0];
	}
	
	var soid = $("input[name='selectSoid']").val();
	var dateStart = $("input[name='dateStart']").val();
	var dateEnd = $("input[name='dateEnd']").val();
	var payType = $("#selectPayType").find("option:selected").text();
	//var status = $("#status").val();
	
	$.get(
			"/SCM/storeCan/AjaxSomainSelectServlet_c",
			{
				"soid":soid,
				"dateStart":dateStart,
				"dateEnd":dateEnd,
				"customerCode":customerCode,
				"payType":payType,
				"status":status
			},
			function(data){
				var tab = document.getElementById("showSomainTab");
				while(tab.rows.length>1){
					tab.deleteRow(1);
				}
				
				for(var i=0;i<data.length;i++){
					if(data[i].status==1){//新增页面只显示新增状态的销售单
						var row = tab.insertRow(-1);
						var cell = row.insertCell(-1);
						cell.innerHTML = "<a onclick='showSomain(this)' style='cursor:pointer;'>"+data[i].soid+"</a>";
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].createTime;
						cell = row.insertCell(-1);
						cell.innerHTML = "<span class='showCustomerCode'>"+data[i].customerCode
						+"</span>"+data[i].customerName;
							
						cell = row.insertCell(-1);
						cell.innerHTML = "<span class='showAccount'>"+data[i].account+"</span>"+data[i].userName;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].tipFee;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].productTotal;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].soTotal;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].payType;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].prePayFee;
						cell = row.insertCell(-1);
						cell.innerHTML = "<input type='button' value='修改' onclick='showSomain(this)'/>"+
							"<input type='button' value='删除' onclick='deleteSomain(this)'/>";
					
					}
				}
				
			},
			"json"
	);
	
}

function selectSomain(){
	$("#SELECT").show();
	$("#showSomain").show();
	$("#somainCtrl").hide();
	$("#soItem").hide();
	$(".showCustomerCode").hide();
	$(".showAccount").hide();
	$("#userAccount").hide();
	$("#Customer").hide();
	$("#customerCode").hide();
}






