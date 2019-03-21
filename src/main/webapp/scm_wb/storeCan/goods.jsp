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
<script type="text/javascript" src="/SCM/script/jsp_c.js"></script>
</head>
<body>

<div id="m">
<table width="100%" border="0" cellpadding="0" cellspacing="0" id="m">
  <tr>
    <td nowrap class="title1">您的位置：工作台面－－提货管理</td>
  </tr>
</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30px" nowrap class="toolbar">&nbsp;</td>
    <td width="60px" nowrap class="toolbar">
    	<span style="cursor:pointer;" onclick="addProduct()">
    		<img src="/SCM/images/new.gif">新增
    	</span>
    </td>
    <td width="10px" nowrap class="toolbar">|</td>
    <td width="60px" nowrap class="toolbar">
    	<span style="cursor:pointer;"  onclick="selectProduct()">
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


<div class="hidd" id="productSelect">
	 <table>
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
</div>


<table width="100%"  border="0" align="center" cellspacing="1" class="c" id="showProduct">
  <tr>
    <td class="title1">产品编号</td>
    <td class="title1">产品名称</td>
	<td class="title1">数量单位</td>
    <td class="title1">分类名称</td>
    <td class="title1">添加日期</td>
    <td class="title1">销售价</td>
    <td class="title1">产品描述</td>
    <td class="title1">操作</td>
  </tr>
  <c:forEach items="${productList }" var="product">
	   <tr>
	    <td align="center">${product.productCode }</td>
	    <td align="center">${product.productName }</td>
		<td align="center">${product.unitName }</td>
	    <td align="center">${product.categoryName }</td>
	    <td align="center">${product.createDate }</td>
	    <td align="center">${product.price }</td>
	    <td align="center">${product.remark }</td>
	    <td align="center">
	    	<a style="cursor:pointer;" onclick="updateProduct(this)">修改</a>
			<a style="cursor:pointer;" onclick="deleteProduct(this)">删除</a>
		</td>
		</tr>
	</c:forEach>
</table>
<p id="back" style="text-align:center;"><a href="/SCM/storeCan/GoodsServlet_c">返回产品管理主页面</a></p>
<div id="paging" style="text-align:center;background-color:#DBEAF5;">
	<input id="firstPage" type="button" value="首页" onclick="firstPage()"/>
	<input id="prePage" type="button" value="上一页" onclick="previousPage()"/>
	<input id="nextPage" type="button" value="下一页" onclick="nextPage()"/>
	<span style="padding-right:20px">第<span id="nowPage">1</span>页</span>
	<span id="showSkip" style="padding-right:20px">
		跳转到
		<input name="skipPage" type="number"/>
		<input type="button" value="跳转" onclick="skip()"/>
	</span>
	<input id="lastPage" type="button" value="尾页" onclick="lastPage()"/>
	<span style="padding-right:20px">共<span id="allPages">${pages }</span>页</span>
</div>
<p id="deleteGoodsHint" style="color:red;text-align:center;font-size:20px;">${error }</p>


</div>
<div id="productCtrl" class="hidd">
<table width="100%"  border="0" align="center" cellspacing="1" class="c">
 	<tr>
	   <td align="center" width="40%">产品编号</td>
	   <td>
		   	<input name="productCode" type="text" onkeyup="productCodeChange()" style="padding-left:20%;"/>
		   	<span style="color:red">*</span>
		   	<span id="productCodeHint" style="cloor:red;"></span>
	   </td>
	</tr>
	<tr>
	   <td align="center">产品名称</td>
	   <td>
		   	<input name="productName" type="text" style="padding-left:20%;"/>
		   	<span style="color:red">*</span>
		   	<span id="productNameHint" style="cloor:red;"></span>
	   </td>
	</tr>
	<tr>
	   <td align="center">产品分类</td>
	   <td>
			<select id="categoryName">
		   	</select>
	   </td>
	</tr>
	<tr>
	   <td align="center">销售价</td>
	   <td>
	  		<input name="price" type="number" value="0" />
	  		<span id="productPriceHint"></span>
	   </td>
	</tr>
	<tr>
	   <td align="center">数量单位</td>
	   <td>
	   		<input name="unitName" type="text" style="padding-left:20%;"/>
	   		<span style="color:red">*</span>
		   	<span id="productUnitNameHint" style="cloor:red;"></span>
	   </td>
	</tr>
	<tr>
	   <td align="center">添加日期</td>
	   <td>
	   		<input name="createDate" type="text" style="padding-left:20%;"/>
	   </td>
	</tr>
	<tr>
	   <td align="center">描述</td>
	   <td>
	   		<input name="produatRemark" type="text" style="padding-left:20%;"/>
	   </td>
	</tr>
	<tr>
	   <td align="center" colspan="2">
	   	<input id="saveProduct" type="button" value="保存"/>
	   </td>
	</tr>
	
</table>
<p style="text-align:center;"><a href="/SCM/storeCan/GoodsServlet_c">返回产品管理主页面</a></p>
</div>

</body>
</html>