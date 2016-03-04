<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("orderinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改评论</h1>
            <div class="btn-toolbar">
                <a href="#myModal" data-toggle="modal" class="btn btn-primary"><i class="icon-save"></i>　保存</a>
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">订单信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" method="post" action="/B2cShop/servlet/OrderServlet?action=updateorderinfo&order_id=<%=map.get("ORDER_ID") %>">
                            
                            <label>订单状态</label>
                            <select name="order_state" id="order_state" class="input-xlarge" name="state">
                                <option value="0">已支付</option>
                                <option value="1">未支付</option>
                                <option value="2">已完成</option>
                                <option value="3">已发货</option>
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


