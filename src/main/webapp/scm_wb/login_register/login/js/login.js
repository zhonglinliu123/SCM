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
                alert(data);
             }
	     });
    }
}        
 
