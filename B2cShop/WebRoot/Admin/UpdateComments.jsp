<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("commentsinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改评论</h1>
            <div class="btn-toolbar">
            </div>
            <div class="well">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">评论信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                         <form id="tab" method="post" action="/B2cShop/servlet/CommentsServlet?action=updatecommentsinfo&comm_id=<%=map.get("COMM_ID") %>">
                            <label>评论状态</label>
                            <select name="comments_state" id="comments_state" class="input-xlarge" name="state">
                                <option value="1">审核通过</option>
                                <option value="2">审核未通过</option>
                                <option value="3">未审核</option>
                            </select>
                            
                            <div>
                            	<input type="submit" value="修改" class="btn btn-primary" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include  page="Public/ManageFooter.jsp"/>


