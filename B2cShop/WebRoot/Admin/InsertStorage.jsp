<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">库存变化记录</h1>
            <div class="btn-toolbar">
            </div>  
            <div class="well">  
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">库存变化</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" action="/B2cShop/servlet/StorageServlet?action=insertstorage" method="post">
                            <label>商品名</label>
                            <input type="text" name="goods" class="input-xlarge">

                            <label>商品价格</label>
                            <input type="text" name="price" class="input-xlarge">
                            
                            <label>操作类型</label>
                            <select name="stor_type" id="stor_type" class="input-xlarge">
                                <option value="0">卖出</option>
                                <option value="1">买入</option>
                            </select>
                            <label>操作数量</label>
                            <input type="text" name="num" class="input-xlarge">
                            
                            <div>
                            	<input type="submit" class="btn btn-primary">
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