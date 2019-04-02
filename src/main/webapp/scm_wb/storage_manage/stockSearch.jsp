<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>库存查询</title>
			<link href="/SCM/scm_wb/css/style.css" rel="stylesheet" type="text/css">
			<script src="/SCM/scm_wb/script/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
			<script src="/SCM/scm_wb/storage_manage/js/stockSearch.js" type="text/javascript" charset="UTF-8"></script>
	</head>
	<body>
		<table style="width:100%;border:0;cellpadding:0;cellspacing:0;">
		  <tr>
		    <td nowrap class="title1">你的位置：工作台面--仓储管理--库存查询</td>
		  </tr>
		</table>
		<div class="formVisiblitly" id="formDiv"></div>
		
		<div id="m">
			<form action="" >
				<div style="width:100%;height:30px;border:0;cellpadding:0;cellspacing:0;background-color:#2879FF;">
					产品编号<input type="text" id="productCode" />
					产品名称<input type="text" id="productName" />
					<button onclick="search()">查询</button>
				</div>
				<div style="width:100%;align:center;">
					<c:forEach items="${sessionScope.searchProductList }" var="product" >
							<table id="headTable" class="a1" style="width:100%;border:0;align:center;">
								<tr align="justify">
				   					<td style="cursor:pointer;background-color:#EAC0C5;width:20%;height:20px;">产品编号${product.productCode}</td>
				  				</tr>
				  				<tr align="justify">
				   					<td style="color:#DD6166;">产品名称</td>
				    				<td><span style="size:15;">${product.productName}</span> </td>
				    				
				    				<td style="color:#DD6166;">当前库存</td>
				    				<td><span style="size:15;">${product.nowNum}</span></td>
				    				
								    <td style="color:#DD6166;">采购在途数</td>
								    <td><span style="size:15;">${product.poNum}</span></td>
								    
								    <td style="color:#DD6166;">销售代发数</td>
							      	<td><span style="size:15;">${product.soNum}</span></td>
				  				</tr>
							</table>
			  	  	</c:forEach>
				</div>
		</form>
	</div>
	
		<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="../common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
	</body>
</html>