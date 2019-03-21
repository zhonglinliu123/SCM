<%@page import="java.util.ArrayList"%>
<%@page import="scm.model.*"%>
<%@page import="scm.dao.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/SCM/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/SCM/script/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/SCM/script/productDiv.js" ></script>
<script type="text/javascript" src="/SCM/script/jsp_c.js"></script>

</head>
<body>
	<div id="m">
	<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
	  <tr>
	    <td nowrap class="title1">您的位置：工作台面－－提货管理</td>
	  </tr>
	</table>
	<table style="background-color:#92C3E7;width:100%;">
		<tr>
		    <td style="text-align:left;" nowrap class="toolbar" onclick="addCategory()">
		    	<span style="cursor:pointer;"><img src="/SCM/images/new.gif">新增</span>
		    </td>
	    </tr>
	</table>
	<table width="100%" border="0" align="center" cellspacing="1" class="c" id="showcate">
	  <tr>
	    <td class="title1">产品编号</td>
	    <td class="title1">名称</td>
		<td class="title1">描述</td>
	    <td class="title1">操作</td>
	  </tr>
	  <c:forEach items="${categorylist_c}" var="category">
	  <tr>
	    <td align="center">${category.categoryID }</td>
	    <td align="center">${category.name }</td>
		<td align="center">${category.remark }</td>
	    <td align="center">
	    	<a onClick="updateCategory(this)" style="cursor:pointer;">修改</a> 
	    	<a onclick="deleteCategory(this)" style="cursor:pointer;">删除</a>
	    </td>
	  </tr>
	  </c:forEach>
	</table>
	<p id="categoryError" style="text-align:center;color:red;font-size:20px;">${categoryerror }</p>
	<!-- <p style="text-align:center;"><a href="/SCM/storeCan/goodsClass.jsp">返回</a></p> -->
	</div>
	<br/>
	
	
<div id="cateCtrl" class="hidd">
	<table width="100%"  border="0" align="center" cellspacing="1" class="c">
	  <tr id="cateID">
	    <td align="center">分类序列号：</td>
	    <td align="center"><input name="categoryID" type="text"/></td>
	  </tr>
	  <tr>
	    <td align="center">分类名称：</td>
	    <td align="center">
	    	<input name="categoryName" type="text" required onkeyup="haveName()"/>
	    	<span id="cateHint" style="color:red;"></span>
	    </td>
	  </tr>
	  <tr>
	    <td align="center">分类描述：</td>
	    <td align="center"><input name="categoryRemark" type="text"/></td>
	  </tr>
	  <tr>
	    <td height="18" colspan="2" align="center">
		    <input type="button" value="保存" onClick="saveCategory()"/>
	    </td>
	  </tr>
	</table>
<p style="text-align:center;"><a href="/SCM/storeCan/GoodsClassServlet_c">返回产品分类主页</a></p>

</div>
</body>
</html>