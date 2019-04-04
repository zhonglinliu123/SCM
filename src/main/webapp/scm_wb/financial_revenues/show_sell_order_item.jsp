<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>销售明细</title>
			<link href="/SCM/scm_wb/css/style.css" rel="stylesheet" type="text/css">
			<script src="/SCM/scm_wb/script/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
	</head>
	<body>
		<table style="width:100%;border:0;cellpadding:0;cellspacing:0;">
		  <tr>
		    <td nowrap class="title1">你的位置: 工作台面--财务收支--销售单明细</td>
		  </tr>
		</table>
		<div class="formVisiblitly" id="formDiv"></div>
	
	<div id="purchase_order_item_div" >
			<div style="background-color:#A0C0F1;width:100%;height:20px;">采购单明细</div>
			<c:forEach items="${sessionScope.sellOrderItemList }" var="sellOrderItem" >
				<table id="headTable" class="a1" style="width:100%;border:0;align:center;">
	  				<tr align="justify">
	   					<td style="color:#A3CEEC;">产品编号</td>
	    				<td><span style="size:15;">${sellOrderItem.productCode}</span> </td>
	    				
	    				<td style="color:#A3CEEC;">产品名称</td>
	    				<td><span style="size:15;">${sellOrderItem.productName}</span></td>
	    				
					    <td style="color:#A3CEEC;">产品数量</td>
					    <td><span style="size:15;">${sellOrderItem.num}</span></td>
					    
					    <td style="color:#A3CEEC;">数量单位</td>
					    <td><span style="size:15;">${sellOrderItem.unitNum}</span></td>
					    
					    <td style="color:#A3CEEC;">销售单价</td>
				      	<td><span style="size:15;">${sellOrderItem.itemPrice}</span></td>
				      	
				      	<td style="color:#A3CEEC;">产品明细总价</td>
				      	<td><span style="size:15;">${sellOrderItem.sellDetailsTotal}</span></td>
				      	
				    </tr>
				</table> 
 	  		</c:forEach> 
	</div>
 
<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="../common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
</body>
</html>