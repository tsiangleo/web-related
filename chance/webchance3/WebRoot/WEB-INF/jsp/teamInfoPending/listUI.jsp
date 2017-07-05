<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>
      
<!-- 显示提示信息-->
<c:if test="${not empty requestScope.tipMsg}">
<script type="text/javascript">alert("${requestScope.tipMsg}");</script>
</c:if>

<script>
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function checkAll(){   
   var listc = document.getElementsByName("checkList");
   if(document.getElementById("CheckAll").checked==true){
    for(var i=0;i<listc.length;i++){
     listc[i].checked=true;
    }
   }else{
    for(var i=0;i<listc.length;i++){
     listc[i].checked=false;
    }
   } 
  }
  
</script>

</head>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/style/images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="${pageContext.request.contextPath}/style/images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><div align="center"><img src="${pageContext.request.contextPath}/style/images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[内容管理]-[小组审核管理]</td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>
              
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"></div></td>
                    <td class="STYLE1"><div align="center"></div></td>
                  </tr>
                </table></td>
                
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/style/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="${pageContext.request.contextPath}/style/images/tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <!-- 
            <td width="3%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center">
            
            </div></td>
           -->
            <td width="10%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">序号</span></div></td>
            <td width="15%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">小组所有者ID</span></div></td>
            <td width="15%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">小组所有者昵称</span></div></td>
            <td width="10%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">小组ID</span></div></td>
            <td width="15%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">小组名称</span></div></td>
            <td width="10%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">小组等级</span></div></td>
            <td width="15%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">创建时间</span></div></td>
            <td width="10%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF" class="STYLE1"><div align="center">基本操作</div></td>
          </tr>
          
     
  		<c:if test="${not empty requestScope.teamInfoPendingList}">
		<c:forEach items="${requestScope.teamInfoPendingList}" var="oneEntity" varStatus="status">
			<tr>
            <td height="20" bgcolor="#FFFFFF">
	            <div align="center" class="STYLE1">
	              <div align="center">${status.index + 1}</div>
	            </div>
            </td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.ownCid}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.ownName}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.teamId}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.teamName}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.level}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">
	            <jsp:useBean id="myDate" class="java.util.Date"/> 
				<c:set target="${myDate}" property="time" value="${oneEntity.time}"/> 
				<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
			</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE4">
            <img src="${pageContext.request.contextPath}/style/images/edt.gif" width="16" height="16" />
          	<a href="detail?teamid=${oneEntity.teamId}" target="Iqq">查看</a> 
           </span></div></td>
          </tr>

     	</c:forEach>
     
	</c:if>
       
       
        </table></td>
        <td width="8" background="${pageContext.request.contextPath}/style/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
 
</table>
</body>
<script>
function sub(){
	var pagenow = document.getElementById("pageNow").value;
//	alert(pagenow);
	if(!isNaN(pagenow))
	document.frm.submit();
}
</script>
</html>
