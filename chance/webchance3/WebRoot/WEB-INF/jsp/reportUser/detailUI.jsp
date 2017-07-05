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
					<span class="STYLE3">你当前的位置</span>：[反馈管理]-[用户举报]-[用户举报详细信息] </span>
				</div></td>
			</tr>
	</table>
	
	<!-- show content -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">举报用户昵称：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.reportUser.userName}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">举报用户id：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.reportUser.userId}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">被举报用户昵称：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.reportUser.byUserName}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">被举报用户id：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.reportUser.byUserId}</span>
				</div></td>
		</tr>		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">举报类型：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<c:choose> 
						  <c:when test="${requestScope.reportUser.type == 1}">骚扰</c:when> 
						  <c:when test="${requestScope.reportUser.type == 2}">广告</c:when> 
						  <c:when test="${requestScope.reportUser.type == 3}">色情</c:when> 
						  <c:otherwise>未知类型</c:otherwise> 
						</c:choose>
					
					</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">举报原因：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.reportUser.reason}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">举报时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<jsp:useBean id="myDate" class="java.util.Date"/> 
						<c:set target="${myDate}" property="time" value="${requestScope.reportUser.time}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
				   </span>
				</div></td>
		</tr>		
	</table>	
	
	<c:if test="${not empty requestScope.reportUser.msgs}">
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22" 
				background="${pageContext.request.contextPath}/style/images/bg.gif"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3"> 举报聊天信息</span> </span>
				</div></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="${pageContext.request.contextPath}/style/images/tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td width="5%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">序号</span></div></td>
            <td width="10%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">发送者CID</span></div></td>
            <td width="10%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">接收者CID</span></div></td>
            <td width="40%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">消息内容</span></div></td>
            <td width="15%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">消息类型</span></div></td>
            <td width="20%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">发送时间</span></div></td>
          </tr>
          
     
  	
		<c:forEach items="${requestScope.reportUser.msgs}" var="oneEntity" varStatus="status">
			<tr>
            <td height="20" bgcolor="#FFFFFF">
	            <div align="center" class="STYLE1">
	              <div align="center">${status.index + 1}</div>
	            </div>
            </td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.sendCID}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.receiveCID}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center">
            <span class="STYLE1">
           				 <c:choose> 
						  <c:when test="${oneEntity.type == 0}">${oneEntity.msgContent}</c:when> 
						  <c:when test="${oneEntity.type == 1}"><a href="${oneEntity.msgContent}" target="_blank">点击查看图片</a></c:when> 
						  <c:when test="${oneEntity.type == 2}">音频消息,待处理</c:when> 
						  <c:when test="${oneEntity.type == 3}">${oneEntity.msgContent}</c:when>
						  <c:otherwise>未知类型</c:otherwise> 
						</c:choose>
            
            </span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center">
            	<span class="STYLE1">
            			<c:choose> 
						  <c:when test="${oneEntity.type == 0}">文字消息</c:when> 
						  <c:when test="${oneEntity.type == 1}">图片消息</c:when> 
						  <c:when test="${oneEntity.type == 2}">音频消息</c:when> 
						  <c:when test="${oneEntity.type == 3}">位置消息</c:when>
						  <c:otherwise>未知类型</c:otherwise> 
						</c:choose>
            	</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">
				<c:set target="${myDate}" property="time" value="${oneEntity.sendTime}"/> 
				<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
			</span></div></td>
          </tr>

     	</c:forEach>
        </table></td>
        <td width="8" background="${pageContext.request.contextPath}/style/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table>
	</c:if>	
	
	<!-- operation area -->
	<br/>
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
				            <a href="delete?reportUserid=${requestScope.reportUser.id}&deleteTime=${requestScope.reportUser.time}" 
				            target="Iqq" onclick="return confirm('确定要【删除本条举报记录】吗？')">删除本条举报记录</a> &nbsp; &nbsp;
			            </c:if>
						
						<c:if test="${sessionScope.admin.level == 1}">
						<a href="#"
 							onclick="return confirm('确定要【屏蔽被举报用户】吗？')">
 							屏蔽被举报用户
 						</a> 
						</c:if>
							 
						&nbsp;&nbsp;   
						<a href="${pageContext.request.contextPath}/user/query?userid=${requestScope.reportUser.byUserId}">
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
