<%@ page language="java" import="java.util.*, com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		OrderService user = new OrderService();
		pages = user.selectOrderInfoByPage(new HashMap<String, String>(), 1);
		list = pages.selectListPage();
	} else {
		list = (List)request.getAttribute("result");
		pages = (Paging)request.getAttribute("pages");
	}
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">查看订单</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>操作人</th>
                            <th>订单时间</th>
                            <th>订单状态</th>
                            <th>修改</th>
                            <th>删除</th>
                            <!-- <th style="width: 26px;"></th> -->
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    	for(Map<String, String> map : list) {
                    		String user = "";
                    		String goods = "";
                    		Map<String, String> smap = new HashMap<String, String>();
                    		Map<String, String> umap = new HashMap<String, String>();
                    		String state = "";
                    		if (map.get("USER_ID") != null) {
                    			user = map.get("USER_ID").toString();
                    		}
                    		if (map.get("GOODS_ID") != null) {
                    			goods = map.get("GOODS_ID").toString();
                    		}

                    		UserService utemp = new UserService();
                    		GoodsService stemp = new GoodsService();
                    		umap = utemp.selectUserInfoById(user);
                    		smap = stemp.selectGoodsInfoById(goods);

                    		user = (umap.size() != 0) ? umap.get("USER_NAME") : "";
                    		goods = (smap.size() != 0) ? smap.get("GOODS_NAME") : "";

                    		if ("1".equals(map.get("ORDER_STATE"))) {
                    			state = "未支付";
                    		} else if ("2".equals(map.get("ORDER_STATE"))) {
                    			state = "已完成";
                    		} else if ("3".equals(map.get("ORDER_STATE"))) {
                    			state = "已发货";
                    		} else if ("4".equals(map.get("ORDER_STATE"))) {
                    			state = "未发货";
                    		} else if ("0".equals(map.get("ORDER_STATE"))) {
                    			state = "已支付";
                    		}
                   	  %>
                        <tr>
                            <td><%=map.get("ORDER_ID") %></td>
                            <td><%=user %></td>
                            <td><%=map.get("ORDER_DATE") %></td>
                            <td><%=state %></td>
                            <td>
                                <a href="/B2cShop/servlet/OrderServlet?action=updateorder&order_id=<%=map.get("ORDER_ID") %>"><i class="icon-pencil"></i></a>
                            </td>
                            <td>
                                <a href="/B2cShop/servlet/OrderServlet?action=deleteorder&order_id=<%=map.get("ORDER_ID") %>" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                            </td>
                        </tr>
 						<%
                    	}
 						%>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <ul> 
           	 	<li><a href="/B2cShop/servlet/OrderServlet?action=selectorderbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i <= pages.getPageCount(); i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/OrderServlet?action=selectorderbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/OrderServlet?action=selectorderbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>