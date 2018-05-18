<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../../layui/css/layui.css">
<title>update</title>
</head>
<body>
<%
String permissionId = request.getParameter("permissionId");
String description = new String(request.getParameter("description").getBytes("ISO8859_1"),"utf-8"); 
%>

	<div class="site-block">
		<form class="layui-form" action="../../updatePermissionById" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">权限名称</label>
				<div class="layui-input-block">
					<input type="text" value="<%= permissionId%>"
						readonly="true" name="permissionId" required lay-verify="required"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">信息</label>
				<div class="layui-input-block">
					<textarea name="description" required lay-verify="required"
						class="layui-textarea"><%= description%></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>

	<script src="../../layui/layui.js"></script>
	<script>
		layui.use('form', function() {
			var form = layui.form;
		});
	</script>
</body>
</html>