$(function(){
	$("#m").show();
	$.ajax({
		url:"/SCM/SelectAllPurchaseOrderServlet",
		type:"GET"
	});
});

function show_poitem(obj){
	var poidstr = $(obj).text();
	var poid = poidstr.charAt(poidstr.length-1);
	$.ajax({
		url:"/SCM/ShowPurchaseOrderItemServlet",
		type:"POST",
		data:{poid:poid},
		success:function(){
			location.href="/SCM/scm_wb/purchase_manage/show_purchase_order_item.jsp";
		}
	});
}

function add_purchase_order(){
	$("#add").show();
	$("#m").hide();
	var date = new Date();
	var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var timestamp = date.getTime();
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    $("#poid").val(timestamp);
    $("#createTime").val(currentdate);
    $.ajax({
		url:"/SCM/ShowVenderServlet",
		type:"POST",
		success:function(json){
			var arr = eval("("+json+")");
			for(var i=0;i<arr.length;i++){ 
				$("#select_vender").append("<option value='"+arr[i].vendercode+"'>"+arr[i].vendercode+":"+arr[i].name+"</option>");
			}
		}
	});
    
}
function paytype_change(){
	var value = $("#select-paytype option:selected").text();
	if(value == "预付款到发货"){
		$("#prePayFee").removeAttr("disabled");
	}else{
		$("#prePayFee").attr("disabled","disabled");
	}
}
function qx(){
	location.href="/SCM/scm_wb/purchase_manage/add_purchase_order.jsp";
}
function save(){
	var poid = $("#poid").val();
	var createTime = $("#createTime").val();
	var venderCode = $("#select_vender option:selected").text().split(":")[0];
	var account = $("#account").val();
	var tipFee = $("#tipFee").val();
	var productTotal = $("#productTotal").val();
	var paytype = $("#select-paytype option:selected").text();
	var remark = $("#remark").val();
	var prePayFee = $("#prePayFee").val();
	$.ajax({
		url:"/SCM/AddPurchaseOrderServlet",
		type:"POST",
		data:{
			poid:poid,
			createTime:createTime,
			venderCode:venderCode,
			account:account,
			tipFee:tipFee,
			productTotal:productTotal,
			paytype:paytype,
			remark:remark,
			prePayFee:prePayFee
		},
		success:function(){
			location.href="/SCM/SelectAllPurchaseOrderServlet?tp=1";
		}
	});
}