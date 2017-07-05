/**
 * Created by michael on 2015/7/24.
 */

$(document).ready(function(){
	
    $('#nav').tree({
        url : 'http://localhost:8080/webchance4/static/js/nav.json',
        lines : true,
        onLoadSuccess : function (node, data) {
            if (data) {
                $(data).each(function (index, value) {
                    if (this.state == 'closed') {
                        $('#nav').tree('expandAll');
                    }
                });
            }
        },
        onClick : function (node) {
            console.log(node);
            if (node.url) {//只有带有url属性的节点才打开新的页面
                if ($('#tabs').tabs('exists', node.text)) {
                    $('#tabs').tabs('select', node.text);
                } else {
                    $('#tabs').tabs('add', {
                        title : node.text,
                        //iconCls : node.iconCls,
                        closable : true,
                        href : node.url,
                    });
                }
            }
        }
    });

    $('#tabs').tabs({
        fit : true,
        border : false,
    });

});