<%@ page language="java" import="java.util.* ,java.util.Date, java.text.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//System.out.println(basePath);
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
	}
	.STYLE1 {
		font-size: 12px;
		color: #FFFFFF;
	}
	.STYLE2 {font-size: 9px}
	.STYLE3 {
		color: #033d61;
		font-size: 12px;
	}
	-->
	</style>
  </head>
  
  <body>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="70" background="${pageContext.request.contextPath}/style/images/main_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td height="24"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="270" height="24" background="${pageContext.request.contextPath}/style/images/main_03.gif">&nbsp;</td>
	            <td width="505" background="${pageContext.request.contextPath}/style/images/main_04.gif">&nbsp;</td>
	            <td>&nbsp;</td>
	            <td width="21"><img src="${pageContext.request.contextPath}/style/images/main_07.gif" width="21" height="24"></td>
	          </tr>
	        </table></td>
	      </tr>
	      <tr>
	        <td height="38"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="270" height="38" background="${pageContext.request.contextPath}/style/images/main_09.gif">&nbsp;</td>
	            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="77%" height="25" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
                    <td width="50" height="19"><div align="center"><a href="home/index" onclick="script:window.parent.frames.location.reload();"><img src="${pageContext.request.contextPath}/style/images/main_12.gif" border="0" width="49" height="19"></a></div></td>
                    <td width="50"><div align="center"><a href="#" onclick="script:goback();"><img src="${pageContext.request.contextPath}/style/images/main_14.gif" border="0" width="48" height="19"></a></div></td>
                    <td width="50"><div align="center"><a href="#" onclick="script:gofront();"><img src="${pageContext.request.contextPath}/style/images/main_16.gif" border="0" width="48" height="19" ></a></div></td>
                    <td width="50"><div align="center"><a href="#" onclick="window.parent.frames.location.reload();"><img src="${pageContext.request.contextPath}/style/images/main_18.gif" border="0" width="48" height="19"></a></div></td>
                    <td width="50"><div align="center"><a target="_top" href="admin/logout" onclick="return confirm('确定要退出吗？')"><img src="${pageContext.request.contextPath}/style/images/main_20.gif" border="0" width="48" height="19"></a></div></td>
                    <td width="26"><div align="center"><img src="${pageContext.request.contextPath}/style/images/main_21.gif" width="26" height="19"></div></td>
                    <td width="100"><div align="center"><img src="${pageContext.request.contextPath}/style/images/main_22.gif" width="98" height="19"></div></td>
                    <td>&nbsp;</td>
	                  </tr>
	                </table></td>
	                <td width="220" valign="bottom"  nowrap="nowrap"><div align="right"><span class="STYLE1">
	                <span class="STYLE2"> </span>
	         
	                 welcome : ${sessionScope.admin.loginName}
	                
	                <span class="STYLE2">■</span>
	                <%
	                	SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd EEEE");
						Date date = new Date();  
						String strToday = bartDateFormat.format(date).toString(); 
						
						out.print(strToday);
	               %>
	                </span></div></td>
	              </tr>
	            </table></td>
	            <td width="21"><img src="${pageContext.request.contextPath}/style/images/main_11.gif" width="21" height="38"></td>
	          </tr>
	        </table></td>
	      </tr>
	      <tr>
	        <td height="8" style=" line-height:8px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="270" background="${pageContext.request.contextPath}/style/images/main_29.gif" style=" line-height:8px;">&nbsp;</td>
	            <td width="505" background="${pageContext.request.contextPath}/style/images/main_30.gif" style=" line-height:8px;">&nbsp;</td>
	            <td style=" line-height:8px;">&nbsp;</td>
	            <td width="21" style=" line-height:8px;"><img src="${pageContext.request.contextPath}/style/images/main_31.gif" width="21" height="8"></td>
	          </tr>
	        </table></td>
	      </tr>
	    </table></td>
	  </tr>
	  <tr>
	    <td height="28" background="${pageContext.request.contextPath}/style/images/main_36.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="177" height="28" background="${pageContext.request.contextPath}/style/images/main_32.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td width="20%"  height="22">&nbsp;</td>
	            <td width="59%" valign="bottom"><div align="center" class="STYLE1">当前用户:${sessionScope.admin.loginName}</div></td>
	            <td width="21%">&nbsp;</td>
	          </tr>
	        </table></td>
	        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            
	          </tr>
	        </table></td>
	        <td width="21"><img src="${pageContext.request.contextPath}/style/images/main_37.gif" width="21" height="28"></td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
<script type="text/javascript">
var ct=5;
function goback(){
//	var count=window.parent.frames['mainFrame'].history.length;
//	alert(count);
	var page=window.parent.frames['mainFrame'].frames['I2'].frames['Iqq'].location.href;
//	alert(i);
//	alert(ct);
	var actionpage="<%=basePath%>"+"ShowFirstComplainBlogAction";
	if(page!=actionpage){
//		alert(actionpage);
//		alert(window.parent.frames['mainFrame'].frames['I2'].frames['Iqq'].location);
//		window.parent.frames['mainFrame'].frames['I2'].history.back();
		window.location.reload();
		window.parent.frames['mainFrame'].frames['I2'].frames['Iqq'].history.back();
//		ct=count;
	}else
		window.location.reload();
}

function gofront(){




		window.location.reload();
		window.parent.frames['mainFrame'].history.forward();
}
</script>
  </body>
</html>
