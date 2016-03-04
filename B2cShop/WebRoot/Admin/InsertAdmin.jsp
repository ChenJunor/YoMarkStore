<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">新增管理员</h1>
            <div class="btn-toolbar">
            </div>  
            <div class="well">  
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">管理员信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" action="/B2cShop/servlet/AdminServlet?action=insertadmin" method="post">
                            <label>用户名</label>
                            <input type="text" class="input-xlarge" name="username">
                            
                            <label>密码</label>
                            <input type="passsword" class="input-xlarge" name="password">
                            
                            <label>邮箱</label>
                            <input type="text" class="input-xlarge" name="email">
                            
                            <label>状态</label>
                            <select name="status" id="admin_type" class="input-xlarge">
                                <option value=1>正常</option>
                                <option value=0>未激活</option>
                            </select>
                            
                            <div>
                            	<input type="submit" value="添加" class="btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby   = "myModalLabel" aria-hidden="true">
                <div class="modal-header">  
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> 
                    <h3 id="myModalLabel">确认</h3>
                </div>  
                <div class="modal-body">
                    <p class="error-text"><i class="icon-warning-sign modal-icon"></i>确认修改信息么？</p>
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