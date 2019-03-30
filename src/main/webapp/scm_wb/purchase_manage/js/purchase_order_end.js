$(function(){
	$("#m").show();
	$.ajax({
		url:"/SCM/SelectEndPurchaseOrderServlet",
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

function end_purchase_order(obj){
	var str = $(obj).parent().parent().find("td").eq(0).text();
	var poid = str.substr(5,str.length-5);
	$.ajax({
		url:"/SCM/EndPurchaseOrderServlet",
		type:"POST",
		data:{poid:poid}
	});
}