<%@ page language="java" import="java.util.*,com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		CommentsService user = new CommentsService();
		pages = user.selectCommentsInfoByPage(new HashMap<String, String>(), 1);
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
            <h1 class="page-title">查看评论信息</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>商品</th>
                            <th>所属者</th>
                            <th>评论时间</th>
                            <th>评论状态</th>
                            <th>查看</th>
                            <th>修改</th>
                            <th>删除</th>
                        </tr>
                    </thead>
                    <tbody>
                      <%
                    	for(Map<String, String> map : list) {
                    		String goods = "";
                    		String state = "";
                    		Map<String, String> gmap = new HashMap<String, String>();
                    		Map<String, String> umap = new HashMap<String, String>();
                    		String user = "";
                    		if (map.get("GOODS_ID") != null) {
                    			goods = map.get("GOODS_ID").toString();
                    		}
                    		if (map.get("BUY_USER") != null) {
                    			user = map.get("BUY_USER").toString();
                    		}
                    		if ("1".equals(map.get("COMM_STATE"))) {
                    			state = "审核通过";
                    		} else if ("2".equals(map.get("COMM_STATE"))) {
                    			state = "审核未通过";
                    		} else if ("3".equals(map.get("COMM_STATE"))) {
                    			state = "未审核";
                    		}
                    		
                    		GoodsService ktemp = new GoodsService();
                    		UserService stemp = new UserService();
                    		gmap = ktemp.selectGoodsInfoById(goods);
                    		umap = stemp.selectUserInfoById(user);
                    		goods = (gmap.size() != 0) ? gmap.get("GOODS_NAME") : "";
                    		user = (umap.size() != 0) ? umap.get("USER_NAME") : "";		
                   	  %>
                        <tr>
                            <td><%=map.get("COMM_ID") %></td>
                            <td><%=goods %></td>
                            <td><%=user %></td>
                            <td><%=map.get("COMM_DATE") %></td>
                            <td><%=state %></td>
                            <td>
                                <a href="/B2cShop/servlet/CommentsServlet?action=commentscontent&comm_id=<%=map.get("COMM_ID") %>"><i class="icon-search"></i></a>
                            </td>
                            <td>
                            	<a href="/B2cShop/servlet/CommentsServlet?action=updatecomments&comm_id=<%=map.get("COMM_ID") %>"><i class="icon-pencil"></i></a>
                        	</td>
                        	<td>
                            	<a href="/B2cShop/servlet/CommentsServlet?action=deletecomments&comm_id=<%=map.get("COMM_ID") %>" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
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
           	 	<li><a href="/B2cShop/servlet/CommentsServlet?action=selectcommentsbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i <= pages.getPageCount(); i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/CommentsServlet?action=selectcommentsbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/CommentsServlet?action=selectcommentsbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>