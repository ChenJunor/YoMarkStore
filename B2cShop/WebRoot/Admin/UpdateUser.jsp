<%@ page language="java" import="java.util.*,com.dzsw.service.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("userinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改用户</h1>
            <div class="btn-toolbar">
                
            </div>  
            <div class="well">  
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">用户信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" method="post" action="/B2cShop/servlet/UserServlet?action=updateuserinfo&user_id=<%=map.get("USER_ID") %>">
                            <label>用户名</label>
                            <input type="text" value="<%=map.get("USER_NAME") %>" class="input-xlarge" name="name">
                            
                            <label>邮箱</label>
                            <input type="text" value="<%=map.get("USER_EMAIL") %>" class="input-xlarge" name="email">
                            
                            <%
                            	String str = ("woman".equals(map.get("USER_SEX"))? "selected" : "");
                            %>
                            
                            <label>性别</label>
                            <select name="user_sex" id="user_sex" class="input-xlarge" name="sex">
                                <option value="man">男</option>
                                <option value="woman" <%=str %>>女</option>
                            </select>
                    
                            <label>生日</label>
                            <input type="text" value="<%=(map.get("USER_BIRTH")==null) ? "":map.get("USER_BIRTH") %>" class="input-xlarge" name="birth">

                            <label>联系方式</label>
                            <input type="text" value="<%=map.get("USER_PHONE") %>" class="input-xlarge" name="phone">

                            <label>地址</label>
                            <textarea value="address%>" rows="3" class="input-xlarge" name="address">
                            <%=map.get("USER_ADDR") %>
                            </textarea>
                            
                            <div>
                            	<input type="submit" class="btn btn-primary" values="修改">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>