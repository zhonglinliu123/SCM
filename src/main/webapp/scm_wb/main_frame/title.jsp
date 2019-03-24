<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="/SCM/scm_wb/css/style.css" rel="stylesheet" type="text/css">
		<title>title</title>
	    <script src="/SCM/scm_wb/script/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
		<script type="text/JavaScript">
			function leave() {
				parent.location.href="/SCM/scm_wb/system_manage/thank.html";
			}
			
		</script>
	</head>

	<body topmargin=0 leftmargin=0>
		<div style="width:100%;height:200px;background-color:#A0C0F1;">
			<div style="float:right;margin-right:10px;" onclick="leave()">
				<img src="/SCM/scm_wb/images/lkxt.jpg" border="0">
				<span>离开</span>
			</div>
			<div style="float:right;margin-right:20px;">
				<img src="/SCM/scm_wb/images/jrxt.jpg" border="0">你好，${sessionScope.user.name }
			</div>
		</div>
		
	</body>
</html>
