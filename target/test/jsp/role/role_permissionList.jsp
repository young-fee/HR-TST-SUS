<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet"  href="layui/css/layui.css" >
</head>
<div class="demoTable" style="width: 100%;display: block;height: 50px;padding-top: 10px;" >
        
        <form class="layui-form" action = "addPermissionToRole" style="float: right">
            <div class="layui-form-item"  style="float: left">
                <div class="layui-inline">
                <input type="hidden" id="roleId" name = "roleId" value="${roleId}">
                    <select id="permissionId" name="permissionId" lay-verify="required">
                            <c:forEach var="permission" items="${permissionList}">
                                <option value="${permission.permissionId}">${permission.permissionId}</option>
                            </c:forEach>
                    </select>
                </div>
            </div>
            <button style="float: left"  class="layui-btn" >添加权限</button>
        </form>
</div>
<div>
    <table class="layui-hide" id="LAY_table_permission" lay-filter="user"></table>
</div>

<script src="layui/layui.js"></script>
<script src="hfr/js/jquery.min.js"></script>
<script>
	var roleId = $("#roleId").val();
    layui.use('table', function(){
        var table = layui.table;
        table.render({
             elem: '#LAY_table_permission'
            ,url: 'showRolePermission?roleId='+roleId
            ,cellMinWidth: 80
            ,cols: [[
				 {field:'permissionId', title: '权限ID',sort: true} 
                ,{field:'description', title: '描述'}
                ,{field:'id',title:'操作',toolbar:'#barDemo1'}
            ]]
            ,id: 'testReload'
            ,page: true
        });

        var $ = layui.$;
        var active = {
            reload: function(){
                var departmentIdReload = $("#roleId");
                table.reload('testReload', {
                    where: {
                        roleId:departmentIdReload.val()
                    }
                });
                
            }
        };

        table.on('tool(user)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除行么', function(index){
                     //删除对应行（tr）的DOM结构，并更新缓存
                     layer.close(index);
                   //向服务端发送删除指令
        	     	   window.location.href = "delRolePermission?permissionId="+data.permissionId+"&roleId="+$("#roleId").val();
            })
            }
        });
    });

</script>
<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xsl layui-btn-danger" lay-event="del">移除</a>
</script>
