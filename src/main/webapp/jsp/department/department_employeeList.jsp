	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet"  href="layui/css/layui.css" >
</head>
<div class="demoTable" style="width: 100%;display: block;height: 50px;padding-top: 10px;" >
        
        <form class="layui-form" action = "addEmployeeToDepartment" style="float: right">
            <div class="layui-form-item"  style="float: left">
                <div class="layui-inline">
                <input type="hidden" id="departmentId" name = "departmentId" value="${departmentId}">
                    <select id="employeeId" name="employeeId" lay-verify="required">
                            <c:forEach var="employee" items="${employeeList}">
                                <option value="${employee.employeeId}">${employee.eName}</option>
                            </c:forEach>
                    </select>
                </div>
            </div>
            <button style="float: left"  class="layui-btn" >添加人员</button>
        </form>
</div>
<div>
    <table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>
</div>

<script src="layui/layui.js"></script>
<script src="hfr/js/jquery.min.js"></script>
<script>
	var departmentId = $("#departmentId").val();
    layui.use('table', function(){
        var table = layui.table;
        table.render({
             elem: '#LAY_table_user'
            ,url: 'findEmployeeByDepartment?departmentId='+departmentId
            ,cellMinWidth: 80
            ,cols: [[
				 {field:'employeeId', title: '员工ID',sort: true} 
                ,{field:'eName', title: '姓名'}
                ,{field:'eSex', title: '性别',sort: true}
                ,{field:'eAge', title: '年龄',sort: true}
                ,{field:'id',title:'操作',toolbar:'#barDemo1'}
            ]]
            ,id: 'testReload'
            ,page: true
        });

        var $ = layui.$;
        var active = {
            reload: function(){
                var departmentIdReload = $("#departmentId");
                table.reload('testReload', {
                    where: {
                        departmentId:departmentIdReload.val()
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
        	     	   window.location.href = "delDepartmentEmployee?employeeId="+data.employeeId+"&departmentId="+$("#departmentId").val();
            })
            }
        });
    });

</script>
<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xsl layui-btn-danger" lay-event="del">移除</a>
</script>
