<%@ page language="java" import="java.util.*,com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		AdminService user = new AdminService();
		pages = user.selectAdminInfoByPage(new HashMap<String, String>(), 1);
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
            <h1 class="page-title">查看管理员</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>用户名</th>
                            <th>邮箱</th>
                            <th>用户状态</th>
                            <th>修改</th>
                            <th>删除</th>
                            <!-- <th style="width: 26px;"></th> -->
                        </tr>
                    </thead>
                    <tbody>
					<%
                    	for(Map<String, String> map : list) {
                    		String state = ("1".equals(map.get("ADMIN_STATE"))) ? "正常" : "未激活";
                   	  %>
                        <tr>
                            <td><%=map.get("ADMIN_ID") %></td>
                            <td><%=map.get("ADMIN_NAME") %></td>
                            <td><%=map.get("ADMIN_EMAIL") %></td>
                            <td><%=state%></td>
                            <td>
                                <a href="/B2cShop/servlet/AdminServlet?action=updateadmin&admin_id=<%=map.get("ADMIN_ID") %>"><i class="icon-pencil"></i></a>
                            </td>
                            <td>
                                <a href="/B2cShop/servlet/AdminServlet?action=deleteadmin&admin_id=<%=map.get("ADMIN_ID") %>" role="button" data-toggle="modal" onclice="return confirm('确认删除？');"><i class="icon-remove"></i></a>
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
           	 	<li><a href="/B2cShop/servlet/AdminServlet?action=selectadminbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i < pages.getPageCount()+1; i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/AdminServlet?action=selectadminbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/AdminServlet?action=selectadminbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>
