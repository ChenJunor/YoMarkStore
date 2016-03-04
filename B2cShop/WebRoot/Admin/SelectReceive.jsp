<%@ page language="java" import="java.util.*, com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		ReceiveService user = new ReceiveService();
		pages = user.selectReceiveInfoByPage(new HashMap<String, String>(), 1);
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
            <h1 class="page-title">查看收货地址</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>用户名</th>
                            <th>收货人姓名</th>
                            <th>收货人联系方式</th>
                            <th>收货人地址</th>
                            <th>修改</th>
                            <th>删除</th>
                        </tr>
                    </thead>
                    <tbody>
                       <%
                    	for(Map<String, String> map : list) {
                    		String user = "";
                    		Map<String, String> umap = new HashMap<String, String>();
                    		if (map.get("USER_ID") != null) {
                    			user = map.get("USER_ID").toString();
                    		}

                    		UserService utemp = new UserService();
                    		umap = utemp.selectUserInfoById(user);

                    		user = (umap.size() != 0) ? umap.get("USER_NAME") : "";
                   	  %>
                        <tr>
                            <td><%=map.get("RECE_ID") %></td>
                            <td><%=user %></td>
                            <td><%=map.get("RECE_NAME") %></td>
                            <td><%=map.get("RECE_PHONE") %></td>
                            <td><%=map.get("RECE_ADDRESS") %></td>
                            <td>
                            <a href="/B2cShop/servlet/ReceiveServlet?action=updatereceive&rece_id=<%=map.get("RECE_ID") %>"><i class="icon-pencil"></i></a>
                        </td>
                        <td>
                            <a href="/B2cShop/servlet/ReceiveServlet?action=deletereceive&rece_id=<%=map.get("RECE_ID") %>" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
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
           	 	<li><a href="/B2cShop/servlet/ReceiveServlet?action=selectreceivebypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i <= pages.getPageCount(); i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/ReceiveServlet?action=selectreceivebypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/ReceiveServlet?action=selectreceivebypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>