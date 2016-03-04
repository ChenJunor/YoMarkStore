<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("shopinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改店铺信息</h1>
            <div class="btn-toolbar">
            </div>	
            <div class="well">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">店铺信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab"  action="/B2cShop/servlet/ShopServlet?action=updateshopinfo&shop_id=<%=map.get("SHOP_ID") %>" method="post">
                  
                            <label>店铺名</label>
                            <input type="text" value="<%=map.get("SHOP_NAME") %>" class="input-xlarge" name="name">
                            
                            <label>拥有者</label>
                            <input type="text" value="<%=map.get("USER_NAME") %>" class="input-xlarge" name="user">
                            
                            <label>店铺等级</label>
                            <input type="text" value="<%=map.get("SHOP_LEVEL") %>" class="input-xlarge" name="level">
                            
                            <div>
                        		<input type="submit" value="修改" class="btn btn-primary"/>
                        	</div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include  page="Public/ManageFooter.jsp"/>


