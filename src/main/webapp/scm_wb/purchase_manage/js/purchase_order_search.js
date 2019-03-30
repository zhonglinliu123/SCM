function purchase_order_search(){
	var poid = $("#poid").val();
	var venderCode = $("#venderCode").val();
	var payType = $("#payType").val();
	var status = $("#status").val();$("#m").show();
	$.ajax({
		url:"/SCM/SearchPurchaseOrderServlet",
		type:"POST",
		data:{
			poid:poid,
			venderCode:venderCode,
			payType:payType,
			status:status
		},
		success:function(){
			location.href="/SCM/scm_wb/purchase_manage/add_purchase_order.jsp";
		}
	});
}
