<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/SCM/css/style.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/SCM/script/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/SCM/script/productDiv.js" ></script>
<script type="text/javascript" src="/SCM/script/customer.js"></script>
</head>
<body>

<div id="m">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
	  <tr>
	    <td nowrap class="title1">您的位置：工作台面－－客户管理</td>
	  </tr>
	</table>
	<table width="100%"  border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td width="30px" nowrap class="toolbar">&nbsp;</td>
	    <td width="60px" nowrap class="toolbar">
	    	<span style="cursor:pointer;" onclick="addCustomer()">
	    		<img src="/SCM/images/new.gif">新增
	    	</span>
	    </td>
	    <td width="10px" nowrap class="toolbar">|</td>
	    <td width="60px" nowrap class="toolbar">
	    	<span style="cursor:pointer;"  onclick="">
	    		<img src="/SCM/images/search.gif">查询
	    	</span>
	    </td>
		<td nowrap class="toolbar">&nbsp;</td>
	    <td width="60px" nowrap class="toolbar">&nbsp;</td>
	    <td width="20px" nowrap class="toolbar">&nbsp;</td>
	    <td width="60px" nowrap class="toolbar">&nbsp;</td>
	    <td width="20px" nowrap class="toolbar">&nbsp;</td>
	    <td width="60px" nowrap class="toolbar">&nbsp;</td>
	    <td width="20px" nowrap class="toolbar">&nbsp;</td>
	    <td width="60px" nowrap class="toolbar">&nbsp;</td>
	    <td width="20px" nowrap class="toolbar">&nbsp;</td>
	  </tr>
	</table>
	
	
	
	<div  class="hidd" id="customerSelect">
	 <table>
	 	<tr>
	 		<td>请输入客户编号：<input name="selectProductCode" type="text"/></td>
	 		<td>请输入客户名称：<input name="selectProductName" type="text"/></td>
	 	</tr>
	 </table>
	</div>
	
	
	
	<table id="customerShow" width="100%"  border="0" align="center" cellspacing="1" class="c">
	  <tr>
	    <td class="title1">客户编号</td>
	    <td class="title1">客户名称</td>
		<td class="title1">联系人</td>
	    <td class="title1">地址</td>
	    <td class="title1">邮政编码</td>
	    <td class="title1">注册日期</td>
	    <td class="title1">电话</td>
	    <td class="title1">传真</td>
	    <td class="title1">操作</td>
	  </tr>
	  <c:forEach items="${customerList }" var="customer">
		   <tr>
		    <td align="center">${customer.customerCode }</td>
		    <td align="center">${customer.customerName }</td>
			<td align="center">${customer.contactor }</td>
		    <td align="center">${customer.address }</td>
		    <td align="center">${customer.postcode }</td>
		    <td align="center">${customer.createDate }</td>
		    <td align="center">${customer.tel }</td>
		    <td align="center">${customer.fax }</td>
		    <td align="center">
		    	<a style="cursor:pointer;" onclick="updateCustomer(this)">修改</a>
				<a style="cursor:pointer;" onclick="deleteCustomer(this)">删除</a>
			</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	
	
	<div id="customerCtrl" class="hidd">
	<table width="100%"  border="0" align="center" cellspacing="1" class="c">
	 	<tr>
		   <td align="center" width="40%">客户编号</td>
		   <td>
			   	<input name="customerCode" type="text" onkeyup="customerCodeChange()""/>
			   	<span style="color:red">*</span>
			   	<span id="customerCodeHint"></span>
		   </td>
		</tr>
		<tr>
		   <td align="center">客户名称</td>
		   <td>
			   	<input name="customerName" type="text"/>
			   	<span style="color:red">*</span>
			   	<span id="customerNameHint"></span>
		   </td>
		</tr>
		<tr>
		   <td align="center">客户密码</td>
		   <td>
				<input name="customerPasseprd" type="password"/>
				<span style="color:red">*</span>
			   	<span id="customerPasswordHint"></span>
		   </td>
		</tr>
		<tr>
		   <td align="center">联系人</td>
		   <td>
		  		<input name="contactor" type="text"/>
		  		<span style="color:red">*</span>
		  		<span id="contactorHint"></span>
		   </td>
		</tr>
		<tr>
		   <td align="center">地址</td>
		   <td>
		   		<input name="customerAddress" type="text""/>
		   		<span style="color:red">*</span>
			   	<span id="customerAddressHint" style="cloor:red;"></span>
		   </td>
		</tr>
		<tr>
		   <td align="center">邮政编码</td>
		   <td>
		   		<input name="postcode" type="text" style="padding-left:20%;"/>
		   </td>
		</tr>
		<tr>
		   <td align="center">注册日期</td>
		   <td>
		   		<input name="customerCreateDate" type="text" style="padding-left:20%;"/>
		   </td>
		</tr>
		<tr>
		   <td align="center">电话</td>
		   <td>
		   		<input name="customerTel" type="text" style="padding-left:20%;"/>
		   		<span style="color:red">*</span>
		   		<span id="costomerTelHint"></span>
		   </td>
		</tr>
		<tr>
		   <td align="center">传真</td>
		   <td>
		   		<input name="fax" type="text" style="padding-left:20%;"/>
		   </td>
		</tr>
		<tr>
		   <td align="center" colspan="2">
		   	<input id="saveCustomer" type="button" value="保存"/>
		   </td>
		</tr>
	
</table>

</div>
</body>
</html>