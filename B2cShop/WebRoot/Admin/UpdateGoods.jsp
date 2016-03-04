<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("goodsinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改商品</h1>
            <div class="btn-toolbar">
            </div>
            <div class="well">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">商品信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                         <form id="tab" action="/B2cShop/servlet/GoodsServlet?action=updategoodsinfo&admin_id=<%=map.get("GOODS_ID") %>" method="post">
                  
                            <label>商品名称</label>
                            <input type="text" value="<%=map.get("GOODS_NAME") %>" class="input-xlarge" name="name">
                            
                            <label>商品分类</label>
                            <input type="text" value="<%=map.get("KIND_NAME") %>" class="input-xlarge" name="kind">
                            
                            <label>所属店铺</label>
                            <input type="text" value="<%=map.get("SHOP_NAME") %>" class="input-xlarge" name="shop">

                            <label>商品产地</label>
                            <input type="text" value="<%=map.get("GOODS_ORIGN") %>" class="input-xlarge" name="orign">
                            
                            <label>商品零售价格</label>
                            <input type="text" value="<%=map.get("ORD_PRICE") %>" class="input-xlarge" name="price">
                    
                            <label>商品VIP价格</label>
                            <input type="text" value="<%=map.get("VIP_PRICE") %>" class="input-xlarge" name="pricevip">
                          
                            
                            <label>库存量</label>
                            <input type="text" value="<%=map.get("GOODS_INVENTORY") %>" class="input-xlarge" name="storage">
                            
                            <label>描述信息</label>
                            <textarea  rows="3" class="input-xlarge" name="content">
<%=map.get("GOODS_CONTENT") %>
                            </textarea>
                            
                            <div>
                            	<input type="submit" value="修改"/>
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


