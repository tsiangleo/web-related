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
					<span class="STYLE3">你当前的位置</span>：[内容管理]-[日志管理]-[日志详细信息] </span>
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
					<span class="STYLE1">${requestScope.diary.userName}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">日志内容：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diary.content}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">发表日志的地址：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diary.address}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">日志ID：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diary.diaryId}</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">发表日志的时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<jsp:useBean id="myDate" class="java.util.Date"/> 
						<c:set target="${myDate}" property="time" value="${requestScope.diary.time}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
					</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">日志的类型：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:choose> 
						  <c:when test="${requestScope.diary.type == 0}">图片</c:when> 
						  <c:when test="${requestScope.diary.type == 1}">文本</c:when> 
						  <c:when test="${requestScope.diary.type == 2}">链接</c:when> 
						  <c:when test="${requestScope.diary.type == 2}">贴纸</c:when> 
						  <c:otherwise>未知类型</c:otherwise> 
						</c:choose>
					</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">日志属性：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:choose> 
						  <c:when test="${requestScope.diary.property == 0}">私有</c:when> 
						  <c:when test="${requestScope.diary.property == 1}">公开</c:when> 
						  <c:when test="${requestScope.diary.property == 2}">所有人</c:when> 
						  <c:otherwise>未知类型</c:otherwise> 
						</c:choose>
					</span>
				</div></td>
		</tr>
		
		<!-- show  picture -->
		<c:if test="${requestScope.diary.type == 0 && requestScope.diary.picUrl != null }">
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">日志图片：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
					<img id="blogpic" src='${requestScope.diary.picUrl}' width="250" height="250" alt="日志图片" />
					</span>
				</div></td>
		</tr>
		</c:if>
									
	</table>	
	
	<!-- operation area -->
	<c:if test="${sessionScope.admin.level == 1}">
	<br/><br/>
	<!-- navigation table -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF">
				<div align="center">
					<span class="STYLE1">
					<span class="STYLE3">删除日志</span>
					 </span>
				</div>
			</td>
		</tr>
	</table>
	
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="center">
						<span class="STYLE3">
 							 <a onclick="return confirm('确定要删除该日志吗？')" 
 							 href="delete?userid=${requestScope.diary.userId}&diaryid=${requestScope.diary.diaryId}" >
 							 删除日志</a>
						</span>
					</div>
				</td>
			</tr>
	</table>
	<br/><br/>
	
	<c:if test="${requestScope.diary.userId > 100000 && requestScope.diary.userId < 101000}">
	 	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF">
				<div align="center">
					<span class="STYLE1">
					<span class="STYLE3">推送日志</span>
					 </span>
				</div>
			</td>
		</tr>
	</table>
	 	
	 	<form id="form1" name="form1" method="post" action="pushDiary">
		<table width="100%" border="0" cellpadding="0" cellspacing="1"
			bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
			
			
			<tr>
				<td width="10%" height="22"
					bgcolor="#FFFFFF"><div align="center">
						<span class="STYLE1">是否公开：</span>
					</div>
				<input type="hidden" name="diaryid" value="${requestScope.diary.diaryId}" />
				<input type="hidden" name="userid" value="${requestScope.diary.userId}" />	
				</td>
				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						 <select name="pushDiaryType" id="pushDiaryType">
							<option value="0" selected="selected">推送给内部成员</option>
							<option value="1">推送给所有成员</option>
	     				 </select>
						</span>
					</div></td>
			</tr>
			<tr>
				<td width="10%" height="22"
					bgcolor="#FFFFFF"><div align="center">
						<span class="STYLE1">密码：</span>
					</div></td>
	
				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						 <input type="password" name="pushDiaryPwd" id="pushDiaryPwd"></input>
						</span>
					</div></td>
			</tr>
			<tr>
				<td width="10%" height="22"
					bgcolor="#FFFFFF"><div align="center">
						<span class="STYLE1">内容：</span>
					</div></td>
	
				<td width="15%" height="22"
					bgcolor="#FFFFFF"><div align="left">
						<span class="STYLE1">
						 <textarea name="pushDiaryDesc" id="pushDiaryDesc" cols="45" rows="5"></textarea>
						</span>
					</div></td>
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

	</c:if>
	
	</c:if>
 	
</center>	
</body>
</html>
