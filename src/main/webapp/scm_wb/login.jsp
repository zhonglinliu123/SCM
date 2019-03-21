<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>

<form action="/SCM/LoginServlet_c" method="post">
<table width="100%"  border="0">
	<tr>
		<td class="title1">&nbsp;</td>
	</tr>
  <tr>
    <td width="50%" align="right">用户名&nbsp;&nbsp;</td>
    <td width="50%"><input name="account" type="text" required/></td>
  </tr>
  <tr>
    <td align="right">密码&nbsp;&nbsp;&nbsp;</td>
    <td><input name="password" type="password" required/></td>
  </tr>
  <tr>
  	<td class="title1"><div align="right"><input type="submit" value="登录"/></div></td>
	<td class="title1"><div align="left"><input type="reset" value="重置"/></div></td>
  </tr>
</table>
<p id="hint">${error }</p>
</form>

</body>
</html>