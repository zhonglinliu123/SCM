function login(){
    var account = $(".name").val(); 
    var password = $(".pwd").val();
    if(!account || !password) {
      $("#err").css("display","inline-block");
      $("#err").text("请输入用户名或密码");
       return false;
    }else{
    	$.ajax({
    		 type: "GET",
             url: "/SCM/LoginServlet",
             data: {account:account, password:password},
             success: function(data){
                if(data == "登陆成功"){
                	location.href="/SCM/scm_wb/main_frame/index.htm";
                }else{
                	$("#err").show();
                }
             }
	     });
    }
}        
 
