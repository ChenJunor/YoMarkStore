<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("receiveinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改收货地址</h1>
            <div class="btn-toolbar">
            </div>
            <div class="well">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">收货地址信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" method="post" action="/B2cShop/servlet/ReceiveServlet?action=updatereceiveinfo&rece_id=<%=map.get("RECE_ID") %>">
                  
                            <label>用户名</label>
                            <input type="text" value="<%=map.get("USER_NAME") %>" class="input-xlarge" name="name">
                            
                            <label>收货人姓名</label>
                            <input type="text" value="<%=map.get("RECE_NAME") %>" class="input-xlarge" name="user">
                            
                            <label>收货人联系方式</label>
                            <input type="text" value="<%=map.get("RECE_PHONE") %>" class="input-xlarge" name="phone">

                            <label>收货人地址</label>
                            <input type="text" value="<%=map.get("RECE_ADDRESS") %>" class="input-xlarge" name="address">
                            
                            
                            <div>
                            	<input type="submit" value="修改" class="btn btn-primary" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" alabelledby="   myModalLabel" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"button>
                    <h3 id="myModalLabel">确认</h3>
                </div>
                <div class="modal-body">
                    <p class="error-text"><i class="icon-warning-sign modal-icon"></i>确认修改</p>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                    <button class="btn btn-danger" data-dismiss="modal">确认</button>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include  page="Public/ManageFooter.jsp"/>


