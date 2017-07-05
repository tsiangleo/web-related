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
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF"><div align="left">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3">你当前的位置</span>：[反馈管理]-[帮帮举报]-[举报帮帮详细信息] </span>
				</div></td>
			</tr>
	</table>
	
	<!-- show content -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">用户昵称：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.bangBang.userName}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">帮帮内容：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.bangBang.content}</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">帮帮ID：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.bangBang.bangBangId}</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">帮帮开始时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<jsp:useBean id="myDate" class="java.util.Date"/> 
						<c:set target="${myDate}" property="time" value="${requestScope.bangBang.startTime}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
				   </span>
				</div></td>
		</tr>	
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">帮帮结束时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:set target="${myDate}" property="time" value="${requestScope.bangBang.endTime}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
				   </span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">帮帮是否有效：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:choose> 
						  <c:when test="${requestScope.bangBang.isActive == true}">是</c:when> 
						  <c:when test="${requestScope.bangBang.isActive == false}">否</c:when> 
						</c:choose> 
					</span>
				</div></td>
		</tr>
		
		<!-- show  picture -->
		<c:if test="${requestScope.bangBang.picUrl != null}">
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">帮帮图片：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
					<img id="blogpic" src='${requestScope.bangBang.picUrl}' width="250" height="250" alt="帮帮图片" />
					</span>
				</div></td>
		</tr>
		</c:if>			
	</table>	
	
	
	<!-- operation area -->
	<!-- navigation table -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3">请选择以下操作</span> </span>
				</div></td>
			</tr>
	</table>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="center">
						<span class="STYLE3">
						
						<c:if test="${sessionScope.admin.level == 1}">
				            <a href="delete?reportBangBangid=${requestScope.reportBangBangid}&deleteTime=${requestScope.deleteTime}" 
				            target="Iqq" onclick="return confirm('确定要【仅删除本条举报记录】吗？')">仅删除本条举报记录</a> &nbsp; &nbsp;
			            </c:if>
						
						<c:if test="${sessionScope.admin.level == 1}">
						<a href="deleteBoth?userid=${requestScope.bangBang.userId}&bangBangid=${requestScope.bangBang.bangBangId}&deleteTime=${requestScope.deleteTime}&reportBangBangid=${requestScope.reportBangBangid}"
 							onclick="return confirm('确定要【同时删除本条举报记录和对应的帮帮】吗？')">
 							同时删除本条举报记录和对应的帮帮
 						</a> 
						</c:if>
							 
						&nbsp;&nbsp;   
						<a href="${pageContext.request.contextPath}/user/query?userid=${requestScope.bangBang.userId}">
							查看被举报用户信息
						</a>
							 
						</span>
					</div>
				</td>
			</tr>
	</table>
 	
</center>	
</body>
</html>
