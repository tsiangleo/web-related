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
  
   
    function deleteAll()
  {
  	var idstime=document.getElementsByName("checkTimes");
   var checkedIdsTime=new Array();
   
   var ids=document.getElementsByName("checkList");
   var checkedIds=new Array();
   var flag=0
   for(var j=0;j<ids.length;j++)
   {
		if(ids[j].checked)
		{
			flag=1;
			break;
		}
   }
   if(flag==0) {
	   alert("未选中任何项");
	   return false;
   }
   for(var i=0;i<ids.length;i++)
   {
		if(ids[i].checked)
		{
			checkedIds.push(ids[i].value);
			checkedIdsTime.push(idstime[i].value)
		}
   }
   
   //create xmlhttpRequest object
   var xmlHttp;
   if(window.XMLHttpRequest)
   {
    	xmlHttp = new XMLHttpRequest();
    	
   }else if(window.ActiveXObject){
	   try
	   {
	   		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP.3.0");
	   }catch(e){
	   		newsstring = "对不起，您的浏览器不支持XMLHttpRequest对象！";
	   		alert(newsstring);
	   }
   
   }  
   if(xmlHttp!=null)
   {
   		xmlHttp.open("post","reportUser_deleteAll?checkList="+checkedIds+"&&timeList="+checkedIdsTime,true);
   		xmlHttp.onreadystatechange=callback;
   		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
   		xmlHttp.send(null);
   }
   
   //callback fuction
   function callback()
   {
   		if(4==xmlHttp.readyState)
   		{
   			if(200==xmlHttp.status)
   			{
   				var respText=xmlHttp.responseText;
   				alert("操作成功！");
   				window.location.href="reportUser_jump?pageNow=${requestScope.pager.currentPage}";
   			}
   		}
   }
  }
</script>

