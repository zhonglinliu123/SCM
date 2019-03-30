<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>采购单查询</title>
			<link href="/SCM/scm_wb/css/style.css" rel="stylesheet" type="text/css">
			<script src="/SCM/scm_wb/script/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
			<script src="/SCM/scm_wb/purchase_manage/js/purchase_order_search.js" type="text/javascript" charset="UTF-8"></script>
	</head>
	<body>
		<table style="width:100%;border:0;cellpadding:0;cellspacing:0;">
		  <tr>
		    <td nowrap class="title1">你的位置：工作台面--采购管理--采购单查询</td>
		  </tr>
		</table>
		
		<div id="m">
				<div style="width:100%;height:20px;border:0;cellpadding:0;cellspacing:0;background-color:#2879FF;">
				</div>
				
				<div style="width:100%;height:50px;align:center;background-color:#FFF7E5;">
					采购单编号
					<input id="poid" type="text"/>
					
					供应商
					<input id="venderCode" type="text"/>
					
					付款方式
					<select id="payType">
						<option value="1">货到付款</option>
						<option value="2">款到发货</option>
						<option value="3">预付款到发货</option>
					</select>
					
					处理状态
					<select id="status">
						<option value="1">新增</option>
						<option value="2">已收货</option>
						<option value="3">已付款</option>
						<option value="4">已了结</option>
						<option value="5">已预付</option>
					</select>
					
					<br/>
					<button onclick="purchase_order_search()">查找</button>
				</div>		
		</div>
		
	</body>
</html>