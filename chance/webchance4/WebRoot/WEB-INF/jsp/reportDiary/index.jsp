<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

  <!-- 提示操作成功，返回上一页并刷新 -->
  <!--  
   <script type="text/javascript">alert("^_^ 操作成功!");window.location.href = document.referrer;</script>
 -->
   <!-- 提示操作成功，返回上2页并刷新-->
    <!--
     <script type="text/javascript">alert("^_^ 操作成功!");history.go(-2);opener.location.reload();</script>	
    --> 
<%

	//获取管理员的权限等级
	//int	limit = Integer.parseInt(session.getAttribute("limit").toString());
	    
	//Page mypage = (Page)request.getAttribute("page");
	
	//int pagenow = 0;
	//int lastpage = 0;
	
	//if(mypage != null){
	//	pagenow = mypage.getPageNow();
	//	lastpage = mypage.getLastPage();
	//}

	//显示提示信息
	//String tip = (String)request.getAttribute("tip");
	//if(tip != null){%>
	<script type="text/javascript">alert("<s:property value="#request.tip" />");</script>
	<%
	//}	
%>

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
   		xmlHttp.open("post","reportDiary_deleteAll?checkList="+checkedIds+"&&timeList="+checkedIdsTime,true);
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
   				window.location.href="reportDiary_jump?pageNow=${requestScope.pager.currentPage}";
   			}
   		}
   }
  }
</script>

	<!-- 显示提示信息-->
	<c:if test="${not empty requestScope.tipMsg}">
	<script type="text/javascript">alert("${requestScope.tipMsg}");</script>
	</c:if>
	
</head>

<body class="STYLE1">
	
	<ul>
		<li> <a href="unchecked/get">查看待处理的reportDiary</a> </li>
		<li> <a href="checked/get">查看已处理的reportDiary</a>  </li>
		<li> <a href="unchecked/checkLatest">从服务获取最新的reportDiary</a>  </li>
		<li> <a href="statistics/reporteeTopList">被举报用户排行榜</a>  </li>
	</ul>

</body>

</html>
