<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	List<Map<String, String>> list = (session.getAttribute("admin_info") == null) ? new ArrayList<Map<String, String>>(2) : (List<Map<String, String>>)session.getAttribute("admin_info");
	Map<String, String> map = (list.size() != 0) ? list.get(0) : new HashMap<String, String>();
	String username = (map.size() != 0) ? map.get("ADMIN_NAME") : "";
	String str = (map.size() == 0) ? "" : "";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>YoMark 后台管理系统</title>
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/Admin/lib/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/Admin/lib/bootstrap/css/bootstrap-responsive.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Admin/stylesheets/theme.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/Admin/lib/font-awesome/css/font-awesome.css">

		<script src="<%=request.getContextPath()%>/Admin/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

		<!-- Demo page code -->

		<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>

		<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
      <script src="javascripts/html5.js"></script>
      <![endif]-->

		<!-- Le fav and touch icons -->
		<link rel="shortcut icon" href="../assets/ico/favicon.ico">
		<link rel="apple-touch-icon-precomposed" sizes="144x144"
			href="../assets/ico/apple-touch-icon-144-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114"
			href="../assets/ico/apple-touch-icon-114-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="72x72"
			href="../assets/ico/apple-touch-icon-72-precomposed.png">
		<link rel="apple-touch-icon-precomposed"
			href="../assets/ico/apple-touch-icon-57-precomposed.png">
	</head>

	<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
	<!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
	<!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
	<!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->
	<body>
		<!--<![endif]-->
  	<div class="navbar">
  		<div class="navbar-inner">
  			<div class="container-fluid">
  				<ul class="nav pull-right">
  					
  					<li id="fat-menu" class="dropdown">
  						<a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
  							<i class="icon-user"></i> <%=username %>
  							<i class="icon-caret-down"></i>
  						</a>

  						<%
  							if (map.size() != 0) {
  						%>
  							<ul class="dropdown-menu">
  								<li><a tabindex="-1" href="/B2cShop/servlet/AdminServlet?action=updateadmin&admin_id=<%=map.get("ADMIN_ID") %>">个人设置</a></li>
  								<li><a tabindex="-1" href="/B2cShop/servlet/AdminServlet?action=logout">注销</a></li>
  							</ul>
  						<%
  							}
  						%>
  						
  					</li>
  					
  				</ul>
  				<a class="brand" href="index.jsp"><span class="first">Yo</span> <span class="second">Mark</span></a>
  			</div>
  		</div>
  	</div>