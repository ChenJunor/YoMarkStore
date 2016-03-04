<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("admininfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改管理员信息</h1>
            <div class="btn-toolbar">
            </div>
            <div class="well">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">管理员信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" action="/B2cShop/servlet/AdminServlet?action=updateadmininfo&admin_id=<%=map.get("ADMIN_ID") %>" method="post">
                            <label>用户名</label>
                            <input type="text" value="<%=map.get("ADMIN_NAME") %>" class="input-xlarge" name="name">
                            
                            <label>密码</label>
                            <input type="passsword" value="<%=map.get("PASSWORD") %>" class="input-xlarge" name="password">
                            
                            <label>邮箱</label>
                            <input type="text" value="<%=map.get("ADMIN_EMAIL") %>" class="input-xlarge" name="email">
                        	<div>
                        		<input type="submit" value="修改" class="btn btn-primary"/>
                        	</div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include  page="Public/ManageFooter.jsp"/>


