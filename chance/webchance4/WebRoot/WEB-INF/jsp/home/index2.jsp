<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/easyui/themes/icon.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/home.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/easyui/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/home.js" type="text/javascript"></script>
    
</head>
<body class="easyui-layout" >

<div data-options="region:'north',title:'header',split:true,noheader:true" style="height:60px;background:#666;">
    <div class="logo">Chance后台管理系统</div>
    <div class="logout">您好，${sessionScope.admin.loginName}| <a href="${pageContext.request.contextPath}/admin/logout">退出</a></div>
</div>


<div data-options="region:'west',title:'导航',split:true" style="width:180px;padding:10px;">
    <ul id="nav"></ul>
</div>


<div data-options="region:'center'" style="overflow:hidden;">
    <div id="tabs">
        <div title="起始页" style="padding:0 10px;display:block;">
            欢迎来到后台管理系统！
        </div>
    </div>
</div>

<div data-options="region:'south',title:'footer',split:true,noheader:true" style="height:35px;line-height:30px;text-align:center;">
    &copy;2011-2015 Chance后台管理系统.
</div>

</body>
</html>