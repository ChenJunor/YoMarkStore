<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">新增店铺</h1>
            <div class="btn-toolbar">
            </div>  
            <div class="well">  
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">店铺信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" action="/B2cShop/servlet/ShopServlet?action=insertshop" method="post">
                            <label>店铺名</label>
                            <input type="text" class="input-xlarge" name="shop_name">

                            <label>所属卖家用户名</label>
                            <input type="text" class="input-xlarge" name="user_name">
                            
                            <label>店铺等级</label>
                            <select id="shop_level" class="input-xlarge" name="level">
                                <option value="1" selected>1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                            
                            <label>状态</label>
                            <select name="status" class="input-xlarge">
                            	<option value=0 selected>未审核</option>
                            	<option value=1>审核通过</option>
                            	<option value=2>审核未通过</option>
                            </select>
                            <div>
                            	<input type="submit" class="btn btn-primary" />
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
<jsp:include page="Public/ManageFooter.jsp"/>