
<%@page import="java.util.ArrayList"%>
<%@page import="scau.info.volunteertime.vo.Vote"%>

<%@page import="com.chao.tool.*"%>
<%@page import="scau.info.volunteertime.dao.votesCenter.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="userinfo" scope="session" class="com.chao.model.User">
	<jsp:setProperty property="*" name="userinfo" />
</jsp:useBean>
<jsp:useBean id="pagination" class="com.chao.tool.impl.MyPagination"
	scope="session"></jsp:useBean>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%
	String str = request.getParameter("Page");
	String messagekeyword = request.getParameter("messagekeyword");
	String dropdownmessage = request.getParameter("dropdownmessage");
	System.out.println(str + ":" + messagekeyword + ":"
	+ dropdownmessage);
	int Page = 1;
	List list = null;
	VotesDao votesDao = new VotesDaoImpl();
	if (str == null) {
		list = votesDao.getAll(messagekeyword);
		int pagesize = 6; //指定每页显示的记录数
		list = pagination.getInitPage(list, Page, pagesize); //初始化分页信息
	} else {
		Page = pagination.getPage(str);
		list = pagination.getAppointPage(Page); //获取指定页的数据
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Simpla Admin</title>

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
				"/VolunteerTimeWeb/VolunteerTimeVotesServlet?action_type=3&id="
						+ id);
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

	function uploadForm() {
		document.form1.action = "/VolunteerTimeWeb/servlet/messageServlet?sign=3&inputpublishMessageTitle="
				+ document.form1.inputpublishMessageTitle.value;
		alert(document.form1.inputpublishMessageTitle.value);
		document.form1.submit();
	}
</script>


<link rel="stylesheet" href="css/style.css" media="screen"
	type="text/css" />
<script src='js/jquery.js'></script>

