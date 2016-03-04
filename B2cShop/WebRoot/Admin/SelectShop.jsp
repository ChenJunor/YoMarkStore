<%@ page language="java" import="java.util.*, com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		ShopService user = new ShopService();
		pages = user.selectShopInfoByPage(new HashMap<String, String>(), 1);
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
            <h1 class="page-title">查看店铺信息</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>店铺名</th>
                            <th>所属用户</th>
                            <th>开张时间</th>
                            <th>店铺等级</th>
                            <th>店铺状态</th>
                            <th>修改</th>
                            <th>删除</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    	for(Map<String, String> map : list) {
                    		String state = "";
                    		Map<String, String> umap = new HashMap<String, String>();
                    		
                    		String userid = "";
                    		if (map.get("USER_ID") != null) {
                    			userid = map.get("USER_ID").toString();
                    		}
                    		if (map.get("SHOP_STATE") != null) {
                    			state = map.get("SHOP_STATE").toString();
                    		}
                    		
                    		UserService user = new UserService();
                    		
                    		umap = user.selectUserInfoById(userid);
                    		
                    		String username = (umap.size() != 0) ? umap.get("USER_NAME") : "";
                    		
                    		if ("1".equals(state)) {
                    			state = "审核通过";
                    		} else if ("2".equals(state)) {
                    			state = "未审核";
                    		} else if ("3".equals(state)){
                    			state = "审核未通过";
                    		}
                    		
                   	  %>
                        <tr>
                            <td><%=map.get("SHOP_ID") %></td>
                            <td><%=map.get("SHOP_NAME") %></td>
                            <td><%=username %></td>
                            <td><%=map.get("SHOP_DATE") %></td>
                            <td><%=map.get("SHOP_LEVEL") %></td>
                            <td><%=state %></td>
                            <td>
                            <a href="/B2cShop/servlet/ShopServlet?action=updateshop&shop_id=<%=map.get("SHOP_ID") %>"><i class="icon-pencil"></i></a>
                        </td>
                        <td>
                            <a href="/B2cShop/servlet/ShopServlet?action=deleteshop&shop_id=<%=map.get("SHOP_ID") %>" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
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
            <ul> 
           	 	<li><a href="/B2cShop/servlet/ShopServlet?action=selectshopbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i < pages.getPageCount()+1; i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/ShopServlet?action=selectshopbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/ShopServlet?action=selectshopbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>