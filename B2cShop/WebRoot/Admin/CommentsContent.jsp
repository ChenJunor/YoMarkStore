<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("commentsinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">查看评论信息</h1>
            <div class="well">
                <div id="myTabContent" class="tab-content">
                    <div class="selcon" id="home">
                        <ul>
                            <li>商品名：　<span><%=map.get("GOODS_NAME") %></span></li>
                            <li>评论人：　<span><%=map.get("USER_NAME") %></span></li>
                            <li>时　间：　<span><%=map.get("COMM_DATE") %></span></li>
                        </ul>
                        <hr />
                        <ul>
                            <li>评论内容：　<span><%=map.get("COMM_REVIEW") %></span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>