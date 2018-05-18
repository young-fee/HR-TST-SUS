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
String employeeId = request.getParameter("employeeId");
String eName = new String(request.getParameter("eName").getBytes("ISO8859_1"),"utf-8"); 
/* String employeeName = new String(request.getParameter("employeeName").getBytes("ISO8859_1"),"utf-8");  */
String eSex = request.getParameter("eSex");
String eAge = new String(request.getParameter("eAge").getBytes("ISO8859_1"),"utf-8"); 
%>
	<form class="layui-form" action="../../updateEmployeeById" method="post">
		<div class="layui-form-item">
			<div class="layui-input-block">
				<input type="hidden" name="employeeId" value = "<%=employeeId %>">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">姓名</label>
				<div class="layui-input-block">
					<input type="text" name="eName" value = "<%=eName %>"
						autocomplete="off" class="layui-input" >
				</div>
				<br>
				<div class="layui-form-item">
					<label class="layui-form-label">单选框</label>
					<div class="layui-input-block">
						<input type="radio" name="eSex" value="男" title="男"> 
						<input type="radio" name="eSex" value="女" title="女" checked>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">年龄</label>
					<div class="layui-input-block">
						<input type="text" name="eAge" value = "<%=eAge %>"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<%-- <div class="layui-form-item">
					<label class="layui-form-label">所属部门</label>
					<div class="layui-input-block">
						<input type="text" name="employeeName" value = "<%=employeeName %>" 
							autocomplete="off" class="layui-input">
					</div>
				</div> --%>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="*">提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</div>
	</form>

	<script src="../../layui/layui.js"></script>
	<script>
		layui.use('form', function() {
			var form = layui.form;
		});
	</script>
</body>
</html>