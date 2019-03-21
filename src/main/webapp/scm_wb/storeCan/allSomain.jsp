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
<script type="text/javascript" src="/SCM/script/somain.js"></script>

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
    <td nowrap class="title1">您的位置：工作台面－－销售管理－－销售单添加</td>
  </tr>
</table>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="50px" nowrap class="toolbar">
	    <span style="cursor:pointer;" onclick="addSomain()">
	    	<img src="/SCM/images/add.gif">新增销售单
	    </span>
    </td>
    <td width="5px" nowrap class="toolbar">|</td>
    
    <td width="30px" nowrap class="toolbar" onclick="document.mainFrm.bc.click()">
    	<span onclick="selectSomain()" style="cursor:pointer;">
    		<img src="/SCM/images/search.gif">查询
    	</span>
    </td>
    <td width="5px" nowrap class="toolbar">&nbsp;</td>
    <td width="30px" nowrap class="toolbar">&nbsp;</td>
    <td width="80%" nowrap class="toolbar">&nbsp;</td>
  </tr>
</table>

<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="SELECT">
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
		<input name="cuName" type="text" onclick="selectChooseCustomer()" style="width:100px;" autocomplete="off"/>
		<div style="position:absolute;width:100px;background-color:#FFF;" id="selectCustomer">
			<table id="selectCustomerTab">
				<c:forEach items="${customerList }" var="customer"> 
					<tr>
						<td>
						<a style="cursor:pointer;" onclick="selectCustomer(this)">
							<span>${customer.customerCode }</span>
							<span>${customer.customerName }</span>
						</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
    </td>
    <td nowrap class="toolbar">
    	付款方式
    	<select id="selectPayType" onchange="search()">
    		<option></option>
    		<option>货到付款</option>
    		<option>款到发货</option>
    		<option>预付款到发货</option>
    	</select>
    </td>
    <td width="60%" nowrap class="toolbar">&nbsp;</td>
  </tr>
</table>

<div id="showSomain">
<table style="width:100%;text-align:center;" id="showSomainTab">
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
		<td class="title1">操作</td>
	</tr>
	<c:forEach items="${somainList }" var="somain">
	<tr>
		<td><a style="cursor:pointer;" onclick="showSomain(this)">${somain.soid }</a></td>
		<td>${somain.createTime }</td>
		<td><span class="showCustomerCode">${somain.customerCode }</span>${somain.customerName }</td>
		<td><span class="showAccount">${somain.account }</span>${somain.userName }</td>
		<td>${somain.tipFee }</td>
		<td>${somain.productTotal }</td>
		<td>${somain.soTotal }</td>
		<td>${somain.payType }</td>
		<td>${somain.prePayFee }</td>
		<td>
			<input type="button" value="修改" onclick="showSomain(this)"/>
			<input type="button" value="删除" onclick="deleteSomain(this)"/>
		</td>
	</tr>
	</c:forEach>
</table>
<p id="deleteHint"></p>

</div>


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
			<span id="customerCode"></span>
			<input name="customerName" type="text" onclick="showChooseCustomer()" autocomplete="off"/>
			<span style="color:red;">*</span>
			<div style="height:50px;position:absolute;width:160px;background-color:#FFF;" id="Customer">
				<table id="chooseCustomerTab" style="background-color:#FFF">
				<c:forEach items="${customerList }" var="customer"> 
					<tr class="Customer">
						<td>
						<a style="cursor:pointer;" onclick="chooseCustomer(this)">
							<span>${customer.customerCode }</span>
							<span>${customer.customerName }</span>
						</a>
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</td>
		<td class="title1">创建用户</td>
		<td>
			<span id="userAccount">${user.account }</span>
			<input name="userAccount" type="text" value="${user.name }" readonly/><span style="color:red;">*</span>
		</td>
		<td class="title1">附加费用</td>
		<td>
			<input name="tipFee" type="number" value="0" onkeyup="tipFee()"/>
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
			<select id="chooseType" onchange="selectType()">
				<option value="货到付款">货到付款</option>
				<option value="款到发货">款到发货</option>
				<option value="预付款到发货">预付款到发货</option>
			</select>
		</td>
		<td class="title1">备注</td>
		<td>
			<input name="soRemark" type="text"/><span style="color:red;">*</span>
		</td>
		<td class="title1">最低预付款金额</td>
		<td width="15px">
			<input name="prePayFee" type="number" value="0"/>
		</td>
		
	</tr>
</table>

<table width="100%"  border="0" cellspacing="1" id="soitemTab">
	<tr>
	    <th class="toolbar">&nbsp;</th>
	    <th class="toolbar"> 商品编码 </th>
	    <th class="toolbar"> 商品名称 </th>
	    <th class="toolbar"> 产品数量 </th>
	    <th class="toolbar">数量单位</th>
	    <th class="toolbar">销售单价</th>
	    <th class="toolbar">销售明细总价</th>
	    <th class="toolbar">&nbsp;</th>
	</tr>
</table>
<p id="soHint" style="color:red;text-align:center;"></p>

<div align="center" id="button">
    <input type="button" id="mx" value="增加明细" onclick="addItem()"/>
    <input type="submit" id="saveSomain" value="保存"/>
    <input type="button" id="dy" value="打印" onclick="window.print()"/>
</div>
<p style="text-align:center;"><a href="/SCM/storeCan/SomainShowServlet_c">返回新增销售单主界面</a></p>
</div>

<div id="soItem">
	
	<table style="width:100%;text-align:center;">
	 	<tr>
	 		<td>请输入产品编号：<input name="selectProductCode" type="text"/></td>
	 		<td>请输入产品名称：<input name="selectProductName" type="text"/></td>
	 		<td>请选择分类名
				<select id="selectCategoryName">
					<option></option>
		   		</select>
			</td>
	 	</tr>
	 </table>
	
	<table style="width:100%;text-align:center;" id="productTab">
		<tr>
			<td class="title1">选择</td>
			<td class="title1">产品编号</td>
			<td class="title1">产品名称</td>
			<td class="title1">数量单位</td>
			<td class="title1">数量</td>
			<td class="title1">单价</td>
		</tr>
		<c:forEach items="${productList }" var="product">
		<tr>
			<td><input name="chooseProduct" type="radio" /></td>
			<td>${product.productCode }</td>
			<td>${product.productName }</td>
			<td>${product.unitName }</td>
			<td><input type="number" value="1"/></td>
			<td>${product.price }</td>
		</tr>
		</c:forEach>
	</table>
	<p id="productHint" style="color:red;text-align:center;"></p>
	<p style="text-align:center;">
		<input type="button" value="确定" onclick="queding()" id="queding"/>
		<input type="button" value="取消" onclick="cancel()"/>
	</p>
</div>


</body>
</html>