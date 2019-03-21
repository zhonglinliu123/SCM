//处理状态
var zt={
		"1":"新增",
		"2":"已发货",
		"3":"已付款",
		"4":"已了结",
		"5":"预付款"
}



$(document).ready(function(){
	$("#somainCtrl").hide();
	$.get(
			"/SCM/storeCan/AjaxSalesReportServlet_c",
			{"dateStart":"2018-10-1","dateEnd":"2018-10-31"},
			function(data){
				allSomain = data.length;
				//alert("all"+allSomain);
				var tab = document.getElementById("reportTab");
				for(var i=0;i<data.length;i++){
					var row = tab.insertRow(-1);
					var cell = row.insertCell(-1);
					cell.innerHTML = "<a onclick='showSomain(this)' style='cursor:pointer;'>"+data[i].soid+"</a>";
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].customerCode;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].customerName;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].createTime;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].userName;
					cell = row.insertCell(-1);
					cell.innerHTML = data[i].soTotal;
					
					
					if("货到付款"==data[i].payType){
						if(data[i].status>=3){
							cell = row.insertCell(-1);
							cell.innerHTML = 0;
						}else{
							cell = row.insertCell(-1);
							cell.innerHTML = data[i].soTotal;
						}
					}
					
					if("款到发货"==data[i].payType){
						if(data[i].status>=2){
							cell = row.insertCell(-1);
							cell.innerHTML = 0;
						}else{
							cell = row.insertCell(-1);
							cell.innerHTML = data[i].soTotal;
						}
					}
					
					if("预付款到发货"==data[i].payType){
						if(data[i].status==3 || data[i].status==4){
							cell = row.insertCell(-1);
							cell.innerHTML = 0;
						}else if(data[i].status>=2){
							cell = row.insertCell(-1);
							cell.innerHTML = parseFloat(data[i].soTotal)-parseFloat(data[i].prePayFee);
						}else{
							cell = row.insertCell(-1);
							cell.innerHTML = data[i].soTotal;
						}
					}
					
					cell = row.insertCell(-1);
					cell.innerHTML = zt[data[i].status];
				}
			},
			"json"
	);
	
	setInterval('statistic()',200);
	
});

//统计数据
function statistic(){
	var tab = document.getElementById("reportTab");
	
	var allSomain = tab.rows.length - 2;
	var overSomain = 0;
	var allMoney = 0;
	var sum = 0;//未付款总数
	var payedMoney = 0;
	
	for(var i=2;i<tab.rows.length;i++){
		if(tab.rows[i].cells[7].innerHTML == "已了结"){
			overSomain++;
		}
		allMoney += parseFloat(tab.rows[i].cells[5].innerHTML);
		sum += parseFloat(tab.rows[i].cells[6].innerHTML);
	}
	
	payedMoney = allMoney - sum;
	
	$("#allSomain").text(allSomain);
	$("#overSomain").text(overSomain);
	$("#allMoney").text(allMoney);
	$("#payedMoney").text(payedMoney);
}

//查询
function search(){
	
	var dateStart = $("input[name='dateStart']").val();
	var dateEnd = $("input[name='dateEnd']").val();
	
	datereg = /[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}/;
	
	if(datereg.test(dateStart) && datereg.test(dateEnd)){
	
		$("#date").text(dateStart+"——"+dateEnd);
		
		var tab = document.getElementById("reportTab");
		while(tab.rows.length>2){
			tab.deleteRow(2);
		}
		
		$.get(
				"/SCM/storeCan/AjaxSalesReportServlet_c",
				{"dateStart":dateStart,"dateEnd":dateEnd},
				function(data){
					allSomain = data.length;
					//alert("all"+allSomain);
					var tab = document.getElementById("reportTab");
					for(var i=0;i<data.length;i++){
						var row = tab.insertRow(-1);
						var cell = row.insertCell(-1);
						cell.innerHTML = "<a onclick='showSomain(this)' style='cursor:pointer;'>"+data[i].soid+"</a>";
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].customerCode;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].customerName;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].createTime;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].userName;
						cell = row.insertCell(-1);
						cell.innerHTML = data[i].soTotal;
						
						
						if("货到付款"==data[i].payType){
							if(data[i].status>=3){
								cell = row.insertCell(-1);
								cell.innerHTML = 0;
							}else{
								cell = row.insertCell(-1);
								cell.innerHTML = data[i].soTotal;
							}
						}
						
						if("款到发货"==data[i].payType){
							if(data[i].status>=2){
								cell = row.insertCell(-1);
								cell.innerHTML = 0;
							}else{
								cell = row.insertCell(-1);
								cell.innerHTML = data[i].soTotal;
							}
						}
						
						if("预付款到发货"==data[i].payType){
							if(data[i].status==3 || data[i].status==4){
								cell = row.insertCell(-1);
								cell.innerHTML = 0;
							}else if(data[i].status>=2){
								cell = row.insertCell(-1);
								cell.innerHTML = parseFloat(data[i].soTotal)-parseFloat(data[i].prePayFee);
							}else{
								cell = row.insertCell(-1);
								cell.innerHTML = data[i].soTotal;
							}
						}
						
						cell = row.insertCell(-1);
						cell.innerHTML = zt[data[i].status];
					}
				},
				"json"
		);
		
		setInterval('statistic()',200);
	}else{
		alert("输入格式为 xxxx-xx-xx");
	}
	
}

//点击编号显示详细内容
function showSomain(obj){
	var soid = obj.innerHTML;
	$("#somainCtrl").show();
	$("#reportTab").hide();
	
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

function back(){
	$("#somainCtrl").hide();
	$("#reportTab").show();
}






