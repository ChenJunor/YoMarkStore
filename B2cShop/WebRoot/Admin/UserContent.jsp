<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("userinfo");
%>
<jsp:include  page="/Admin/Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">查看用户信息</h1>
            <div class="well">
                <div id="myTabContent" class="tab-content">
                    <div class="selcon" id="home">
                        <ul>
                            <li>用户名：　<span><%=map.get("USER_NAME") %></span></li>
                            <li>性　别：　<span><%=("woman".equals(map.get("USER_SEX"))) ? "女" : "男" %></span></li>
                            <li>生　日：　<span><%=map.get("USER_BIRTH") %></span></li>
                            <li>电　话：　<span><%=map.get("USER_PHONE") %></span></li>
                            <li>邮　箱：　<span><%=map.get("USER_EMAIL") %></span></li>
                            <li>地　址：　<span><%=map.get("USER_ADDR") %></span></li>
                        </ul>
                        <hr />
                        <ul>
                            <li>类　型：　<span><%=("1".equals(map.get("USER_TYPE"))) ? "卖家" : "买家" %></span></li>
                            <li>状　态：　<span><%=("1".equals(map.get("USER_STATE"))) ? "正常" : "未激活" %></span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="/Admin/Public/ManageFooter.jsp"/>