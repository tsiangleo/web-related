<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>


<script language="javascript">
function check() 
{
	if (myform.loginName.value=="") 
	{ 
		alert("登陆名不能为空！"); myform.loginName.focus(); return false;
	}
	
	/*
	if (myform.adminName.value=="") 
	{ 
		alert("管理员姓名不能为空！"); myform.adminName.focus(); return false;
	}
	*/
	
	if (myform.password.value=="") 
	{ 
		alert("登陆密码不能为空！"); myform.password.focus(); return false;
	}	
}

</script>


</head>

<body>
<center>	

	<!-- navigation table -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF"><div align="left">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3">你当前的位置</span>：[系统管理]-[添加管理员] </span>
				</div></td>
			</tr>
	</table>
	
	<form name="myform" onsubmit="return check()"  method="post" action="add">
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">登陆名:</span>
				</div></td>

				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						<input type="text"  name="loginName" id="loginName"/>
  						</span>
					</div>
				</td>
			</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">登陆密码:</span>
				</div></td>

				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						<input type="password"  name="password" id="password"/>
  						</span>
					</div>
				</td>
			</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">管理员姓名:</span>
				</div></td>

				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						<input type="text"  name="name" id="name"/>
  						</span>
					</div>
				</td>
			</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">权限:</span>
				</div></td>

				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						<select name="level" id="level">
							<option value="0" >普通管理员</option>
							<option value="1">超级管理员</option>
      					</select>
  						</span>
					</div>
				</td>
			</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp; </span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
					<span class="STYLE1">
					 <input type="submit"id="button" value="确定" />
					</span>
				</div></td>
		</tr>
	</table>
	</form>
	
</center>	
</body>
</html>

