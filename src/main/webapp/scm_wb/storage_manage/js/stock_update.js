$(function(){
	$.ajax({
		url:"/SCM/SelectAllProductServlet",
		type:"GET"
	});
});

function stock_update(obj){
	var str = $(obj).parent().parent().find("td").eq(0).text();
	var num = $("#num").val();
	var productCode = str.substr(4,str.length-4);
	var originNum = $(obj).parent().parent().parent().find("tr").eq(1).find("td").eq(3).text();
	var type = $("#type option:selected").text();
	var description = $("#reason").val();
	var realNum = 0;
	if(type == "损耗"){
		realNum = Number(originNum)-Number(num);
	}else{
		realNum = Number(originNum)+Number(num);
	}
	var createDate=(new Date()).toLocaleDateString();
	$.ajax({
		url:"/SCM/StockUpdateServlet",
		type:"POST",
		data:{productCode:productCode,
			originNum:originNum,
			type:type,
			description:description,
			realNum:realNum,
			createDate:createDate}
	});
}
