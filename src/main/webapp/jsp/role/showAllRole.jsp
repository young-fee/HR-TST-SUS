<%@page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<div class="demoTable" style="width: 100%;display: block;height: 40px;" >
    <a href="jsp/role/addRole.jsp" style="float: right;margin-right: 10px;margin-top: 10px;" class="layui-btn">新增</a>
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
<title>角色</title>
<link rel="stylesheet"  href="layui/css/layui.css" >
</head>
<body>
<table id="department" lay-filter="test"></table>
<script src="layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table;

  //第一个实例
  table.render({
    elem: '#department'
    ,id:'testReload'
    ,height: 500
    ,url: 'showAllRole' //数据接口
    ,cellMinWidth: 80
    ,page: true //开启分页
    ,cols: [[ //表头
       {type: 'numbers', fixed: 'left'}
      ,{field: 'roleId', title: 'ID',sort: true}
      ,{field: 'roleName', title: '角色名称', unresize: false}//unresize 是否禁用拖拽列宽（默认：false）
      ,{field: 'id',toolbar: '#barDemo1', title: '操作'}
    ]]
  });
  		//button事件
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
		  
		  
    //监听单元格编辑
    table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的DOM对象
  	 if(layEvent === 'del'){ //删除
  		layer.confirm('删除角色可能出错！', function(index){
  	        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
  	        layer.close(index);
  	        //向服务端发送删除指令
  	     	   window.location.href = "delRole?roleId="+data.roleId;
  	        });
    } else if(layEvent === 'edit'){ //编辑
    	<shiro:hasAnyRoles name = "ROLE_ADMIN">
        window.location.href = "jsp/role/updateRole.jsp?roleId="+data.roleId+"&roleName="+data.roleName;
        </shiro:hasAnyRoles>
       }else if(layEvent === 'showRolePermission'){//查看权限
    	   window.location.href = "showRolePermissionView?roleId="+data.roleId;
       }
    });
});
</script>
<script type="text/html" id="barDemo1">
    <a class="layui-btn layui-btn-xsl layui-btn-primary" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xsl layui-btn-danger" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xsl layui-btn-danger" lay-event="showRolePermission">查看权限</a>
</script>

</body>
</html>