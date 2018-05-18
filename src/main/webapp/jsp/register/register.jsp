<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<style type="text/css">
body {
	background: #ddd
}

.loginwarrp {
	margin: 250px auto;
	width: 400px;
	padding: 30px 50px;
	background: #FFFFFF;
	overflow: hidden;
	font-size: 14px;
	font-family: '微软雅黑', '文泉驿正黑', '黑体';
}

.loginwarrp .logo {
	width: 100%;
	height: 44px;
	line-height: 44px;
	font-size: 20px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

.loginwarrp .login_form {
	margin-top: 15px;
}

.loginwarrp .login_form .login-item {
	padding: 2px 8px;
	border: 1px solid #dedede;
	border-radius: 8px;
	margin-top: 10px;
}

.loginwarrp .login_form .login_input {
	height: 35px;
	border: none;
	line-height: 35px;
	width: 200px;
	font-size: 14px;
	outline: none;
}

.loginwarrp .login_form .verify {
	float: left;
}

.loginwarrp .verify .verify_input {
	width: 160px;
}

.loginwarrp .verifyimg {
	height: 30px;
	margin: 20px 0 0 20px;
}

.loginwarrp .login-sub {
	text-align: center;
}

.loginwarrp .login-sub button {
	margin-top: 15px;
	background: #45B549;
	line-height: 35px;
	width: 150px;
	color: #FFFFFF;
	font-size: 16px;
	font-family: '微软雅黑', '文泉驿正黑', '黑体';
	border: none;
	border-radius: 5px;
}

.loginwarrp .login_form .login-item .error {
	color: #F00;
	font-family: '微软雅黑', '文泉驿正黑', '黑体';
}
</style>

</head>
<body>
	<div class="page">
		<div class="loginwarrp">
			<div class="logo">注 册</div>
			<div class="login_form">
				<form action="../../registerUser" method="post">

					<li class="login-item"><span>用户名：</span> <input type="text"
						id="userName" name="userName" placeholder="请输入" autocomplete="off"
						class="login_input" required="required" onchange="checkIsExist()">
						<div id="message"></div></li>

					<li class="login-item"><span>密 码：</span> <input
						type="password" name="password" placeholder="请输入"
						autocomplete="off" class="login_input" required="required"></li>

					<input type="password" name="password" style="display: none"
						autocomplete="off">

					<li class="login-item"><span>性 别：</span> &nbsp;&nbsp;&nbsp; <span>男：</span><input
						type="radio" name="sex" value="男" title="男" checked>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>女：</span><input
						type="radio" name="sex" value="女" title="女"></li>

					<li class="login-sub">
						<button class="login-sub" type="submit">提交</button>
						<button type="reset">重置</button>
					</li>
				</form>
			</div>
		</div>
	</div>

</body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	function checkIsExist() {
		//var userName = document.getElementById("userName");
		var userName = $("#userName").val();
		$.ajax({
			type : 'post',
			url : '../../checkUserName',
			data : "userName=" + userName,
			//dataType:"json",
			//contentType : "application/x-www-form-urlencoded",
			//cache : false,
			success : function(data) {
				//data = data.replace("\"","").replace("\"","");
				if(data==0) {
					 $("#message").html("<font color='red'>该用户名已存在</font>");
				} else if(data==1) 
				{
					 $("#message").html("<font color='green'>该用户名不存在</font>");
				}
			}
		})
	}
</script>
</html>