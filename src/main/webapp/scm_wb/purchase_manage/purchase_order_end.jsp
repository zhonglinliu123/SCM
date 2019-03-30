<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>采购单了结</title>
			<link href="/SCM/scm_wb/css/style.css" rel="stylesheet" type="text/css">
			<script src="/SCM/scm_wb/script/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
			<script src="/SCM/scm_wb/purchase_manage/js/purchase_order_end.js" type="text/javascript" charset="UTF-8"></script>
	</head>
	<body>
		<table style="width:100%;border:0;cellpadding:0;cellspacing:0;">
		  <tr>
		    <td nowrap class="title1">你的位置：工作台面--采购管理--采购单了结</td>
		  </tr>
		</table>
		<div class="formVisiblitly" id="formDiv"></div>
		
		<div id="m">
			<form action="" >
				<div style="width:100%;height:20px;border:0;cellpadding:0;cellspacing:0;background-color:#2879FF;">
				
				</div>
				<div style="width:100%;align:center;">
					<c:forEach items="${sessionScope.purchaseOrderMainEndList }" var="purchaseOrderMainEnd" >
							<table id="headTable" class="a1" style="width:100%;border:0;align:center;">
								<tr align="justify">
				   					<td style="cursor:pointer;background-color:#EAC0C5;width:30%;height:20px;" onclick="show_poitem(this)">采购单编号${purchaseOrderMainEnd.poid}</td>
				    				<td><button onclick="end_purchase_order(this)">采购单了结</button></td>
				    				
				  				</tr>
				  				<tr align="justify">
				   					<td style="color:#DD6166;">创建时间</td>
				    				<td><span style="size:15;">${purchaseOrderMainEnd.createDate}</span> </td>
				    				
				    				<td style="color:#DD6166;">供应商的名称</td>
				    				<td><span style="size:15;">${purchaseOrderMainEnd.venderName}</span></td>
				    				
								    <td style="color:#DD6166;">创建用户</td>
								    <td><span style="size:15;">${purchaseOrderMainEnd.account}</span></td>
								    
								    <td style="color:#DD6166;">附加费用</td>
							      	<td><span style="size:15;">${purchaseOrderMainEnd.tipFee}</span></td>
				  				</tr>
							    <tr align="justify">
								      <td style="color:#DD6166;">采购产品总价</td>
								      <td><span style="size:15;">${purchaseOrderMainEnd.productTotal}</span></td>
								      
								      <td style="color:#DD6166;">处理状态</td>
								      <td><span style="size:15;">${purchaseOrderMainEnd.status}</span></td>
								      
								      <td style="color:#DD6166;">付款方式</td>
								      <td><span style="size:15;">${purchaseOrderMainEnd.payType}</span></td>
								      
								      <td style="color:#DD6166;">最低预付金额</td>
								      <td><span style="size:15;">${purchaseOrderMainEnd.prePayFee}</span></td>
							    </tr>
							</table>
			  	  	</c:forEach>
				</div>
		</form>
	</div>
	
		<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="../common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
	</body>
</html>