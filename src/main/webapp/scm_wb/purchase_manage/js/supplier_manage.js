$(function(){
	$("#m").show();
	$.ajax({
		url:"/SCM/SelectAllVenderServlet",
		type:"GET"
	});
});
function venderdelete(obj){
	$("#delete_div").show();
	var vendercode = $(obj).parent().parent().find("td").eq(0).text();
	$("#delete_code").val(vendercode);
}
function qd_delete(){
	var vendercode = $("#delete_code").val();
	$.ajax({
		url:"/SCM/VenderDeleteServlet",
		type:"POST",
		data:{vendercode:vendercode},
		success:function(data){
			if(data == "删除成功"){
				location.href="/SCM/scm_wb/purchase_manage/supplier_manage.jsp";
			}else{
				$("#delete_div").hide();
				$("#not_delete_div").show();
			}
		}
	});
}
function canot_delete(){
	$("#not_delete_div").hide();
}
function qx_delete(){
	$("#delete_div").hide();
}
function venderupdate(obj){
	var code = $(obj).parent().parent().find("td").eq(0).text();
	var name = $(obj).parent().parent().find("td").eq(1).text();
	var contactor = $(obj).parent().parent().find("td").eq(2).text();
	var address = $(obj).parent().parent().find("td").eq(3).text();
	var postcode = $(obj).parent().parent().find("td").eq(4).text();
	var tel = $(obj).parent().parent().find("td").eq(6).text();
	var fax = $(obj).parent().parent().find("td").eq(7).text();
	$("#m").hide();
	$("#add").show();
	$("#code").attr("disabled",true);
	$("#code").val(code);
	$("#name").val(name);
	$("#contactor").val(contactor);
	$("#address").val(address);
	$("#postcode").val(postcode);
	$("#tel").val(tel);
	$("#fax").val(fax);
	$("#up_or_add").val("修改");
}
function save(){
	var code = $("#code").val();
	var name = $("#name").val();
	var contactor = $("#contactor").val();
	var address =$('#address').val();
	var postcode =$('#postcode').val();
	var tel =$('#tel').val();
	var fax =$('#fax').val();
	var password = code;
	var createDate=(new Date()).toLocaleDateString();
	createDate = createDate.replace(/\//g,'-');
	var up_or_add = $("#up_or_add").val();

	var act_psd_jy = /^[0-9a-zA-Z]{4,12}$/;
	if(!act_psd_jy.test(code)){
		$("#jiaoyan").text("供应商编号只能是4-12位数字和字母");
	}else{
		if(name == "" || name==null){
			$("#jiaoyan").text("供应商名字不能为空");
		}else{
			if(contactor == "" || contactor==null){
				$("#jiaoyan").text("联系人不能为空");
			}else{
				if(tel == "" || tel==null){
					$("#jiaoyan").text("电话号码不能为空");
				}else{
						//新增校验账号是否存在
						if(up_or_add == "新增"){
							$.ajax({
								type:"POST",
								url:"/SCM/VenderCheckExistServlet",
								data:{code:code},
								success:function(data){
									if(data == "已存在"){
										$("#jiaoyan").text("账号已经存在，请从新输入");
									}else{
										$.ajax({
											type:"POST",
											url:"/SCM/VenderUpdateServlet",
											data:{code:code,
												  name:name,
												  contactor:contactor,
												  address:address,
												  postcode:postcode,
												  tel:tel,
												  fax:fax,
												  password:password,
												  createDate:createDate,
												  up_or_add:up_or_add},
											success:function(){
												location.href="/SCM/scm_wb/purchase_manage/supplier_manage.jsp";
											}
										});
									}
								}
							});
						}else{
							$.ajax({
								type:"POST",
								url:"/SCM/VenderUpdateServlet",
								data:{code:code,
									  name:name,
									  contactor:contactor,
									  address:address,
									  postcode:postcode,
									  tel:tel,
									  fax:fax,
									  password:password,
									  createDate:createDate,
									  up_or_add:up_or_add},
								success:function(){
									location.href="/SCM/scm_wb/purchase_manage/supplier_manage.jsp";
								}
							});
						}
				}
			}
		}
	}
}
function qx(){
	location.href="/SCM/scm_wb/purchase_manage/supplier_manage.jsp";
}
function add(){
	$("#m").hide();
	$("#add").show();
	$("#code").attr("disabled",false);
	$("#up_or_add").val("新增");
}
function query(){
	var code = $("#search_vender_code").val();
	var name = $("#search_vender_name").val();
	$.ajax({
		type:"POST",
		url:"/SCM/VenderSearchServlet",
		data:{code:code,
			  name:name},
		success:function(){
			location.href="/SCM/scm_wb/purchase_manage/supplier_manage.jsp";
		}
	});
}