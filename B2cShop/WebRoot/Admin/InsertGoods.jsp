<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">新增商品</h1>
            <div class="btn-toolbar">
            </div>
            <div class="well">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">商品信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" action="/B2cShop/servlet/GoodsServlet?action=insertgoods" method="post">
                  
                            <label>商品名称</label>
                            <input type="text" name="name" class="input-xlarge">
                            
                            <label>商品分类</label>
                            <input type="text" name="kind" class="input-xlarge">
                            
                            <label>所属店铺</label>
                            <input type="text" name="shop" class="input-xlarge">

                            <label>商品产地</label>
                            <input type="text" name="orign" class="input-xlarge">
                            
                            <label>商品零售价格</label>
                            <input type="text" name="price" class="input-xlarge">
                    
                            <label>商品VIP价格</label>
                            <input type="text" name="privevip" class="input-xlarge">
                            
                            <label>商品图片</label>
                            <input type="file" name="photo" class="input-xlarge">
                            
                            <label>库存量</label>
                            <input type="text" name="storage" class="input-xlarge">
                            
                            <label>描述信息</label>
                            <textarea name="content" rows="3" class="input-xlarge">
                            </textarea>
                            
                            <label>状态</label>
                            <select name="status" id="user_type" class="input-xlarge">
                                <option value=1>出售</option>
                                <option value=0>不出售</option>
                            </select>
                            
                            <div>
                            	<input type="submit" value="新增" class="btn btn-primary"/>
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


