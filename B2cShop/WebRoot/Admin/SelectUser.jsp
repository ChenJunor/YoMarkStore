<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		UserService user = new UserService();
		pages = user.selectUserInfoByPage(new HashMap<String, String>(), 1);
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
            <h1 class="page-title">查看用户</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>用户名</th>
                            <th>类型</th>
                            <th>用户状态</th>
                            <th>查看</th>
                            <th>修改</th>
                            <th>删除</th>
                            <!-- <th style="width: 26px;"></th> -->
                        </tr>
                    </thead>
                    <tbody>
                      <%
                    	for(Map<String, String> map : list) {
                    		String type = ("1".equals(map.get("USER_TYPE"))) ? "卖家" : "买家";
                    		String state = ("1".equals(map.get("USER_STATE"))) ? "正常" : "未激活";
                   	  %>
                        <tr>
                            <td><%=map.get("USER_ID")%></td>
                            <td><%=map.get("USER_NAME")%></td>
                            <td><%=type %></td>
                            <td><%=state %></td>
                            <td>
                                <a href="/B2cShop/servlet/UserServlet?action=usercontent&user_id=<%=map.get("USER_ID") %>"><i class="icon-search"></i></a>
                            </td>
                            <td>
                                <a href="/B2cShop/servlet/UserServlet?action=updateuser&user_id=<%=map.get("USER_ID") %>"><i class="icon-pencil"></i></a>
                            </td>
                            <td>
                                <a href="/B2cShop/servlet/UserServlet?action=deleteuser&user_id=<%=map.get("USER_ID") %>" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
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
           	 	<li><a href="/B2cShop/servlet/UserServlet?action=selectuserbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i <= pages.getPageCount(); i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/UserServlet?action=selectuserbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/UserServlet?action=selectuserbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>
