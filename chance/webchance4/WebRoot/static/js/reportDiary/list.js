/**
 * Created by michael on 2015/7/26.
 */
$(function () {

    $('#reportDiary').datagrid({
        url : 'http://localhost:8080/webchance4/reportDiary/checked/get',
        title:'当前位置：日志举报-待处理',
        fit : true,
        fitColumns : true,
        striped : true,
        rownumbers : true,
        border : false,
        pagination : true,
        pageSize : 20,
        pageList : [10, 20, 30, 40, 50],
        pageNumber : 1,
        sortName : 'date',
        sortOrder : 'desc',
        toolbar : '#reportDiary_tool',
        columns : [[
            {
                field : 'userId',
                title : '举报用户id',
                checkbox : true,
            },
            {
                field : 'userName',
                title : '举报用户昵称',
            },
            {
                field : 'byUserId',
                title : '被举报用户id',
            },
            {
                field : 'byUserName',
                title : '被举报用户昵称',
            },
            {
                field : 'time',
                title : '举报时间',
            },
            {
                field : 'diaryId',
                title : '对应日志id',
            },
            {
                field : 'checkAdminId',
                title : '审核管理员id',
            },
            {
                field : 'checkTime',
                title : '处理时间',
            },
        ]],
//        onLoadSuccess:function(){
//        	alert("加载成功");
//        },
//        onLoadError:function(){
//        	alert("加载错误");
//        },
//        onBeforeLoad:function(){
//        	alert("加载请求");
//        }
    });

});