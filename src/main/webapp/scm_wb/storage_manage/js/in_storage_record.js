$(function(){
	$.ajax({
		url:"/SCM/SelectCanInStorageServlet",
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
			location.href="/SCM/scm_wb/storage_manage/show_purchase_order_item.jsp";
		}
	});
}

function in_storage_purchase_order(obj){
	var str = $(obj).parent().parent().find("td").eq(0).text();
	var poid = str.substr(5,str.length-5);
	var createDate=(new Date()).toLocaleDateString();
	$.ajax({
		url:"/SCM/InStorageRecordServlet",
		type:"POST",
		data:{poid:poid,
			createDate:createDate}
	});
}