<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>简单人力资源管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Copyright" content="zfsoft" />
<link rel="icon" href="/logo/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/logo/favicon.ico" type="image/x-icon" />
<!--jQuery核心框架库 -->
<script type="text/javascript"
	src="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/js/jquery-1.11.1-min.js?ver=20180327"></script>
<!--Bootstrap布局框架-->
<link rel="stylesheet" type="text/css"
	href="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/plugins/bootstrap/css/bootstrap.min.css?ver=20180327" />
<script type="text/javascript"
	src="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/plugins/bootstrap/js/bootstrap.min.js?ver=20180327"
	charset="utf-8"></script>
<!--jQuery.chosen美化插件-->
<link rel="stylesheet" type="text/css"
	href="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/plugins/chosen/css/chosen-min.css?ver=20180327" />
<script type="text/javascript"
	src="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/plugins/chosen/jquery.choosen.concat-min.js?ver=20180327"
	charset="utf-8"></script>
<!--业务框架前端脚本国际化库-->
<script type="text/javascript"
	src="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/js/zftal/lang/jquery.zftal_zh_CN-min.js?ver=20180327"></script>

<meta http-equiv="Content-Security-Policy"
	content="script-src 'self' http://jwglxt.whpu.edu.cn 'unsafe-inline'">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<style type="text/css">
.btn-lang {
	width: 76px;
	border-color: #357ebd;
	font-weight: bold;
	height: 30px;
}

.btn-lang-enabled {
	background-color: #428bca;
	color: #fff;
}

.btn-lang-disabled {
	background-color: #fff;
	color: #333;
}

.btn-lang-enabled:hover, .btn-lang-enabled:focus {
	color: #fff;
}

.btn-group>.btn-lang:last-child {
	border-top-right-radius: 3px;
	border-bottom-right-radius: 3px;
}
</style>
</head>
<body style="background: #fafafa;">
	<input type="hidden" name="yzcskz" id="yzcskz" value=3>
	<input type="hidden" name="mmsfjm" id="mmsfjm" value=1>
	<div class="container container_1170">
		<div class="row sl_log_top">
			<div class="col-sm-8 logo_1">
				<img
					src="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/images/logo/logo_jw_d.png"
					style="margin-top: -3px" /> <span id="xtmc">简单人力资源管理系统</span>
			</div>
			<div class="col-sm-4 text-right hidden-xs"></div>
		</div>
		<div class="row sl_log_bor4">
			<div class="col-sm-8 hidden-xs sl_log_lf">
				<img class="img-responsive"
					src="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/images/login_bg_pic.jpg" />
			</div>
			<div class="col-sm-4 sl_log_rt">
				<form class="form-horizontal" role="form"
					action="${ contextPath}/login" method="post">
					<h5>用户登录</h5>

					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<img
									src="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/images/log_ic01.png"
									width="16" height="16" />
							</div>
							<input type="text" class="form-control" name="userName" id="yhm"
								value="" placeholder="用户名" onblur="" autocomplete="off">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">
								<img
									src="http://jwglxt.whpu.edu.cn/zftal-ui-v5-1.0.2/assets/images/log_ic02.png"
									width="16" height="16" />
							</div>
								
								<input type="password"
								class="form-control" name="password" id="mm" value=""
								placeholder="密码" autocomplete="off">
								
								<input type="password" name="password" style="display: none"
								autocomplete="off"> 
						</div>
					</div>
					<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block" id="dl">登
					录</button>
				<button type="button" class="btn btn-primary btn-block"
					onclick="register()">注 册</button>

			</div>
			<div><h5 style="color: blue;">管理员账号：admin<br>密码:young</h5></div>
			</form>
		</div>
	</div>
	</div>


</body>
<script type="text/javascript">
	/**  
	 * 注册  
	 */
	function register() {
		// 如果需要在新窗口打开注册界面；  
		//window.open('../user/addUser.jsp');  
		// 如果需要在当前窗口打开注册界面  
		window.location.href = '${ contextPath}/jsp/register/register.jsp';
	}
</script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		//$('#localChange').trigger("chosen");
		/// detectOS();
		//用户名密码 输错n次 需输入验证码            
	});

	//ip

	jQuery(function($) {

		function changeLanguage(lang) {
			$.ajaxSetup({
				async : false
			});
			jQuery.post(_path + "/xtgl/init_changeLocal.html", {//config-shiro.xml里要加这个
				language : lang != "en_US" ? "zh_CN" : "en_US"
			}, function(b) {
				if (1 == parseInt(b)) {
					if ($("#topButton").size() > 0) {
						$("#topButton").click();
					} else {
						window.location.reload();
					}
				}
			}, "json");
			$.ajaxSetup({
				async : true
			});
		}
		$("button.btn-lang").click(function() {
			if (!$(this).hasClass("btn-lang-enabled")) {
				changeLanguage($(this).attr("value"));
			}
		})

		//切换英文
		if ($(".btn-lang-enabled").val() == "en_US") {
			$("#xtmc").text("");
			$("title").text("")
			$("#wpjssq").text("New Teacher Account Application");
			$("#wjmm").text("Forgot your password?");
			$("#jzfw").text("Parents visit");
			$("#dlktsxx").hide();
		}
	});
</script>
</html>