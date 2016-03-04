<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	List<Map<String, String>> list = (List)session.getAttribute("admin_info");
	Map<String, String> map = (list.size() != 0) ? list.get(0) : new HashMap<String, String>();
%>
<div class="span3">
	<div class="sidebar-nav">
		<%
		if ("0".equals(map.get("ADMIN_TYPE"))) { 
		%>
		<div class="nav-header" data-toggle="collapse"
			data-target="#user-menu">
			<i class="icon-user"></i>用户信息管理
		</div>
		<ul id="user-menu" class="nav nav-list collapse in">
			<li>
				<a href="<%=request.getContextPath()%>/Admin/InsertUser.jsp">新增用户</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectUser.jsp">查看用户</a>
			</li>
		</ul>
		<% 
		}
		if ("1".equals(map.get("ADMIN_TYPE"))) { 
		%>
		<div class="nav-header" data-toggle="collapse"
			data-target="#admin-menu">
			<i class="icon-magic"></i>管理员信息管理
		</div>
		<ul id="admin-menu" class="nav nav-list collapse in">
			<li>
				<a href="<%=request.getContextPath()%>/Admin/InsertAdmin.jsp">新增管理员</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectAdmin.jsp">查看管理员</a>
			</li>
		</ul>
		<%
			}
			if ("0".equals(map.get("ADMIN_TYPE"))) {
		%>
		<div class="nav-header" data-toggle="collapse"
			data-target="#shop-menu">
			<i class="icon-hdd"></i>店铺信息管理
		</div>
		<ul id="shop-menu" class="nav nav-list collapse in">
			<li>
				<a href="<%=request.getContextPath()%>/Admin/InsertShop.jsp">新增店铺</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectShop.jsp">查看店铺</a>
			</li>
		</ul>

		<div class="nav-header" data-toggle="collapse"
			data-target="#kind-menu">
			<i class="icon-tasks"></i>商品分类管理
		</div>
		<ul id="kind-menu" class="nav nav-list collapse in">
			<li>
				<a href="<%=request.getContextPath()%>/Admin/InsertKind.jsp">新增分类</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectKind.jsp">查看分类</a>
			</li>
		</ul>
		<div class="nav-header" data-toggle="collapse"
			data-target="#goods-menu">
			<i class="icon-legal"></i>商品信息管理
		</div>
		<ul id="goods-menu" class="nav nav-list collapse in">
			<li>
				<a href="<%=request.getContextPath()%>/Admin/InsertGoods.jsp">新增商品</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectGoods.jsp">查看商品</a>
			</li>
		</ul>
		<%
			}
		%>
		<div class="nav-header" data-toggle="collapse"
			data-target="#other-menu">
			<i class="icon-th"></i>其他信息管理
		</div>
		<ul id="other-menu" class="nav nav-list collapse in">
			<%
				if ("0".equals(map.get("ADMIN_TYPE"))) {
			%>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectOrder.jsp">查看订单</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectComments.jsp">查看评论</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectReceive.jsp">查看收货地址</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectReport.jsp">查看举报</a>
			</li>
			<%
				}
			%>
			<li>
				<a href="<%=request.getContextPath()%>/Admin/SelectLog.jsp">查看日志</a>
			</li>
		</ul>
	</div>
</div>