<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	Map<String, String> map = (Map)request.getAttribute("kindinfo");
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">修改商品分类</h1>
            <div class="btn-toolbar">
            </div>
            <div class="well">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#home" data-toggle="tab">商品分类信息</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                         <form id="tab"  action="/B2cShop/servlet/KindServlet?action=updatekindinfo&kind_id=<%=map.get("KIND_ID") %>" method="post">
                  
                            <label>分类名称</label>
                            <input type="text" value="<%=map.get("KIND_NAME") %>" class="input-xlarge" name="kind">
                            
                            <label>父分类名称</label>
                            <input type="text" value="<%=map.get("FATH_NAME") %>" class="input-xlarge" name="fath">
                            
                            <label>备注信息</label>
                            <input type="text" value="<%=map.get("SHOP_REMARK") %>" class="input-xlarge" name="remark">
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


