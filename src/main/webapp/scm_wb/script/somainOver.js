//处理状态
var zt={
		"1":"新增",
		"2":"已发货",
		"3":"已付款",
		"4":"已了结",
		"5":"预付款"
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


$(document).ready(function(){
	$("#somainCtrl").hide();
	$("#user").hide();
	var tab = document.getElementById("chooseCustomerTab");
	$.get(
			"/SCM/storeCan/AjaxSomainSelectServlet_c",
			{
				"soid":"",
				"dateStart":"",
				"dateEnd":"",
				"customerCode":"",
				"payType":"",
				"status":""
			},
			function(data){
				var tab = document.getElementById("showSomain");
				while(tab.rows.length>1){
					tab.deleteRow(1);
				}
				for(var i=0;i<data.length;i++){
					if("货到付款"==data[i].payType && data[i].status==3){
						aadData(data[i]);
					}
					if("款到发货"==data[i].payType && data[i].status==2){
						aadData(data[i]);
					}
					if("预付款到发货"==data[i].payType && data[i].status==3){
						aadData(data[i]);
					}
				}
			},
			"json"
	);
});

//在了结的table中添加数据
function aadData(data){
	var tab = document.getElementById("showSomain");
	var row = tab.insertRow(-1);
	var cell = row.insertCell(-1);
	cell.innerHTML = "<a onclick='showSomain(this)' style='cursor:pointer;'>"+data.soid+"</a>";
	cell = row.insertCell(-1);
	cell.innerHTML = data.createTime;
	cell = row.insertCell(-1);
	cell.innerHTML = data.customerName;
	cell = row.insertCell(-1);
	cell.innerHTML = data.userName;
	cell = row.insertCell(-1);
	cell.innerHTML = data.tipFee;
	cell = row.insertCell(-1);
	cell.innerHTML = data.productTotal;
	cell = row.insertCell(-1);
	cell.innerHTML = data.soTotal;
	cell = row.insertCell(-1);
	cell.innerHTML = data.payType;
	cell = row.insertCell(-1);
	cell.innerHTML = data.prePayFee;
	cell = row.insertCell(-1);
	cell.innerHTML = zt[data.status];
	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='button' value='了结' onclick='over(this)'/>";
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

//返回
function back(){
	$("#showSomainDiv").show();
	$("#somainCtrl").hide();
}

//了结
function over(obj){
	var tr = $(obj).parent().parent();
	var soid = tr.find("a").eq(0).text();
	var userName = $("#user").text();
	if(confirm("确定了结？") == true){
		location.href="/SCM/storeCan/SomainOverServlet_c?soid="+soid+"&endTime="+time()+"&endUser="+userName;
	}
}



