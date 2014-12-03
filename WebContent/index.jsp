<%@page import="scau.info.volunteertime.vo.FeedBack"%>
<%@page import="java.util.List"%>
<%@page import="scau.info.volunteertime.dao.feedback.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%><%@ page import="com.chao.model.User"%>
<jsp:useBean id="userinfo" scope="session" class="com.chao.model.User">
	<jsp:setProperty property="*" name="userinfo" />
</jsp:useBean>
<jsp:useBean id="pagination" class="com.chao.tool.impl.MyPagination"
	scope="session"></jsp:useBean>
<%
	FeedBackDao feedBackDao = new FeedBackDaoImpl();
	int Page = 1;
	List list = feedBackDao.getFeedBack();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

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

<!-- jQuery Datepicker Plugin -->
<script type="text/javascript"
	src="./resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="./resources/scripts/jquery.date.js"></script>
<!--[if IE]><script type="text/javascript" src="./resources/scripts/jquery.bgiframe.js"></script><![endif]-->


<!-- Internet Explorer .png-fix -->

<!--[if IE 6]>
			<script type="text/javascript" src="./resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->
<script type="text/javascript">
	var xmlHttp;
	var flag = false;
	function createXMLHttp() {
		if (xmlHttp == null) {
			if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
	}

	function toClickDeleteMessage(id) {
		createXMLHttp();
		xmlHttp.open("POST",
				"/VolunteerTimeWeb/VolunteerTimeFeedBackServlet?action_type=1&id=" + id);
		xmlHttp.onreadystatechange = toClickDeleteMessageCallback;
		xmlHttp.send(null);
	}
	function toClickDeleteMessageCallback() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var text = xmlHttp.responseText;
				if (text == "true") {
					alert("删除成功！");
					location.reload(true);
				} else {
					alert("删除失败！");
				}
			}
		}
	}
