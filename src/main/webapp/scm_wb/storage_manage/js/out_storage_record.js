$(function(){
	$.ajax({
		url:"/SCM/SelectCanOutStorageServlet",
		type:"GET"
	});
});

function show_poitem(obj){
	var poidstr = $(obj).text();
	var soid = poidstr.charAt(poidstr.length-1);
	$.ajax({
		url:"/SCM/ShowSellOrderItemServlet",
		type:"POST",
		data:{soid:soid},
		success:function(){
			location.href="/SCM/scm_wb/storage_manage/show_sell_order_item.jsp";
		}
	});
}

function out_storage_sell_order(obj){
	var str = $(obj).parent().parent().find("td").eq(0).text();
	var soid = str.substr(5,str.length-5);
	var createDate=(new Date()).toLocaleDateString();
	$.ajax({
		url:"/SCM/OutStorageRecordServlet",
		type:"POST",
		data:{soid:soid,
			createDate:createDate}
	});
}