<table id="reportDiary"></table>

<div id="reportDiary_tool" style="padding:5px;">
    <div style="margin-bottom:5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="obj.add();">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="obj.edit();">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj.remove();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" id="save" onclick="obj.save();">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" id="redo" onclick="obj.redo();">取消编辑</a>
    </div>
    <div style="padding:0 0 0 7px;color:#333;">
        查询帐号：<input type="text" class="textbox" name="user" style="width:110px">
        创建时间从：<input type="text" name="date_from" class="easyui-datebox" editable="false" style="width:110px">
        到：<input type="text" name="date_to" class="easyui-datebox" editable="false" style="width:110px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/reportDiary/list.js"></script>