</script>
</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->

		<div id="sidebar">
			<div id="sidebar-wrapper">
				<!-- Sidebar with logo and menu -->


				<!-- Logo (221px wide) -->
				<a href="#"><img id="logo" src="./resources/images/logo.png"
					alt="scau admin logo" /></a>

				<!-- Sidebar Profile links -->
				<div id="profile-links">
					Hello, <a href="#" title="Edit your profile"><jsp:getProperty
							property="username" name="userinfo" /> </a>, 你可以<a href="#messages"
						rel="modal" title="3 Messages">编辑信息</a><br /> <br /> <a
						href="../login.jsp" title="View the Site">查看网页</a> | <a
						href="login.jsp" title="Sign Out">退出</a>
				</div>

				<ul id="main-nav">
					<!-- Accordion Menu -->
					<li><a href="#" class="nav-top-item no-submenu current">常用功能</a></li>
					<li><a href="#" class="nav-top-item"> <!-- Add the class "current" to current menu item -->
							管理平台
					</a>
						<ul class="dropdown">
							<li><a href="manageActivityCenter.jsp">活动管理</a></li>
							<li><a href="manageResults.jsp">成果管理</a></li>
							<li><a href="manageVotes.jsp">投票管理</a></li>
						</ul></li>

					<li><a href="manageUser.jsp" class="nav-top-item"> 管理用户 </a>
						<ul class="dropdown">
							<li><a href="manageUser.jsp">增加用户</a></li>
							<li><a href="manageUser.jsp">移除用户</a></li>
							<li><a href="manageUser.jsp">用户授权</a></li>
						</ul></li>
					<li><a href="#" class="nav-top-item"> 新手教程 </a>
						<ul class="dropdown">
							<li><a href="intro.jsp">平台教程</a></li>
							<li><a href="intro.jsp">用户教程</a></li>
						</ul></li>

					<li><a href="#" class="nav-top-item"> 设置 </a>
						<ul class="dropdown">
							<li><a href="setting.jsp">基本信息</a></li>
							<li><a href="setting.jsp">通用设置</a></li>
						</ul></li>

				</ul>
				<!-- End #main-nav -->

				<div id="messages" style="display: none">
					<form
						action="/ScauActivityWeb/servlet/messageServlet?sign=0&index=1"
						method="post">

						<h4>发布消息</h4>

						<fieldset>
							<p>标题</p>
							<input class="text-input small-input" type="text" id="title"
								name="texttitle" />
							<p style="border: none;">内容</p>
							<textarea class="textarea" name="textfieldmessage" cols="79"
								rows="5"></textarea>
						</fieldset>

						<fieldset>
							<select name="dropdownmessageclass" class="small-input">
								<option value="option1">重要信息</option>
								<option value="option2">警告信息</option>
							</select> <input class="button" type="submit" value="发送" />
						</fieldset>

					</form>

				</div>
				<!-- End #messages -->

			</div>
		</div>
		<!-- End #sidebar -->

		<div id="main-content">
			<!-- Main Content Section with everything -->

			<noscript>
				<!-- Show a notification if the user has disabled javascript -->
				<div class="notification error png_bg">
					<div>
						您的浏览器或许不能使用Javascript或者是被您禁用了.请 <a href="http://browsehappy.com/"
							title="Upgrade to a better browser">升级</a> 您的浏览器或者 <a
							href="http://www.google.com/support/bin/answer.py?answer=23852"
							title="Enable Javascript in your browser">开启</a> Javascript
						的功能以保证能够正常使用 Download From <a href="http://www.exet.tk">exet.tk</a>
					</div>
				</div>
			</noscript>

			<!-- Page Head -->
			<h2><jsp:getProperty property="username" name="userinfo" />
				你好
			</h2>
			<p id="page-intro">今天你想干嘛？</p>

			<ul class="shortcut-buttons-set">

				<li><a class="shortcut-button" href="#messages" rel="modal"><span>
							<img src="./resources/images/icons/pencil_48.png" alt="icon" /><br />
							快速发消息
					</span></a></li>
				<li><a class="shortcut-button" href="intro.jsp"><span>
							<img src="./resources/images/icons/guide.ico" alt="icon" /><br />
							平台教程
					</span></a></li>

				<li><a class="shortcut-button" href="manageScauInfo.jsp"><span>
							<img src="./resources/images/icons/news.png" alt="icon" /><br />
							发布资讯
					</span></a></li>

				<li><a class="shortcut-button" href="manageUser.jsp"><span>
							<img src="./resources/images/icons/user.png" alt="icon" /><br />
							增加用户
					</span></a></li>

			</ul>
			<!-- End .shortcut-buttons-set -->

			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>内容盒子</h3>

					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">反馈信息</a></li>
						<!-- href must be unique and match the id of target div -->

					</ul>

					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->

						<div class="notification attention png_bg">
							<a href="#" class="close"><img
								src="./resources/images/icons/cross_grey_small.png"
								title="Close this notification" alt="close" /></a>
							<div>在这里可以查看用户反馈信息. 另外你可以通过点击×关闭这个提示框.</div>
						</div>

						<table>

							<thead>
								<tr>
									<th>用户名</th>
									<th>反馈信息</th>
									<th>操作</th>
								</tr>

							</thead>



							<tbody>

								<%
									for (int i = 0; i < list.size(); i++) {
										FeedBack feedBack = (FeedBack) list.get(i);
								%>
								<tr>
									<td><%=feedBack.getUserId()%></td>

									<td><%=feedBack.getContent()%></td>
									<td>
										<!-- Icons --> <input type="image"
										src="./resources/images/icons/cross.png" name="delete"
										value=<%=feedBack.getId()%>
										onclick="toClickDeleteMessage(this.value)"> </input>
									</td>
								</tr>

								<%
									}
								%>

								</form>

							</tbody>

						</table>

					</div>
					<!-- End #tab1 -->

					<div class="tab-content" id="tab2">

						<form
							action="/ScauActivityWeb/servlet/messageServlet?sign=3&index=1"
							method="post" enctype="multipart/form-data">

							<fieldset>
								<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->

								<p>
									<label>消息名称</label> <input class="text-input small-input"
										type="text" id="small-input" name="inputpublishMessageTitle" />
									<!-- Classes for input-notification: success, error, information, attention -->
									<br /> <small>A small description of the field</small>
								</p>
								<p>
									<label>类别</label> <select name="dropdownPublishMessageClass"
										class="small-input">
										<option value="option1">重要通知</option>
										<option value="option2">警告通知</option>
									</select>
								</p>
								<p>
									<label>上传图片</label><input type="file"
										name="publishMessageTitlePic" />
								</p>
								<p>
									<label>新消息编辑</label>
									<textarea class="text-input textarea " id="textarea"
										name="textfieldPublishMessage" cols="79" rows="15"></textarea>
								</p>

								<p>
									<input class="button" type="submit" value="提交" />
								</p>

							</fieldset>

							<div class="clear"></div>
							<!-- End .clear -->

						</form>

					</div>
					<!-- End #tab2 -->

				</div>
				<!-- End .content-box-content -->

			</div>
			<!-- End .content-box -->
			<div class="clear"></div>

			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->
					&#169; Copyright 2014 <a href="#"> duziaqin chao_king banana </a>|
					Power by Fatwo| <a href="#">Top</a>
				</small>
			</div>
			<!-- End #footer -->

		</div>
		<!-- End #main-content -->
	</div>
</body>



</html>
