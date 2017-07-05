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
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3">用户的附加信息如下</span>： </span>
				</div></td>
			</tr>
	</table>
	
	
	<!-- show content -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">关注个数上限：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				<form name="myform" method="post" action="updateOtherInfo">
					<span class="STYLE1">
						<input type="number" name="attentionLimitNum" value="${requestScope.userOtherInfo.attentionLimitNum}" />
						<input type="hidden" name="userid" value="${requestScope.userid}" />
						<c:if test="${sessionScope.admin.level == 1}"><input type="submit" value="更新"/></c:if>
					</span>
					</form>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">暗恋个数上限：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				<form name="myform" method="post" action="updateOtherInfo">
					<span class="STYLE1">
						<input type="number" name="loveLimitNum" value="${requestScope.userOtherInfo.loveLimitNum}" />
						<input type="hidden" name="userid" value="${requestScope.userid}" />
						<c:if test="${sessionScope.admin.level == 1}"><input type="submit" value="更新"/></c:if>
					</span>
					</form>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">标记个数上限：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				<form name="myform" method="post" action="updateOtherInfo">
					<span class="STYLE1">
						<input type="number" name="markLimitNum" value="${requestScope.userOtherInfo.markLimitNum}" />
						<input type="hidden" name="userid" value="${requestScope.userid}" />
						<c:if test="${sessionScope.admin.level == 1}"><input type="submit" value="更新"/></c:if>
					</span>
					</form>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">好友个数上限：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				<form name="myform" method="post" action="updateOtherInfo">
					<span class="STYLE1">
						<input type="number" name="friendLimitNum" value="${requestScope.userOtherInfo.friendLimitNum}" />
						<input type="hidden" name="userid" value="${requestScope.userid}" />
						<c:if test="${sessionScope.admin.level == 1}"><input type="submit" value="更新"/></c:if>
					</span>
					</form>
				</div></td>
		</tr>

		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">创建小组的上限：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				<form name="myform" method="post" action="updateOtherInfo">
					<span class="STYLE1">
						<input type="number" name="createTeamLimitNum" value="${requestScope.userOtherInfo.createTeamLimitNum}" />
						<input type="hidden" name="userid" value="${requestScope.userid}" />
						<c:if test="${sessionScope.admin.level == 1}"><input type="submit" value="更新"/></c:if>
					</span>
					</form>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">参加小组的上限：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				<form name="myform" method="post" action="updateOtherInfo">
					<span class="STYLE1">
						<input type="number" name="attenTeamLimitNum" value="${requestScope.userOtherInfo.attenTeamLimitNum}" />
						<input type="hidden" name="userid" value="${requestScope.userid}" />
						<c:if test="${sessionScope.admin.level == 1}"><input type="submit" value="更新"/></c:if>
					</span>
					</form>
				</div></td>
		</tr>	
		
	<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">上一次取消暗恋的时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
					<span class="STYLE1">
						<jsp:useBean id="myDate" class="java.util.Date"/> 
						<c:set target="${myDate}" property="time" value="${requestScope.userOtherInfo.lastLoveTime}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
					</span>

				</div>
			</td>
		</tr>
			
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">暗恋的冷冻时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
					<span class="STYLE1">
					${requestScope.userOtherInfo.sleepLoveTime}
					</span>
				</div></td>
		</tr>
		
	<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">发表日志数：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				
					<span class="STYLE1">
					${requestScope.userOtherInfo.diaryCount}
					</span>
				</div></td>
		</tr>	

	<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">产生新鲜事数：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				
					<span class="STYLE1">
					${requestScope.userOtherInfo.freshNewsCount}
					</span>
				</div></td>
		</tr>
		
	<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">书写记忆的个数：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				
					<span class="STYLE1">
					${requestScope.userOtherInfo.meetMemoryCount}
					</span>
				</div></td>
		</tr>
		
	<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">发出的帮帮个数：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
				
					<span class="STYLE1">
					${requestScope.userOtherInfo.bangbangCount}
					</span>
				</div></td>
		</tr>
		
	<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="right">
					<span class="STYLE1">上一篇日志时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
					<span class="STYLE1">
						<c:set target="${myDate}" property="time" value="${requestScope.userOtherInfo.diarySerial}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
					</span>
				</div></td>
		</tr>	
				
	</table>	
	
</center>	
</body>
</html>
