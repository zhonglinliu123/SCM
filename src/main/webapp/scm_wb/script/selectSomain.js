
//处理状态
var zt={
		"1":"新增",
		"2":"已发货",
		"3":"已付款",
		"4":"已了结",
		"5":"预付款"
}

$(document).ready(function(){
	$("#Customer").hide();
	$("#somainCtrl").hide();
	var tab = document.getElementById("chooseCustomerTab");
	$.get(
			"/SCM/storeCan/AjaxCustomerServlet_c",
			{"se":""},
			function(data){
				//alert(data.length)
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

//返回按钮
function back(){
	$("#Customer").hide();
	$("#somainCtrl").hide();
	$("#showSomainDiv").show();
}

//显示可选用户
function showChooseCustomer(){
	$("#Customer").show();
	
	$("input[name='cuName']").keyup(function(){
		var tab = document.getElementById("chooseCustomerTab");
		
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
	
	$("input[name='cuName']").val(customerCode+" "+customerName);
	
	$("#Customer").hide();
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
	var payType = $("#payType").find("option:selected").text();
	var status = $("#status").val();
	
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
				var tab = document.getElementById("showSomain");
				while(tab.rows.length>1){
					tab.deleteRow(1);
				}
				
				for(var i=0;i<data.length;i++){
					var row = tab.insertRow(-1);
					var cell = row.insertCell(-1);
					cell.innerHTML = "<a onclick='showSomain(this)' style='cursor:pointer;'>"+data[i].soid+"</a>";
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].createTime;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].customerName;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].userName;
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
					cell.innerHTML = zt[data[i].status];
					
				}
				
			},
			"json"
	);
	
}

//点击编号显示详细内容
function showSomain(obj){
	var soid = obj.innerHTML;
	$("#somainCtrl").show();
	$("#showSomainDiv").hide();
	
	$.get(
			"/SCM/storeCan/AjaxSomainServlet_c",
			{"soid":soid},
			function(data){
				$("input[name='soid']").val(data.soid);
				$("input[name='createTime']").val(data.createTime);
				$("#customerCode").text(data.customerCode);
				$("input[name='customerName']").val(data.customerCode+" "+data.customerName);
				$("input[name='userAccount']").val(data.userName);
				$("input[name='tipFee']").val(data.tipFee);
				$("input[name='productTotal']").val(data.productTotal);
				$("input[name='soTotal']").val(data.soTotal);
				$("#chooseType option[value='"+data.payType+"']").attr("selected",true);
				$("input[name='soRemark']").val(data.remark);
				$("input[name='prePayFee']").val(data.prePayFee);
				//
				$("input[name='stockTime']").val(data.stockTime);
				$("input[name='prePayTime']").val(data.prePayTime);
				$("input[name='payTime']").val(data.payTime);
				
				$("input[name='stockUser']").val(data.stockUser);
				$("input[name='prePayUser']").val(data.prePayUser);
				$("input[name='payUser']").val(data.payUser);
				
			},
			"json"
	);
	
	$.post(
			"/SCM/storeCan/AjaxSomainServlet_c",
			{"soid":soid},
			function(data){
				var tab = document.getElementById("soitemTab");
				while(tab.rows.length>1){
					tab.deleteRow(1);
				}
				
				for(var i=0;i<data.length;i++){
					var row = tab.insertRow(-1);
					var cell = row.insertCell(-1);
					cell.innerHTML = "<input type='text' name='productCode' value='"+data[i].productCode
					+"' size='10' readonly/>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='text' name='productName' value='"+data[i].productName+"' size='15' readonly/>"
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='number' name='productNumber' size='10' value='"+data[i].productNumber
					+"'readonly/>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='text' name='productUnitName' value='"+data[i].unitName+"' size='10' readonly/>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='number' name='productPrice' value='"+data[i].unitPrice
					+"' size='10' readonly/>";
					cell = row.insertCell(-1);
					cell.innerHTML = "<input type='text' name='itemPrice' size='10' value='"+data[i].itemPrice
					+"' readonly/>";
				}
			},
			"json"
	);

}






