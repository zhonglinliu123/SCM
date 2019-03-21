<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/JavaScript">

function closeAlert() {
  	if (confirm("您要退出系统，确定吗？") == true){
  		location.href = "/SCM/LoginServlet_c"
  	}
}

function tiaozhuan(){
	location.href = "/SCM/temp.htm"
}
	
</script>

</head>


<body topmargin=0 leftmargin=0>
<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" height="25">
  <tr>
    <td class="toolbar" width="70%">&nbsp;</td>
    <td class="toolbar" onClick="tiaozhuan()" style="cursor:pointer;">
      <span style="cursor:pointer;">
      <img src="images/jrxt.jpg" border="0"><span id="login">${user.name }</span>
      </span>
    </td>
    <td width="20px" nowrap class="toolbar">|</td>
    <td class="toolbar" onClick="closeAlert()" style="cursor:pointer;">
      <span style="cursor:pointer;"><img src="images/lkxt.jpg" border="0">离开</span>
    </td>
    <td width="20px" nowrap class="toolbar">|</td>
  </tr>
</table>
</body>
</html>