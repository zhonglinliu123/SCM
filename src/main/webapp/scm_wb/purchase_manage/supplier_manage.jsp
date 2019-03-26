<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>供应商管理</title>
			<link href="/SCM/scm_wb/css/style.css" rel="stylesheet" type="text/css">
			<script src="/SCM/scm_wb/script/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
			<script type="text/javascript" src="/SCM/scm_wb/script/productDiv.js" ></script>
		 	<script src="/SCM/scm_wb/purchase_manage/js/supplier_manage.js" type="text/javascript" charset="UTF-8"></script>
	</head>
	
	<body>
		<div id="m">
			<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
		  		<tr>
				    <td nowrap class="title1">你的位置：工作台面-供应商管理</td>
			  	</tr>
			</table>
			<div style="width:100%;height:25px;background-color:#8BBFE5;">
				<div style="float:left;margin-top:5px;margin-left:10px;cursor:pointer;" onclick="add()">
					<img src="/SCM/scm_wb/images/new.gif">新增
				</div>
				<div style="float:left;margin-top:5px;margin-left:10px;cursor:pointer;" onclick="query()">
					<img src="/SCM/scm_wb/images/search.gif">查询
				</div>
				<div style="float:left;margin-top:5px;margin-left:20px;">
					供应商编号<input type="text" id="search_vender_code" style="width:120px;"/>
					供应商名称<input type="text" id="search_vender_name" style="width:120px;"/>
				</div>	
			</div>
			<table width="100%"  border="0" align="center" cellspacing="1" class="c">
			  <tr>
			    <td class="title1">供应商编号</td>
			    <td class="title1">供应商名称</td>
			    <td class="title1">联系人</td>
				<td class="title1">地址</td>
			    <td class="title1">邮政编码</td>
			    <td class="title1">注册日期</td>
			    <td class="title1">电话</td>
			    <td class="title1">传真</td>
			    <td class="title1">操作</td>
			  </tr>
			  <c:forEach items="${sessionScope.allvenderlist }" var="vender" >
		  		<tr>
		  			<td style="text-align:center;">${vender.vendercode}</td>
		  			<td style="text-align:center;">${vender.name}</td>
		  			<td style="text-align:center;"> ${vender.contactor}</td>
		  			<td style="text-align:center;"> ${vender.address}</td>
		  			<td style="text-align:center;"> ${vender.postcode}</td>
		  			<td style="text-align:center;"> ${vender.createdate}</td>
		  			<td style="text-align:center;"> ${vender.tel}</td>
		  			<td style="text-align:center;"> ${vender.fax}</td>
				    <td style="text-align:center;">
					    <button onclick="venderupdate(this)">修改</button>
					    <button onclick="venderdelete(this)">删除</button>
				    </td>	
		  		</tr>
		  	  </c:forEach>
			</table>
		</div>
		
		<div id="add" class="hidd">
			<table width="100%"  border="0" align="center" cellspacing="1" class="c">
		    	 <tr>
				    <td align="center">供应商账号</td>
				    <td align="center"><input id="code" type="text"/></td>
				 </tr>
				 <tr>
		    		<td align="center">供应商名称</td>
		            <td align="center"><input id="name" type="text"/></td>
			     </tr>
			     <tr>
		    		<td align="center">供应商密码</td>
		            <td align="center"><input id="password" type="password"/></td>
			     </tr>
				 <tr>
					<td align="center">联系人</td>
		            <td align="center"><input id="contactor" type="text"/></td>
				 </tr>
				 <tr>
					<td align="center">地址</td>
		            <td align="center"><input id="address" type="text"/></td>
				 </tr>
				 <tr>
					<td align="center">邮政编号</td>
		            <td align="center"><input id="postcode" type="text"/></td>
				 </tr>
				 <tr>
					<td align="center">电话</td>
		            <td align="center"><input id="tel" type="text"/></td>
				 </tr>
				 <tr>
					<td align="center">传真</td>
		            <td align="center"><input id="fax" type="text"/></td>
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
		
		<div id="delete_div" class="hidd" style="background-color:#8BBFE5;height:170px;width:200px;padding-top:30px;position:absolute; top:60px; left:150px;">
			<div style="margin-left:50px;">
				确定删除?
			</div>
			<div type="hidden" id="delete_code"></div>
			<button onclick="qd_delete()" style="margin-left:50px;margin-top:30px;">确定</button>
			<button onclick="qx_delete()" style="margin-left:20px;">取消</button>
		</div>
		
		<div id="not_delete_div" class="hidd" style="background-color:#8BBFE5;height:170px;width:200px;padding-top:30px;position:absolute; top:60px; left:150px;">
			<h1>有其他相关的依赖存在，不能删除</h1>
			<button onclick="canot_delete()" style="margin-left:50px;margin-top:30px;">确定</button>
		</div>
		<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src=/SCM/scm_wb/common/calendar/ipopeng.htm scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
	</body>
</html>
