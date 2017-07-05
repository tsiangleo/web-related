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
					<span class="STYLE3">你当前的位置</span>：[内容管理]-[日志评论管理]-[日志评论详细信息] </span>
				</div></td>
			</tr>
	</table>
	

	<!-- show content -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">评论ID：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diaryComment.commentId}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">被评论的日志ID：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diaryComment.diaryId}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">被评论的日志的作者的ID：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diaryComment.byUserId}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">发评论的用户的ID：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diaryComment.userId}</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">发评论的用户的昵称：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diaryComment.userName}</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">评论内容：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.diaryComment.content}</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">评论时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<jsp:useBean id="myDate" class="java.util.Date"/> 
						<c:set target="${myDate}" property="time" value="${requestScope.diaryComment.time}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/>
					</span>
				</div></td>
		</tr>							
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
					<span class="STYLE3">删除日志评论</span>
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
 							 <a onclick="return confirm('确定要删除该日志评论吗？')" 
 							 href="delete?userid=${requestScope.diaryComment.byUserId}&diaryid=${requestScope.diaryComment.diaryId}&commentid=${requestScope.diaryComment.commentId}" >
 							 删除日志评论</a>
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
