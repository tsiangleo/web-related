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
					<span class="STYLE3">你当前的位置</span>：[内容管理]-[店铺审核管理]-[待审核店铺详细信息] </span>
				</div></td>
			</tr>
	</table>
	
	<!-- show content -->
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">商家id：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.id}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">店铺名称：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.shopName}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">店铺描述：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.shopDesc}</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">店铺类型：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.shopType}</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">店铺电话：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.shopPhone}</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">店铺地址：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.shopAddr}</span>
				</div></td>
		</tr>	
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">商店是否被认证的状态：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.status}</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">商店的标语：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.slogan}</span>
				</div></td>
		</tr>
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">店铺的标签：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">${requestScope.shopInfoPending.shopTags}</span>
				</div></td>
		</tr>
				
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">商店创建的时间：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">
						<jsp:useBean id="myDate" class="java.util.Date"/> 
						<c:set target="${myDate}" property="time" value="${requestScope.shopInfoPending.time}"/> 
						<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
					</span>
				</div></td>
		</tr>
		
		
		<c:if test="${not empty requestScope.shopInfoPending.shopLogo}">
			<c:forEach items="${requestScope.shopInfoPending.shopLogo}" var="oneEntity" varStatus="status">
				<c:if test="${oneEntity != null}">
					<tr>
						<td width="10%" height="22"
							bgcolor="#FFFFFF"><div align="center">
								<span class="STYLE1">店铺商标${status.index + 1}：</span>
							</div></td>
			
						<td width="15%" height="22"
							bgcolor="#FFFFFF"><div align="center">
								<span class="STYLE1">
								 <img id="blogpic" src='${oneEntity}' width="150" height="150" alt="店铺商标" />
								</span>
							</div></td>
					</tr>		
				</c:if>	
			</c:forEach>
		</c:if>	
		
		<c:if test="${not empty requestScope.shopInfoPending.shopLicense}">
			<c:forEach items="${requestScope.shopInfoPending.shopLicense}" var="oneEntity" varStatus="status">
				<c:if test="${oneEntity != null}">
					<tr>
						<td width="10%" height="22"
							bgcolor="#FFFFFF"><div align="center">
								<span class="STYLE1">店铺营业执照${status.index + 1}：</span>
							</div></td>
			
						<td width="15%" height="22"
							bgcolor="#FFFFFF"><div align="center">
								<span class="STYLE1">
								 <img id="blogpic" src='${oneEntity}' width="150" height="150" alt="店铺营业执照" />
								</span>
							</div></td>
					</tr>		
				</c:if>	
			</c:forEach>
		</c:if>	
       
 		<c:if test="${not empty requestScope.shopInfoPending.idCard}">
			<c:forEach items="${requestScope.shopInfoPending.idCard}" var="oneEntity" varStatus="status">
				<c:if test="${oneEntity != null}">
					<tr>
						<td width="10%" height="22"
							bgcolor="#FFFFFF"><div align="center">
								<span class="STYLE1">个人身份证${status.index + 1}：</span>
							</div></td>
			
						<td width="15%" height="22"
							bgcolor="#FFFFFF"><div align="center">
								<span class="STYLE1">
								 <img id="blogpic" src='${oneEntity}' width="150" height="150" alt="个人身份证" />
								</span>
							</div></td>
					</tr>		
				</c:if>	
			</c:forEach>
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
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="STYLE3">请选择以下操作</span> </span>
				</div></td>
			</tr>
	</table>
	
	<form id="form1" name="form1" method="post" action="audit">
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">
		
		<input type="hidden" name="id" value="${requestScope.shopInfoPending.id}" />
		
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">是否通过：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
					<span class="STYLE1">
					 <select name="pass" id="pass">
						<option value="1" selected="selected">通过</option>
						<option value="2">不通过</option>
     				 </select>
					</span>
				</div></td>
		</tr>
		<tr>
			<td width="10%" height="22"
				bgcolor="#FFFFFF"><div align="center">
					<span class="STYLE1">理由：</span>
				</div></td>

			<td width="15%" height="22"
				bgcolor="#FFFFFF"><div align="left">
					<span class="STYLE1">
					 <textarea name="reason" id="reason" cols="45" rows="5"></textarea>
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
	
</center>	
</body>
</html>