<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>操作成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body class="STYLE3">
  
  	${requestScope.tipMsg}
  	<a href="admin/index">点击返回管理员设置首页</a>
  	 
  	
  	<!-- 显示提示信息并返回上一页
	<c:if test="${not empty requestScope.tipMsg}">
	<script type="text/javascript">alert("${requestScope.tipMsg}");javascript:history.go(-1);</script>
	</c:if>
	-->
  </body>
</html>
