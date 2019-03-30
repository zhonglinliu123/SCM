<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>添加采购单</title>
			<link href="/SCM/scm_wb/css/style.css" rel="stylesheet" type="text/css">
			<script src="/SCM/scm_wb/script/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
	 		<script src="/SCM/scm_wb/purchase_manage/js/add_purchase_order.js" type="text/javascript" charset="UTF-8"></script>
	</head>
	<body>
		<table style="width:100%;border:0;cellpadding:0;cellspacing:0;">
		  <tr>
		    <td nowrap class="title1">你的位置：工作台面--采购管理--添加采购单</td>
		  </tr>
		</table>
		<div class="formVisiblitly" id="formDiv"></div>
		
		<div id="m">
			<form action="" >
					<table style="width:100%;border:0;cellpadding:0;cellspacing:0;">
					  <tr>
					    <td width="40px" nowrap class="toolbar" onclick="add_purchase_order()" style="cursor:pointer;"><img src="/SCM/scm_wb/images/new.gif">新增</td>
					  </tr>
					</table>
				
				<div style="width:100%;align:center;">
					<c:forEach items="${sessionScope.purchaseOrderMainList }" var="purchaseOrderMain" >
							<div style="cursor:pointer;background-color:#EAC0C5;width:100%;height:20px;" onclick="show_poitem(this)">采购单编号${purchaseOrderMain.poid}</div>
							<table id="headTable" class="a1" style="width:100%;border:0;align:center;">
				  				<tr align="justify">
				   					<td style="color:#DD6166;">创建时间</td>
				    				<td><span style="size:15;">${purchaseOrderMain.createDate}</span> </td>
				    				
				    				<td style="color:#DD6166;">供应商的名称</td>
				    				<td><span style="size:15;">${purchaseOrderMain.venderName}</span></td>
				    				
								    <td style="color:#DD6166;">创建用户</td>
								    <td><span style="size:15;">${purchaseOrderMain.account}</span></td>
								    
								    <td style="color:#DD6166;">附加费用</td>
							      	<td><span style="size:15;">${purchaseOrderMain.tipFee}</span></td>
				  				</tr>
							    <tr align="justify">
								      <td style="color:#DD6166;">采购产品总价</td>
								      <td><span style="size:15;">${purchaseOrderMain.productTotal}</span></td>
								      
								      <td style="color:#DD6166;">采购单总价格</td>
								      <td><span style="size:15;">${purchaseOrderMain.poTotal}</span></td>
								      
								      <td style="color:#DD6166;">付款方式</td>
								      <td><span style="size:15;">${purchaseOrderMain.payType}</span></td>
								      
								      <td style="color:#DD6166;">最低预付金额</td>
								      <td><span style="size:15;">${purchaseOrderMain.prePayFee}</span></td>
							    </tr>
							</table>
			  	  	</c:forEach>
				</div>
		</form>
	</div>
	
	<div id="add" class="hidd">
			<table width="100%"  border="0" align="center" cellspacing="1" class="c">
		    	 <tr>
				    <td align="center">采购单编号</td>
				    <td align="center"><input id="poid" type="text" readonly="readonly"/></td>
				 </tr>
				 <tr>
		    		<td align="center">创建时间</td>
		            <td align="center"><input id="createTime" type="text" readonly="readonly"/></td>
			     </tr>
			     <tr>
		    		<td align="center">供应商</td>
		            <td align="center">
		            	<select id="select_vender">
		            	</select>
		            </td>
			     </tr>
				 <tr>
					<td align="center">创建用户</td>
		            <td align="center"><input id="account" type="text" value="${sessionScope.user.account }" readonly="readonly"/></td>
				 </tr>
				 <tr>
					<td align="center">附加费用</td>
		            <td align="center"><input id="tipFee" type="text" value="0"/></td>
				 </tr>
				 <tr>
					<td align="center">采购产品总价</td>
		            <td align="center"><input id="productTotal" type="text"/></td>
				 </tr>
				 <tr>
					<td align="center">付款方式</td>
		            <td align="center">
		            	<select id="select-paytype" onchange="paytype_change()">
		            		<option>货到付款</option>
		            		<option>款到发货</option>
		            		<option>预付款到发货</option>
		            	</select>
		            </td>
				 </tr>
				 <tr>
					<td align="center">备注</td>
		            <td align="center"><input id="remark" type="text"/></td>
				 </tr>
				 <tr>
					<td align="center">最低预付金额</td>
		            <td align="center"><input id="prePayFee" type="text" value="0" disabled="disabled"/></td>
				 </tr>
				 <tr>
		    		<td height="18" colspan="2" align="center">
		    			<input type="button" value="取消" onClick="qx()"/>
		    			<input type="button" value="保存" onClick="save()"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td height="18" colspan="2" align="center">
		    			<div id="jiaoyan" style="color:red;"></div>
		    		</td>
		    	</tr>
			</table>
			<div type="hidden" id="up_or_add"></div>	
		</div>
		
		<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="../common/calendar/ipopeng.htm" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
	</body>
</html>