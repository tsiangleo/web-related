<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
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
		overflow:hidden;
	}
	-->
	</style>
	
<script>
	function switchSysBar(){ 
	var locate=location.href.replace('middle.jsp','');
	var ssrc=document.all("img1").src.replace(locate,'');
	if (ssrc=="res/images/main_55.gif")
	{ 
	document.all("img1").src="${pageContext.request.contextPath}/style/images/main_55_1.gif";
	document.all("frmTitle").style.display="none" 
	} 
	else
	{ 
	document.all("img1").src="${pageContext.request.contextPath}/style/images/main_55.gif";
	document.all("frmTitle").style.display="" 
	} 
	} 
	</script>
	
  </head>
  
  <body>
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
	  <tr>
	    <td>
			 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
			  <tr>
			    <td width="171" id=frmTitle noWrap name="fmTitle" align="center" valign="top"><table width="171" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
			      <tr>
			        <td  bgcolor="#1873aa" style="width:6px;">&nbsp;</td>
			        <td width="165">
			        <iframe name="I1" height="100%" width="165" src="home/left" border="0" frameborder="0" scrolling="no"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
			        </iframe>
			        </td>
			      </tr> 
			    </table>		</td>
			    <td width="6"  style="width:6px;"valign="middle" bgcolor="1873aa" onclick=switchSysBar()><SPAN class=navPoint id=switchPoint title=关闭/打开左栏><img src="${pageContext.request.contextPath}/style/images/main_55.gif" name="img1" width=6 height=40 id=img1></SPAN></td>
			    <td width="100%" align="center" valign="top">
			    <iframe name="Iqq" height="100%" width="100%" border="0" frameborder="0" src="home/right"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
			    </iframe>
			    </td>
			  </tr>
			</table>
		</td>
	    <td width="6" bgcolor="#1873aa" style=" width:6px;">&nbsp;</td>
	  </tr>
	</table>
  </body>
</html>
