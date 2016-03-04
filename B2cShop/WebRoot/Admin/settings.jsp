<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改个人信息</h1>
            <div class="btn-toolbar">
                <a href="#myModal" data-toggle="modal" class="btn btn-primary"><i class="icon-save"></i>  　保存</a>
                <div clas="btn-group">  </div>  
            </div>  
            <div class="well">  
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">管理员信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab">
                            <label>用户名</label>
                            <input type="text" value="jsmith" class="input-xlarge">
                            
                            <label>邮箱</label>
                            <input type="text" value="jsmith@yourcompany.com" class="input-xlarge">
                            
                            <label>性别</label>
                            <select name="user_sex" id="user_sex" class="input-xlarge">
                                <option value="man">男</option>
                                <option value="woman">女</option>
                            </select>
                    
                            <label>生日</label>
                            <input type="text" value="1995-01-08" class="input-xlarge">

                            <label>联系方式</label>
                            <input type="text" value="1995-01-08" class="input-xlarge">

                            <label>地址</label>
                            <textarea value="phone" rows="3" class="input-xlarge">
                                2817 S 49th
                                Apt 314
                                San Jose, CA 95101
                            </textarea>
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