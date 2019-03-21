//customer 的js

//获得当前时间
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



//页面加载后执行
$(document).ready(function(){
	$("#customerSelect").hide();
	$("#customerCtrl").hide();
	
});

//新增
function addCustomer(){
	$("#customerCtrl").show();
	$("#customerShow").hide();
	$("#customerSelect").hide();
	
	$("#saveCustomer").click(function(){
		var code = $("input[name='customerCode']").val();
		var name = $("input[name='customerName']").val();
		var password = $("input[name='customerPassword']").val();
		var contactor = $("input[name='contactor']").val();
		var Address = $("input[name='customerAddress']").val();
		var postcode = $("input[name='postcode']").val();
		var createDate = $("input[name='createDate']").val(time);
		$("input[name='createDate']").attr("readonly","readonly");
		var tel = $("input[name='customerTel']").val();
		
	});
}