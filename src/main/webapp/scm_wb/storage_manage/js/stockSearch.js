function search(){
	var productCode = $("#productCode").val();
	var productName = $("#productName").val();
	$.ajax({
		url:"/SCM/StockSearchServlet",
		type:"POST",
		data:{productCode:productCode,
			productName:productName}
	});
}
