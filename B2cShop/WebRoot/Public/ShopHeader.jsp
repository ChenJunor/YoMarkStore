<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	List<Map<String, String>> list = (session.getAttribute("user_info") == null) ? new ArrayList<Map<String, String>>(2) : (List<Map<String, String>>)session.getAttribute("user_info");
	Map<String, String> map = (list.size() != 0) ? list.get(0) : new HashMap<String, String>();
	String username = (map.size() != 0) ? map.get("USER_NAME") : "";
	String str = (map.size() == 0) ? "" : "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>优码客数码商城</title>

  <!-- Bootstrap -->
  <link href="<%=request.getContextPath()%>/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/lib/font-awesome/css/font-awesome.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
  
  <link href="<%=request.getContextPath()%>/css/owl.carousel.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/owl.theme.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/owl.transitions.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/config.css" rel="stylesheet">

</head>
<body>

<!-- Header -->
<header id="header">

  <!-- ========== TOP BAR START ========== -->

  <div id="top-bar">
    <div class="container">

      <nav id="shopping-cart">
        <a href="#">
          <i class="fa fa-shopping-cart fa-lg"></i> 购物车
        </a>
      </nav>

      <nav id="top-nav" role="navigation" class="navbar">
        <div class="navbar-header">
          <button data-target=".top-nav" data-toggle="collapse" class="navbar-toggle" type="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="collapse navbar-collapse top-nav">
          <ul class="nav navbar-nav">
            <li><a href="#">关于我们</a></li>
            <%
            	if (map.size() != 0) {
            %>
            <li><a href="/B2cShop/servlet/UserServlet?action=logout">注销</a></li>
            <li><a href="SelfManagement.jsp">个人中心</a></li>
            
            <%
					if ("0".equals(map.get("USER_TYPE"))) {
			%>
						<li><a href="ShopApply.jsp">申请开店</a>
			<%
					}
            	} else {
            %>
            <li><a href="login.jsp">登录 / 注册</a></li>
            <%
            }
            %>
          </ul>
        </div><!-- /.navbar-collapse -->
      </nav>

    </div>
  </div><!-- / #top-bar -->

  <!-- ========== MENU START ========== -->

  <nav id="main-nav">
    <div class="navbar">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".main-nav">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/img/logo.png" alt="Garbini" class="img-responsive"></a>
        </div>
        <div class="navbar-collapse collapse main-nav">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="index-1.html" class="dropdown-toggle" data-toggle="dropdown">PC/平板 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="ShopKindDisp.jsp">电脑　/　PC</a></li>
                <li><a href="ShopKindDisp.jsp">平板　/　上网本</a></li>
                <li><a href="ShopKindDisp.jsp">PC平板二合一</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="category.html" class="dropdown-toggle" data-toggle="dropdown">硬件/配件 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="ShopKindDisp.jsp">内存　/　硬盘</a></li>
                <li><a href="ShopKindDisp.jsp">CPU　/　显卡</a></li>
                <li><a href="ShopKindDisp.jsp">U盘　/　移动硬盘</a></li>
                <li><a href="ShopKindDisp.jsp"></a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="category.html" class="dropdown-toggle" data-toggle="dropdown">移动/通信 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="ShopKindDisp.jsp">手机　/　配件</a></li>
                <li><a href="ShopKindDisp.jsp">交换机　/　路由器</a></li>
                <li><a href="ShopKindDisp.jsp">数码　/　配件</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="category.html" class="dropdown-toggle" data-toggle="dropdown">智能家居 <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="ShopKindDisp.jsp">智能开关</a></li>
                <li><a href="ShopKindDisp.jsp">智能摄像头</a></li>
                <li><a href="ShopKindDisp.jsp">智能开关</a></li>
                <li><a href="ShopKindDisp.jsp">其他智能设备</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>

</header>

<!-- ========== MENU END ========== -->
