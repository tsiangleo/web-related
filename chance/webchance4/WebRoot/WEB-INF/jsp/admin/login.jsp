<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆页面 - Chance后台管理系统</title>
    <link href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/easyui/themes/icon.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/easyui/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/login.js" type="text/javascript"></script>
</head>
<body>

<div id="loginBox">

    <div id="login">
        <p>管理员帐号：<input id="loginName" type="text" name="loginName" class="textbox"></p>
        <p>管理员密码：<input id="loginPwd" type="password" name="userpass" class="textbox"></p>
    </div>
    <div id="btn">
        <a href="#" class="easyui-linkbutton">登录</a>
    </div>
</div>

</body>
</html>