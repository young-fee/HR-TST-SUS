<%@page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<div class="demoTable" style="width: 100%;display: block;height: 40px;" >
    <a href="jsp/permission/addPermission.jsp" style="float: right;margin-right: 10px;margin-top: 10px;" class="layui-btn">新增</a>
    <div style="float: right;margin-right: 10px;margin-top: 10px;">
        <div class="layui-inline">
            <input class="layui-input" name="keyword" id="demoReload" autocomplete="off">
        </div>
        <button class="layui-btn" data-type="reload">搜索</button>
    </div>
</div>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>employee list</title>
<link rel="stylesheet"  href="layui/css/layui.css" >
</head>
<body>
<table class="layui-hide" id="LAY_table_permission" lay-filter="permission"></table>
<script src="layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
             elem: '#LAY_table_permission'
            ,url: 'showAllPermission'
            ,cellMinWidth: 80
            ,cols: [[
                {field:'permissionId', title: '权限名称'}
                ,{field:'description', title: '信息'}
                ,{field:'id',title:'操作',toolbar:'#barDemo1'}
            ]]
            ,id: 'testReload'
            ,page: true
        });

        var $ = layui.$;
        var active = {
            reload: function(){
                var demoReload = $('#demoReload');
                table.reload('testReload', {
                    where: {
                        keyword: demoReload.val()
                    }
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        table.on('tool(permission)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'edit'){ //编辑
            	<shiro:hasAnyRoles name = "ROLE_ADMIN">
               window.location.href ="jsp/permission/updatePermissionById.jsp?permissionId="+ data.permissionId+"&description="+data.description;
               </shiro:hasAnyRoles>         
            } else if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                     //删除对应行（tr）的DOM结构，并更新缓存
                     layer.close(index);
                     window.location.href = "delPermission?permissionId="+ data.permissionId;
                    
                });
            }
        });
    });

</script>

<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xsl layui-btn-primary" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xsl layui-btn-danger" lay-event="del">删除</a>
</script>

</body>
</html>