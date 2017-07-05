<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.management.ManagementFactory" %>
<%@ page import="com.sun.management.OperatingSystemMXBean" %>
<%@ page import="java.util.Properties" %>
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

</head>

<body>

	
	<%
 Properties props=System.getProperties(); //系统属性
 OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

 %>
 
 <center>


	
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3">${sessionScope.admin.loginName} 您好！欢迎使用Chance后台管理系统！</span> </span>
				</div></td>
		</tr>
		
		<tr>
			<td width="15%" height="22" bgcolor="#FFFFFF">
			<div align="center">
				<span class="STYLE3">
				已用内存：<%=(osmb.getTotalPhysicalMemorySize() - osmb.getFreePhysicalMemorySize()) / 1024 / 1024 %>MB
				&nbsp;&nbsp;&nbsp;&nbsp;剩余内存：<%=osmb.getFreePhysicalMemorySize() / 1024/1024 %>MB
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最大内存：<%=osmb.getTotalPhysicalMemorySize() / 1024/1024 %>MB
				</span>
			</div>
			</td>
		</tr>	
			
	</table>
	
		<br/>
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3"> 服务器信息</span> </span>
				</div></td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">服务器的站点名称：</span>
				</div></td>

			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=request.getServerName() %></span>
				</div></td>
		</tr>
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">服务器ip：</span>
				</div></td>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=request.getLocalAddr() %></span>
				</div></td>
		</tr>
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">操作系统：</span>
				</div></td>

			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=props.getProperty("os.name") %>&nbsp;<%=props.getProperty("os.version") %></span>
				</div></td>
		</tr>
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">服务器端口：</span>
				</div></td>

			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=request.getServerPort() %></span>
				</div></td>
		</tr>
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">JDK相关信息：</span>
				</div></td>

			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=props.getProperty("java.specification.name") %>&nbsp;<%=props.getProperty("java.specification.version") %></span>
				</div></td>
		</tr>
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">JVM相关信息：</span>
				</div></td>

			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=props.getProperty("java.vm.name") %>&nbsp;<%=props.getProperty("java.vm.version") %> </span>
				</div></td>
		</tr>
	</table>


	<br/>
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3"> 本地浏览器信息</span> </span>
				</div></td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">浏览器信息：</span>
				</div></td>

			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=request.getHeader("user-agent") %></span>
				</div></td>
		</tr>
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">客户端ip：</span>
				</div></td>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=request.getRemoteAddr() %></span>
				</div></td>
		</tr>
		<tr>
			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">客户端主机名：</span>
				</div></td>

			<td width="50%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"><%=request.getRemoteHost()%></span>
				</div></td>
		</tr>
	</table>	
 	
</center>	
</body>
</html>
