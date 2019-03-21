<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/SCM/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/SCM/script/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/SCM/script/productDiv.js"></script>
<script type="text/javascript" src="/SCM/script/salesReport.js"></script>
</head>

<body>
<div id="m">
<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
  <tr>
    <td nowrap class="title1">您的位置：工作台面－－采购报表</td>
  </tr>
</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30px" nowrap class="toolbar">&nbsp;</td>
    <td nowrap class="toolbar">请输入日期范围：</td>
    <td width="40px" nowrap class="toolbar">
    	<input name="dateStart" type="text" placeholder="yyyy-MM-dd"/>
    </td>
	<td nowrap class="toolbar">——</td>
    <td width="60px" nowrap class="toolbar">
		<input name="dateEnd" type="text" placeholder="yyyy-MM-dd"/>
	</td>
    <td width="20px" nowrap class="toolbar"><input type="button" value="查询" onclick="search()"/></td>
    <td width="80%" nowrap class="toolbar">&nbsp;</td>
  </tr>
</table>
</div>


<table width="100%"  border="0" align="center" cellspacing="1" class="c" id="reportTab">
  <tr>
    <td class="title1">总单据数：<span id="allSomain"></span></td>
    <td class="title1">已了结单数：<span id="overSomain"></span></td>
    <td class="title1">销售总金额：￥<span id="allMoney"></span></td>
    <td class="title1">已付款金额：￥<span id="payedMoney"></span></td>
	<td class="title1" colspan="4">时间：<span id="date">2018-10-1——2018-10-31</span></td>
  </tr>
  <tr>
    <td class="title1">销售单号</td>
    <td class="title1">客户编号</td>
	<td class="title1">客户名称</td>
    <td class="title1">销售日期</td>
    <td class="title1">经手人</td>
    <td class="title1">销售单总金额</td>
    <td class="title1">未付款金额</td>
    <td class="title1">处理状态</td>
  </tr>
</table>

<div id="somainCtrl">
<table style="width:100%;">
	<tr>
		<td class="title1">销售单编号</td>
		<td>
			<input name="soid" type="text" readonly/><span style="color:red;">*</span>
		</td>
		<td class="title1">创建时间</td>
		<td>
			<input name="createTime" type="text" readonly/><span style="color:red;">*</span>
		</td>
		<td class="title1">客户名称</td>
		<td>
			<input name="customerName" type="text" autocomplete="off" readonly/>
		</td>
		<td class="title1">创建用户</td>
		<td>
			<span id="userAccount">${user.account }</span>
			<input name="userAccount" type="text" value="${user.name }" readonly/><span style="color:red;">*</span>
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