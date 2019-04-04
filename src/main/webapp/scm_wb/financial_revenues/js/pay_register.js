$(function(){
	$.ajax({
		url:"/SCM/SelectCanFuKuanServlet",
		type:"GET"
	});
});

function show_poitem(obj){
	var poidstr = $(obj).text();
	var soid = poidstr.charAt(poidstr.length-1);
	$.ajax({
		url:"/SCM/ShowPurchaseOrderItemServlet",
		type:"POST",
		data:{soid:soid},
		success:function(){
			location.href="/SCM/scm_wb/financial_revenues/show_purchase_order_item.jsp";
		}
	});
}

function fu_kuan(obj){
	var str = $(obj).parent().parent().find("td").eq(0).text();
	var soid = str.substr(5,str.length-5);
	var status = $(obj).parent().parent().parent().find("tr").eq(2).find("td").eq(3).text();
	$.ajax({
		url:"/SCM/FuKuanServlet",
		type:"POST",
		data:{soid:soid,
			status:status}
	});
}