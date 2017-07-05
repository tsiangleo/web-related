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
	if (myform.oldPwd.value=="") 
	{ 
		alert("原始密码不能为空！"); myform.oldPwd.focus(); return false;
	}
	
	if (myform.newPwd.value=="") 
	{ 
		alert("新密码不能为空！"); myform.newPwd.focus(); return false;
	}
	
	if (myform.reNewPwd.value=="") 
	{ 
		alert("再次确认密码不能为空！"); myform.reNewPwd.focus(); return false;
	}
	
	if (myform.oldPwd.value==myform.newPwd.value) 
	{ 
		alert("新密码和原始密码不能相同！"); myform.newPwd.focus(); return false;
	}
	
	if (myform.reNewPwd.value!=myform.newPwd.value) 
	{ 
		alert("两次输入的新密码不相同，请重新输入！"); myform.reNewPwd.focus(); return false;
	}
}

</script>

<!-- 显示提示信息 -->
<s:if test="#request.tip != null">
	<script type="text/javascript">alert("<s:property value="#request.tip" />");</script>
</s:if>

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
					<span class="STYLE3">你当前的位置</span>：[系统管理]-[修改个人登陆密码] </span>
				</div></td>
			</tr>
	</table>
	
	<form name="myform" onsubmit="return check()" method="post" action="changePwd">
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">请输入原始密码:</span>
				</div></td>

				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						<input type="password"  name="oldPwd" id="oldPwd"/>
  						</span>
					</div>
				</td>
				
				
			</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">请输入新密码:</span>
				</div></td>

				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						<input type="password"  name="newPwd" id="newPwd"/>
  						</span>
					</div>
				</td>
			</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">请再次确认新密码:</span>
				</div></td>

				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						<input type="password"  name="reNewPwd" id="reNewPwd"/>
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
					 <input type="submit"id="button" value="提交" />
					</span>
				</div></td>
		</tr>
	</table>
	</form>
	
</center>	
</body>
</html>