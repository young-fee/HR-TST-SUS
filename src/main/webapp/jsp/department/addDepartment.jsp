<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"  href="../../layui/css/layui.css" >
<title>添加部门</title>
</head>
<body>
	<br>
	<form class="layui-form" action="../../addDepartment" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">ID</label>
			<div class="layui-input-block">
				<input type="text" name="departmentId" placeholder="请输入"
					autocomplete="off" class="layui-input" lay-verify='required'>
			</div>
	<br>
		<div class="layui-form-item">
			<label class="layui-form-label">部门名称</label>
			<div class="layui-input-block">
				<input type="text" name="departmentName" placeholder="请输入"
					autocomplete="off" class="layui-input" lay-verify='required'>
			</div>
	<br>
			<div class="layui-form-item">
				<label class="layui-form-label">部门描述</label>
				<div class="layui-input-block">
					<input type="text" name="departmentDescription" placeholder="请输入"
						autocomplete="off" class="layui-input" lay-verify='required'>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</div>
	</form>

	<script src="../../	layui/layui.js"></script>
	<script>
		layui.use('form', function() {
			var form = layui.form;
		});
	</script>

</body>
</html>