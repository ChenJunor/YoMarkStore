<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("goodsinfo");
	Map<String, String> cmap = (Map)request.getAttribute("commentsinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">查看商品信息</h1>
            <div class="well">
                <div id="myTabContent" class="tab-content">
                    <div class="selcon" id="home">
  						<ul>
							<li>商品名：　<span><%=map.get("GOODS_NAME") %></span></li>
							<li>分　类：　<span><%=map.get("KIND_NAME") %></span></li>
							<li>产　地：　<span><%=map.get("GOODS_ORIGN") %></span></li>
							<li>商品描述：　<span><%=map.get("GOODS_CONTENT") %></span></li>
						</ul>
						<hr />
						<ul>
							<li>售　价：　<span><%=map.get("ORD_PRICE") %></span></li>
							<li>会员价：　<span><%=map.get("VIP_PRICE") %></span></li>
							<li>状　态：　<span><%=map.get("GOODS_ONSALE") %></span></li>
							<li>库存量：　<span><%=map.get("GOODS_INVENTORY") %></span></li>
							<li>上架时间：　<span><%=map.get("START_DATE") %></span></li>
						</ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>