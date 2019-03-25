<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
	<html>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<title>用户管理</title>
			<link href="/SCM/scm_wb/css/style.css" rel="stylesheet" type="text/css">
			<script src="/SCM/scm_wb/script/jquery-1.8.1.min.js" type="text/javascript" charset="UTF-8"></script>
			<script type="text/javascript" src="/SCM/scm_wb/script/productDiv.js" ></script>
		 	<script src="/SCM/scm_wb/system_manage/js/usermanage.js" type="text/javascript" charset="UTF-8"></script>
	</head>
	
	<body>
		<div id="m">
			<table width="100%"  border="0" cellpadding="0" cellspacing="0" id="m">
		  		<tr>
				    <td nowrap class="title1">你的位置：工作台面-用户管理</td>
			  	</tr>
			</table>
			<div style="width:100%;height:25px;background-color:#8BBFE5;">
				<div style="float:left;margin-top:5px;margin-left:10px;" onclick="add()">
					<img src="/SCM/scm_wb/images/new.gif">新增
				</div>
				<div style="float:left;margin-top:5px;margin-left:10px;" onclick="query()">
					<img src="/SCM/scm_wb/images/search.gif">查询
				</div>
				<div style="float:left;margin-top:5px;margin-left:20px;">
					账号<input type="text" id="search_account" style="width:120px;"/>
					密码<input type="text" id="search_password" style="width:120px;"/>
					名字<input type="text" id="search_name" style="width:120px;"/>
					创建日期<input type="text" id="search_createDate" style="width:120px;"/>
					权限<input type="text" id="search_status" style="width:120px;"/>
				</div>	
			</div>
			<table width="100%"  border="0" align="center" cellspacing="1" class="c">
			  <tr>
			    <td class="title1">账号</td>
			    <td class="title1">密码</td>
			    <td class="title1">名字</td>
				<td class="title1">创建日期</td>
			    <td class="title1">权限</td>
			    <td class="title1">操作</td>
			  </tr>
			  <c:forEach items="${sessionScope.alluserlist }" var="user" >
		  		<tr>
		  			<td style="text-align:center;">${user.account}</td>
		  			<td style="text-align:center;">${user.password}</td>
		  			<td style="text-align:center;"> ${user.name}</td>
		  			<td style="text-align:center;"> ${user.createDate}</td>
		  			<td style="text-align:center;"> ${user.status}</td>
				    <td style="text-align:center;">
					    <c:if test="${user.status != '管理员'}">
					    	<button onclick="userupdate(this)">修改</button>
					    	<button onclick="userdelete(this)">删除</button>
					    </c:if>
				    </td>	
		  		</tr>
		  	  </c:forEach>
			</table>
		</div>
		
		<div id="add" class="hidd">
			<table width="100%"  border="0" align="center" cellspacing="1" class="c">
		 		 <tr>
				    <td align="center">用户账号</td>
				    <td align="center"><input id="account" type="text"/></td>
				 </tr>
				 <tr>
		    		<td align="center">用户名</td>
		            <td align="center"><input id="name" type="text"/></td>
			     </tr>
			     <tr>
		    		<td align="center">密码</td>
		            <td align="center"><input id="password" type="text"/></td>
			     </tr>
				 <tr>
				    <td align="center">用户权限</td>
				    <td align="center">
				    	<select id="status">
						  <option value ="管理员">管理员</option>
						  <option value ="采购员">采购员</option>
						  <option value="仓管员">仓管员</option>
						</select>
				    </td>
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
		<iframe width=174 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src=/SCM/scm_wb/common/calendar/ipopeng.htm scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;"></iframe>
	</body>
</html>
