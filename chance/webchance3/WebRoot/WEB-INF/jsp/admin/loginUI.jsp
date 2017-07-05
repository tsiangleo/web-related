<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Chance后台管理系统_用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #016aa9;
		overflow:hidden;
	}
	.STYLE1 {
		color: #000000;
		font-size: 12px;
	}
	-->
	</style>

<script language="javascript">
function checkLogin() 
{
	if (loginform.loginName.value=="") 
	{ 
		alert("用户id不能为空！"); loginform.loginName.focus(); return false;
	}
	if (loginform.userpass.value=="") 
	{ 
		alert("密码不能为空！"); loginform.userpass.focus(); return false;
	}	
}
	//在被嵌套时就刷新上级窗口
	if(window.parent != window)
	{
		window.parent.location.reload(true);
	}
	

</script>

  </head>

 <!-- 显示提示信息-->
<c:if test="${not empty param.securityMsg && param.securityMsg == 'please login first'}">
<script type="text/javascript">alert("请先登陆");</script>
</c:if>

 
 <!-- 显示提示信息-->
<c:if test="${not empty requestScope.tipMsg}">
<script type="text/javascript">alert("${requestScope.tipMsg}");</script>
</c:if>

 <body>
  
    <form name="loginform" onsubmit="return checkLogin()" action="admin/login" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" method="post">
	  <tr>
	    <td><table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td height="235" background="${pageContext.request.contextPath}/style/images/login_03.gif">&nbsp;</td>
	      </tr>
	      <tr>
	        <td height="53"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="394" height="53" background="${pageContext.request.contextPath}/style/images/login_05.gif">&nbsp; </td>
	            <td width="206" background="${pageContext.request.contextPath}/style/images/login_06.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                <td width="16%" height="25"><div align="right"><span class="STYLE1">用户</span></div></td>
	                <td width="57%" height="25"><div align="center">
	                  <input type="text" id="loginName" name="loginName" style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
	                </div></td>
	                <td width="27%" height="25">&nbsp;</td>
	              </tr>
	              <tr>
	                <td height="25"><div align="right"><span class="STYLE1">密码</span></div></td>
	                <td height="25"><div align="center">
	                  <input type="password" id="userpass" name="userpass" style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
	                </div></td>
	                
	                <td height="25">
	                <div align="left">
	                	<input type="submit"  width="49" height="18" border="0" value="登陆"/>
	               	</div>
	               	</td>
	             
	              </tr>
	            </table></td>
	            <td width="362" background="${pageContext.request.contextPath}/style/images/login_07.gif">&nbsp;</td>
	          </tr>
	        
	        </table></td>
	      </tr>
	      <tr>
	        <td height="213" background="${pageContext.request.contextPath}/style/images/login_08.gif">&nbsp;</td>
	      </tr>
	    </table></td>
	  </tr>
    </form>
  </body>
</html>
