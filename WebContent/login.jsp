<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.chao.model.User"%>
<jsp:useBean id="userinfo" scope="session" class="com.chao.model.User">
	<jsp:setProperty property="*" name="userinfo" />
</jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Scau Admin</title>

<!--                       CSS                       -->

<!-- Reset Stylesheet -->
<link rel="stylesheet" href="./resources/css/reset.css" type="text/css"
	media="screen" />

<!-- Main Stylesheet -->
<link rel="stylesheet" href="./resources/css/style.css" type="text/css"
	media="screen" />

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="./resources/css/invalid.css"
	type="text/css" media="screen" />

<!-- Colour Schemes
	  
		Default colour scheme is green. Uncomment prefered stylesheet to use it.
		
		<link rel="stylesheet" href="./resources/css/blue.css" type="text/css" media="screen" />
		
		<link rel="stylesheet" href="./resources/css/red.css" type="text/css" media="screen" />  
	 
		-->

<!-- Internet Explorer Fixes Stylesheet -->

<!--[if lte IE 7]>
			<link rel="stylesheet" href="./resources/css/ie.css" type="text/css" media="screen" />
		<![endif]-->

<!--                       Javascripts                       -->

<!-- jQuery -->
<script type="text/javascript"
	src="./resources/scripts/jquery-1.3.2.min.js"></script>

<!-- jQuery Configuration -->
<script type="text/javascript"
	src="./resources/scripts/simpla.jquery.configuration.js"></script>

<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="./resources/scripts/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript"
	src="./resources/scripts/jquery.wysiwyg.js"></script>

<!-- Internet Explorer .png-fix -->

<!--[if IE 6]>
			<script type="text/javascript" src="./resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->

</head>

<body id="login">
	<script type="text/javascript">
		var xmlHttp;
		var flag;
		function createXMLHttp() {
			if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		function checkLogin(userid, password) {
			createXMLHttp();
			xmlHttp.open("POST",
					"/VolunteerTimeWeb/servlet/login?sign=0&authority=1&userid="
							+ userid + "&password=" + password);
			xmlHttp.onreadystatechange = loginCallback;
			xmlHttp.send(null);
			document.getElementById("subtn").value = "正在登录...";
		}
		function loginCallback() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var text = xmlHttp.responseText;
					if (text == "true") {
						flag = true;
						window.location.href = "index.jsp";
					} else {
						flag = false;
						tips.style.display = "block";
						document.getElementById("subtn").value = "登录";
					}
				}
			}
		}
		function checkForm() {
			return flag;
		}
	</script>
	<%
	  
	%>

	<div id="login-wrapper" class="png_bg">
		<div id="login-top">

			<h1>Simpla Admin</h1>
			<!-- Logo (221px width) -->
			<img id="logo" src="./resources/images/logo.png"
				alt="Simpla Admin logo" />
		</div>
		<!-- End #logn-top -->

		<div id="login-content">

			<form>
				<div id="tips" class="notification error png_bg"
					style="display: none;">
					<a href="#" class="close" style="padding-left: 5px;"><img
						src="resources/images/icons/cross.png" title="关闭通知" alt="close"></a>
					<div>用户名或者密码错误，请检查输入是否正确。</div>
				</div>

				<p>
					<label>Username</label> <input class="text-input" type="text"
						name="userid" />
				</p>
				<div class="clear"></div>
				<p>
					<label>Password</label> <input class="text-input" type="password"
						name="password" />
				</p>
				<div class="clear"></div>
				<p id="remember-password">
					<input type="checkbox" />Remember me
				</p>
				<div class="clear"></div>
				<p>
					<input id="subtn" class="button" type="button" value="登录"
						onclick="checkLogin(userid.value,password.value)" />
				</p>

			</form>
		</div>
		<!-- End #login-content -->

	</div>
	<!-- End #login-wrapper -->

</body>
</html>
