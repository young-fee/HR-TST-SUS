<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function toLogin(){
		window.location.href = "${contextPath}/jsp/login/layuiLogin.jsp";
	}
</script>
</head>
<body>
<div align="center">
<h3>注册成功</h3>
<div>
<input type="button" onclick="toLogin()" value="返回"/>
</div>
</div>
</body>
</html>