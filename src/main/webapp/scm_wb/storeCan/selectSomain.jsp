<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/SCM/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/SCM/script/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/SCM/script/productDiv.js" ></script>
<script type="text/javascript" src="/SCM/script/selectSomain.js" ></script>

<style type="text/css">
	div.product{
		position: absolute;
		top:2px;
		left: 2px;
		width:100%;
		height: 98%;
		background-color: #fffffe;
	}

</style>

</head>
<body>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="title1">您的位置：工作台面－－销售管理－－销售单查询</td>
  </tr>
</table>
<div class="formVisiblitly" id="formDiv"></div>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td nowrap class="toolbar">
    	销售单编号：
    	<input style="width:120px;" name="selectSoid" type="text" onkeyup="search()"/>
    </td>
    <td nowrap class="toolbar">
    	日期范围：
    	<input style="width:120px;" name="dateStart" type="text" placeholder="yyyy-MM-dd" onkeyup="search()"/>
    </td>
    <td nowrap class="toolbar">——</td>
    <td nowrap class="toolbar">
    	<input style="width:120px;" name="dateEnd" type="text" placeholder="yyyy-MM-dd"  onkeyup="search()"/>
    </td>
    <td nowrap class="toolbar">
    	客户选择
    </td>
    <td>
		<input name="cuName" type="text" onclick="showChooseCustomer()" style="width:100px;" autocomplete="off"/>
		<div style="position:absolute;width:100px;background-color:#FFF;" id="Customer">
			<table id="chooseCustomerTab">
			
			</table>
		</div>
    </td>
    <td nowrap class="toolbar">
    	付款方式
    	<select id="payType" onchange="search()">
    		<option></option>
    		<option>货到付款</option>
    		<option>款到发货</option>
    		<option>预付款到发货</option>
    	</select>
    </td>
    <td nowrap class="toolbar">
    	处理状态
    	<select id ="status" onchange="search()">
    		<option></option>
    		<option value="1">新增</option>
    		<option value="2">已发货</option>
    		<option value="3">已付款</option>
    		<option value="4">已了结</option>
    	</select>
    </td>
    <td width="20%" nowrap class="toolbar">&nbsp;</td>
  </tr>
</table>

<div id="showSomainDiv">
<table style="width:100%;text-align:center;" id="showSomain">
	<tr>
		<td class="title1">销售单编号</td>
		<td class="title1">创建时间</td>
		<td class="title1">客户名称</td>
		<td class="title1">创建用户</td>
		<td class="title1">附加费用</td>
		<td class="title1">产品总价</td>
		<td class="title1">销售单总价</td>
		<td class="title1">付款方式</td>
		<td class="title1">最低预付款金额</td>
		<td class="title1">处理状态</td>
	</tr>
</table>

</div>
<br/>
<div id="somainCtrl">
<table style="width:100%;">
	<tr>
		<td class="title1">销售单编号</td>
		<td>
			<input name="soid" type="text" readonly/>
		</td>
		<td class="title1">创建时间</td>
		<td>
			<input name="createTime" type="text" readonly/>
		</td>
		<td class="title1">客户名称</td>
		<td>
			<input name="customerName" type="text" autocomplete="off" readonly/>
		</td>
		<td class="title1">创建用户</td>
		<td>
			<input name="userAccount" type="text" readonly/>
		</td>
		<td class="title1">附加费用</td>
		<td>
			<input name="tipFee" type="number" value="0" readonly/>
		</td>
	</tr>
	<tr>
		<td class="title1">产品总价</td>
		<td>
			<input name="productTotal" type="number" value="0" readonly/>
		</td>
		<td class="title1">售货单总价</td>
		<td>
			<input name="soTotal" type="number" value="0" readonly/>
		</td>
		<td class="title1">付款方式</td>
		<td>
			<select id="chooseType" onchange="selectType()" disabled="dipabled">
				<option value="货到付款">货到付款</option>
				<option value="款到发货">款到发货</option>
				<option value="预付款到发货">预付款到发货</option>
			</select>
		</td>
		<td class="title1">备注</td>
		<td>
			<input name="soRemark" type="text" readonly/>
		</td>
		<td class="title1">最低预付款金额</td>
		<td width="15px">
			<input name="prePayFee" type="number" value="0" readonly/>
		</td>
		
	</tr>
</table>

<table width="100%">
	<tr>
		<td class="title1">出库时间</td>
		<td><input name="stockTime" type="text" readonly/></td>
		
		<td class="title1">预付时间</td>
		<td><input name="prePayTime" type="text" readonly/></td>
		
		<td class="title1">付款时间</td>
		<td><input name="payTime" type="text" readonly/></td>
		
	</tr>
	<tr>
		<td class="title1">出库用户</td>
		<td><input name="stockUser" type="text" readonly/></td>
		<td class="title1">预付操作用户</td>
		<td><input name="prePayUser" type="text" readonly/></td>
		<td class="title1">付款操作用户</td>
		<td><input name="payUser" type="text" readonly/></td>
	</tr>
</table>


<table width="100%"  border="0" cellspacing="1" id="soitemTab">
	<tr>
	    <th class="toolbar"> 商品编码 </th>
	    <th class="toolbar"> 商品名称 </th>
	    <th class="toolbar"> 产品数量 </th>
	    <th class="toolbar">数量单位</th>
	    <th class="toolbar">销售单价</th>
	    <th class="toolbar">销售明细总价</th>
	</tr>
</table>
<p style="text-align:center;"><input type="button" value="返回" onclick="back()"/></p>
</div>

</body>
</html>