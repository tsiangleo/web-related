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

</head>

<body>
<center>
 	
	<!-- navigation table -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3">用户的详细信息如下</span>： </span>
				</div></td>
			</tr>
	</table>
	
	
	<!-- show content -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">用户ID：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.userId}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">登陆名：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.loginName}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">昵称：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.nickName}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">性别：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:choose> 
						  <c:when test="${requestScope.user.sex == 1}">男</c:when> 
						  <c:when test="${requestScope.user.sex == 0}">女</c:when> 
						</c:choose> 
					</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">签名档：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.status}</span>
				</div></td>
		</tr>	
			
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">地址：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.address}</span>
				</div></td>
		</tr>	
				<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">上次登录时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<jsp:useBean id="myDate" class="java.util.Date"/> 
						<c:set target="${myDate}" property="time" value="${requestScope.user.lastLoginTime}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
					</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">上次发消息时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1"> 
						<c:set target="${myDate}" property="time" value="${requestScope.user.lastMsgTime}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
					</span>
				</div></td>
		</tr>	
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">生日：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:set target="${myDate}" property="time" value="${requestScope.user.birthday}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd" value="${myDate}" type="both"/> 
					</span>
				</div></td>
		</tr>	
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">是否在线：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:choose> 
						  <c:when test="${requestScope.user.online == true}">是</c:when> 
						  <c:when test="${requestScope.user.online == false}">否</c:when> 
						</c:choose> 
					</span>
				</div></td>
		</tr>

		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">总在线时长：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.onlineTime}</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">客户端版本号：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.apkVersion}</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">注册地址：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.registerAddress}</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">注册时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:set target="${myDate}" property="time" value="${requestScope.user.registerTime}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
					</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">使用的手机型号：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.mobileType}</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">安卓的版本号：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.user.androidSdk}</span>
				</div></td>
		</tr>		
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">账号是否处于激活状态：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:choose> 
						  <c:when test="${requestScope.user.active == true}">是</c:when> 
						  <c:when test="${requestScope.user.active == false}">否</c:when> 
						</c:choose> 
					</span>
				</div></td>
		</tr>	
		
		<c:if test="${requestScope.user.url != null}">
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">头像：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
					<img src='${requestScope.user.url}' width="100" height="100" alt="个人头像" />
					</span>
				</div></td>
		</tr>				
		</c:if>		
	</table>	
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="center">
						<span class="STYLE3">

						<a href="${pageContext.request.contextPath}/diary/query?q_username=${requestScope.user.loginName}">
						查看该用户的日志</a>
 						
 						<a href="getOtherInfo?userid=${requestScope.user.userId}">
						查看更多信息</a>
						
 						
 						<c:if test="${sessionScope.admin.level == 1}">
 							<c:if test="${requestScope.user.active == true}">
		 						<a href="noActive?loginName=${requestScope.user.loginName}"
		 						onclick="return confirm('确定要屏蔽该用户吗')">屏蔽用户</a>
	 						</c:if>
	 						
	 						<c:if test="${requestScope.user.active == false}">
		 						<a href="active?loginName=${requestScope.user.loginName}"
		 						onclick="return confirm('确定要激活该用户吗')">激活用户</a>
	 						</c:if>
 						</c:if>
						</span>
					</div>
				</td>
			</tr>
	</table>
	
</center>	
</body>
</html>
