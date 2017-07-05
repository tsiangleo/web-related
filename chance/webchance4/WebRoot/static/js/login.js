/**
 * Created by michael on 2015/7/26.
 */

$(document).ready(function(){

    //定义一个最小长度(minLength)的自定义验证
    $.extend($.fn.validatebox.defaults.rules, {
        minLength: {
            validator: function(value, param){
                return value.length >= param[0];
            },
            message: '请至少输入{0}个字符.'
        }
    });


    $("#loginBox").dialog({
        title: '登陆 - Chance后台管理系统',
        width: 500,
        height: 200,
        closable: false,
        draggable: false,
        modal: true,
        iconCls : 'icon-login',
        buttons : '#btn',
    });

    $("#loginName").validatebox({
        required: true,
        validType:"length[5,14]",
        missingMessage:"用户名不能为空",
        invalidMessage:"登录名长度为5-14个字符"
    });

    $("#loginPwd").validatebox({
        required: true,
        validType:"minLength[5]",
        missingMessage:"密码不能为空",
        invalidMessage:"密码长度为至少为5个字符"
    });

    //加载页面时判断
    if (!$('#loginName').validatebox('isValid')) {
        $('#loginName').focus();
    } else if (!$('#loginPwd').validatebox('isValid')) {
        $('#loginPwd').focus();
    }

    //登录按钮
    $('#btn a').click(function () {
        if (!$('#loginName').validatebox('isValid')) {
            $('#loginName').focus();
        } else if (!$('#loginPwd').validatebox('isValid')) {
            $('#loginPwd').focus();
        } else {
            //服务器提交
            $.ajax({
                url : 'http://localhost:8080/webchance4/admin/login',
                type : 'POST',
                data : {
                    loginName : $('#loginName').val(),
                    userpass : $('#loginPwd').val(),
                },
                beforeSend : function () {
                    $.messager.progress({
                        text : '正在尝试登录...',
                    });
                },
                success : function(data, response, status){
                    $.messager.progress('close');
                    if (data.status == 'success') {
                        location.href = 'http://localhost:8080/webchance4/home/index';
                    } else if(data.status == 'fail'){
                        $.messager.alert('登录失败！',data.desc,'warning',
                            function () {
                                $('#loginPwd').select();
                            });
                    }
                }
            });
        }
    });

});