<script src="js/index.js"></script>

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

				<div id="profile-links">
					Hello, <a href="#" title="Edit your profile"><jsp:getProperty
							property="username" name="userinfo" /> </a>, 你可以<a href="#messages"
						rel="modal" title="3 Messages">编辑信息</a><br /> <br /> <a
						href="../login.jsp" title="View the Site">查看网页</a> | <a
						href="login.jsp" title="Sign Out">退出</a>
				</div>

				<ul id="main-nav">
					<!-- Accordion Menu -->
					<li><a href="index.jsp" class="nav-top-item no-submenu">常用功能</a></li>
					<li><a href="#" class="nav-top-item current"> <!-- Add the class "current" to current menu item -->
							管理平台
					</a>
						<ul class="dropdown">
							<li><a href="manageActivityCenter.jsp">活动管理</a></li>
							<li><a href="manageResults.jsp">成果管理</a></li>
							<li><a href="manageVotes.jsp" class="current">投票管理</a></li>
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
					<form action="/ScauActivityWeb/servlet/messageServlet?sign=0"
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
			<p id="page-intro">欢迎来到消息管理</p>
			<ul class="shortcut-buttons-set">

				<li><a class="shortcut-button" href="#messages" rel="modal"><span>
							<img src="./resources/images/icons/pencil_48.png" alt="icon" /><br />
							快速发消息
					</span></a></li>

				<li><a class="shortcut-button" href="#messageList"><span>
							<img src="./resources/images/icons/userInfo.png" alt="icon" /><br />
							用户消息列表
					</span></a></li>

				<li><a class="shortcut-button user-btn" href="#writeMess"><span>
							<img src="./resources/images/icons/userMess.png" alt="icon" /><br />
							发布用户消息
					</span></a></li>
				<li><a class="shortcut-button" href="#SysList"><span>
							<img src="./resources/images/icons/sysInfo.png" alt="icon" /><br />
							系统消息管理
					</span></a></li>

				<li><a class="shortcut-button" href="#SysMess"><span>
							<img src="./resources/images/icons/sysChat.png" alt="icon" /><br />
							发布系统消息
					</span></a></li>

			</ul>
			<!-- End .shortcut-buttons-set -->
			<div class="clear"></div>
			<!-- End .clear -->
			<div class="content-box" id="messageList">

				<div class="content-box-header">

					<h3>成果展示列表管理</h3>
				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab">
						<form class="navbar-form column-left" role="search">
							<input type="text" class="form-control" placeholder="关键字搜索"
								name="messagekeyword" />

							<input class="button" type="submit" value="搜索" />
						</form>

						<table>

							<thead>
								<tr>
									<th>投票内容</th>
									<th>编辑者</th>
									<th>发布时间</th>
									<th>投票结果</th>
									<th>操作</th>
								</tr>

							</thead>

							<tfoot>
								<tr>
									<td colspan="6">


										<div class="pagination">

											<%=pagination.printCtrl(Page, request.getRequestURI())%>

										</div> <!-- End .pagination -->
										<div class="clear"></div>
									</td>
								</tr>
							</tfoot>

							<tbody>
								<%
									for (int i = 0; i < list.size(); i++) {
																																																																												Vote vote = (Vote) list.get(i);
								%>

								<tr>
									<td><%=vote.getTitle()%></td>
									<td><%=vote.getEditor()%></td>
									<td><%=Util.getTime(vote.getDate(), Util.DATE_FORMAT_DATE)%></td>

									<td>
										<%
											ArrayList<Integer> integers = vote.getVotes();
																																																																																																				ArrayList<String> strings = vote.getChoice();
																																																																																																				int sum = vote.getSum();
										%> <label>共有<%=sum%>人投票
									</label> <%
 	for (int j = 0; j < integers.size(); j++) {
            			String percent = Util.myPercent(integers.get(j), sum);
 %>
										<div class="skillbar clearfix " data-percent="<%=percent%>">
											<div class="skillbar-title" style="background: #27ae60;">
												<span><%=strings.get(j)%></span>
											</div>
											<div class="skillbar-bar" style="background: #2ecc71;"></div>
											<div class="skill-bar-percent"><%=percent%></div>
										</div> <!-- End Skill Bar --> <%
 	}
 %>

										<div style="text-align: center; clear: both;">
											<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
											<script src="/follow.js" type="text/javascript"></script>
										</div>

									</td>



									<td>
										<!-- Icons --> <input type="image"
										src="./resources/images/icons/cross.png" name="delete"
										value=<%=vote.getId()%>
										onclick="toClickDeleteMessage(this.value)"> </input>
									</td>
								</tr>

								<%
									}
								%>

							</tbody>

						</table>

					</div>
					<!-- End table -->

				</div>
				<!-- End .content-box-content -->
				<div class="clear"></div>
			</div>
			<!-- End .content-box -->
			<div class="content-box " id="writeMess">
				<div class="content-box-header">
					<h3>发起投票</h3>
				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab">

						<form
							action="/VolunteerTimeWeb/VolunteerTimeVotesServlet?action_type=2"
							name="form1" id="form1" class="form1" method="post">

							<fieldset>
								<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->

								<p>
									<label>投票标题</label> <input class="text-input small-input"
										type="text" id="small-input" name="inputpublishMessageTitle"
										required="required" />
									<!-- Classes for input-notification: success, error, information, attention -->
								</p>

								<p>
									<label>投票类型</label> <label><input type="radio"
										name="publishVoteType" value="1" checked="checked" />单选</label><input
										type="radio" name="publishVoteType" value="2" />多选</label>
								</p>
								<p>
									<label>投票结束时间</label> <input class="text-input small-input"
										type="datetime-local" id="small-input"
										name="inputpublisActivityETime" required="required" />
									<!-- Classes for input-notification: success, error, information, attention -->
									<br />
								</p>
								<p>
									<label>投票选项</label> <input class="text-input small-input"
										type="text" id="small-input" name="inputpublishMessageChoice"
										required="required" /> <input id="buttontest" class="button"
										type=button onclick="add()" value="增加选项">
									<script type="text/javascript">
										function add() {
											document.all.buttontest
													.insertAdjacentHTML(
															'beforeBegin',
															"<br><input class=\"text-input small-input\" type='text' name='inputpublishMessageChoice' required='required'/>");
										}
									</script>
								</p>

								<p>
									<input class="button" type="submit" value="提交" />
								</p>

							</fieldset>

							<div class="clear"></div>
							<!-- End .clear -->

						</form>
					</div>
					<!-- End table -->

				</div>
				<!-- End .content-box-content -->

			</div>
			<!-- End .content-box -->
			<div class="clear"></div>


			<div class="clear"></div>
			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->
					&#169; Copyright 2014 <a href="#"> duziaqin chao_king banana </a>|
					Power by Fatwo| <a href="#">Top</a>
				</small>
			</div>
		</div>
		<!-- End #footer -->

	</div>
	<!-- End #main-content -->

	</div>
</body>


<!-- Download From www.exet.tk-->
</html>