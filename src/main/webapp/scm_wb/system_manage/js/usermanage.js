$(function(){
	$("#m").show();
	$.ajax({
		url:"/SCM/SelectAllUserServlet",
		type:"GET"
	});
});
function userdelete(obj){
	var account = $(obj).parent().parent().find("td").eq(0).text();
	$.ajax({
		url:"/SCM/UserDeleteServlet",
		type:"POST",
		data:{account:account},
		success:function(){
			location.href="/SCM/scm_wb/system_manage/usermanage.jsp";
		}
	});
}
function userupdate(obj){
	var account = $(obj).parent().parent().find("td").eq(0).text();
	var password = $(obj).parent().parent().find("td").eq(1).text();
	var name = $(obj).parent().parent().find("td").eq(2).text();
	var status = $(obj).parent().parent().find("td").eq(4).text();
	$("#m").hide();
	$("#add").show();
	$("#account").attr("disabled",true);
	$("#account").val(account);
	$("#password").val(password);
	$("#name").val(name);
	$("#up_or_add").val("修改");
}
function save(){
	var account = $("#account").val();
	var newpassword = $("#password").val();
	var newname = $("#name").val();
	var newstatus =$('#status').val();
	var createDate=(new Date()).toLocaleDateString();
	createDate = createDate.replace(/\//g,'-');
	var up_or_add = $("#up_or_add").val();
	var act_psd_jy = /^[0-9a-zA-Z]{4,12}$/;
	if(!act_psd_jy.test(account)){
		$("#jiaoyan").text("账号只能是4-12位数字和字母");
	}else{
		if(!act_psd_jy.test(newpassword)){
			$("#jiaoyan").text("密码只能是4-12位数字和字母");
		}else{
			if(newname == "" || newname==null){
				$("#jiaoyan").text("名字不能为空");
			}else{
				//新增校验账号是否存在
				if(up_or_add == "新增"){
					$.ajax({
						type:"POST",
						url:"/SCM/UserCheckExistServlet",
						data:{account:account},
						success:function(data){
							if(data == "已存在"){
								$("#jiaoyan").text("账号已经存在，请从新输入");
							}else{
								$.ajax({
									type:"POST",
									url:"/SCM/UserUpdateServlet",
									data:{account:account,
										  password:newpassword,
										  name:newname,
										  status:newstatus,
										  createDate:createDate,
										  up_or_add:up_or_add},
									success:function(){
										location.href="/SCM/scm_wb/system_manage/usermanage.jsp";
									}
								});
							}
						}
					});
				}else{
					$.ajax({
						type:"POST",
						url:"/SCM/UserUpdateServlet",
						data:{account:account,
							  password:newpassword,
							  name:newname,
							  status:newstatus,
							  createDate:createDate,
							  up_or_add:up_or_add},
						success:function(){
							location.href="/SCM/scm_wb/system_manage/usermanage.jsp";
						}
					});
				}
				
			}
		}
	}
}
function qx(){
	location.href="/SCM/scm_wb/system_manage/usermanage.jsp";
}
function add(){
	$("#m").hide();
	$("#add").show();
	$("#account").attr("disabled",false);
	$("#up_or_add").val("新增");
}
function query(){
	var account = $("#search_account").val();
	var password = $("#search_password").val();
	var name = $("#search_name").val();
	var status =$('#search_status').val();
	var createDate =$('#search_createDate').val();
	$.ajax({
		type:"POST",
		url:"/SCM/UserSearchServlet",
		data:{account:account,
			  password:password,
			  name:name,
			  status:status,
			  createDate:createDate},
		success:function(){
			location.href="/SCM/scm_wb/system_manage/usermanage.jsp";
		}
	});
}