<script language="javascript">
function check() 
{
	if (myform.username.value=="") 
	{ 
		alert("用户名不能为空！"); myform.username.focus(); return false;
	}
	if (myform.diaryid.value=="") 
	{ 
		alert("日志id不能为空！"); myform.diaryid.focus(); return false;
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[内容管理]-[日志评论管理]</td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>
          <!--  
                <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center">
                       <input type="checkbox" name="checkbox62" id="CheckAll" onclick="checkAll()"/>
                    </div></td>
                    <td class="STYLE1"><div align="center">全选</div></td>
                  </tr>
                </table></td>
          
                 <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/style/images/22.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><a href="reportUser_getReportDiary">处理</a></div></td>
                  </tr>
                </table></td>
           
                 <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/style/images/33.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><a href="${pageContext.request.contextPath}/reportUser/checked/get">已处理</a></div></td>
                  </tr>
                </table></td>
           -->
           <!-- 
                <td width="52"><table width="88%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/style/images/11.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"> <c:if test="${sessionScope.admin.level == 1}"><a href="#" onclick="deleteAll()" >删除</a></c:if>
                    </div></td>
                  </tr>
                </table></td>
          -->
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/style/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  
  <!-- 第二行 -->
  <tr>
    <td height="30" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="100%" class="STYLE1">
                 
				<form name="myform" onsubmit="return check()" method="post" action="query">
						用户名 <input type="string" autofocus="true" required  name="q_username" id="username" placeholder="请输入用户名">
						日志ID<input id="diaryid" type="number" required  name="q_diaryid" id="diaryid" placeholder="请输入日志id">
							 <input type="submit" value="查询">
				</form>
				   
                </td>
              </tr>
            </table></td>
         </tr>
        </table></td>
        <!-- 这个td主要显示排序 ，靠右对齐-->
        <td align="right" class="STYLE1">
        		<form name="myform2"  method="post" action="query">
					排序方式 <select name="orderBy">
						<option value="time">默认排序</option>
						<option value="time">举报时间</option>
					</select>
				</form>
				
		</td>
        
        <td width="16"></td>
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
            <td width="5%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">序号</span></div></td>
            <td width="5%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">评论ID</span></div></td>
            <td width="13%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">评论时间</span></div></td>
            <td width="8%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">发表评论的用户ID</span></div></td>
            <td width="10%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">发表评论的用户昵称</span></div></td>
            <td width="44%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">评论内容</span></div></td>
            <td width="15%" height="22" background="${pageContext.request.contextPath}/style/images/bg.gif" bgcolor="#FFFFFF" class="STYLE1"><div align="center">基本操作</div></td>
          </tr>
          
     
  	<c:if test="${not empty requestScope.pager.dataList}">
		<c:forEach items="${requestScope.pager.dataList}" var="oneEntity" varStatus="status">
			<tr>
			<!--
            <td height="20" bgcolor="#FFFFFF">
	            <div align="center">
	            	<input type="checkbox" name="checkList" value="${oneEntity.id}" />
	            	<input type="hidden" name="checkTimes" value="${oneEntity.time}" />
	            </div>
            </td>
            -->
            <td height="20" bgcolor="#FFFFFF">
	            <div align="center" class="STYLE1">
	              <div align="center">${status.index + 1}</div>
	            </div>
            </td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.commentId}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center">
            	<span class="STYLE1">
	            	<jsp:useBean id="myDate" class="java.util.Date"/> 
					<c:set target="${myDate}" property="time" value="${oneEntity.time}"/> 
					<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${myDate}" type="both"/> 
            	</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.userId}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.userName}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${oneEntity.content}</span></div></td>
            <td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE4">
            <img src="${pageContext.request.contextPath}/style/images/edt.gif" width="16" height="16" />
          	<a href="detail?userid=${oneEntity.byUserId}&diaryid=${oneEntity.diaryId}&commentid=${oneEntity.commentId}" target="Iqq">查看</a> &nbsp; &nbsp;
      
     		</span></div></td>
          </tr>

     	</c:forEach>
     
	</c:if>
     
        </table></td>
        <td width="8" background="${pageContext.request.contextPath}/style/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="${pageContext.request.contextPath}/style/images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/style/images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4">&nbsp;&nbsp;共有 ${requestScope.pager.totalCount}条记录，当前第 ${requestScope.pager.currentPage}/${requestScope.pager.pageCount}页</td>
            <td>
            
            <table border="0" align="right" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="40">
                  <c:choose> 
					  <c:when test="${requestScope.pager.currentPage > 1}">   
					  	<a href="first?totalCount=${requestScope.pager.totalCount}&q_username=${requestScope.q_username}&q_diaryid=${requestScope.q_diaryid}">
					  	<img src="${pageContext.request.contextPath}/style/images/first.gif" border="0" width="37" height="15"/>
					  	</a> 
					  </c:when> 
					  <c:otherwise>   
					   <img src="${pageContext.request.contextPath}/style/images/_first.gif" border="0" width="43" height="15" />
					  </c:otherwise> 
				</c:choose> 
                  </td>
                  
                  <td width="45">
                  
                  <c:choose> 
					  <c:when test="${requestScope.pager.currentPage == 1 || requestScope.pager.currentPage == 0}">   
					  	<img src="${pageContext.request.contextPath}/style/images/_back.gif" border="0" width="43" height="15" />
					  </c:when> 
					  <c:when test="${requestScope.pager.currentPage > 1}">   
					  	<a href="pre?pageNo=${requestScope.pager.currentPage - 1}&totalCount=${requestScope.pager.totalCount}&q_username=${requestScope.q_username}&q_diaryid=${requestScope.q_diaryid} ">
					  	<img src="${pageContext.request.contextPath}/style/images/back.gif" border="0" width="43" height="15" />
					  	</a> 
					  </c:when> 
				</c:choose> 
                  </td>
                   
                  <td width="45">
                  <c:choose> 
					  <c:when test="${requestScope.pager.currentPage != requestScope.pager.pageCount}">   
					  	<a href="next?pageNo=${requestScope.pager.currentPage + 1}&totalCount=${requestScope.pager.totalCount}&q_username=${requestScope.q_username}&q_diaryid=${requestScope.q_diaryid}">
					  	<img src="${pageContext.request.contextPath}/style/images/next.gif" border="0" width="43" height="15" />
					  	</a>
					  </c:when> 
					  <c:otherwise>   
					  	<img src="${pageContext.request.contextPath}/style/images/_next.gif" border="0" width="43" height="15" />
					  </c:otherwise> 
				</c:choose> 
                  </td>
                  
                  <td width="40">
                  <c:choose> 
					  <c:when test="${requestScope.pager.currentPage != requestScope.pager.pageCount}">   
					  	<a href="last?totalCount=${requestScope.pager.totalCount}&pageNo=${requestScope.pager.pageCount}&q_username=${requestScope.q_username}&q_diaryid=${requestScope.q_diaryid}">
					  	<img src="${pageContext.request.contextPath}/style/images/last.gif" border="0" width="43" height="15" />
					  	</a> 
					  </c:when> 
					  <c:otherwise>   
					  	<img src="${pageContext.request.contextPath}/style/images/_last.gif" border="0" width="43" height="15" />
					  </c:otherwise> 
				</c:choose> 
                  </td>
                  <td width="150">
                    <form name="frm" action="jump">
                  		<div align="center">
                  		<input type="hidden" name="totalCount" value="${requestScope.pager.totalCount}" />
                  		<input type="hidden" name="q_username" value="${requestScope.q_username}" />
                  		<input type="hidden" name="q_diaryid" value="${requestScope.q_diaryid}" />
                  			<span class="STYLE1">转到第<input name="pageNo" id="currentPageId" type="text" size="4" style="height:12px; width:20px; border:1px solid #999999;" />页 </span>
                  			<img src="${pageContext.request.contextPath}/style/images/go.gif" width="37" height="15" onclick="script:sub();" />
                  		</div>
                  </form>
                  </td>
                </tr>
            </table>
        
            </td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/style/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
<script>
function sub(){
	var pagenow = document.getElementById("currentPageId").value;
//	alert(pagenow);
	if(!isNaN(pagenow))
	document.frm.submit();
}
</script>
</html>
